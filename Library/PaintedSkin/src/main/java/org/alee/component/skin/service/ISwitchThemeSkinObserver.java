package org.alee.component.skin.service;

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
    void onThemeSkinSwitch();
}
