package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.ListView
import org.alee.component.skin.constant.DefaultSkinAttribute.AbsListViewAttribute
import org.alee.component.skin.constant.DefaultSkinAttribute.ListViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [ListView] 属性换肤执行器
 *
 * <p> 所有[ListView]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [AbsListViewAttribute.ATTRIBUTE_CACHE_COLOR_HINT]
 * <p> [AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR]
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
open class ListViewSkinExecutor<T : ListView> : AbsListViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            ListViewAttribute.ATTRIBUTE_LIST_VIEW_DIVIDER -> {
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
            ListViewAttribute.ATTRIBUTE_LIST_VIEW_DIVIDER -> {
                applyDrawable(view, ColorDrawable(color), attrName)
            }
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            ListViewAttribute.ATTRIBUTE_LIST_VIEW_DIVIDER -> {
                val dvh = view.dividerHeight
                view.divider = drawable
                view.dividerHeight = dvh
            }
        }
    }
}
