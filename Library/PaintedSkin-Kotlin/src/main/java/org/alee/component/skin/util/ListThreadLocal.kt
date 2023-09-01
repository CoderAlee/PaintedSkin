package org.alee.component.skin.util

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal class ListThreadLocal<T> : ThreadLocal<ThrowawayList<T>>() {

    override fun initialValue(): ThrowawayList<T> {
        return ThrowawayList()
    }

    fun gc() {
        get()?.run {
            clear()
        }
        remove()
    }
}
