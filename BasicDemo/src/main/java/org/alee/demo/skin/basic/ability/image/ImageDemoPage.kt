package org.alee.demo.skin.basic.ability.image

import android.os.Bundle
import android.view.View
import org.alee.component.skin.pack.IThemeSkinPack.SkinPackType
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.util.bindView
import org.alee.demo.skin.basic.ability.util.drawableResource

/**
 * 各类Icon、背景图片、.9 换肤Demo
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/14
 *
 */
class ImageDemoPage : BasePage() {

    private val mBusinessBtn by bindView<View>(R.id.btn_business)

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_image_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        updateBackgroundByBusiness()
    }

    override fun onThemeSkinSwitchRunOnUiThread() {
        updateBackgroundByBusiness()
    }


    private fun updateBackgroundByBusiness() {
        // FixMe  模拟根据业务抉择不同的资源
        mBusinessBtn.background = when (ThemeSkinService.getInstance().currentThemeSkinPack.skinPackType) {
            SkinPackType.TYPE_DEFAULT -> R.drawable.selector_bg_big_ghost_button
            else -> R.drawable.selector_bg_primary_button
        }.drawableResource
    }
}