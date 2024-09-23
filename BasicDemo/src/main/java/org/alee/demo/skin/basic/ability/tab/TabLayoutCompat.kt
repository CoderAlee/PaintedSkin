package org.alee.demo.skin.basic.ability.tab

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import com.google.android.material.tabs.TabLayout
import org.alee.component.skin.executor.ISkinExecutor
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.executor.ViewSkinExecutor
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R

internal fun tabLayoutCompatible() {
    ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(TabLayoutSkinExecutorBuilder())
}

private class TabLayoutSkinExecutor(
    fullElement: SkinElement,
) : ViewSkinExecutor<TabLayout>(fullElement) {
    override fun applyColor(
        view: TabLayout,
        color: Int,
        attrName: String,
    ) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_INDICATOR_COLOR -> view.setSelectedTabIndicatorColor(color)
            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_SELECTED_TEXT_COLOR,
            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_TEXT_COLOR,
            -> applyColor(view, ColorStateList.valueOf(color), attrName)
        }
    }

    override fun applyColor(
        view: TabLayout,
        colorStateList: ColorStateList,
        attrName: String,
    ) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_INDICATOR_COLOR -> applyColor(view, colorStateList.defaultColor, attrName)
            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_SELECTED_TEXT_COLOR -> {
                // 从TabTextColors中获取原本设置的未选中的颜色
                val originalTabTextColor =
                    view.tabTextColors?.getColorForState(
                        intArrayOf(android.R.attr.state_empty),
                        colorStateList.defaultColor,
                    )
                // 这里需要将未选中颜色与要换肤的选中色一起设置
                view.setTabTextColors(originalTabTextColor ?: colorStateList.defaultColor, colorStateList.defaultColor)
            }

            TabLayoutSkinExecutorBuilder.ATTRIBUTE_TAB_TEXT_COLOR -> {
                // 同上
                val originalTabSelectedTextColor =
                    view.tabTextColors?.getColorForState(
                        intArrayOf(android.R.attr.state_selected),
                        colorStateList.defaultColor,
                    )
                view.setTabTextColors(colorStateList.defaultColor, originalTabSelectedTextColor ?: colorStateList.defaultColor)
            }
        }
    }
}

private class TabLayoutSkinExecutorBuilder : IThemeSkinExecutorBuilder {
    companion object {
        internal const val ATTRIBUTE_TAB_INDICATOR_COLOR = "tabIndicatorColor"
        internal const val ATTRIBUTE_TAB_SELECTED_TEXT_COLOR = "tabSelectedTextColor"
        internal const val ATTRIBUTE_TAB_TEXT_COLOR = "tabTextColor"

        private val SUPPORT_ATTR: Map<Int, String> =
            HashMap<Int, String>(3).apply {
                put(R.styleable.TabLayout_tabIndicatorColor, ATTRIBUTE_TAB_INDICATOR_COLOR)
                put(R.styleable.TabLayout_tabSelectedTextColor, ATTRIBUTE_TAB_SELECTED_TEXT_COLOR)
                put(R.styleable.TabLayout_tabTextColor, ATTRIBUTE_TAB_TEXT_COLOR)
            }
    }

    /**
     * 解析支持换肤的属性
     *
     * @param context      [Context]
     * @param attributeSet [AttributeSet]
     *
     * @return [SkinElement]
     */
    override fun parse(
        context: Context,
        attributeSet: AttributeSet,
    ): MutableSet<SkinElement> =
        context.obtainStyledAttributes(attributeSet, R.styleable.TabLayout).use { typedArray ->
            SUPPORT_ATTR
                .filter {
                    typedArray.hasValue(
                        it.key,
                    )
                }.map { SkinElement(it.value, typedArray.getResourceId(it.key, -1)) }
                .toMutableSet()
        }

    /**
     * 需要换肤执行器
     *
     * @param view    需要换肤的View
     * @param element 需要执行的元素
     *
     * @return [ISkinExecutor]
     */
    override fun requireSkinExecutor(
        view: View,
        element: SkinElement,
    ): ISkinExecutor = TabLayoutSkinExecutor(element)

    /**
     * 是否支持属性
     *
     * @param view     View
     * @param attrName 属性名称
     *
     * @return true: 支持
     */
    override fun isSupportAttr(
        view: View,
        attrName: String,
    ): Boolean {
        if (view is TabLayout) {
            return SUPPORT_ATTR.containsValue(attrName)
        }
        return false
    }
}
