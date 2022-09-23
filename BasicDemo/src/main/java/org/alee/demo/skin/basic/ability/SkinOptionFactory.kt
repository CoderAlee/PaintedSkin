package org.alee.demo.skin.basic.ability

import org.alee.component.skin.service.IOptionFactory
import org.alee.component.skin.service.IThemeSkinOption

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
class SkinOptionFactory : IOptionFactory {
    companion object {
        const val MODE_DAY = 0x1000
        const val MODE_NIGHT = 0x1001
    }

    override fun defaultTheme() = MODE_DAY

    override fun requireOption(theme: Int): IThemeSkinOption? {
        return when (theme) {
            MODE_NIGHT -> NightOption()
            else -> null
        }
    }
}