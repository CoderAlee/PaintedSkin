package org.alee.demo.skin.basic.ability.basic.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble

/**
 * 支持换肤能力的Fragment
 *
 * <p> 此处没有在基类实现[IThemeSkinAble] 接口。而是由子类自己决定是否需要实现[IThemeSkinAble],这样更加灵活一些。
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
abstract class SkinAblePage : Fragment() {
    /**
     * 在附着到Window后判断如果子类实现了[IThemeSkinAble] 则注册主题切换观察者
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (this is IThemeSkinAble) {
            ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this)
        }
    }

    override fun onDetach() {
        if (this is IThemeSkinAble) {
            ThemeSkinService.getInstance().unsubscribeSwitchThemeSkin(this)
        }
        super.onDetach()
    }
}