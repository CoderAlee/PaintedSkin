package org.alee.demo.skin.basic.ability.enable

import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage

/**
 * Skin:Enable = false 属性的使用 Demo
 *
 * <p>  Config.getInstance().setSkinMode(Config.SkinMode.REPLACE_ALL); 为默认配置，
 *  既默认为所有View换肤，只有在xml中添加了Enable = false的View才不会参与换肤
 *
 * <p>  Config.getInstance().setSkinMode(Config.SkinMode.REPLACE_MARKED)时,只会对在xml中添加了Skin:Enable = true属性的View进行换肤
 * <p> Config.getInstance().setSkinMode(Config.SkinMode.DO_NOT_REPLACE)时，不会对XML中任何View进行换肤。需要使用者通过addEnabledThemeSkinView 方法添加支持换肤的View
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */
class SkinEnableDemoPage : BasePage() {

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_skin_enable_demo
}