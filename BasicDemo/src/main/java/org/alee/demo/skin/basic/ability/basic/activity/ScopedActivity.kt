package org.alee.demo.skin.basic.ability.basic.activity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal abstract class ScopedActivity : SkinAbleActivity(), CoroutineScope by MainScope() {

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}