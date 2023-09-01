package org.alee.component.skin.core.pack

import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import org.alee.component.skin.constant.SkinPackStatus
import org.alee.component.skin.core.resources.provider.ISkinResourcesProvider
import java.util.concurrent.atomic.AtomicInteger

/**
 * 皮肤包基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
internal sealed class BaseThemeSkinPack(
    /**
     * 皮肤包名称
     */
    private val mSkinPackName: String,
    /**
     * @see IThemeSkinPack 托底皮肤包
     */
    private val mUnderpinThemeSkinPack: IThemeSkinPack?,
) : IThemeSkinPack {

    /**
     * 皮肤包当前状态
     */
    private val mCurrentStatus = AtomicInteger(SkinPackStatus.NEW)

    /**
     * @see ISkinResourcesProvider
     */
    private lateinit var mSkinResourcesProvider: ISkinResourcesProvider

    /**
     * 皮肤包就绪
     * @param provider ISkinResourcesProvider [ISkinResourcesProvider]
     * @return Boolean true:已就绪
     */
    internal fun onReady(provider: ISkinResourcesProvider): Boolean {
        mSkinResourcesProvider = provider
        mCurrentStatus.set(SkinPackStatus.RUNNABLE)
        return true
    }

    /**
     * 皮肤包名称
     */
    override val name: String
        get() = mSkinPackName

    /**
     * 判断 当前主题皮肤包是否可用
     * <p> true: 皮肤包有效且可以使用；false ：皮肤包无效且无法使用
     */
    override val isAvailable: Boolean
        get() = SkinPackStatus.RUNNABLE == state && this::mSkinResourcesProvider.isInitialized

    /**
     * 皮肤包当前状态
     */
    override val state: Int
        get() = mCurrentStatus.get()

    /**
     * 主题
     */
    override val theme: Theme?
        get() = if (isAvailable) mSkinResourcesProvider.theme else null

    /**
     * 获取状态颜色集合
     * @param resId Int 颜色资源id
     * @return ColorStateList?
     */
    override fun getColorStateList(resId: Int): ColorStateList? {
        return fetchResource { provider -> provider.getColorStateList(resId) }
    }

    /**
     * 获取状态颜色集合
     * @param resNames Array<out String> 资源名称
     * @return ColorStateList?
     */
    override fun getColorStateList(vararg resNames: String): ColorStateList? {
        return fetchResource { provider -> provider.getColorStateList(resNames = resNames) }
    }

    /**
     *  根据资源id 获取颜色资源
     * @param resId Int 资源id
     * @return Int?
     */
    override fun getColor(resId: Int): Int? {
        return fetchResource { provider -> provider.getColor(resId) }
    }

    /**
     * 根据资源名 获取颜色值
     * @param resNames Array<out String> 资源名称
     * @return Int?
     */
    override fun getColor(vararg resNames: String): Int? {
        return fetchResource { provider -> provider.getColor(resNames = resNames) }
    }

    /**
     * 根据资源id 获取可拉伸资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    override fun getDrawable(resId: Int): Drawable? {
        return fetchResource { provider -> provider.getDrawable(resId) }
    }

    /**
     * 根据名称 获取可拉伸资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    override fun getDrawable(vararg resNames: String): Drawable? {
        return fetchResource { provider -> provider.getDrawable(resNames = resNames) }
    }

    /**
     * 根据资源id 获取mipmap目录下的图片资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    override fun getMipmap(resId: Int): Drawable? {
        return fetchResource { provider -> provider.getMipmap(resId) }
    }

    /**
     * 根据名称 获取mipmap目录下的图片资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    override fun getMipmap(vararg resNames: String): Drawable? {
        return fetchResource { provider -> provider.getMipmap(resNames = resNames) }
    }

    private inline fun <T> fetchResource(block: (ISkinResourcesProvider) -> T?): T? {
        if (isAvailable) {
            block(mSkinResourcesProvider)?.run {
                return this
            }
        }
        return mUnderpinThemeSkinPack?.run(block)
    }
}
