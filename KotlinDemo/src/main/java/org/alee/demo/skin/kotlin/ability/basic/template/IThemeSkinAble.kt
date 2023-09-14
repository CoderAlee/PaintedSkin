package org.alee.demo.skin.kotlin.ability.basic.template

import android.view.View
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.IThemeSkinObserver

/**
 * 换肤能力
 *
 * <p> 实现了此接口的View既支持动态换肤
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
interface IThemeSkinAble : IThemeSkinObserver {

    /**
     * 动态添加支持换肤View
     * @param view [View]
     * @param attributes [SkinElement]
     */
    fun addEnabledThemeSkinView(view: View, vararg attributes: SkinAttribute) {
        ThemeSkinService.entrustSkinnableView(view, skinAttributes = attributes)
    }
}
