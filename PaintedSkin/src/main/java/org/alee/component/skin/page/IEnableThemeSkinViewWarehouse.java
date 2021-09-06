package org.alee.component.skin.page;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.executor.SkinElement;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IEnableThemeSkinViewWarehouse {
    /**
     * 动态添加允许换肤的View
     *
     * @param view     可以换肤的View
     * @param elements 动态皮肤属性
     */
    void addEnabledThemeSkinView(@NonNull View view, @NonNull SkinElement... elements);

    /**
     * 动态添加允许换肤的View
     *
     * @param view         可以换肤的View
     * @param attributeSet 动态皮肤属性
     */
    void addEnabledThemeSkinView(@NonNull View view, @NonNull AttributeSet attributeSet);

    /**
     * 应用当前的主题皮肤
     */
    void applyThemeSkin();

    /**
     * 释放并回收资源
     */
    void gc();
}
