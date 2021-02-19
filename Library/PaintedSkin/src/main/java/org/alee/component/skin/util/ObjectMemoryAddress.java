package org.alee.component.skin.util;

import androidx.annotation.NonNull;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: 对象内存地址获取工具
 *
 *********************************************************/
public final class ObjectMemoryAddress {
    /**
     * 获取对象的内存地址
     *
     * @param object 对象
     * @return 地址
     */
    public static int getAddress(@NonNull Object object) {
        return System.identityHashCode(object);
    }
}
