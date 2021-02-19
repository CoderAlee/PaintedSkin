package org.alee.component.compiler.util;

import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public final class MapUtils {

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
