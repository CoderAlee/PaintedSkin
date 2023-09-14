package org.alee.demo.skin.kotlin.ability

import android.os.Bundle
import org.alee.demo.skin.kotlin.ability.basic.activity.ScopedActivity

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
internal class DemoActivity : ScopedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
    }
}
