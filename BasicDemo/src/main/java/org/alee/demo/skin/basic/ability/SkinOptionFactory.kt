package org.alee.demo.skin.basic.ability

import org.alee.component.skin.service.IOptionFactory
import org.alee.component.skin.service.IThemeSkinOption

/**
 * 主题皮肤配置工厂
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
class SkinOptionFactory : IOptionFactory {
    companion object {
        // 默认皮肤
        const val MODE_DEFAULT = 0x1000

        // 白天模式
        const val MODE_DAY = 0x1001

        // 黑夜模式
        const val MODE_NIGHT = 0x1002
    }

    override fun defaultTheme() = MODE_DEFAULT

    override fun requireOption(theme: Int): IThemeSkinOption? {
        return when (theme) {
            MODE_DAY -> DayOption()
            MODE_NIGHT -> NightOption()
            else -> null
        }
    }
}