package org.alee.component.skin.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.BuildConfig;
import org.alee.component.skin.collection.SparseStack;
import org.alee.component.skin.factory2.ExpandedFactory2Manager;
import org.alee.component.skin.pack.ILoadThemeSkinObserver;
import org.alee.component.skin.pack.IThemeSkinPack;
import org.alee.component.skin.pack.ThemeSkinPackFactory;
import org.alee.component.skin.parser.DefaultExecutorBuilder;
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder;
import org.alee.component.skin.parser.ThemeSkinExecutorBuilderManager;
import org.alee.component.skin.util.ObjectMemoryAddress;
import org.alee.component.skin.util.PrintUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class DefaultService implements IThemeSkinService, ILoadThemeSkinObserver {
    private final ExpandedFactory2Manager mExpandedFactory2Manager;
    private final SparseStack<WeakReference<ISwitchThemeSkinObserver>> mSwitchThemeSkinObserverStack;
    private final ThreadLocal<List<ISwitchThemeSkinObserver>> mThreadLocal;
    private Context mContext;
    private IThemeSkinPack mCurrentThemeSkinPack;
    private IOptionFactory mOptionFactory;
    private int mCurrentTheme = -1;

    DefaultService() {
        mExpandedFactory2Manager = new ExpandedFactory2Manager();
        mSwitchThemeSkinObserverStack = new SparseStack<>();
        mThreadLocal = new ThreadLocal<List<ISwitchThemeSkinObserver>>() {
            @Nullable
            @Override
            protected List<ISwitchThemeSkinObserver> initialValue() {
                return new ArrayList<>();
            }
        };
    }

    /**
     * 初始化
     *
     * @param context       {@link Context}
     * @param optionFactory {@link IOptionFactory}
     */
    @Override
    public IThemeSkinService init(@NonNull Context context, @NonNull IOptionFactory optionFactory) {
        mContext = context;
        mOptionFactory = optionFactory;
        PrintUtil.getInstance().printI("V3.1.8");
        addThemeSkinExecutorBuilder(new DefaultExecutorBuilder());
        switchThemeSkin(optionFactory.defaultTheme());
        return null;
    }

    private void restoreDefaultThemeSkin() {
        ThemeSkinPackFactory.loadThemeSkinPack(mContext, this, null);
        //        ThemeSkinPackFactoryMapping.loadThemeSkinPack.call(mContext, this, null);
    }

    /**
     * 订阅主题皮肤切换事件
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    @Override
    public void subscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer) {
        synchronized (mSwitchThemeSkinObserverStack) {
            mSwitchThemeSkinObserverStack.put(ObjectMemoryAddress.getAddress(observer), new WeakReference<>(observer));
        }
    }

    /**
     * 取消订阅
     *
     * @param observer {@link ISwitchThemeSkinObserver}
     */
    @Override
    public void unsubscribeSwitchThemeSkin(@NonNull ISwitchThemeSkinObserver observer) {
        synchronized (mSwitchThemeSkinObserverStack) {
            mSwitchThemeSkinObserverStack.remove(ObjectMemoryAddress.getAddress(observer));
        }
    }

    /**
     * 获取当前正在使用的主题皮肤包
     *
     * @return {@link IThemeSkinPack}
     */
    @Override
    public IThemeSkinPack getCurrentThemeSkinPack() {
        return mCurrentThemeSkinPack;
    }

    /**
     * 切换主题皮肤
     *
     * @param theme 要切换的主题
     */
    @Override
    public void switchThemeSkin(int theme) {
        if (mCurrentTheme == theme) {
            return;
        }
        mCurrentTheme = theme;
        if (theme == mOptionFactory.defaultTheme()) {
            restoreDefaultThemeSkin();
            return;
        }
        IThemeSkinOption option = mOptionFactory.requireOption(theme);
        if (null == option) {
            return;
        }
        Set<String> path = option.getStandardSkinPackPath();
        List<String> pathArray;
        if (null == path || path.isEmpty()) {
            pathArray = null;
        } else {
            pathArray = new ArrayList<>(path);
        }
        ThemeSkinPackFactory.loadThemeSkinPack(mContext, this, pathArray);
        //        ThemeSkinPackFactoryMapping.loadThemeSkinPack.call(mContext, this, pathArray);

    }

    /**
     * 获取创建View拦截器
     *
     * @return {@link ExpandedFactory2Manager}
     */
    @Override
    public ExpandedFactory2Manager getCreateViewInterceptor() {
        return mExpandedFactory2Manager;
    }

    /**
     * 添加皮肤替换者构造器
     *
     * @param builder {@link IThemeSkinExecutorBuilder}
     */
    @Override
    public IThemeSkinService addThemeSkinExecutorBuilder(@NonNull IThemeSkinExecutorBuilder builder) {
        ThemeSkinExecutorBuilderManager.getInstance().addThemeSkinExecutorBuilder(builder);
        return null;
    }

    @Override
    public void onLoadCompleted(IThemeSkinPack pack) {
        mCurrentThemeSkinPack = pack;
        List<ISwitchThemeSkinObserver> tempList = mThreadLocal.get();
        synchronized (mSwitchThemeSkinObserverStack) {
            tempList.clear();
            for (int i = mSwitchThemeSkinObserverStack.size() - 1; i >= 0; i--) {
                WeakReference<ISwitchThemeSkinObserver> reference = mSwitchThemeSkinObserverStack.valueAt(i);
                if (null == reference) {
                    continue;
                }
                ISwitchThemeSkinObserver observer = reference.get();
                if (null == observer) {
                    continue;
                }
                tempList.add(observer);
            }
        }
        for (ISwitchThemeSkinObserver observer : tempList) {
            try {
                observer.onThemeSkinSwitch();
            }catch (Throwable e){
                PrintUtil.getInstance().printE(e);
            }
        }
    }

}
