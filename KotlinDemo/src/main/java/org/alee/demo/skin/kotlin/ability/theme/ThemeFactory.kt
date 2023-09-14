package org.alee.demo.skin.kotlin.ability.theme

import org.alee.component.skin.template.EmptyThemeSkin
import org.alee.component.skin.template.IThemeFactory
import org.alee.component.skin.template.IThemeSkin

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
internal class ThemeFactory : IThemeFactory {
    /**
     * 默认主题 (一般是App内默认样式)
     */
    override val defaultTheme: Int
        get() = AppTheme.STANDARD_LIGHT.ordinal

    /**
     * 通过主题获取主题皮肤配置
     * @param theme Int
     * @return [IThemeSkin]
     */
    override fun fetchThemeOption(theme: Int): IThemeSkin {
        return when (theme) {
            AppTheme.STANDARD_DARK.ordinal -> Dark()
            AppTheme.SPRING_FESTIVAL_LIGHT.ordinal -> SpringFestivalLight()
            AppTheme.SPRING_FESTIVAL_DARK.ordinal -> SpringFestivalDark()
            else -> EmptyThemeSkin
        }
    }
}
