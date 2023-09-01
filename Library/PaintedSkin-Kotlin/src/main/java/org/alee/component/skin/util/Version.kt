package org.alee.component.skin.util

/**
 * 内部使用 用于标记属性或函数的版本信息
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
internal annotation class Version(
    /**
     * 函数或属性初次出现的版本
     */
    val join: String,
    /**
     * 当前版本
     */
    val now: String = "",
    /**
     * 函数或属性发生变更的原因
     */
    val description: String = "",

)
