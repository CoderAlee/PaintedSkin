package org.alee.demo.skin.basic.ability.basic.template

import android.view.View
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.page.WindowManager
import org.alee.component.skin.service.ISwitchThemeSkinObserver

/**
 * 换肤能力
 *
 * <p> 实现了此接口的View既支持动态换肤
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
interface IThemeSkinAble : ISwitchThemeSkinObserver {

    /**
     * 动态添加支持换肤View
     * @param view [View]
     * @param attributes [SkinElement]
     */
    fun addEnabledThemeSkinView(view: View, vararg attributes: SkinElement) {
        WindowManager.getInstance().getWindowProxy(view.context).addEnabledThemeSkinView(view, *attributes)
    }
}