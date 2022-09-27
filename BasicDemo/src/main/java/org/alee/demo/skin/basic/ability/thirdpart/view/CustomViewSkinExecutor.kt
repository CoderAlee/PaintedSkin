package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.executor.ViewSkinExecutor
import org.alee.demo.skin.basic.ability.widget.CustomView

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CustomViewSkinExecutor(fullElement: SkinElement) : ViewSkinExecutor<CustomView>(fullElement) {

    override fun applyColor(view: CustomView, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_POINT_COLOR -> view.pointColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_CIRCLE_COLOR -> view.circleBorderColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_RECTANGLE_COLOR -> view.rectangleFillColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_TEXT_COLOR -> view.textColor = colorStateList
        }
    }

    override fun applyColor(view: CustomView, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_POINT_COLOR -> view.pointColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_CIRCLE_COLOR -> view.circleBorderColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_RECTANGLE_COLOR -> view.rectangleFillColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_TEXT_COLOR -> view.textColor = ColorStateList.valueOf(color)
        }
    }

    override fun applyDrawable(view: CustomView, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_ICON -> view.icon = drawable
        }
    }

}