package org.alee.component.skin.page;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.service.ISwitchThemeSkinObserver;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IWindowProxy extends ISwitchThemeSkinObserver {
    /**
     * 绑定 LayoutInflate
     *
     * @param context {@link Context}
     */
    void bindLayoutInflate(Context context);

    /**
     * 动态添加允许换肤的View
     *
     * @param view     可以换肤的View
     * @param elements 动态皮肤属性
     */
    void addEnabledThemeSkinView(@NonNull View view, @NonNull SkinElement... elements);

    /**
     * 恢复
     */
    void onVisible();

    /**
     * 暂停
     */
    void Invisible();


}
