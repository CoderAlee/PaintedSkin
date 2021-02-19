package org.alee.component.skin.pack;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.util.PrintUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: xxxx
 *
 *********************************************************/
public abstract class BaseSkinResourcesProvider implements ISkinResourcesProvider {

    /**
     * {@link ResourcesBuffer}
     */
    protected final ResourcesBuffer mResourcesBuffer;
    /**
     * 黑盒 Object = Resources Name Or Resources Id
     */
    private final Map<String, List<Object>> mBlackBoxMap;


    public BaseSkinResourcesProvider() {
        mBlackBoxMap = Collections.synchronizedMap(new HashMap<>());
        mResourcesBuffer = new ResourcesBuffer();
    }

    /**
     * 获取状态颜色集合
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    @Override
    public final ColorStateList getColorStateList(@ColorRes int resId) {
        if (!isValidResourcesId(resId)) {
            return requiredDefaultColorStateList(resId);
        }
        if (isBadResources(ResourcesType.COLOR_STATE_LIST, resId)) {
            return requiredDefaultColorStateList(resId);
        }
        ColorStateList temp = mResourcesBuffer.getColorStateList(resId);
        if (isValidColorStateList(temp)) {
            return temp;
        }
        try {
            temp = getColorStateList(resId, getTheme());
            if (isValidColorStateList(temp)) {
                mResourcesBuffer.addColorStateList(resId, temp);
            } else {
                addBadResources(ResourcesType.COLOR_STATE_LIST, resId);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidColorStateList(temp) ? temp : requiredDefaultColorStateList(resId);
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
        String resourcesName = compositeResourcesName(resName);
        if (TextUtils.isEmpty(resourcesName)) {
            return requiredDefaultColorStateList(resourcesName);
        }
        if (isBadResources(ResourcesType.COLOR_STATE_LIST, resourcesName)) {
            return requiredDefaultColorStateList(resourcesName);
        }
        ColorStateList temp = mResourcesBuffer.getColorStateList(resourcesName);
        if (isValidColorStateList(temp)) {
            return temp;
        }
        try {
            temp = getColorStateList(resourcesName, getTheme());
            if (isValidColorStateList(temp)) {
                mResourcesBuffer.addColorStateList(resourcesName, temp);
            } else {
                addBadResources(ResourcesType.COLOR_STATE_LIST, resourcesName);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidColorStateList(temp) ? temp : requiredDefaultColorStateList(resourcesName);
    }

    /**
     * 组合资源名称
     *
     * @param names 名称数组
     * @return 资源名称
     */
    protected final String compositeResourcesName(String... names) {
        StringBuilder tempResourcesName = new StringBuilder();
        if (null == names) {
            return tempResourcesName.toString();
        }
        for (String s : names) {
            if (TextUtils.isEmpty(s)) {
                continue;
            }
            tempResourcesName.append(s.trim());
        }
        return tempResourcesName.toString();
    }

    /**
     * 需要一个默认的颜色状态集合
     *
     * @param resName 资源名称
     * @return {@link ColorStateList}
     */
    protected ColorStateList requiredDefaultColorStateList(String resName) {
        return null;
    }

    /**
     * 获取状态颜色集合
     *
     * @param resourcesName 原始资源名称
     * @param theme         主题
     * @return {@link ColorStateList}
     * @throws Throwable
     */
    protected abstract @Nullable
    ColorStateList getColorStateList(@NonNull String resourcesName, @NonNull Resources.Theme theme) throws Throwable;

