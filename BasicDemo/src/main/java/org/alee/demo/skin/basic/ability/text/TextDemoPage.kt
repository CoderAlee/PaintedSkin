package org.alee.demo.skin.basic.ability.text

import android.os.Build
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.parser.DefaultExecutorBuilder
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble
import org.alee.demo.skin.basic.ability.util.append
import org.alee.demo.skin.basic.ability.util.bindView
import org.alee.demo.skin.basic.ability.util.colorResource
import org.alee.demo.skin.basic.ability.util.layout.addSkin
import org.alee.demo.skin.basic.ability.util.setLeftDrawable
import org.alee.demo.skin.basic.ability.util.spannableString

/**
 * 文本 换肤 Demo
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
class TextDemoPage : BasePage(), IThemeSkinAble {

    private val mSpannableView by bindView<TextView>(R.id.tv_spannable)

    private val mDrawableView by bindView<TextView>(R.id.tv_drawable)

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_text_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            findView<TextView>(R.id.tv_highlight).defaultFocusHighlightEnabled = true
        }
        setSpannableText()
        setDrawable()
    }

    override fun onThemeSkinSwitchRunOnUiThread() {
        // FIXME 动态设置的字体颜色，需要在主题发生变化后重新设置一次
        setSpannableText()
    }

    private fun setSpannableText() {
        mSpannableView.text = spannableString {
            append("通过", ForegroundColorSpan(R.color.text_primary.colorResource))
            append("Spannable", ForegroundColorSpan(R.color.text_secondary.colorResource))
            append("设置", ForegroundColorSpan(R.color.text_error.colorResource))
            append("Text", ForegroundColorSpan(R.color.text_prompt.colorResource))
            append("Color", ForegroundColorSpan(R.color.text_correct.colorResource))
        }
    }

    private fun setDrawable() {
        mDrawableView.setLeftDrawable(R.mipmap.icon_arrow_left, R.dimen.tp_12)
        mDrawableView.addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_LEFT, R.mipmap.icon_arrow_left) }
    }
}