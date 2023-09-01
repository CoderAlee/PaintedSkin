package org.alee.component.skin.util.ext

/**
 * 校验是否为有效的资源Id
 * @receiver Int 资源Id
 * @return Boolean true - 有效 false - 无效
 */
internal inline val Int.isValidResourcesId: Boolean
    get() {
        if (0 >= this) {
            return false
        }
        return Integer.toHexString(this).startsWith("1").not()
    }
