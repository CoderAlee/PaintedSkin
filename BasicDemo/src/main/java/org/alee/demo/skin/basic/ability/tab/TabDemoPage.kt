package org.alee.demo.skin.basic.ability.tab

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2024/9/11
 *
 */
internal class TabDemoPage : BasePage() {
    companion object {
        init {
            tabLayoutCompatible()
        }
    }

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId(): Int = R.layout.page_tab_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        super.onBindViewValue(savedInstanceState)
        findView<TabLayout>(R.id.tab_signal).apply {
            addTab(newTab().setText("Tab1"))
            addTab(newTab().setText("Tab2"))
            addTab(newTab().setText("Tab3"))
        }
    }
}
