package org.alee.component.skin.core.template

import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.util.WeakReference

/**
 * 支持换肤的View存储库
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal interface ISkinnableViewWarehouse {
    /**
     * 获取当前仓库中支持换肤的View数量
     */
    val skinnableViewSize: Int

    /**
     * 添加允许换肤的View
     * @param view View 支持换肤的View
     * @param skinAttributes Array<out SkinAttribute> 支持换肤的属性
     */
    fun addSkinnableView(@WeakReference view: View, vararg skinAttributes: SkinAttribute)

    /**
     *  添加允许换肤的View
     * @param view View 支持换肤的View
     * @param attributeSet AttributeSet 属性集
     */
    fun addSkinnableView(@WeakReference view: View, attributeSet: AttributeSet)

    /**
     * 将一个可换肤View移交到另一个仓库
     * @param view View 可换肤View
     * @param receiver ISkinnableViewWarehouse 接收者
     */
    fun transferSkinnableView(view: View, receiver: ISkinnableViewWarehouse)

    /**
     * 应用当前的主题皮肤
     */
    fun applyThemeSkin()

    /**
     * 销毁仓库并释放资源
     */
    fun destroy()
}
