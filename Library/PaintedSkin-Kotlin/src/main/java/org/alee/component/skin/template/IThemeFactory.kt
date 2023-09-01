package org.alee.component.skin.template

import androidx.annotation.AnyThread

/**
 * 主题工厂
 *
 * <p> 使用者通过实现此接口来配置当前项目支持换肤的主题
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
interface IThemeFactory {
    /**
     * 默认主题 (一般是App内默认样式)
     */
    val defaultTheme: Int

    /**
     * 通过主题获取主题皮肤配置
     * @param theme Int
     * @return [IThemeSkin]
     */
    @AnyThread
    fun fetchThemeOption(theme: Int): IThemeSkin
}
