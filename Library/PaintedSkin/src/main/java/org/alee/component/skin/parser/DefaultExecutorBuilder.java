package org.alee.component.skin.parser;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import org.alee.component.skin.R;
import org.alee.component.skin.executor.BasicViewSkinExecutorFactory;
import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.executor.SkinElement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class DefaultExecutorBuilder implements IThemeSkinExecutorBuilder {
    /**
     * 换肤支持的属性 背景
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_BACKGROUND = "background";
    /**
     * 换肤支持的属性 前景色
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_FOREGROUND = "foreground";
    /**
     * 换肤支持的属性 字体颜色
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_TEXT_COLOR = "textColor";
    /**
     * 换肤支持的属性 暗示字体颜色
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_TEXT_COLOR_HINT = "textColorHint";
    /**
     * 换肤支持的属性 选中时高亮背景颜色
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT = "textColorHighlight";
    /**
     * 换肤支持的属性 链接的颜色
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_TEXT_COLOR_LINK = "textColorLink";
    /**
     * 换肤支持的属性 进度条背景
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_PROGRESS_DRAWABLE = "progressDrawable";
    /**
     * 换肤支持的属性 ListView分割线
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_LIST_VIEW_DIVIDER = "divider";
    /**
     * 换肤支持的属性 填充内容
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_SRC = "src";
    /**
     * 换肤支持的属性 按钮背景
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static final String ATTRIBUTE_BUTTON = "button";
    private static final Map<Integer, String> SUPPORT_ATTR = new HashMap<>();

    static {
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_background, ATTRIBUTE_BACKGROUND);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_foreground, ATTRIBUTE_FOREGROUND);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColor, ATTRIBUTE_TEXT_COLOR);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorHint, ATTRIBUTE_TEXT_COLOR_HINT);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorHighlight, ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorLink, ATTRIBUTE_TEXT_COLOR_LINK);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_progressDrawable, ATTRIBUTE_PROGRESS_DRAWABLE);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_divider, ATTRIBUTE_LIST_VIEW_DIVIDER);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_src, ATTRIBUTE_SRC);
        SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_button, ATTRIBUTE_BUTTON);
    }

    /**
     * 解析支持换肤的属性
     *
     * @param context      {@link Context}
     * @param attributeSet {@link AttributeSet}
     * @return {@link SkinElement}
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Override
    public Set<SkinElement> parse(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BasicSupportAttr);
        if (null == typedArray) {
            return null;
        }
        Set<SkinElement> elementSet = new HashSet<>();
        try {
            for (Integer key : SUPPORT_ATTR.keySet()) {
                try {
                    if (typedArray.hasValue(key)) {
                        elementSet.add(new SkinElement(SUPPORT_ATTR.get(key), typedArray.getResourceId(key, -1)));
                    }
                } catch (Throwable ignored) {
                }
            }
        } catch (Throwable ignored) {
        } finally {
            typedArray.recycle();
        }
        return elementSet;
    }

    /**
     * 需要换肤执行器
     *
     * @param view    需要换肤的View
     * @param element 需要执行的元素
     * @return {@link ISkinExecutor}
     */
    @Override
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public ISkinExecutor requireSkinExecutor(@NonNull View view, @NonNull SkinElement element) {
        return BasicViewSkinExecutorFactory.requireSkinExecutor(view, element);
    }

    /**
     * 是否支持属性
     *
     * @param view     View
     * @param attrName 属性名称
     * @return true: 支持
     */
    @Override
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public boolean isSupportAttr(@NonNull View view, @NonNull String attrName) {
        return SUPPORT_ATTR.containsValue(attrName);
    }
}
