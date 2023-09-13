package org.alee.component.skin.exception

import org.alee.component.skin.util.INotProguard

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal class StrictModeException : RuntimeException, INotProguard {
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
