package org.alee.component.skin

import android.app.Application
import org.alee.component.skin.constant.PerformanceMode
import org.alee.component.skin.constant.SkinMode
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.exception.UninitializedException
import org.alee.component.skin.template.ILogger
import org.alee.component.skin.template.IThemeFactory
import org.alee.component.skin.util.INotProguard
import org.alee.component.skin.util.InnerLogger
import org.alee.component.skin.util.ext.logI
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
object ThemeSkinService : IThemeSkinService by DefaultService, INotProguard {

    /**
     * 标识是否已经进行了初始化
     */
    private val mIsInitialized by lazy { AtomicBoolean(false) }

    /**
     * @see Config
     */
    val config: Config = Config()

    override val currentSkinPack: IThemeSkinPack
        get() {
            assertInit()
            return DefaultService.currentSkinPack
        }

    override fun initialize(application: Application, factory: IThemeFactory, theme: Int) {
        if (mIsInitialized.get()) {
            return
        }
        "ThemeSkin version is ${BuildConfig.VERSION_NAME}".logI()
        mIsInitialized.set(true)
        DefaultService.initialize(application, factory, theme)
    }

    override fun switchTheme(theme: Int) {
        assertInit()
        DefaultService.switchTheme(theme)
    }

    @Throws(UninitializedException::class)
    @Synchronized
    private fun assertInit() {
        if (mIsInitialized.get()) {
            return
        }
        throw UninitializedException()
    }

    class Config internal constructor() {
        /**
         * 标识 是否启用Debug模式
         */
        var debugMode: Boolean = true

        /**
         * 标识 是否启用严格模式
         */
        var strictMode: Boolean = false

        /**
         * 换肤模式
         */
        @get:SkinMode
        @setparam:SkinMode
        var skinMode: Int = SkinMode.REPLACE_ALL

        /**
         * 性能模式
         */
        @get:PerformanceMode
        @setparam:PerformanceMode
        var performanceMode: Int = PerformanceMode.PERFORMANCE_PRIORITY

        /**
         * [ILogger]
         */
        var logger: ILogger = InnerLogger
    }
}
