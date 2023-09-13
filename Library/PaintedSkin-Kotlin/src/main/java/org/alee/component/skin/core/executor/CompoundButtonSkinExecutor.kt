package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import org.alee.component.skin.constant.DefaultSkinAttribute.CompoundButtonAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [CompoundButton] 属性换肤执行器
 *
 * <p> 所有[CompoundButton]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [CompoundButtonAttribute.ATTRIBUTE_BUTTON]
 * <p> [CompoundButtonAttribute.ATTRIBUTE_BUTTON_TINT]
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
open class CompoundButtonSkinExecutor<T : CompoundButton> : TextViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            CompoundButtonAttribute.ATTRIBUTE_BUTTON -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    applyDrawable(view, ColorStateListDrawable(colorStateList), attrName)
                } else {
                    applyColor(view, colorStateList.defaultColor, attrName)
                }
            }

            CompoundButtonAttribute.ATTRIBUTE_BUTTON_TINT -> {
                CompoundButtonCompat.setButtonTintList(view, colorStateList)
            }
        }
    }

    override fun applyColor(view: T, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            CompoundButtonAttribute.ATTRIBUTE_BUTTON -> applyDrawable(view, ColorDrawable(color), attrName)
            CompoundButtonAttribute.ATTRIBUTE_BUTTON_TINT -> applyColor(view, ColorStateList.valueOf(color), attrName)
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            CompoundButtonAttribute.ATTRIBUTE_BUTTON -> view.buttonDrawable = drawable
        }
    }
}
