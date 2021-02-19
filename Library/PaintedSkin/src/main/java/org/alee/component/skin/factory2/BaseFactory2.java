package org.alee.component.skin.factory2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
abstract class BaseFactory2 implements LayoutInflater.Factory2 {
    /**
     * 原始Factory2
     */
    private final LayoutInflater.Factory2 mOriginalFactory2;

    public BaseFactory2(@Nullable LayoutInflater.Factory2 originalFactory2) {
        mOriginalFactory2 = originalFactory2;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return onCreateView(null, s, context, attributeSet);
    }

    @Nullable
    @Override
    public final View onCreateView(@Nullable View view, @NonNull String s, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View originalView = null == mOriginalFactory2 ? null : (null == view ? mOriginalFactory2.onCreateView(s, context, attributeSet) : mOriginalFactory2.onCreateView(view, s, context, attributeSet));
        if (null == originalView) {
            originalView = ViewInflater.createView(view, s, context, attributeSet);
        }
        return onReplaceView(originalView, view, s, context, attributeSet);
    }

    /**
     * 替换view
     *
     * @param originalView 父类生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      {@link Context}
     * @param attributeSet {@link AttributeSet}
     * @return 替换后的view
     */
    protected abstract View onReplaceView(@Nullable View originalView, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet);
}
