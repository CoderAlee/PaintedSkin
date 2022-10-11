package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.executor.ISkinExecutor
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.executor.ViewSkinExecutor
import org.alee.component.skin.factory2.IExpandedFactory2
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.widget.CustomView

/**
 * 自定义View兼容
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/10/11
 *
 */

internal fun customViewCompatible() {
    //FIXME 第三方View、自定义View 需要添加适配器。否则换肤框架无法创建View实例
    ThemeSkinService.getInstance().createViewInterceptor.add(CustomViewFactory())
    // FIXME 第三方View、自定义View 的自定义属性如果需要换肤，需要由使用者通过实现IThemeSkinExecutorBuilder 为框架提供自己的换肤执行器
    ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(CustomViewSkinExecutorBuilder())
}


/**
 * 自定义View孵化工厂
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
private class CustomViewFactory : IExpandedFactory2 {

    private companion object {
        /**
         * [CustomView] 类名
         */
        private val CLASS_NAME = CustomView::class.java.name
    }


    /**
     * 创建View
     *
     * @param originalView 上一个IExpandedFactory生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      [Context]
     * @param attrs        [AttributeSet]
     * @return 生成的View
     */
    override fun onCreateView(
        originalView: View?, parent: View?, name: String,
        context: Context, attrs: AttributeSet
    ): View? {
        return if (CLASS_NAME == name) CustomView(context, attrs) else originalView
    }
}

/**
 * 自定义View 自定义属性换肤执行器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
private class CustomViewSkinExecutor(fullElement: SkinElement) : ViewSkinExecutor<CustomView>(fullElement) {

    override fun applyColor(view: CustomView, colorStateList: ColorStateList, attrName: String) {
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_POINT_COLOR -> view.pointColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_CIRCLE_COLOR -> view.circleBorderColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_RECTANGLE_COLOR -> view.rectangleFillColor = colorStateList.defaultColor
            CustomViewSkinExecutorBuilder.ATTRIBUTE_TEXT_COLOR -> view.textColor = colorStateList
        }
    }

    override fun applyColor(view: CustomView, color: Int, attrName: String) {
        super.applyColor(view, color, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_POINT_COLOR -> view.pointColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_CIRCLE_COLOR -> view.circleBorderColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_RECTANGLE_COLOR -> view.rectangleFillColor = color
            CustomViewSkinExecutorBuilder.ATTRIBUTE_TEXT_COLOR -> view.textColor = ColorStateList.valueOf(color)
        }
    }

    override fun applyDrawable(view: CustomView, drawable: Drawable, attrName: String) {
        super.applyDrawable(view, drawable, attrName)
        when (attrName) {
            CustomViewSkinExecutorBuilder.ATTRIBUTE_ICON -> view.icon = drawable
        }
    }

}


/**
 * 自定义View换肤执行器构建者
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