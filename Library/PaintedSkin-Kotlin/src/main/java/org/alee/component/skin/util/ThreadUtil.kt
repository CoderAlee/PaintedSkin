package org.alee.component.skin.util

import android.os.Handler
import android.os.Looper

/**
 * Android UI 线程Handler
 */
internal val MAIN_THREAD_HANDLER = Handler(Looper.getMainLooper())

/**
 * 判断当前线程是否是UI线程
 */
internal inline val isOnMainThread: Boolean
    get() = Looper.getMainLooper() == Looper.myLooper()

/**
 * 将任务抛至UI线程执行
 * @param block Function0<Unit>
 */
internal fun postOnMainThread(block: () -> Unit) {
    MAIN_THREAD_HANDLER.post(block)
}

/**
 * 保证代码块一定在UI线程被执行
 * @param block Function0<Unit>
 */
internal fun runOnMainThread(block: () -> Unit) {
    if (isOnMainThread) {
        block()
        return
    }
    postOnMainThread(block)
}
