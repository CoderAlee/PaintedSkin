package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.res.ColorStateList
import de.hdodenhof.circleimageview.CircleImageView
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.executor.ViewSkinExecutor

/**
 * 第三方View 换肤执行器
 *
 * <p> 需要注意 继承关系。所有执行器都应最少继承于ViewSkinExecutor。有ViewSkinExecutor 来提供background等属性的换肤能力
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CircleImageViewSkinExecutor(fullElement: SkinElement) : ViewSkinExecutor<CircleImageView>(fullElement) {

    override fun applyColor(view: CircleImageView, color: Int, attrName: String) {
        // FIXME 需要注意调用super，来保证父类所支持执行的属性能够官服成功。
        super.applyColor(view, color, attrName)
        when (attrName) {
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BORDER_COLOR -> view.borderColor = color
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BACKGROUND_COLOR -> view.circleBackgroundColor = color
        }

    }

    // FIXME 如果不想支持 ColorStateList 可以不实现此函数
    override fun applyColor(view: CircleImageView, colorStateList: ColorStateList, attrName: String) {
        // FIXME 需要注意调用super，来保证父类所支持执行的属性能够官服成功。
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BORDER_COLOR -> view.borderColor = colorStateList.defaultColor
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BACKGROUND_COLOR -> view.circleBackgroundColor = colorStateList.defaultColor
        }
    }
}