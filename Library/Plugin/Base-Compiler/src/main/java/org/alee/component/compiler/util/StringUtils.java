package org.alee.component.compiler.util;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public final class StringUtils {

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str) || 0 >= str.length();
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(CharSequence str) {
        return null == str || "".equals(str.toString()) || 0 >= str.length();
    }
}
