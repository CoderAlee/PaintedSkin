package org.alee.demo.skin.basic.ability.basic.activity

import androidx.appcompat.app.AppCompatActivity
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble
import org.alee.demo.skin.basic.ability.util.logI

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal abstract class SkinAbleActivity : AppCompatActivity(), IThemeSkinAble {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this)
    }

    override fun onDetachedFromWindow() {
        ThemeSkinService.getInstance().unsubscribeSwitchThemeSkin(this)
        super.onDetachedFromWindow()
    }

    override fun onThemeSkinSwitchRunOnUiThread() {
        "${this::javaClass.name} received switch theme skin command".logI()
    }
}