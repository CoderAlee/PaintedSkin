package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import de.hdodenhof.circleimageview.CircleImageView
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.util.dimenResource
import org.alee.demo.skin.basic.ability.util.layout.addSkin
import org.alee.demo.skin.basic.ability.util.layout.layout_height
import org.alee.demo.skin.basic.ability.util.layout.layout_width
import org.alee.demo.skin.basic.ability.util.layout.padding
import org.alee.demo.skin.basic.ability.util.layout.src
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
class ViewAndAttrDemoPage : BasePage() {

    private companion object {
        init {
            //FIXME 第三方View、自定义View 需要添加适配器。否则换肤框架无法创建View实例
            ThemeSkinService.getInstance().createViewInterceptor.add(CircleImageViewFactory())
            ThemeSkinService.getInstance().createViewInterceptor.add(CustomViewFactory())
            // FIXME 第三方View、自定义View 的自定义属性如果需要换肤，需要由使用者通过实现IThemeSkinExecutorBuilder 为框架提供自己的换肤执行器
            ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(CircleImageViewSkinExecutorBuilder())
            ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(CustomViewSkinExecutorBuilder())
        }
    }

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_third_part_view_and_attr_demo


    override fun onBindViewValue(savedInstanceState: Bundle?) {
        super.onBindViewValue(savedInstanceState)
        findView<ViewGroup>(R.id.fl_root).run {
            CircleImageView {
                layout_width = R.dimen.tp_96
                layout_height = R.dimen.tp_96
                padding = R.dimen.tp_6
                border_color = R.color.text_error
                // FIXME 由于填充的图片太大了，内圈背景颜色看不到
                circle_background_color = R.color.text_prompt
                border_width = R.dimen.tp_2
                src = R.mipmap.bdd
            }
        }
        findView<CustomView>(R.id.custom_view).apply {
            addSkin { SkinElement(CustomViewSkinExecutorBuilder.ATTRIBUTE_CIRCLE_COLOR, R.color.secondary_main) }
        }
    }

    //FIXME DSL
    private inline fun ViewGroup.CircleImageView(block: CircleImageView.() -> Unit): CircleImageView {
        return context.CircleImageView(block).also { addView(it) }
    }

    //FIXME DSL
    private inline fun Context.CircleImageView(block: CircleImageView.() -> Unit): CircleImageView {
        return CircleImageView(this).apply(block)
    }

    //FIXME DSL 圆圈颜色
    private inline var CircleImageView.border_color: Int
        get() = 0
        set(value) {
            // FIXME addEnabledThemeSkinView 后会立即对属性进行一次赋值
            addSkin { SkinElement(CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BORDER_COLOR, value) }
        }

    //FIXME DSL 内圈颜色
    private inline var CircleImageView.circle_background_color: Int
        get() = 0
        set(value) {
            addSkin { SkinElement(CircleImageViewSkinExecutorBuilder.ATTRIBUTE_BACKGROUND_COLOR, value) }
        }

    private inline var CircleImageView.border_width: Int
        get() = 0
        set(value) {
            borderWidth = value.dimenResource
        }
}
