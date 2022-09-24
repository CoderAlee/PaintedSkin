package org.alee.demo.skin.basic.ability.dynamic

import android.view.View
import android.view.ViewGroup
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.util.bindView
import org.alee.demo.skin.basic.ability.util.layout.ImageView
import org.alee.demo.skin.basic.ability.util.layout.TextView
import org.alee.demo.skin.basic.ability.util.layout.background_color
import org.alee.demo.skin.basic.ability.util.layout.background_drawable
import org.alee.demo.skin.basic.ability.util.layout.gravity_center
import org.alee.demo.skin.basic.ability.util.layout.layout_height
import org.alee.demo.skin.basic.ability.util.layout.layout_width
import org.alee.demo.skin.basic.ability.util.layout.margin_top
import org.alee.demo.skin.basic.ability.util.layout.padding
import org.alee.demo.skin.basic.ability.util.layout.padding_horizontal
import org.alee.demo.skin.basic.ability.util.layout.padding_vertical
import org.alee.demo.skin.basic.ability.util.layout.src
import org.alee.demo.skin.basic.ability.util.layout.text_color
import org.alee.demo.skin.basic.ability.util.layout.wrap_content
import org.alee.demo.skin.basic.ability.util.stringResource

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/24
 *
 */
class DynamicDemoPage : BasePage() {

    private val mDynamicViewGroup by bindView<ViewGroup>(R.id.lly_dynamic_root)

    private var mIndex = 0

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_dynamic_page

    override fun onBindViewListener() {
        findView<View>(R.id.btn_add_view).setOnClickListener {
            appendView()
        }
    }

    private fun appendView() {
        mIndex++
        if (0 == mIndex % 2) {
            mDynamicViewGroup.TextView {
                layout_width = wrap_content
                layout_height = wrap_content
                text_color = R.color.white_800
                background_drawable = R.drawable.selector_bg_primary_button
                padding_horizontal = R.dimen.tp_12
                padding_vertical = R.dimen.tp_12
                gravity = gravity_center
                margin_top = R.dimen.tp_12
                text = R.string.dynamic_add_view_content.stringResource(mIndex)
            }
            return
        }
        mDynamicViewGroup.ImageView {
            layout_width = R.dimen.tp_36
            layout_height = R.dimen.tp_36
            margin_top = R.dimen.tp_12
            padding = R.dimen.tp_6
            background_color = R.color.secondary_400
            src = R.mipmap.outline_view_in_ar
        }
    }
}