package org.alee.demo.skin.kotlin.ability.theme

import android.app.Application
import androidx.annotation.AnyThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.alee.component.skin.ThemeSkinService
import org.alee.demo.skin.kotlin.ability.KEY_DAY_NIGHT_MODE
import org.alee.demo.skin.kotlin.ability.KEY_THEME
import org.alee.demo.skin.kotlin.ability.util.loadInt
import org.alee.demo.skin.kotlin.ability.util.logI
import org.alee.demo.skin.kotlin.ability.util.saveInt
import kotlin.system.measureTimeMillis

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
object AppThemeManager : CoroutineScope by MainScope() {
    /**
     * 当前应使用的日夜模式
     */
    private val usedDayMode: Boolean
        get() = DayNightMode.DAY == KEY_DAY_NIGHT_MODE.loadInt(DayNightMode.NIGHT)

    /**
     * 当前选中的主题
     */
    private val selectedTheme: Int
        get() = KEY_THEME.loadInt(Theme.SPRING_FESTIVAL)

    /**
     * 当前应应用的主题皮肤
     */
    val theme: AppTheme
        get() {
            return when (selectedTheme) {
                Theme.STANDARD -> if (usedDayMode) AppTheme.STANDARD_LIGHT else AppTheme.STANDARD_DARK
                Theme.SPRING_FESTIVAL -> if (usedDayMode) AppTheme.SPRING_FESTIVAL_LIGHT else AppTheme.SPRING_FESTIVAL_DARK
                else -> AppTheme.STANDARD_LIGHT
            }
        }

    fun init(application: Application) {
        measureTimeMillis {
            ThemeSkinService.initialize(application, ThemeFactory(), theme.ordinal)
        }.run {
            "框架初始化耗时:$this ms".logI()
        }
    }

    @AnyThread
    fun switchTheme(@Theme theme: Int) {
        theme.saveTheme()
        refreshThemeSkin()
    }

    @AnyThread
    fun switchDayNightMode(@DayNightMode dayNightMode: Int) {
        dayNightMode.saveDayNightMode()
        refreshThemeSkin()
    }

    @AnyThread
    private fun refreshThemeSkin() {
        val appTheme = theme
        "即将切换至$appTheme".logI()
        ThemeSkinService.switchTheme(appTheme.ordinal)
    }

    private fun @receiver:Theme Int.saveTheme() {
        KEY_THEME.saveInt(this)
    }

    private fun @receiver:DayNightMode Int.saveDayNightMode() {
        KEY_DAY_NIGHT_MODE.saveInt(this)
    }
}
