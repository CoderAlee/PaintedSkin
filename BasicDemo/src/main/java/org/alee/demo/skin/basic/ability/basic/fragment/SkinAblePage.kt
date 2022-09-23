package org.alee.demo.skin.basic.ability.basic.fragment

import android.content.Context
import androidx.fragment.app.Fragment
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
abstract class SkinAblePage : Fragment(), IThemeSkinAble {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this)
    }

    override fun onDetach() {
        ThemeSkinService.getInstance().unsubscribeSwitchThemeSkin(this)
        super.onDetach()
    }

    override fun onThemeSkinSwitchRunOnUiThread() {
        "${this::javaClass.name} received switch theme skin command".logI()
    }
}