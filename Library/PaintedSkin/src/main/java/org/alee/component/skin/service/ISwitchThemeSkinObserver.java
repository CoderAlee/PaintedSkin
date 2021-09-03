package org.alee.component.skin.service;

import org.alee.component.skin.util.ThreadUtils;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface ISwitchThemeSkinObserver {
    /**
     * 当主题皮肤切换时被回调
     * <p> 注意此回调可能为非UI线程
     */
    default void onThemeSkinSwitch() {
        ThreadUtils.runOnMainThread(this::onThemeSkinSwitchRunOnUiThread);

    }

    /**
     * 当主题皮肤切换时被回调
     */
    default void onThemeSkinSwitchRunOnUiThread() {
    }
}
