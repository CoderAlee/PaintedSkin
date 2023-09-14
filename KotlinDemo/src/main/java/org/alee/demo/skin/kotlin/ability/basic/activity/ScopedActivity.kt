package org.alee.demo.skin.kotlin.ability.basic.activity

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 支持协程的Activity
 *
 * <p> 如果添加了 androidx.lifecycle:lifecycle-runtime-kt:xxx;可以通过[LifecycleOwner]的扩展属性lifecycleScope[LifecycleCoroutineScope] 启动协程
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal abstract class ScopedActivity : SkinAbleActivity(), CoroutineScope by MainScope() {
    /**
     * 页面销毁时取消协程
     */
    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}