package org.alee.component.skin.pack;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/10
 * @description: 主题皮肤资源提供者
 *
 *********************************************************/
interface ISkinResourcesProvider {

    /**
     * 获取状态颜色集合
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    ColorStateList getColorStateList(@ColorRes int resId);


    /**
     * 获取状态颜色集合
     *
     * @param resName 资源名称
     * @return 结果
     */
    @Nullable
    ColorStateList getColorStateList(@NonNull String... resName);


    /**
     * 根据id 获取颜色资源
     *
     * @param resId 资源id
     * @return 结果
     */
    int getColor(@ColorRes int resId);

    /**
     * 根据资源名 获取颜色值
     *
     * @param resName 资源名
     * @return 结果
     */
    int getColor(String... resName);

    /**
     * 根据id 获取可拉伸资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    Drawable getDrawable(@DrawableRes int resId);

    /**
     * 根据名称 获取可拉伸资源
     *
     * @param resName 资源名
     * @return 结果
     */
    @Nullable
    Drawable getDrawable(String... resName);

    /**
     * 根据id 获取图片资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    Drawable getMipmap(@DrawableRes int resId);

    /**
     * 根据名称 获取图片资源
     *
     * @param resName 资源名
     * @return 结果
     */
    @Nullable
    Drawable getMipmap(String... resName);


    /**
     * 根据可拉伸资源id获取图片资源
     *
     * @param resId id
     * @return 结果
     */
    @Nullable
    Bitmap getBitmapForDrawable(@DrawableRes int resId);

    /**
     * 根据可拉伸资源名称获取图片资源
     *
     * @param resName 资源名称
     * @return 结果
     */
    @Nullable
    Bitmap getBitmapForDrawable(String... resName);

    /**
     * 获取主题
     *
     * @return {@link Theme}
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    Theme getTheme();


}
