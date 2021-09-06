package org.alee.component.skin.util.template;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: 日志输出器
 *
 *********************************************************/
public interface ILogger {
    /**
     * 调试级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    void debug(String tag, String message);

    /**
     * 消息级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    void info(String tag, String message);

    /**
     * 错误级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    void error(String tag, String message);
}
