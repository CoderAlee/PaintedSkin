package org.alee.component.skin.util;

import android.util.Log;

import org.alee.component.skin.util.template.ILogger;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public final class DefaultLogger implements ILogger {
    /**
     * 调试级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    /**
     * 消息级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Override
    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * 错误级别日志输出
     *
     * @param tag     Tag
     * @param message 日志内容
     */
    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }
}
