package org.alee.component.skin.core.view.factory

import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.constant.SkinMode
import org.alee.component.skin.core.template.IEnableSkinViewWarehouse
import org.alee.component.skin.util.safeCall

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal class ThemeSkinRecognizer(
    factory2: LayoutInflater.Factory2?,
    viewCreatorManager: IViewCreatorManager,
    private val mWarehouse: IEnableSkinViewWarehouse,
) : ViewInterceptor(factory2, viewCreatorManager) {

    private companion object {
        /**
         * 是否使用换肤功能
         */
        private const val ATTRIBUTE_SKIN_ENABLE = "enable"

        /**
         * 自定义属性命名空间
         */
        private const val ATTRIBUTE_NAMESPACE = "http://schemas.android.com/android/skin"
    }

    /**
     * 识别是否支持换肤
     *
     * @param view         view
     * @param attributeSet 属性集
     */
    override fun distinguishEnableSkin(view: View, attributeSet: AttributeSet) {
        if (attributeSet.isEnableThemeSkin.not()) {
            return
        }
        mWarehouse.addEnableSkinView(view, attributeSet)
    }

    private inline val AttributeSet.isEnableThemeSkin: Boolean
        get() {
            if (SkinMode.DO_NOT_REPLACE == ThemeSkinService.config.skinMode) {
                return false
            }
            safeCall {
                return getAttributeBooleanValue(
                    ATTRIBUTE_NAMESPACE,
                    ATTRIBUTE_SKIN_ENABLE,
                    SkinMode.REPLACE_MARKED != ThemeSkinService.config.skinMode,
                )
            }
            return false
        }
}
