package org.alee.component.skin.pack;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;

import androidx.annotation.NonNull;

import org.alee.component.skin.util.PrintUtil;

import java.io.File;
import java.util.function.Supplier;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: 主题皮肤加载任务
 *
 *********************************************************/
final class LoadThemeSkinTask implements Supplier<Boolean> {

    private static final LruCache<String, ISkinResourcesProvider> CACHE = new LruCache<>(1 << 5);

    private final BaseThemeSkinPack mThemeSkinPack;

    private final Context mContext;

    LoadThemeSkinTask(@NonNull BaseThemeSkinPack themeSkinPack, @NonNull Context context) {
        mThemeSkinPack = themeSkinPack;
        mContext = context;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public Boolean get() {
        if (IThemeSkinPack.SkinPackType.TYPE_CUSTOMIZE == mThemeSkinPack.getSkinPackType()) {
            return true;
        }
        String path = mThemeSkinPack.getName();
        ISkinResourcesProvider cache = CACHE.get(path);
        if (null != cache) {
            PrintUtil.getInstance().printD("从缓存中加载皮肤包 [ " + path + " ]");
            return mThemeSkinPack.onReady(cache);
        }
        if (IThemeSkinPack.SkinPackType.TYPE_DEFAULT == mThemeSkinPack.getSkinPackType()) {
            cache = new DefaultSkinResourcesProvider(mContext);
            CACHE.put(path, cache);
            return mThemeSkinPack.onReady(cache);
        }
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            PrintUtil.getInstance().printE("路径下不存在皮肤包 [ " + path + " ]");
            return false;
        }
        String packageName = getTargetPackageName(mContext, path);
        if (TextUtils.isEmpty(packageName)) {
            PrintUtil.getInstance().printE("无法获取到皮肤包包名 [ " + path + " ]");
            return false;
        }
        Resources resources = getTargetResources(mContext, path);
        Context context = getTargetContext(mContext, packageName);
        if (null == resources && null == context) {
            PrintUtil.getInstance().printE("无法加载皮肤包 [ " + path + " ]");
            return false;
        }
        cache = new StandardSkinResourcesProvider(context, mContext.getResources(), null == resources ? context.getResources() : resources, packageName);
        CACHE.put(path, cache);
        PrintUtil.getInstance().printD("成功加载皮肤包 [ " + path + " ]");
        return mThemeSkinPack.onReady(cache);
    }

    private String getTargetPackageName(Context context, String path) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        return null == packageInfo ? null : packageInfo.packageName;
    }

    private Resources getTargetResources(Context context, String path) {
        AssetManager targetAssetManager = null;
        try {
            targetAssetManager = AssetManager.class.newInstance();
        } catch (Throwable ignored) {
        }
        if (null == targetAssetManager) {
            return null;
        }
        if (0 == AssetManagerMapping.addAssetPath.call(targetAssetManager, path)) {
            return null;
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!configuration.getLocales().isEmpty()) {
                configuration.setLocale(configuration.getLocales().get(0));
            }
        }
        return new Resources(targetAssetManager, context.getResources().getDisplayMetrics(), configuration);
    }

    private Context getTargetContext(Context context, String targetPackageName) {
        if (null == context || TextUtils.isEmpty(targetPackageName)) {
            return null;
        }
        try {
            return context.createPackageContext(targetPackageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return null;
    }
}
