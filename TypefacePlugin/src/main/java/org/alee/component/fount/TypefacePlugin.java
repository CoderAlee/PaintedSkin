package org.alee.component.fount;

import android.graphics.Typeface;

import androidx.annotation.NonNull;

import org.alee.component.skin.service.ThemeSkinService;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/16
 * @description: xxxx
 *
 *********************************************************/
public final class TypefacePlugin {
    /**
     * 字体库
     */
    private Typeface mTypeface;
    /**
     * 字体样式
     */
    private int mTypefaceStyle = -1;
    /**
     * 标识是否启用字体替换
     */
    private boolean mTypefaceEnable;

    private TypefacePlugin() {

    }

    public static void init() {
        ThemeSkinService.getInstance().getCreateViewInterceptor().add(new TypefaceFactory());
    }

    public TypefacePlugin switchTypeface(@NonNull Typeface typeface) {
        switchTypeface(typeface, -1);
        return getInstance();
    }

    public TypefacePlugin switchTypeface(@NonNull Typeface typeface, int style) {
        mTypeface = typeface;
        mTypefaceStyle = style;
        return getInstance();
    }

    /**
     * 获取单例对象
     *
     * @return {@link TypefacePlugin}
     */
    public static TypefacePlugin getInstance() {
        return TypefacePluginHolder.INSTANCE;
    }

    Typeface getTypeface() {
        return mTypeface;
    }

    int getTypefaceStyle() {
        return mTypefaceStyle;
    }

    public TypefacePlugin setEnable(boolean enable) {
        mTypefaceEnable = enable;
        return getInstance();
    }

    boolean isTypefaceEnable() {
        return mTypefaceEnable;
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class TypefacePluginHolder {
        /**
         * {@link TypefacePlugin}
         */
        private static final TypefacePlugin INSTANCE = new TypefacePlugin();
    }
}
