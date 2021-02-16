package org.alee.component.skin.util;

import androidx.annotation.NonNull;

import org.alee.component.skin.exception.StrictModeException;
import org.alee.component.skin.service.Config;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: xxxx
 *
 *********************************************************/
public final class PrintUtil {
    /**
     * Tag
     */
    private static final String TAG = "ThemeSkin";

    private PrintUtil() {

    }

    /**
     * 获取单例对象
     *
     * @return {@link PrintUtil}
     */
    public static PrintUtil getInstance() {
        return PrintUtilHolder.INSTANCE;
    }

    public void printD(String message) {
        if (Config.getInstance().isEnableDebugMode()) {
            Config.getInstance().getLogger().debug(TAG, message);
        }
    }

    public void printI(String message) {
        Config.getInstance().getLogger().info(TAG, message);
    }

    public void printE(String message) {
        Config.getInstance().getLogger().error(TAG, message);
        if (Config.getInstance().isEnableStrictMode()) {
            throw new StrictModeException(message);
        }
    }

    public void printE(@NonNull Throwable throwable) {
        Config.getInstance().getLogger().error(TAG, throwable.getMessage());
        if (Config.getInstance().isEnableStrictMode()) {
            throw new StrictModeException(throwable);
        }
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class PrintUtilHolder {
        /**
         * {@link PrintUtil}
         */
        private static final PrintUtil INSTANCE = new PrintUtil();
    }


}
