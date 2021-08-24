package org.alee.component.skin.pack;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/12
 * @description: xxxx
 *
 *********************************************************/
abstract class BaseThemeSkinPack implements IThemeSkinPack {
    /**
     * 皮肤包类型
     */
    private final int mSkinPackType;
    /**
     * 皮肤包名称
     */
    private final String mSkinPackName;
    /**
     * {@link IThemeSkinPack} 托底皮肤包
     */
    private final IThemeSkinPack mUnderpinThemeSkinPack;
    /**
     * 皮肤包当前状态
     */
    private int mCurrentStatus = Status.NEW;
    /**
     * 皮肤资源提供者
     */
    private ISkinResourcesProvider mSkinResourcesProvider;

    BaseThemeSkinPack(@SkinPackType int skinPackType, String skinPackName, IThemeSkinPack underpinThemeSkinPack) {
        mSkinPackType = skinPackType;
        mSkinPackName = skinPackName;
        mUnderpinThemeSkinPack = underpinThemeSkinPack;
    }

    boolean onReady(@NonNull ISkinResourcesProvider skinResourcesProvider) {
        mSkinResourcesProvider = skinResourcesProvider;
        mCurrentStatus = Status.RUNNABLE;
        return true;
    }

    /**
     * 获取皮肤包 名称
     *
     * @return 一般是用皮肤包绝对路径
     */
    @Override
    public final String getName() {
        return mSkinPackName;
    }

    /**
     * 判断 当前主题皮肤包是否可用
     *
     * @return true: 皮肤包有效且可以使用；false ：皮肤包无效且无法使用
     */
    @Override
    public final boolean isAvailable() {
        return Status.RUNNABLE == getCurrentStatus() && null != mSkinResourcesProvider;
    }

    /**
     * 获取皮肤包类型
     *
     * @return {@link SkinPackType#TYPE_DEFAULT}{@link SkinPackType#TYPE_STANDARD}{@link SkinPackType#TYPE_CUSTOMIZE}
     */
    @Override
    public final int getSkinPackType() {
        return mSkinPackType;
    }

    /**
     * 获取当前状态
     *
     * @return {@link Status#NEW}\{@link Status#RUNNABLE}|{@link Status#DESTROYED}
     */
    @Override
    public final int getCurrentStatus() {
        return mCurrentStatus;
    }

    /**
     * 获取状态颜色集合
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    @Override
    public final ColorStateList getColorStateList(int resId) {
        ColorStateList temp;
        if (isAvailable()) {
            temp = mSkinResourcesProvider.getColorStateList(resId);
            if (null != temp) {
                return temp;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getColorStateList(resId);
    }

    /**
     * 获取状态颜色集合
     *
     * @param resName 资源名称
     * @return 结果
     */
    @Nullable
    @Override
    public final ColorStateList getColorStateList(@NonNull String... resName) {
        ColorStateList temp;
        if (isAvailable()) {
            temp = mSkinResourcesProvider.getColorStateList(resName);
            if (null != temp) {
                return temp;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getColorStateList(resName);
    }

    /**
     * 根据id 获取颜色资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Override
    public final int getColor(int resId) {
        int color = 0;
        if (isAvailable()) {
            color = mSkinResourcesProvider.getColor(resId);
            if (0 != color) {
                return color;
            }
        }
        return null == mUnderpinThemeSkinPack ? color : mUnderpinThemeSkinPack.getColor(resId);
    }

    /**
     * 根据资源名 获取颜色值
     *
     * @param resName 资源名
     * @return 结果
     */
    @Override
    public final int getColor(String... resName) {
        int color = 0;
        if (isAvailable()) {
            color = mSkinResourcesProvider.getColor(resName);
            if (0 != color) {
                return color;
            }
        }
        return null == mUnderpinThemeSkinPack ? color : mUnderpinThemeSkinPack.getColor(resName);
    }

    /**
     * 根据id 获取可拉伸资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    @Override
    public final Drawable getDrawable(int resId) {
        Drawable drawable;
        if (isAvailable()) {
            drawable = mSkinResourcesProvider.getDrawable(resId);
            if (null != drawable) {
                return drawable;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getDrawable(resId);
    }

    /**
     * 根据名称 获取可拉伸资源
     *
     * @param resName 资源名
     * @return 结果
     */
    @Nullable
    @Override
    public final Drawable getDrawable(String... resName) {
        Drawable drawable;
        if (isAvailable()) {
            drawable = mSkinResourcesProvider.getDrawable(resName);
            if (null != drawable) {
                return drawable;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getDrawable(resName);
    }

    @Nullable
    @Override
    public final Drawable getMipmap(int resId) {
        Drawable drawable;
        if (isAvailable()) {
            drawable = mSkinResourcesProvider.getMipmap(resId);
            if (null != drawable) {
                return drawable;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getMipmap(resId);
    }

    @Nullable
    @Override
    public final Drawable getMipmap(String... resName) {
        Drawable drawable;
        if (isAvailable()) {
            drawable = mSkinResourcesProvider.getMipmap(resName);
            if (null != drawable) {
                return drawable;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getMipmap(resName);
    }

    /**
     * 根据可拉伸资源id获取图片资源
     *
     * @param resId id
     * @return 结果
     */
    @Nullable
    @Override
    public final Bitmap getBitmapForDrawable(int resId) {
        Bitmap bitmap;
        if (isAvailable()) {
            bitmap = mSkinResourcesProvider.getBitmapForDrawable(resId);
            if (null != bitmap) {
                return bitmap;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getBitmapForDrawable(resId);
    }

    /**
     * 根据可拉伸资源名称获取图片资源
     *
     * @param resName 资源名称
     * @return 结果
     */
    @Nullable
    @Override
    public final Bitmap getBitmapForDrawable(String... resName) {
        Bitmap bitmap;
        if (isAvailable()) {
            bitmap = mSkinResourcesProvider.getBitmapForDrawable(resName);
            if (null != bitmap) {
                return bitmap;
            }
        }
        return null == mUnderpinThemeSkinPack ? null : mUnderpinThemeSkinPack.getBitmapForDrawable(resName);
    }

    /**
     * 获取主题
     *
     * @return {@link Resources.Theme}
     */
    @Override
    public final Resources.Theme getTheme() {
        return mSkinResourcesProvider.getTheme();
    }
}
