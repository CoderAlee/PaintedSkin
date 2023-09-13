package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import androidx.annotation.CallSuper
import androidx.core.view.ViewCompat
import org.alee.component.skin.constant.DefaultSkinAttribute.ViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [View] 属性换肤执行器
 *
 * <p> 所有[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [ViewAttribute.ATTRIBUTE_BACKGROUND]
 * <p> [ViewAttribute.ATTRIBUTE_FOREGROUND]
 * <p> [ViewAttribute.ATTRIBUTE_BKG_TINT]
 * <p> [ViewAttribute.ATTRIBUTE_FRG_TINT]
 * <p> [ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL]
 * <p> [ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL]
 * <p> [ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL]
 * <p> [ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL]
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
open class ViewSkinExecutor<T : View> : BaseSkinExecutor<T>() {
    /**
     * 换肤 - ColorStateList
     * @param view T extends View 需要换肤的View
     * @param colorStateList ColorStateList 颜色状态集合
     * @param attrName String 属性名称 例 textColor
     * @throws Throwable 换肤时出现的异常
     */
    @CallSuper
    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        when (attrName) {
            ViewAttribute.ATTRIBUTE_BACKGROUND,
            ViewAttribute.ATTRIBUTE_FOREGROUND,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL,
            -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    applyDrawable(view, ColorStateListDrawable(colorStateList), attrName)
                } else {
                    applyColor(view, colorStateList.defaultColor, attrName)
                }
            }

            ViewAttribute.ATTRIBUTE_BKG_TINT -> {
                ViewCompat.setBackgroundTintList(view, colorStateList)
            }

            ViewAttribute.ATTRIBUTE_FRG_TINT -> {
                if (VERSION.SDK_INT >= VERSION_CODES.M) {
                    view.foregroundTintList = colorStateList
                }
            }
        }
    }

    /**
     * 换肤 - Color
     * @param view T extends View 需要换肤的View
     * @param color Int 色值
     * @param attrName String 属性名称 例 textColor
     * @throws Throwable 换肤时出现的异常
     */
    @CallSuper
    override fun applyColor(view: T, color: Int, attrName: String) {
        when (attrName) {
            ViewAttribute.ATTRIBUTE_BACKGROUND -> {
                view.setBackgroundColor(color)
            }

            ViewAttribute.ATTRIBUTE_FOREGROUND,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL,
            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL,
            -> {
                applyDrawable(view, ColorDrawable(color), attrName)
            }

            ViewAttribute.ATTRIBUTE_BKG_TINT,
            ViewAttribute.ATTRIBUTE_FRG_TINT,
            -> {
                applyColor(view, ColorStateList.valueOf(color), attrName)
            }
        }
    }

    /**
     * 换肤 - Drawable
     * @param view T extends View 需要换肤的View
     * @param drawable Drawable 图片
     * @param attrName String 属性名称 例 background
     * @throws Throwable 换肤时出现的异常
     */
    @CallSuper
    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        when (attrName) {
            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    view.horizontalScrollbarThumbDrawable = drawable
                }
            }

            ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    view.verticalScrollbarThumbDrawable = drawable
                }
            }

            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    view.horizontalScrollbarTrackDrawable = drawable
                }
            }

            ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    view.verticalScrollbarTrackDrawable = drawable
                }
            }

            ViewAttribute.ATTRIBUTE_BACKGROUND,
            -> {
                ViewCompat.setBackground(view, drawable)
            }

            ViewAttribute.ATTRIBUTE_FOREGROUND -> {
                if (VERSION.SDK_INT >= VERSION_CODES.M) {
                    view.foreground = drawable
                }
            }
        }
    }
}
