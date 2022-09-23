package org.alee.demo.skin.basic.ability.util

import android.util.SparseArray

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */

internal fun <E> SparseArray<E>.getOrPut(key: Int, block: (Int) -> E?): E? {
    val data = get(key)
    if (null != data) {
        return data
    }
    return block(key).also {
        put(key, it)
    }
}