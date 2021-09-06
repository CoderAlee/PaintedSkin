package org.alee.component.skin.factory2;

import android.content.Context;
import android.util.AttributeSet;
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
public interface IExpandedFactory2 {

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
    View onCreateView(@Nullable View originalView, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs);
}
