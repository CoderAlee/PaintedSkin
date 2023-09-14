package org.alee.demo.skin.kotlin.ability.theme

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
enum class AppTheme(@DayNightMode val dayNightMode: Int, @Theme val theme: Int) {
    STANDARD_LIGHT(DayNightMode.DAY, Theme.STANDARD),
    STANDARD_DARK(DayNightMode.NIGHT, Theme.STANDARD),
    SPRING_FESTIVAL_LIGHT(DayNightMode.DAY, Theme.SPRING_FESTIVAL),
    SPRING_FESTIVAL_DARK(DayNightMode.NIGHT, Theme.SPRING_FESTIVAL),
}
