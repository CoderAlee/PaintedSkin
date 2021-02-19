package org.alee.component.compiler.util;

import org.alee.component.compiler.Constant;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public class LogUtil {

    /**
     * 日志工具
     */
    private Messager mMessager;
    private String mTag;

    public LogUtil(Messager messager) {
        mMessager = messager;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    /**
     * Print info log.
     */
    public void info(CharSequence info) {
        if (StringUtils.isNotEmpty(info)) {
            mMessager.printMessage(Diagnostic.Kind.NOTE, mTag + Constant.PREFIX_OF_LOGGER + info);
        }
    }

    public void error(CharSequence error) {
        if (StringUtils.isNotEmpty(error)) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, mTag + Constant.PREFIX_OF_LOGGER + "An exception is encountered, [" + error + "]");
            throw new RuntimeException(error.toString());
        }
    }

    public void error(Throwable error) {
        if (null != error) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, mTag + Constant.PREFIX_OF_LOGGER + "An exception is encountered, [" + error.getMessage() + "]" + "\n" + formatStackTrace(error.getStackTrace()));
            throw new RuntimeException(error.getLocalizedMessage());
        }
    }

    private String formatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTrace) {
            sb.append("    at ").append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void warning(CharSequence warning) {
        if (StringUtils.isNotEmpty(warning)) {
            mMessager.printMessage(Diagnostic.Kind.WARNING, mTag + Constant.PREFIX_OF_LOGGER + warning);
        }
    }
}
