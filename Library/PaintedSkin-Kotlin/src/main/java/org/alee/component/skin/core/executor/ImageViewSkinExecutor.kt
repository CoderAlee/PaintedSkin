package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import org.alee.component.skin.constant.DefaultSkinAttribute.ImageViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [ImageView] 属性换肤执行器
 *
 * <p> 所有[ImageView]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [ImageViewAttribute.ATTRIBUTE_SRC]
 * <p> [ImageViewAttribute.ATTRIBUTE_TINT]
 *
 * @author MingYu.Liu
 * created in 2023/9/5
 *
 */
open class ImageViewSkinExecutor<T : ImageView> : ViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            ImageViewAttribute.ATTRIBUTE_SRC -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    applyDrawable(view, ColorStateListDrawable(colorStateList), attrName)
                } else {
                    applyColor(view, colorStateList.defaultColor, attrName)
                }
            }

            ImageViewAttribute.ATTRIBUTE_TINT -> ImageViewCompat.setImageTintList(view, colorStateList)
        }
    }

    override fun applyColor(view: T, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            ImageViewAttribute.ATTRIBUTE_SRC -> applyDrawable(view, ColorDrawable(color), attrName)
            ImageViewAttribute.ATTRIBUTE_TINT -> applyColor(view, ColorStateList.valueOf(color), attrName)
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            ImageViewAttribute.ATTRIBUTE_SRC -> view.setImageDrawable(drawable)
        }
    }
}
