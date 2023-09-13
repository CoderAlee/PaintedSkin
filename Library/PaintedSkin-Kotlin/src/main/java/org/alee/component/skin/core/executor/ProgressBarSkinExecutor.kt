package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.ProgressBar
import org.alee.component.skin.constant.DefaultSkinAttribute.ProgressBarAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [ProgressBar]独有属性换肤执行器
 *
 * <p> 所有[ProgressBar]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [ProgressBarAttribute.ATTRIBUTE_PROGRESS_DRAWABLE]
 * <p> [ProgressBarAttribute.ATTRIBUTE_PROGRESS_TINT]
 * <p> [ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_DRAWABLE]
 * <p> [ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_TINT]
 * <p> [ProgressBarAttribute.ATTRIBUTE_PROGRESS_BACKGROUND_TINT]
 * <p> [ProgressBarAttribute.ATTRIBUTE_SECONDARY_PROGRESS_TINT]
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
open class ProgressBarSkinExecutor<T : ProgressBar> : ViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            ProgressBarAttribute.ATTRIBUTE_PROGRESS_DRAWABLE,
            ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_DRAWABLE,
            -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    applyDrawable(view, ColorStateListDrawable(colorStateList), attrName)
                } else {
                    applyColor(view, colorStateList.defaultColor, attrName)
                }
            }

            ProgressBarAttribute.ATTRIBUTE_PROGRESS_TINT -> {
                if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                    view.progressTintList = colorStateList
                }
            }

            ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_TINT -> {
                if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                    view.indeterminateTintList = colorStateList
                }
            }

            ProgressBarAttribute.ATTRIBUTE_PROGRESS_BACKGROUND_TINT -> {
                if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                    view.progressBackgroundTintList = colorStateList
                }
            }

            ProgressBarAttribute.ATTRIBUTE_SECONDARY_PROGRESS_TINT -> {
                if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                    view.secondaryProgressTintList = colorStateList
                }
            }
        }
    }

    override fun applyColor(view: T, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            ProgressBarAttribute.ATTRIBUTE_PROGRESS_DRAWABLE,
            ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_DRAWABLE,
            -> {
                applyDrawable(view, ColorDrawable(color), attrName)
            }

            ProgressBarAttribute.ATTRIBUTE_PROGRESS_TINT,
            ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_TINT,
            ProgressBarAttribute.ATTRIBUTE_PROGRESS_BACKGROUND_TINT,
            ProgressBarAttribute.ATTRIBUTE_SECONDARY_PROGRESS_TINT,
            -> {
                applyColor(view, ColorStateList.valueOf(color), attrName)
            }
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            ProgressBarAttribute.ATTRIBUTE_PROGRESS_DRAWABLE -> {
                view.progressDrawable = drawable
            }

            ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_DRAWABLE -> {
                view.indeterminateDrawable = drawable
            }
        }
    }
}
