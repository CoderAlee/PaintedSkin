package org.alee.demo.skin.kotlin.ability.page

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.alee.component.skin.ThemeSkinService
import org.alee.demo.skin.kotlin.ability.R
import org.alee.demo.skin.kotlin.ability.basic.fragment.BasePage
import org.alee.demo.skin.kotlin.ability.util.logI

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
class MainPage : BasePage(), View.OnClickListener, CoroutineScope by MainScope() {
    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_main

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        super.onBindViewValue(savedInstanceState)
        ThemeSkinService.currentSkinPack.getMipmap(R.mipmap.bdd)
    }

    override fun onResume() {
        super.onResume()
        "MainPage Resume".logI()
        ThemeSkinService.dump()
    }

    override fun onPause() {
        super.onPause()
        ThemeSkinService.dump()
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}
