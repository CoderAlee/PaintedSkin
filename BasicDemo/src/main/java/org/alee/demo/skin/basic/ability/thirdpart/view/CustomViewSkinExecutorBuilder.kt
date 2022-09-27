package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.executor.ISkinExecutor
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.widget.CustomView

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CustomViewSkinExecutorBuilder : IThemeSkinExecutorBuilder {

    internal companion object {
        /**
         * 点颜色
         */
        internal const val ATTRIBUTE_POINT_COLOR = "point_color"

        /**
         * 圆圈颜色
         */
        internal const val ATTRIBUTE_CIRCLE_COLOR = "circle_color"

        /**
         * 矩形颜色
         */
        internal const val ATTRIBUTE_RECTANGLE_COLOR = "rectangle_color"

        /**
         * icon 图片
         */
        internal const val ATTRIBUTE_ICON = "icon"

        /**
         * 文本颜色
         */
        internal const val ATTRIBUTE_TEXT_COLOR = "textColor"

        private val SUPPORT_ATTR: Map<Int, String> = HashMap<Int, String>().apply {
            put(R.styleable.CustomView_icon, ATTRIBUTE_ICON)
            put(R.styleable.CustomView_rectangle_color, ATTRIBUTE_RECTANGLE_COLOR)
            put(R.styleable.CustomView_circle_color, ATTRIBUTE_CIRCLE_COLOR)
            put(R.styleable.CustomView_point_color, ATTRIBUTE_POINT_COLOR)
            put(R.styleable.CustomView_android_textColor, ATTRIBUTE_TEXT_COLOR)
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
    override fun parse(context: Context, attributeSet: AttributeSet): MutableSet<SkinElement>? {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomView)
        val elementSet: MutableSet<SkinElement> = HashSet()
        try {
            for (key in SUPPORT_ATTR.keys) {
                // FIXME 此处的try catch 是为了避免由于一个属性解析失败导致所有属性都无法换肤
                try {
                    if (typedArray.hasValue(key)) {
                        SUPPORT_ATTR[key]?.run {
                            // FIXME 此处代码与addEnabledThemeSkinView 函数一样，需要提供待换肤的属性名称与所使用的资源id给框架
                            elementSet.add(SkinElement(this, typedArray.getResourceId(key, -1)))
                        }
                    }
                } catch (ignored: Throwable) {
                }
            }
        } catch (ignored: Throwable) {
        } finally {
            typedArray.recycle()
        }
        return elementSet
    }

    /**
     * 需要换肤执行器
     *
     * @param view    需要换肤的View
     * @param element 需要执行的元素
     *
     * @return [ISkinExecutor]
     */
    override fun requireSkinExecutor(view: View, element: SkinElement): ISkinExecutor {
        return CustomViewSkinExecutor(element)
    }

    /**
     * 是否支持属性
     *
     * @param view     View
     * @param attrName 属性名称
     *
     * @return true: 支持
     */
    override fun isSupportAttr(view: View, attrName: String): Boolean {
        // FIXME 避免不同View 相同的自定义属性导致处理异常
        if (view !is CustomView) {
            return false
        }
        return SUPPORT_ATTR.containsValue(attrName)
    }
}