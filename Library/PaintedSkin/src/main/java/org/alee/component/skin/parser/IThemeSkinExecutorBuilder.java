package org.alee.component.skin.parser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.executor.SkinElement;

import java.util.Set;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IThemeSkinExecutorBuilder {
    /**
     * 解析支持换肤的属性
     *
     * @param context      {@link Context}
     * @param attributeSet {@link AttributeSet}
     *
     * @return {@link SkinElement}
     */
    @Nullable
    Set<SkinElement> parse(@NonNull Context context, @NonNull AttributeSet attributeSet);

    /**
     * 需要换肤执行器
     *
     * @param view    需要换肤的View
     * @param element 需要执行的元素
     *
     * @return {@link ISkinExecutor}
     */
    @NonNull
    ISkinExecutor requireSkinExecutor(@NonNull View view, @NonNull SkinElement element);

    /**
     * 是否支持属性
     *
     * @param view     View
     * @param attrName 属性名称
     *
     * @return true: 支持
     */
    boolean isSupportAttr(@NonNull View view, @NonNull String attrName);
}
