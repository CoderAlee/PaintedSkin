package org.alee.component.skin.service;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import org.alee.component.skin.util.DefaultLogger;
import org.alee.component.skin.util.template.ILogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: xxxx
 *
 *********************************************************/
public final class Config {
    /**
     * 标识 是否启用Debug模式
     */
    private boolean mEnableDebugMode;
    /**
     * 标识 是否启用严格模式
     */
    private boolean mEnableStrictMode;
    /**
     * {@link ILogger}
     */
    private ILogger mLogger;
    /**
     * 换肤模式
     */
    private int mSkinMode;

    private Config() {
        mEnableDebugMode = true;
        mEnableStrictMode = false;
        mSkinMode = SkinMode.REPLACE_ALL;
        mLogger = new DefaultLogger();
    }

    /**
     * 获取单例对象
     *
     * @return {@link Config}
     */
    public static Config getInstance() {
        return ConfigHolder.INSTANCE;
    }

    public boolean isEnableDebugMode() {
        return mEnableDebugMode;
    }

    public void setEnableDebugMode(boolean enableDebugMode) {
        mEnableDebugMode = enableDebugMode;
    }

    public boolean isEnableStrictMode() {
        return mEnableStrictMode;
    }

    public void setEnableStrictMode(boolean enableStrictMode) {
        mEnableStrictMode = enableStrictMode;
    }

    public ILogger getLogger() {
        return mLogger;
    }

    public void setLogger(@NonNull ILogger logger) {
        mLogger = logger;
    }

    public int getSkinMode() {
        return mSkinMode;
    }

    public void setSkinMode(@SkinMode int skinMode) {
        mSkinMode = skinMode;
    }

    @IntDef(value = {SkinMode.REPLACE_ALL, SkinMode.REPLACE_MARKED, SkinMode.DO_NOT_REPLACE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SkinMode {
        /**
         * 替换全部
         */
        int REPLACE_ALL = 0;
        /**
         * 替换指定
         */
        int REPLACE_MARKED = 1;
        /**
         * 不替换任何
         */
        int DO_NOT_REPLACE = 2;
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class ConfigHolder {
        /**
         * {@link Config}
         */
        private static final Config INSTANCE = new Config();
    }
}
