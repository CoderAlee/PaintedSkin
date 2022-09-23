package org.alee.demo.skin.ability.util

import org.alee.component.skin.util.ThreadUtils

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */

/**
 * 在UI线程进行懒加载
 * @param initializer Function0<T>
 * @return Lazy<T>
 */
fun <T> lazyInUI(initializer: () -> T): Lazy<T> {
    return if (ThreadUtils.isMainThread()) {
        lazy(LazyThreadSafetyMode.NONE, initializer)
    } else {
        lazy(LazyThreadSafetyMode.SYNCHRONIZED, initializer)
    }
}