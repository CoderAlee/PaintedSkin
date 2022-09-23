package org.alee.demo.skin.basic.ability.main

import android.view.View
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.util.go

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal class MainPage : BasePage(), View.OnClickListener {

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_main


    override fun onBindViewListener() {
        findView<View>(R.id.btn_text_view_demo).setOnClickListener(this)
        findView<View>(R.id.btn_image_view_demo).setOnClickListener(this)
        findView<View>(R.id.btn_list_view_demo).setOnClickListener(this)
        findView<View>(R.id.btn_tint_demo).setOnClickListener(this)
        findView<View>(R.id.btn_dynamic_view_demo).setOnClickListener(this)
        findView<View>(R.id.btn_third_part_list_view_demo).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_text_view_demo -> go(R.id.toTextDemoPage)
            R.id.btn_image_view_demo -> go(R.id.toImageDemoPage)
        }
    }
}