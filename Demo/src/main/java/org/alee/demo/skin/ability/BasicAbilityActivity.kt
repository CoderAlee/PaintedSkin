package org.alee.demo.skin.ability

import android.os.Bundle
import org.alee.demo.skin.R
import org.alee.demo.skin.ability.basic.activity.ScopedActivity

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
class BasicAbilityActivity : ScopedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_ability_demo)
    }
}