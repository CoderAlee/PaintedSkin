package org.alee.component.skin.template

import androidx.annotation.AnyThread
import org.alee.component.skin.util.Version

/**
 * 日志记录器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
interface ILogger {
    companion object {
        const val TAG = "ThemeSkin"
    }

    /**
     * 是否允许打印日志
     */
    @Version("4.0.0")
    val enablePrint: Boolean

    /**
     * 调试级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Version("4.0.0")
    @AnyThread
    fun debug(tag: String, message: String)

    /**
     * 消息级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Version("4.0.0")
    @AnyThread
    fun info(tag: String, message: String)

    /**
     * 错误级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Version("4.0.0")
    @AnyThread
    fun error(tag: String, message: String)

    /**
     * 错误级别日志输出
     * @param tag String Tag
     * @param message String 日志内容
     * @param throwable Throwable 异常
     */
    @Version("4.0.0")
    @AnyThread
    fun error(tag: String, message: String, throwable: Throwable)
}
