package org.alee.component.skin.core.window

import android.content.Context
import android.view.LayoutInflater
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.constant.PerformanceMode
import org.alee.component.skin.util.setFactory2

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal class GlobalWindowProxy : BaseWindowProxy(DEFAULT_WINDOW_NAME) {

    private companion object {
        private const val DEFAULT_WINDOW_NAME = "GlobalWindow"
    }

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    override val isApplyInInvisible: Boolean
        get() = PerformanceMode.EXPERIENCE_FIRST == ThemeSkinService.config.performanceMode

    override fun onWindowCreated(context: Context) {
        super.onWindowCreated(context)
        LayoutInflater.from(context).setFactory2(ThemeSkinService.viewCreatorManager, mViewWarehouse)
    }

}
