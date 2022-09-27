package org.alee.demo.skin.basic.ability

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.basic.activity.ScopedActivity
import org.alee.demo.skin.basic.ability.util.loadBoolean
import org.alee.demo.skin.basic.ability.util.setVector

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal class BasicAbilityActivity : ScopedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_ability_demo)
        if (USE_SPRING_FESTIVAL_SKIN.loadBoolean(false)) {
            SkinOptionFactory.MODE_DAY.switchSkin()
        }
        findViewById<View>(R.id.btn_switch_skin).apply {
            isSelected = false
            setOnClickListener(this@BasicAbilityActivity::onSwitchBtnClicked)
        }
    }

    private fun onSwitchBtnClicked(view: View) {
        view.isSelected = view.isSelected.not()
        val imageView = view as? ImageView
        imageView?.apply {
            setVector(if (isSelected) R.drawable.ic_day else R.drawable.ic_night, R.color.primary_main)
        }
        (if (view.isSelected) SkinOptionFactory.MODE_NIGHT else SkinOptionFactory.MODE_DAY).switchSkin()

    }

    private fun Int.switchSkin() {
        ThemeSkinService.getInstance().switchThemeSkin(this)
    }

}

