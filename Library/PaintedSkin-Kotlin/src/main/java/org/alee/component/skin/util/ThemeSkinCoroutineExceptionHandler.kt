package org.alee.component.skin.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import org.alee.component.skin.util.ext.logE
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Key

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
class ThemeSkinCoroutineExceptionHandler(private val block: (() -> Unit)? = null) : CoroutineExceptionHandler {
    /**
     * A key of this coroutine context element.
     */
    override val key: Key<*>
        get() = CoroutineExceptionHandler.Key

    /**
     * Handles uncaught [exception] in the given [context]. It is invoked
     * if coroutine has an uncaught exception.
     */
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        block?.invoke()
        "An error occurred in the ${context[CoroutineName.Key]} ".logE(throwable = exception)
    }
}
