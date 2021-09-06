package org.alee.component.fount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.factory2.IExpandedFactory2;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/16
 * @description: xxxx
 *
 *********************************************************/
final class TypefaceFactory implements IExpandedFactory2 {
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
        if (originalView instanceof TextView) {
            if (TypefacePlugin.getInstance().isTypefaceEnable()) {
                if (0 > TypefacePlugin.getInstance().getTypefaceStyle()) {
                    ((TextView) originalView).setTypeface(TypefacePlugin.getInstance().getTypeface());
                } else {
                    ((TextView) originalView).setTypeface(TypefacePlugin.getInstance().getTypeface(), TypefacePlugin.getInstance().getTypefaceStyle());
                }
            }

        }
        return originalView;
    }
}
