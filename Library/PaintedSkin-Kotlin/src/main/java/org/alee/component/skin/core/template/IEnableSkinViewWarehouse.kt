package org.alee.component.skin.core.template

import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.model.SkinAttribute

/**
 * 支持换肤的View存储库
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal interface IEnableSkinViewWarehouse {
    /**
     * 添加允许换肤的View
     * @param view View 支持换肤的View
     * @param attributeSet Array<out SkinElement> 支持换肤的属性
     */
    fun addEnableSkinView(view: View, vararg attributeSet: SkinAttribute)

    /**
     *  添加允许换肤的View
     * @param view View 支持换肤的View
     * @param attributeSet AttributeSet 属性集
     */
    fun addEnableSkinView(view: View, attributeSet: AttributeSet)

    /**
     * 应用当前的主题皮肤
     */
    fun applyThemeSkin()

    /**
     * 销毁仓库并释放资源
     */
    fun destroy()
}
