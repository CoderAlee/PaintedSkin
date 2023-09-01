package org.alee.component.skin.util

import android.util.Log
import org.alee.component.skin.template.ILogger

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal object InnerLogger : ILogger {
    /**
     * 是否允许打印日志
     */
    override val enablePrint: Boolean
        get() = true

    /**
     * 调试级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    /**
     * 消息级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    override fun info(tag: String, message: String) {
        Log.i(tag, message)
    }

    /**
     * 错误级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    override fun error(tag: String, message: String) {
        Log.e(tag, message)
    }

    /**
     * 错误级别日志输出
     * @param tag String Tag
     * @param message String 日志内容
     * @param throwable Throwable 异常
     */
    override fun error(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }
}
