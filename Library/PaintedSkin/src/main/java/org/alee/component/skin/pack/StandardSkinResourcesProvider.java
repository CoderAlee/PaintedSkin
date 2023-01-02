package org.alee.component.skin.pack;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/12
 * @description: 标准皮肤包资源提供器
 *
 *********************************************************/
final class StandardSkinResourcesProvider extends BaseStandardSkinResourcesProvider {
    StandardSkinResourcesProvider(@NonNull Context resourcesContext, @NonNull Resources originalResources, @NonNull Resources themeSkinResources, @NonNull String resourcesPackageName) {
        super(resourcesContext, originalResources, themeSkinResources, resourcesPackageName);
    }

    /**
     * 获取状态颜色集合
     *
     * @param resourcesName 原始资源名称
     * @param theme         主题
     *
     * @return {@link ColorStateList}
     *
     * @throws Throwable
     */
    @Nullable
    @Override
    protected ColorStateList getColorStateList(@NonNull String resourcesName, @NonNull Resources.Theme theme) throws Throwable {
        int resourcesId = getTargetResourcesId(ResourcesType.COLOR, resourcesName);
        if (!isValidResourcesId(resourcesId)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getThemeSkinResources().getColorStateList(resourcesId, theme);
        } else {
            return getThemeSkinResources().getColorStateList(resourcesId);
        }
    }

    /**
     * 获取色值
     *
     * @param resName 资源名称
     * @param theme   主题
     *
     * @return 色值
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Integer getColor(@NonNull String resName, Resources.Theme theme) throws Throwable {
        int resourcesId = getTargetResourcesId(ResourcesType.COLOR, resName);
        if (!isValidResourcesId(resourcesId)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getThemeSkinResources().getColor(resourcesId, theme);
        } else {
            return getThemeSkinResources().getColor(resourcesId);
        }
    }

    /**
     * 获取位图
     *
     * @param resName 资源名称
     * @param theme   主题
     *
     * @return 位图
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Bitmap getBitmapForDrawable(@NonNull String resName, Resources.Theme theme) throws Throwable {
        int resourcesId = getTargetResourcesId(ResourcesType.DRAWABLE, resName);
        if (!isValidResourcesId(resourcesId)) {
            return null;
        }
        return BitmapFactory.decodeResource(getThemeSkinResources(), resourcesId);
    }

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     *
     * @return {@link Drawable}
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Drawable getMipmap(@Nullable String resName, Resources.Theme theme) throws Throwable {
        int resourcesId = getTargetResourcesId(ResourcesType.MIPMAP, resName);
        if (!isValidResourcesId(resourcesId)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getThemeSkinResources().getDrawable(resourcesId, theme);
        } else {
            return getThemeSkinResources().getDrawable(resourcesId);
        }
    }

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     *
     * @return {@link Drawable}
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Drawable getDrawable(@Nullable String resName, Resources.Theme theme) throws Throwable {
        int resourcesId = getTargetResourcesId(ResourcesType.DRAWABLE, resName);
        if (!isValidResourcesId(resourcesId)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getThemeSkinResources().getDrawable(resourcesId, theme);
        } else {
            return getThemeSkinResources().getDrawable(resourcesId);
        }
    }
}
