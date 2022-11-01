package org.alee.component.skin.factory2;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.util.PrintUtil;

import java.lang.reflect.Constructor;
import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class ViewInflater {
    /**
     * 自定义view标志
     */
    private static final String CUSTOM_VIEW_SIGN = ".";
    /**
     * 基础
     */
    private static final String BASICS = "View";
    /**
     * 基础view 路径
     */
    private static final String BASICS_PREFIX = "android.view.";
    /**
     * widget 路径
     */
    private static final String WIDGET_PREFIX = "android.widget.";
    /**
     * 兼容包
     */
    private static final String VIEW_COMPAT = "androidx.appcompat.widget.";
    /**
     * webkit 路径
     */
    private static final String WEBKIT_PREFIX = "android.webkit.";
    /**
     * app 路径
     */
    private static final String APP_PREFIX = "android.app.";
    /**
     * V7 兼容包
     */
    private static final String V7_COMPAT = "android.support.v7.widget.";
    /**
     * View 前缀集合
     */
    private static final String[] CLASS_PREFIX_ARRAY = {
            WIDGET_PREFIX,
            VIEW_COMPAT,
            WEBKIT_PREFIX,
            APP_PREFIX,
            V7_COMPAT
    };
    /**
     * View 构造函数参数签名
     */
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
    /**
     * View 构造函数缓存
     */
    private static final Map<String, Constructor<? extends View>> VIEW_CONSTRUCTOR_MAP = new ArrayMap<>();

    /**
     * 创建view
     *
     * @param context      上下文
     * @param name         view名称
     * @param attributeSet 属性集
     *
     * @return 结果
     */
    @Nullable
    static View createView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View temp = null;
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            if (!name.contains(CUSTOM_VIEW_SIGN)) {
                if (BASICS.equals(name)) {
                    try {
                        temp = inflater.createView(name, BASICS_PREFIX, attributeSet);
                    } catch (Throwable ignored) {
                        temp = createView(context, name, BASICS_PREFIX, attributeSet);
                    }
                    if (null == temp) {
                        temp = new View(context, attributeSet);
                    }
                }
                if (null == temp) {
                    for (String prefix : CLASS_PREFIX_ARRAY) {
                        try {
                            temp = inflater.createView(name, prefix, attributeSet);
                        } catch (Throwable ignored) {
                        }

                        if (null == temp) {
                            temp = createView(context, name, prefix, attributeSet);
                        }
                        if (null != temp) {
                            break;
                        }
                    }
                }
            } else {
                temp = inflater.createView(name, null, attributeSet);
            }
        } catch (Exception e) {
            PrintUtil.getInstance().printE(e);
        }
        if (null == temp) {
            PrintUtil.getInstance().printD("ViewInflater内部未能成功创建 [ " + name + " ]");
        }
        return temp;
    }

    private static View createView(@NonNull Context context, @NonNull String name, String prefix, @NonNull AttributeSet attributeSet) {
        Constructor<? extends View> constructor = VIEW_CONSTRUCTOR_MAP.get(name);
        try {
            if (null == constructor) {
                Class<? extends View> clazz = context.getClassLoader()
                        .loadClass(TextUtils.isEmpty(prefix) ? name : (prefix + name))
                        .asSubclass(View.class);
                constructor = clazz.getConstructor(CONSTRUCTOR_SIGNATURE);
                VIEW_CONSTRUCTOR_MAP.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(context, attributeSet);
        } catch (Throwable ignored) {
            return null;
        }
    }
}
