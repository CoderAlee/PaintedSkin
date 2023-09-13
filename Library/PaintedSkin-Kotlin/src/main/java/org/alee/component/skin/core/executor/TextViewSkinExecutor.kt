package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import org.alee.component.skin.constant.DefaultSkinAttribute.TextViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [TextView] 属性换肤执行器
 *
 * <p> 所有[TextView]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [TextViewAttribute.ATTRIBUTE_TEXT_COLOR]
 * <p> [TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HINT]
 * <p> [TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT]
 * <p> [TextViewAttribute.ATTRIBUTE_TEXT_COLOR_LINK]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_LEFT]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_START]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_TOP]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_RIGHT]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_END]
 * <p> [TextViewAttribute.ATTRIBUTE_DRAWABLE_BOTTOM]
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
open class TextViewSkinExecutor<T : TextView> : ViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            TextViewAttribute.ATTRIBUTE_TEXT_COLOR -> {
                view.setTextColor(colorStateList)
            }

            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HINT -> {
                view.setHintTextColor(colorStateList)
            }

            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT -> {
                applyColor(view, colorStateList.defaultColor, attrName)
            }

            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_LINK -> {
                view.setLinkTextColor(colorStateList)
            }

            TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT -> {
                TextViewCompat.setCompoundDrawableTintList(view, colorStateList)
            }

            TextViewAttribute.ATTRIBUTE_SHADOW_COLOR -> {
                applyColor(view, colorStateList.defaultColor, attrName)
            }
        }
    }

    override fun applyColor(view: T, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            TextViewAttribute.ATTRIBUTE_TEXT_COLOR -> view.setTextColor(color)
            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HINT -> view.setHintTextColor(color)
            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT -> view.highlightColor = color
            TextViewAttribute.ATTRIBUTE_TEXT_COLOR_LINK -> view.setLinkTextColor(color)
            TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT -> applyColor(view, ColorStateList.valueOf(color), attrName)
            TextViewAttribute.ATTRIBUTE_SHADOW_COLOR -> view.setShadowLayer(view.shadowRadius, view.shadowDx, view.shadowDy, color)
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            TextViewAttribute.ATTRIBUTE_DRAWABLE_LEFT,
            TextViewAttribute.ATTRIBUTE_DRAWABLE_START,
            -> {
                val drawables = TextViewCompat.getCompoundDrawablesRelative(view)
                drawable.inheritProperty(drawables[0])
                view.setCompoundDrawablesCompat(drawable, drawables[1], drawables[2], drawables[3])
            }

            TextViewAttribute.ATTRIBUTE_DRAWABLE_TOP -> {
                val drawables = TextViewCompat.getCompoundDrawablesRelative(view)
                drawable.inheritProperty(drawables[1])
                view.setCompoundDrawablesCompat(drawables[0], drawable, drawables[2], drawables[3])
            }

            TextViewAttribute.ATTRIBUTE_DRAWABLE_RIGHT,
            TextViewAttribute.ATTRIBUTE_DRAWABLE_END,
            -> {
                val drawables = TextViewCompat.getCompoundDrawablesRelative(view)
                drawable.inheritProperty(drawables[2])
                view.setCompoundDrawablesCompat(drawables[0], drawables[1], drawable, drawables[3])
            }

            TextViewAttribute.ATTRIBUTE_DRAWABLE_BOTTOM -> {
                val drawables = TextViewCompat.getCompoundDrawablesRelative(view)
                drawable.inheritProperty(drawables[3])
                view.setCompoundDrawablesCompat(drawables[0], drawables[1], drawables[2], drawable)
            }
        }
    }

    /**
     * 使Drawable 继承原有Drawable 的部分属性
     * @receiver Drawable 新的Drawable
     * @param drawable Drawable? 旧的Drawable
     */
    private fun Drawable.inheritProperty(drawable: Drawable?) {
        if (null == drawable) {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        } else {
            bounds = drawable.bounds
            state = drawable.state
        }
    }

    /**
     * 以兼容的方式设置TextViewCompoundDrawables
     * @receiver T extends TextView
     *
     * @param left Drawable? 左侧Drawable
     * @param top Drawable? 顶部Drawable
     * @param right Drawable? 右侧Drawable
     * @param bottom Drawable? 底部Drawable
     */
    private fun T.setCompoundDrawablesCompat(left: Drawable?, top: Drawable?, right: Drawable?, bottom: Drawable?) {
        TextViewCompat.setCompoundDrawablesRelative(this, left, top, right, bottom)
        resetCompoundDrawablesTint()
    }

    /**
     * 重新为TextViewCompoundDrawables设置Tint，避免出现先设置了Tint后设置TextViewCompoundDrawables导致的Tint失效问题
     * @receiver T extends TextView
     */
    private fun T.resetCompoundDrawablesTint() {
        val tint = TextViewCompat.getCompoundDrawableTintList(this) ?: return
        applyColor(this, tint, TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT)
    }
}
