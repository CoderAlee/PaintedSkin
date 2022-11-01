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
import androidx.appcompat.content.res.AppCompatResources;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: App原生皮肤提供者
 *
 *********************************************************/
final class DefaultSkinResourcesProvider extends BaseStandardSkinResourcesProvider {

    DefaultSkinResourcesProvider(@NonNull Context resourcesContext) {
        super(resourcesContext, resourcesContext.getResources(), resourcesContext.getResources(), resourcesContext.getPackageName());
    }

    @Nullable
    @Override
    protected ColorStateList getColorStateList(@NonNull String resourcesName, @NonNull Resources.Theme theme) throws Throwable {
        return getColorStateList(getTargetResourcesId(ResourcesType.COLOR, resourcesName), theme);
    }

    @Nullable
    @Override
    protected Integer getColor(@NonNull String resName, Resources.Theme theme) throws Throwable {
        return getColor(getTargetResourcesId(ResourcesType.COLOR, resName), theme);
    }

    @Nullable
    @Override
    protected Bitmap getBitmapForDrawable(@NonNull String resName, Resources.Theme theme) throws Throwable {
        return getBitmapForDrawable(getTargetResourcesId(ResourcesType.DRAWABLE, resName), theme);
    }

    /**
     * 获取位图
     *
     * @param resId 原始资源id
     * @param theme 主题
     *
     * @return 位图
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Bitmap getBitmapForDrawable(int resId, Resources.Theme theme) throws Throwable {
        return BitmapFactory.decodeResource(getThemeSkinResources(), resId);
    }

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     *
     * @return 图片
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Drawable getDrawable(int resId, Resources.Theme theme) throws Throwable {
        if (!isValidResourcesId(resId)) {
            return requiredDefaultDrawable(resId);
        }
        Drawable result = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                result = getThemeSkinResources().getDrawable(resId, theme);
            } else {
                result = getThemeSkinResources().getDrawable(resId);
            }
        } catch (Exception ignored) {
        }
        if (null == result) {
            result = AppCompatResources.getDrawable(getContext(), resId);
        }
        return result;
    }

    /**
     * 获取色值
     *
     * @param resId 原始资源id
     * @param theme 主题
     *
     * @return 色值
     *
     * @throws Throwable 任何异常
     */
    @Nullable
    @Override
    protected Integer getColor(int resId, Resources.Theme theme) throws Throwable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getThemeSkinResources().getColor(resId, theme);
        } else {
            return getThemeSkinResources().getColor(resId);
        }
    }

    /**
     * 获取状态颜色集合
     *
     * @param resId 原始资源Id
     * @param theme 主题
     *
     * @return {@link ColorStateList}
     *
     * @throws Throwable
     */
    @Nullable
    @Override
    protected ColorStateList getColorStateList(int resId, @NonNull Resources.Theme theme) throws Throwable {
        ColorStateList result = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result = getThemeSkinResources().getColorStateList(resId, theme);
            } else {
                result = getThemeSkinResources().getColorStateList(resId);
            }
        } catch (Exception ignored) {
        }
        if (null == result) {
            result = AppCompatResources.getColorStateList(getContext(), resId);
        }
        return result;
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
        return getDrawable(getTargetResourcesId(ResourcesType.MIPMAP, resName), theme);
    }

    @Nullable
    @Override
    protected Drawable getDrawable(@Nullable String resName, Resources.Theme theme) throws Throwable {
        return getDrawable(getTargetResourcesId(ResourcesType.DRAWABLE, resName), theme);
    }

}
