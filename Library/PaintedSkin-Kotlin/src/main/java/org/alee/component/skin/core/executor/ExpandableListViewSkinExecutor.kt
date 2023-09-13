package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.widget.ExpandableListView
import org.alee.component.skin.constant.DefaultSkinAttribute.ExpandableListViewAttribute
import org.alee.component.skin.template.ISkinExecutor

/**
 * [ExpandableListView] 属性换肤执行器
 *
 * <p> 所有[ExpandableListView]及其子类View的[ISkinExecutor]都应该继承于此类
 * <p> 实现了对以下属性的换肤：
 * <p> [ExpandableListViewAttribute.ATTRIBUTE_CHILD_DIVIDER]
 * <p> [ExpandableListViewAttribute.ATTRIBUTE_CHILD_INDICATOR]
 * <p> [ExpandableListViewAttribute.ATTRIBUTE_GROUP_INDICATOR]
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
class ExpandableListViewSkinExecutor<T : ExpandableListView> : ListViewSkinExecutor<T>() {

    override fun applyColor(view: T, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            ExpandableListViewAttribute.ATTRIBUTE_CHILD_DIVIDER,
            ExpandableListViewAttribute.ATTRIBUTE_CHILD_INDICATOR,
            ExpandableListViewAttribute.ATTRIBUTE_GROUP_INDICATOR,
            -> {
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
            ExpandableListViewAttribute.ATTRIBUTE_CHILD_DIVIDER,
            ExpandableListViewAttribute.ATTRIBUTE_CHILD_INDICATOR,
            ExpandableListViewAttribute.ATTRIBUTE_GROUP_INDICATOR,
            -> {
                applyDrawable(view, ColorDrawable(color), attrName)
            }
        }
    }

    override fun applyDrawable(view: T, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            ExpandableListViewAttribute.ATTRIBUTE_CHILD_DIVIDER -> {
                view.setChildDivider(drawable)
            }

            ExpandableListViewAttribute.ATTRIBUTE_CHILD_INDICATOR -> {
                view.setChildIndicator(drawable)
            }

            ExpandableListViewAttribute.ATTRIBUTE_GROUP_INDICATOR -> {
                view.setGroupIndicator(drawable)
            }
        }
    }
}
