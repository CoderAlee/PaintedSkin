package org.alee.component.skin.service;

import android.content.Context;

import androidx.annotation.NonNull;

import org.alee.component.skin.factory2.ExpandedFactory2Manager;
import org.alee.component.skin.pack.IThemeSkinPack;
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IThemeSkinService {
    /**
     * 初始化
     *
     * @param context       {@link Context}
     * @param optionFactory {@link IOptionFactory}
     */
    IThemeSkinService init(@NonNull Context context, @NonNull IOptionFactory optionFactory);

    /**
     * 订阅主题皮肤切换事件
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    void subscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer);

    /**
     * 取消订阅
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    void unsubscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer);

    /**
     * 获取当前正在使用的主题皮肤包
     *
     * @return {@link IThemeSkinPack}
     */
    IThemeSkinPack getCurrentThemeSkinPack();

    /**
     * 获取当前正在使用的主题皮肤包
     *
     * @return {@link IThemeSkinPack} 对应context的皮肤包,主进程context, 插件context
     */
    IThemeSkinPack getCurrentThemeSkinPack(Context context);

    /**
     * 切换主题皮肤
     *
     * @param theme 要切换的主题
     */
    void switchThemeSkin(int theme);

    /**
     * 获取创建View拦截器
     *
     * @return {@link ExpandedFactory2Manager}
     */
    ExpandedFactory2Manager getCreateViewInterceptor();

    /**
     * 添加皮肤替换者构造器
     *
     * @param builder {@link IThemeSkinExecutorBuilder}
     */
    IThemeSkinService addThemeSkinExecutorBuilder(@NonNull IThemeSkinExecutorBuilder builder);

}
