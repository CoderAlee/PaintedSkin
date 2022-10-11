package org.alee.demo.skin.basic.ability.basic.activity

import androidx.appcompat.app.AppCompatActivity
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble

/**
 * 支持换肤能力的Activity
 *
 * <p> 此处没有在基类实现[IThemeSkinAble] 接口。而是由子类自己决定是否需要实现[IThemeSkinAble],这样更加灵活一些。
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal abstract class SkinAbleActivity : AppCompatActivity() {
    /**
     * 在附着到Window后判断如果子类实现了[IThemeSkinAble] 则注册主题切换观察者
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (this is IThemeSkinAble) {
            ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this)
        }
    }

    override fun onDetachedFromWindow() {
        if (this is IThemeSkinAble) {
            ThemeSkinService.getInstance().unsubscribeSwitchThemeSkin(this)
        }
        super.onDetachedFromWindow()
    }
}