package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import de.hdodenhof.circleimageview.CircleImageView
import org.alee.component.skin.executor.ISkinExecutor
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.executor.ViewSkinExecutor
import org.alee.component.skin.factory2.IExpandedFactory2
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R

/**
 * 第三方View兼容器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/10/11
 *
 */

internal fun circleImageViewCompatible() {
    //FIXME 第三方View、自定义View 需要添加适配器。否则换肤框架无法创建View实例
    ThemeSkinService.getInstance().createViewInterceptor.add(CircleImageViewFactory())
    // FIXME 第三方View、自定义View 的自定义属性如果需要换肤，需要由使用者通过实现IThemeSkinExecutorBuilder 为框架提供自己的换肤执行器
    ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(CircleImageViewSkinExecutorBuilder())
}


/**
 * 自定义View构造工厂
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
private class CircleImageViewFactory : IExpandedFactory2 {

    private companion object {
        /**
         * [CircleImageView] 类名
         */
        private val CLASS_NAME = CircleImageView::class.java.name
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
        originalView: View?, parent: View?, name: String, context: Context, attrs: AttributeSet
    ): View? {
        return if (CLASS_NAME == name) CircleImageView(context, attrs) else originalView
    }
}


/**
 * 第三方View 换肤执行器
 *
 * <p> 需要注意 继承关系。所有执行器都应最少继承于ViewSkinExecutor。有ViewSkinExecutor 来提供background等属性的换肤能力
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
private class CircleImageViewSkinExecutor(fullElement: SkinElement) : ViewSkinExecutor<CircleImageView>(fullElement) {

    override fun applyColor(view: CircleImageView, color: Int, attrName: String) {
        // FIXME 需要注意调用super，来保证父类所支持执行的属性能够官服成功。
        super.applyColor(view, color, attrName)
        when (attrName) {
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BORDER_COLOR -> view.borderColor = color
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BACKGROUND_COLOR -> view.circleBackgroundColor = color
        }

    }

    // FIXME 如果不想支持 ColorStateList 可以不实现此函数
    override fun applyColor(view: CircleImageView, colorStateList: ColorStateList, attrName: String) {
        // FIXME 需要注意调用super，来保证父类所支持执行的属性能够官服成功。
        super.applyColor(view, colorStateList, attrName)
        when (attrName) {
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BORDER_COLOR -> view.borderColor = colorStateList.defaultColor
            CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BACKGROUND_COLOR ->
                view.circleBackgroundColor = colorStateList.defaultColor
        }
    }
}


/**
 * 自定义View换肤属性执行器构造器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CircleImageViewSkinExecutorBuilder : IThemeSkinExecutorBuilder {

    internal companion object {
        /**
         * 外圈颜色
         */
        internal const val ATTRIBUTE_BORDER_COLOR = "civ_border_color"

        /**
         * 内圈背景色
         */
        internal const val ATTRIBUTE_BACKGROUND_COLOR = "civ_circle_background_color"

        private val SUPPORT_ATTR: Map<Int, String> = HashMap<Int, String>().apply {
            put(R.styleable.CircleImageView_civ_border_color, ATTRIBUTE_BORDER_COLOR)
            put(R.styleable.CircleImageView_civ_circle_background_color, ATTRIBUTE_BACKGROUND_COLOR)
        }
    }

    /**
     * 解析支持换肤的属性
     *
     * @param context      [Context]
     * @param attributeSet [AttributeSet]
     * @return [SkinElement]
     */
    override fun parse(context: Context, attributeSet: AttributeSet): Set<SkinElement> {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CircleImageView)
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
     * @return [ISkinExecutor]
     */
    override fun requireSkinExecutor(view: View, element: SkinElement): ISkinExecutor {
        // FIXME 在调用此函数前，框架会先调用isSupportAttr 函数来校验当前Builder是否支持此属性的处理.
        return CircleImageViewSkinExecutor(element)
    }

    /**
     * 是否支持属性
     *
     * @param view     View
     * @param attrName 属性名称
     * @return true: 支持
     */
    override fun isSupportAttr(view: View, attrName: String): Boolean {
        // FIXME 避免不同View 相同的自定义属性导致处理异常
        if (view !is CircleImageView) {
            return false
        }
        return SUPPORT_ATTR.containsValue(attrName)
    }
}
