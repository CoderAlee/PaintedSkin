package org.alee.component.skin.compat;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.alee.component.skin.factory2.IExpandedFactory2;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/16
 * @description: xxxx
 *
 *********************************************************/
final class ConstraintLayoutFactory implements IExpandedFactory2 {
    /**
     * {@link ConstraintLayout} 类名
     */
    private final static String CLASS_NAME = ConstraintLayout.class.getName();

    /**
     * 创建View
     *
     * @param originalView 上一个IExpandedFactory生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      {@link Context}
     * @param attrs        {@link AttributeSet}
     * @return 生成的View
     */
    @NonNull
    @Override
    public View onCreateView(@Nullable View originalView, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return TextUtils.equals(CLASS_NAME, name) ? new ConstraintLayout(context, attrs) : originalView;
    }
}
