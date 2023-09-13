package org.alee.component.skin.core.parser

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import org.alee.component.skin.R
import org.alee.component.skin.R.styleable
import org.alee.component.skin.constant.DefaultSkinAttribute
import org.alee.component.skin.core.executor.fetchSkinExecutor
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.ISkinExecutor
import org.alee.component.skin.template.ISkinExecutorBuilder

/**
 * 框架内默认的执行器构造器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
internal class DefaultExecutorBuilder : ISkinExecutorBuilder {

    private companion object {
        private val SUPPORT_ATTR: Map<Int, String> = HashMap<Int, String>().apply {
            // View
            put(R.styleable.BasicSupportAttr_android_background, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_BACKGROUND)
            put(R.styleable.BasicSupportAttr_android_foreground, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_FOREGROUND)
            put(R.styleable.BasicSupportAttr_android_backgroundTint, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_BKG_TINT)
            put(R.styleable.BasicSupportAttr_android_foregroundTint, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_FRG_TINT)
            put(R.styleable.BasicSupportAttr_android_scrollbarThumbHorizontal, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL)
            put(R.styleable.BasicSupportAttr_android_scrollbarThumbVertical, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL)
            put(R.styleable.BasicSupportAttr_android_scrollbarTrackHorizontal, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL)
            put(R.styleable.BasicSupportAttr_android_scrollbarTrackVertical, DefaultSkinAttribute.ViewAttribute.ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL)
            // TextView
            put(R.styleable.BasicSupportAttr_android_textColor, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_TEXT_COLOR)
            put(R.styleable.BasicSupportAttr_android_textColorHint, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HINT)
            put(R.styleable.BasicSupportAttr_android_textColorHighlight, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT)
            put(R.styleable.BasicSupportAttr_android_textColorLink, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_TEXT_COLOR_LINK)
            put(R.styleable.BasicSupportAttr_android_shadowColor, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_SHADOW_COLOR)
            put(R.styleable.BasicSupportAttr_android_drawableLeft, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_LEFT)
            put(R.styleable.BasicSupportAttr_android_drawableStart, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_START)
            put(R.styleable.BasicSupportAttr_android_drawableTop, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_TOP)
            put(R.styleable.BasicSupportAttr_android_drawableRight, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_RIGHT)
            put(R.styleable.BasicSupportAttr_android_drawableEnd, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_END)
            put(R.styleable.BasicSupportAttr_android_drawableBottom, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_BOTTOM)
            put(R.styleable.BasicSupportAttr_android_drawableTint, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT)
            put(R.styleable.BasicSupportAttr_drawableLeftCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_LEFT)
            put(R.styleable.BasicSupportAttr_drawableStartCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_START)
            put(R.styleable.BasicSupportAttr_drawableTopCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_TOP)
            put(R.styleable.BasicSupportAttr_drawableRightCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_RIGHT)
            put(R.styleable.BasicSupportAttr_drawableEndCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_END)
            put(R.styleable.BasicSupportAttr_drawableBottomCompat, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_BOTTOM)
            put(R.styleable.BasicSupportAttr_drawableTint, DefaultSkinAttribute.TextViewAttribute.ATTRIBUTE_DRAWABLE_TINT)
            // CompoundButton
            put(R.styleable.BasicSupportAttr_android_button, DefaultSkinAttribute.CompoundButtonAttribute.ATTRIBUTE_BUTTON)
            put(R.styleable.BasicSupportAttr_android_buttonTint, DefaultSkinAttribute.CompoundButtonAttribute.ATTRIBUTE_BUTTON_TINT)
            // ImageView
            put(R.styleable.BasicSupportAttr_android_src, DefaultSkinAttribute.ImageViewAttribute.ATTRIBUTE_SRC)
            put(R.styleable.BasicSupportAttr_android_tint, DefaultSkinAttribute.ImageViewAttribute.ATTRIBUTE_TINT)
            put(R.styleable.BasicSupportAttr_srcCompat, DefaultSkinAttribute.ImageViewAttribute.ATTRIBUTE_SRC)
            put(R.styleable.BasicSupportAttr_tint, DefaultSkinAttribute.ImageViewAttribute.ATTRIBUTE_TINT)
            // ProgressBar
            put(R.styleable.BasicSupportAttr_android_progressDrawable, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_PROGRESS_DRAWABLE)
            put(R.styleable.BasicSupportAttr_android_progressTint, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_PROGRESS_TINT)
            put(R.styleable.BasicSupportAttr_android_progressBackgroundTint, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_PROGRESS_BACKGROUND_TINT)
            put(R.styleable.BasicSupportAttr_android_indeterminateDrawable, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_DRAWABLE)
            put(R.styleable.BasicSupportAttr_android_indeterminateTint, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_INDETERMINATE_TINT)
            put(R.styleable.BasicSupportAttr_android_secondaryProgressTint, DefaultSkinAttribute.ProgressBarAttribute.ATTRIBUTE_SECONDARY_PROGRESS_TINT)
            // AbsListView
            put(R.styleable.BasicSupportAttr_android_cacheColorHint, DefaultSkinAttribute.AbsListViewAttribute.ATTRIBUTE_CACHE_COLOR_HINT)
            put(R.styleable.BasicSupportAttr_android_listSelector, DefaultSkinAttribute.AbsListViewAttribute.ATTRIBUTE_LIST_SELECTOR)
            // ListView
            put(R.styleable.BasicSupportAttr_android_divider, DefaultSkinAttribute.ListViewAttribute.ATTRIBUTE_LIST_VIEW_DIVIDER)
            // ExpandableListView
            put(R.styleable.BasicSupportAttr_android_childDivider, DefaultSkinAttribute.ExpandableListViewAttribute.ATTRIBUTE_CHILD_DIVIDER)
            put(R.styleable.BasicSupportAttr_android_groupIndicator, DefaultSkinAttribute.ExpandableListViewAttribute.ATTRIBUTE_GROUP_INDICATOR)
            put(R.styleable.BasicSupportAttr_android_childIndicator, DefaultSkinAttribute.ExpandableListViewAttribute.ATTRIBUTE_CHILD_INDICATOR)
        }
    }

    /**
     * 实现类需要判断是否可以构建对应属性的换肤执行器
     * @param view View 需要换肤的View
     * @param attrName String 需要换肤的属性名称
     * @return Boolean true: 可以构建 false: 不可以构建
     */
    override fun canBuildExecute(view: View, attrName: String) = SUPPORT_ATTR.containsValue(attrName)

    /**
     * 从AttributeSet中解析出需要换肤的属性
     * @param context Context [Context]
     * @param attributeSet AttributeSet [AttributeSet]
     * @return Set<SkinAttribute>? [SkinAttribute]
     */
    override fun parse(context: Context, attributeSet: AttributeSet): Set<SkinAttribute>? {
        return context.obtainStyledAttributes(attributeSet, styleable.BasicSupportAttr).use { typedArray ->
            SUPPORT_ATTR.filter { typedArray.hasValue(it.key) }
                .map { SkinAttribute(it.value, typedArray.getResourceId(it.key, -1)) }
                .toSet()
        }
    }

    /**
     * 根据需要换肤的属性与View构建换肤执行器
     * @param view View 需要换肤的View
     * @param attribute SkinAttribute 需要换肤的属性
     * @return ISkinExecutor [ISkinExecutor]
     */
    override fun buildSkinExecutor(view: View, attribute: SkinAttribute) = view.fetchSkinExecutor()
}
