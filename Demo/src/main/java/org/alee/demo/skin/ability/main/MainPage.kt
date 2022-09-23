package org.alee.demo.skin.ability.main

import android.view.View
import org.alee.demo.skin.R
import org.alee.demo.skin.ability.basic.fragment.BasePage
import org.alee.demo.skin.ability.util.bindView

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal class MainPage : BasePage() {

    private val mTextDemo by bindView<View>(R.id.btn_text_view_demo)

    private val mImageDemo by bindView<View>(R.id.btn_image_view_demo)

    private val mListDemo by bindView<View>(R.id.btn_list_view_demo)

    private val mTintDemo by bindView<View>(R.id.btn_tint_demo)

    private val mDynamicDemo by bindView<View>(R.id.btn_dynamic_view_demo)

    private val mThirdPartDemo by bindView<View>(R.id.btn_third_part_list_view_demo)

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_main
}