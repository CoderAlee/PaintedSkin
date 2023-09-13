package org.alee.component.skin.core.pack

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.AssetManagerMapping
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import org.alee.component.skin.core.resources.provider.HostResourcesProvider
import org.alee.component.skin.core.resources.provider.ISkinResourcesProvider
import org.alee.component.skin.core.resources.provider.StandardSkinResourcesProvider
import org.alee.component.skin.template.EmptyThemeSkin
import org.alee.component.skin.template.IAssetThemeSkin
import org.alee.component.skin.template.ICustomThemeSkin
import org.alee.component.skin.template.IStandardThemeSkin
import org.alee.component.skin.template.IThemeSkin
import org.alee.component.skin.util.ext.logE
import org.alee.component.skin.util.ext.printIfDebug
import org.alee.component.skin.util.safeCall
import java.io.File

/**
 * [ISkinResourcesProvider] 缓存上限
 */
private const val MAX_CACHE = 1 shl 3

/**
 * 通过LRU算法缓存经常使用的[ISkinResourcesProvider] 避免重复创建消耗时间
 */
internal val POOL = LruCache<String, ISkinResourcesProvider>(MAX_CACHE)

/**
 * @see DefaultThemeSkinPack
 */
private val DEFAULT_PACK: BaseThemeSkinPack = DefaultThemeSkinPack()

internal fun CoroutineScope.loadSkinPack(context: Context, option: IThemeSkin): IThemeSkinPack {
    return createPack(context, option)
}

private fun CoroutineScope.createPack(context: Context, option: IThemeSkin): IThemeSkinPack {
    if (isActive.not()) {
        return DEFAULT_PACK
    }
    if (option is EmptyThemeSkin) {
        return DEFAULT_PACK.apply {
            if (isAvailable.not()) {
                onReady(HostResourcesProvider(context))
            }
        }
    }
    return when (option) {
        is IStandardThemeSkin -> StandardThemeSkinPack(option.standardSkinPackPath, createPack(context, option.dependencies)).apply {
            safeCall {
                makeReady(context, option.standardSkinPackPath)
            }
        }

        is IAssetThemeSkin -> AssetThemeSkinPack(option.packName, createPack(context, option.dependencies)).apply {
            onReady(option.assetsSkinPack)
        }

        is ICustomThemeSkin -> CustomThemeSkinPack(option.packName, createPack(context, option.dependencies)).apply {
            onReady(option.customSkinPack)
        }
    }
}

private fun StandardThemeSkinPack.makeReady(context: Context, path: String) {
    if (path.isEmpty()) {
        "标准皮肤包路径不能为空".logE()
        return
    }
    val provider = POOL.get(path)
    if (null != provider) {
        printIfDebug {
            "从缓存中加载皮肤包 [ $path ]"
        }
        onReady(provider)
        return
    }
    if (File(path).exists().not()) {
        "路径下不存在皮肤包 [ $path ]".logE()
        return
    }
    val packPackageName = context.getSkinPackPackageName(path)
    if (packPackageName.isNullOrEmpty()) {
        "无法获取到皮肤包包名 [ $path ]".logE()
        return
    }
    val packResources = context.getSkinPackResources(path)
    val packContext = context.getSKinPackContext(packPackageName)
    if (null == packResources) {
        "无法加载皮肤包 [ $path ]".logE()
        return
    }
    StandardSkinResourcesProvider(packContext, context.resources, packResources, packPackageName).run {
        POOL.put(path, this)
        onReady(this)
    }
    printIfDebug {
        "皮肤包[$path]加载成功! packageName = $packPackageName packResources = $packResources packContext = $packContext"
    }
}

private fun Context.getSkinPackPackageName(path: String): String? {
    var packageInfo: PackageInfo? = null
    safeCall {
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
    }
    return packageInfo?.packageName
}

private fun Context.getSkinPackResources(path: String): Resources? {
    val targetAssetManager: AssetManager = AssetManagerMapping.constructor.newInstance() ?: return null
    AssetManagerMapping.addAssetPath.call(targetAssetManager, path)
    val configuration = Configuration(resources.configuration)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (!configuration.locales.isEmpty) {
            configuration.setLocale(configuration.locales[0])
        }
    }
    return Resources(targetAssetManager, resources.displayMetrics, configuration)
}

private fun Context.getSKinPackContext(packageName: String): Context? {
    safeCall {
        return createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY)
    }
    return null
}
