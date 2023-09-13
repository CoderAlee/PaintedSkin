package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.AbsListView
import org.alee.component.skin.constant.DefaultSkinAttribute.AbsListViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [AbsListView] 属性换肤执行器
 *
 * <p> 所有[AbsListView]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [AbsListViewAttribute.ATTRIBUTE_CACHE_COLOR_HINT]
 * <p> [AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR]
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
open class AbsListViewSkinExecutor<T : AbsListView> : ViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            AbsListViewAttribute.ATTRIBUTE_CACHE_COLOR_HINT -> {
                applyColor(view, colorStateList.defaultColor, attrName)
            }

            AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR -> {
                if (VERSION.SDK_INT >= VERSION_CODES.Q) {
                    applyDrawable(view, ColorStateListDrawable(colorStateList), attrName)
                } else {
                    applyColor(view, colorStateList.defaultColor, attrName)
                }
            }
        }
    }

    override fun applyColor(view: T, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            AbsListViewAttribute.ATTRIBUTE_CACHE_COLOR_HINT -> {
                view.cacheColorHint = color
            }

            AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR -> {
                applyDrawable(view, ColorDrawable(color), attrName)
            }
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR -> {
                view.selector = drawable
            }
        }
    }
}
