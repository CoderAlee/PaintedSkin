package org.alee.component.skin.pack;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/10
 * @description: 皮肤资源提供者基类
 *
 *********************************************************/
abstract class BaseStandardSkinResourcesProvider extends BaseSkinResourcesProvider {
    /**
     * 资源管理器
     */
    private final Resources mThemeSkinResources;
    /**
     * App 资源管理器
     */
    private final Resources mOriginalResources;
    /**
     * 资源包名
     */
    private final String mResourcesPackageName;
    /**
     * {@link Context}
     */
    private final Context mResourcesContext;

    BaseStandardSkinResourcesProvider(@NonNull Context resourcesContext, @NonNull Resources originalResources,
                                      @NonNull Resources themeSkinResources, @NonNull String resourcesPackageName) {
        mResourcesContext = resourcesContext;
        mOriginalResources = originalResources;
        mThemeSkinResources = themeSkinResources;
        mResourcesPackageName = resourcesPackageName;

    }

    protected Resources getThemeSkinResources() {
        return mThemeSkinResources;
    }

    /**
     * 获取主题
     *
     * @return {@link Resources.Theme}
     */
    @Override
    public Resources.Theme getTheme() {
        if (null == mResourcesContext) {
            return null;
        }
        return mResourcesContext.getTheme();
    }

    @Nullable
    @Override
    protected Bitmap getBitmapForDrawable(int resId, Resources.Theme theme) throws Throwable {
        return getBitmapForDrawable(getResourceName(resId), theme);
    }

    @Nullable
    @Override
    protected Drawable getMipmap(int resId, Resources.Theme theme) throws Throwable {
        return getMipmap(getResourceName(resId), theme);
    }

    @Nullable
    @Override
    protected Drawable getDrawable(int resId, Resources.Theme theme) throws Throwable {
        return getDrawable(getResourceName(resId), theme);
    }

    @Nullable
    @Override
    protected Integer getColor(int resId, Resources.Theme theme) throws Throwable {
        return getColor(getResourceName(resId), theme);
    }

    @Nullable
    @Override
    protected ColorStateList getColorStateList(int resId, @NonNull Resources.Theme theme) throws Throwable {
        return getColorStateList(getResourceName(resId), theme);
    }

    /**
     * 获取资源名称
     *
     * @param resId 资源id
     *
     * @return name
     */
    protected final String getResourceName(@AnyRes int resId) throws Throwable {
        return mOriginalResources.getResourceEntryName(resId);
    }

    /**
     * 获取目标资源Id
     *
     * @param type          资源类型
     * @param resourcesName 资源名称
     *
     * @return id
     */
    protected int getTargetResourcesId(@ResourcesType.Constraint String type, @NonNull String resourcesName) {
        if (TextUtils.isEmpty(resourcesName)) {
            return -1;
        }
        return mThemeSkinResources.getIdentifier(resourcesName, type, mResourcesPackageName);
    }

    @NonNull
    protected Context getContext() {
        return mResourcesContext;
    }

}