    /**
     * 根据id 获取颜色资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Override
    public final int getColor(int resId) {
        if (!isValidResourcesId(resId)) {
            return requiredDefaultColor(resId);
        }
        if (isBadResources(ResourcesType.COLOR, resId)) {
            return requiredDefaultColor(resId);
        }
        Integer temp = mResourcesBuffer.getColor(resId);
        if (isValidColor(temp)) {
            return temp;
        }
        try {
            temp = getColor(resId, getTheme());
            if (isValidColor(temp)) {
                mResourcesBuffer.addColor(resId, temp);
            } else {
                addBadResources(ResourcesType.COLOR, resId);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidColor(temp) ? temp : requiredDefaultColor(resId);
    }

    /**
     * 根据资源名 获取颜色值
     *
     * @param resName 资源名
     * @return 结果
     */
    @Override
    public final int getColor(String... resName) {
        String resourcesName = compositeResourcesName(resName);
        if (TextUtils.isEmpty(resourcesName)) {
            return requiredDefaultColor(resourcesName);
        }
        if (isBadResources(ResourcesType.COLOR, resourcesName)) {
            return requiredDefaultColor(resourcesName);
        }
        Integer temp = mResourcesBuffer.getColor(resourcesName);
        if (isValidColor(temp)) {
            return temp;
        }
        try {
            temp = getColor(resourcesName, getTheme());
            if (isValidColor(temp)) {
                mResourcesBuffer.addColor(resourcesName, temp);
            } else {
                addBadResources(ResourcesType.COLOR, resourcesName);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidColor(temp) ? temp : requiredDefaultColor(resourcesName);
    }

    /**
     * 需要默认颜色
     *
     * @param resName 资源名称
     * @return 颜色值
     */
    protected int requiredDefaultColor(String resName) {
        return 0;
    }

    /**
     * 获取色值
     *
     * @param resName 资源名称
     * @param theme   主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Integer getColor(@NonNull String resName, Resources.Theme theme) throws Throwable;

    /**
     * 根据id 获取可拉伸资源
     *
     * @param resId 资源id
     * @return 结果
     */
    @Nullable
    @Override
    public final Drawable getDrawable(int resId) {
        if (!isValidResourcesId(resId)) {
            return requiredDefaultDrawable(resId);
        }
        if (isBadResources(ResourcesType.DRAWABLE, resId)) {
            return requiredDefaultDrawable(resId);
        }
        Drawable temp = mResourcesBuffer.getDrawable(resId);
        if (isValidDrawable(temp)) {
            return temp;
        }
        try {
            temp = getDrawable(resId, getTheme());
            if (isValidDrawable(temp)) {
                mResourcesBuffer.addDrawable(resId, temp);
            } else {
                addBadResources(ResourcesType.DRAWABLE, resId);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidDrawable(temp) ? temp : requiredDefaultDrawable(resId);
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
        String resourcesName = compositeResourcesName(resName);
        if (TextUtils.isEmpty(resourcesName)) {
            return requiredDefaultDrawable(resourcesName);
        }
        if (isBadResources(ResourcesType.DRAWABLE, resourcesName)) {
            return requiredDefaultDrawable(resourcesName);
        }
        Drawable temp = mResourcesBuffer.getDrawable(resourcesName);
        if (isValidDrawable(temp)) {
            return temp;
        }
        try {
            temp = getDrawable(resourcesName, getTheme());
            if (isValidDrawable(temp)) {
                mResourcesBuffer.addDrawable(resourcesName, temp);
            } else {
                addBadResources(ResourcesType.DRAWABLE, resourcesName);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidDrawable(temp) ? temp : requiredDefaultDrawable(resourcesName);
    }

    @Nullable
    @Override
    public final Drawable getMipmap(int resId) {
        if (!isValidResourcesId(resId)) {
            return requiredDefaultDrawable(resId);
        }
        if (isBadResources(ResourcesType.MIPMAP, resId)) {
            return requiredDefaultDrawable(resId);
        }
        Drawable temp = mResourcesBuffer.getDrawable(resId);
        if (isValidDrawable(temp)) {
            return temp;
        }
        try {
            temp = getMipmap(resId, getTheme());
            if (isValidDrawable(temp)) {
                mResourcesBuffer.addDrawable(resId, temp);
            } else {
                addBadResources(ResourcesType.MIPMAP, resId);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidDrawable(temp) ? temp : requiredDefaultDrawable(resId);
    }

    @Nullable
    @Override
    public final Drawable getMipmap(String... resName) {
        String resourcesName = compositeResourcesName(resName);
        if (TextUtils.isEmpty(resourcesName)) {
            return requiredDefaultDrawable(resourcesName);
        }
        if (isBadResources(ResourcesType.MIPMAP, resourcesName)) {
            return requiredDefaultDrawable(resourcesName);
        }
        Drawable temp = mResourcesBuffer.getDrawable(resourcesName);
        if (isValidDrawable(temp)) {
            return temp;
        }
        try {
            temp = getMipmap(resourcesName, getTheme());
            if (isValidDrawable(temp)) {
                mResourcesBuffer.addDrawable(resourcesName, temp);
            } else {
                addBadResources(ResourcesType.MIPMAP, resourcesName);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidDrawable(temp) ? temp : requiredDefaultDrawable(resourcesName);
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
        if (!isValidResourcesId(resId)) {
            return requiredDefaultBitmap(resId);
        }
        if (isBadResources(ResourcesType.DRAWABLE, resId)) {
            return requiredDefaultBitmap(resId);
        }
        Bitmap temp = mResourcesBuffer.getBitmap(resId);
        if (isValidBitmap(temp)) {
            return temp;
        }
        try {
            temp = getBitmapForDrawable(resId, getTheme());
            if (isValidBitmap(temp)) {
                mResourcesBuffer.addBitmap(resId, temp);
            } else {
                addBadResources(ResourcesType.DRAWABLE, resId);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidBitmap(temp) ? temp : requiredDefaultBitmap(resId);
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
        String resourcesName = compositeResourcesName(resName);
        if (TextUtils.isEmpty(resourcesName)) {
            return requiredDefaultBitmap(resourcesName);
        }
        if (isBadResources(ResourcesType.DRAWABLE, resourcesName)) {
            return requiredDefaultBitmap(resourcesName);
        }
        Bitmap temp = mResourcesBuffer.getBitmap(resourcesName);
        if (isValidBitmap(temp)) {
            return temp;
        }
        try {
            temp = getBitmapForDrawable(resourcesName, getTheme());
            if (isValidBitmap(temp)) {
                mResourcesBuffer.addBitmap(resourcesName, temp);
            } else {
                addBadResources(ResourcesType.DRAWABLE, resourcesName);
            }
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return isValidBitmap(temp) ? temp : requiredDefaultBitmap(resourcesName);
    }

    /**
     * 需要默认位图
     *
     * @param resName 资源名称
     * @return 位图
     */
    protected Bitmap requiredDefaultBitmap(String resName) {
        return null;
    }

    /**
     * 获取位图
     *
     * @param resName 资源名称
     * @param theme   主题
     * @return 位图
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Bitmap getBitmapForDrawable(@NonNull String resName, Resources.Theme theme) throws Throwable;

    /**
     * 需要默认位图
     *
     * @param resId 资源id
     * @return 位图
     */
    protected Bitmap requiredDefaultBitmap(@DrawableRes int resId) {
        return null;
    }

    /**
     * 校验是否为有效位图
     *
     * @param bitmap 位图
     * @return true:有效
     */
    protected boolean isValidBitmap(Bitmap bitmap) {
        return null != bitmap;
    }

    /**
     * 获取位图
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 位图
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Bitmap getBitmapForDrawable(@DrawableRes int resId, Resources.Theme theme) throws Throwable;

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return {@link Drawable}
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Drawable getMipmap(@Nullable String resName, Resources.Theme theme) throws Throwable;

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Drawable getMipmap(@DrawableRes int resId, Resources.Theme theme) throws Throwable;

    /**
     * 需要默认图片
     *
     * @param resName 资源名
     * @return {@link Drawable}
     */
    protected Drawable requiredDefaultDrawable(String resName) {
        return null;
    }

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return {@link Drawable}
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Drawable getDrawable(@Nullable String resName, Resources.Theme theme) throws Throwable;

    /**
     * 需要默认图片
     *
     * @param resId 原始资源id
     * @return {@link Drawable}
     */
    protected Drawable requiredDefaultDrawable(@DrawableRes int resId) {
        return null;
    }

    /**
     * 校验图片是否有效
     *
     * @param drawable 图片
     * @return true: 有效
     */
    protected boolean isValidDrawable(Drawable drawable) {
        return null != drawable;
    }

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Drawable getDrawable(@DrawableRes int resId, Resources.Theme theme) throws Throwable;

    /**
     * 需要默认的颜色
     *
     * @param resId 资源id
     * @return 颜色值
     */
    protected int requiredDefaultColor(@ColorRes int resId) {
        return 0;
    }

    /**
     * 验证是否为有效的色值
     *
     * @param color 色值
     * @return true： 有效
     */
    protected boolean isValidColor(Integer color) {
        return null != color && 0 != color;
    }

    /**
     * 获取色值
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    protected abstract @Nullable
    Integer getColor(@ColorRes int resId, Resources.Theme theme) throws Throwable;

    /**
     * 校验是否为有效的资源Id
     *
     * @param resId 资源id
     * @return true: 有效
     */
    protected final boolean isValidResourcesId(int resId) {
        if (0 >= resId) {
            return false;
        }
        return !Integer.toHexString(resId).startsWith("1");
    }

    /**
     * 需要默认颜色状态集合
     *
     * @param resId 资源id
     * @return {@link ColorStateList}
     */
    protected ColorStateList requiredDefaultColorStateList(@ColorRes int resId) {
        return null;
    }

    /**
     * 校验资源是否能获取到
     *
     * @param type     资源类型
     * @param nameOrId 资源名称或资源Id
     * @return true:无法获取到
     */
    private boolean isBadResources(@ResourcesType.Constraint String type, Object nameOrId) {
        synchronized (mBlackBoxMap) {
            if (!mBlackBoxMap.containsKey(type)) {
                return false;
            }
            List<Object> temp = mBlackBoxMap.get(type);
            if (null == temp) {
                return false;
            }
            return temp.contains(nameOrId);
        }
    }

    /**
     * 验证是否为有效的颜色状态集合
     *
     * @param stateList {@link ColorStateList}
     * @return true:有效
     */
    protected boolean isValidColorStateList(ColorStateList stateList) {
        return null != stateList && -1 != stateList.getDefaultColor();
    }

    /**
     * 获取状态颜色集合
     *
     * @param resId 原始资源Id
     * @param theme 主题
     * @return {@link ColorStateList}
     * @throws Throwable
     */
    protected abstract @Nullable
    ColorStateList getColorStateList(@ColorRes int resId, @NonNull Resources.Theme theme) throws Throwable;

    /**
     * 记录一条无法获取到的资源
     *
     * @param type     资源类型
     * @param nameOrId 资源名称
     */
    protected final void addBadResources(@ResourcesType.Constraint String type, @NonNull Object nameOrId) {
        synchronized (mBlackBoxMap) {
            List<Object> temp = mBlackBoxMap.get(type);
            if (null == temp) {
                temp = new ArrayList<>();
            }
            temp.add(nameOrId);
            mBlackBoxMap.put(type, temp);
        }
    }

}
