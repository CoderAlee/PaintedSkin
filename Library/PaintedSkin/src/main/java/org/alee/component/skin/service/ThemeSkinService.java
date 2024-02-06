package org.alee.component.skin.service;

import android.content.Context;

import androidx.annotation.NonNull;

import org.alee.component.skin.factory2.ExpandedFactory2Manager;
import org.alee.component.skin.pack.IThemeSkinPack;
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder;
import org.alee.component.skin.util.PrintUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/11
 * @description: xxxx
 *
 *********************************************************/
public final class ThemeSkinService implements IThemeSkinService {
    /**
     * 老实人 手动滑稽
     */
    private final IThemeSkinService mReliableMan;
    /**
     * 标识是否已经进行了初始化
     */
    private boolean mIsInitialized;

    private ThemeSkinService() {
        mReliableMan = new DefaultService();
    }

    /**
     * 初始化
     *
     * @param context       {@link Context}
     * @param optionFactory {@link IOptionFactory}
     */
    @Override
    public IThemeSkinService init(@NonNull Context context, @NonNull IOptionFactory optionFactory) {
        if (mIsInitialized){
            PrintUtil.getInstance().printE(new RuntimeException("PaintedSkin is Initialized! Please do not re-initialize"));
            return getInstance();
        }
        mIsInitialized = true;
        mReliableMan.init(context.getApplicationContext(), optionFactory);
        return getInstance();
    }

    /**
     * 获取单例对象
     *
     * @return {@link ThemeSkinService}
     */
    public static ThemeSkinService getInstance() {
        return ThemeSkinServiceHolder.INSTANCE;
    }

    /**
     * 订阅主题皮肤切换事件
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    @Override
    public void subscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer) {
        mReliableMan.subscribeSwitchThemeSkin(observer);
    }

    /**
     * 取消订阅
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    @Override
    public void unsubscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer) {
        mReliableMan.unsubscribeSwitchThemeSkin(observer);
    }

    @Override
    public IThemeSkinPack getCurrentThemeSkinPack() {
        return mReliableMan.getCurrentThemeSkinPack();
    }

    /**
     * 获取当前正在使用的主题皮肤包
     *
     * @return {@link IThemeSkinPack} 对应context的皮肤包,主进程context, 插件context
     */
    @Override
    public IThemeSkinPack getCurrentThemeSkinPack(Context context) {
        return mReliableMan.getCurrentThemeSkinPack(context);
    }


    /**
     * 切换主题皮肤
     *
     * @param theme 要切换的主题
     */
    @Override
    public void switchThemeSkin(int theme) {
        mReliableMan.switchThemeSkin(theme);
    }

    /**
     * 获取创建View拦截器
     *
     * @return {@link ExpandedFactory2Manager}
     */
    @Override
    public ExpandedFactory2Manager getCreateViewInterceptor() {
        return mReliableMan.getCreateViewInterceptor();
    }

    /**
     * 添加皮肤替换者构造器
     *
     * @param builder {@link IThemeSkinExecutorBuilder}
     */
    @Override
    public IThemeSkinService addThemeSkinExecutorBuilder(@NonNull IThemeSkinExecutorBuilder builder) {
        mReliableMan.addThemeSkinExecutorBuilder(builder);
        return getInstance();
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class ThemeSkinServiceHolder {
        /**
         * {@link ThemeSkinService}
         */
        private static final ThemeSkinService INSTANCE = new ThemeSkinService();
    }
}
