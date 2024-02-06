package org.alee.component.skin.service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.ArrayMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.BuildConfig;
import org.alee.component.skin.collection.SparseStack;
import org.alee.component.skin.factory2.ExpandedFactory2Manager;
import org.alee.component.skin.pack.ILoadThemeSkinObserver;
import org.alee.component.skin.pack.IThemeSkinPack;
import org.alee.component.skin.pack.PluginDefaultThemePack;
import org.alee.component.skin.pack.PluginThemeSkinPack;
import org.alee.component.skin.pack.ThemeSkinPackFactory;
import org.alee.component.skin.parser.DefaultExecutorBuilder;
import org.alee.component.skin.parser.IThemeSkinExecutorBuilder;
import org.alee.component.skin.parser.ThemeSkinExecutorBuilderManager;
import org.alee.component.skin.util.ObjectMemoryAddress;
import org.alee.component.skin.util.PrintUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    /**
     * 插件皮肤包映射: 包名-皮肤包
     * 每个插件单独一个皮肤包
     */
    private Map<String, IThemeSkinPack> mSkinPackMap;

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
        mSkinPackMap = new ArrayMap<>(11);
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
        PrintUtil.getInstance().printI(BuildConfig.VERSION_NAME);
        addThemeSkinExecutorBuilder(new DefaultExecutorBuilder());
        switchThemeSkin(optionFactory.defaultTheme());
        return null;
    }

    private void restoreDefaultThemeSkin() {
        ThemeSkinPackFactory.loadThemeSkinPack(mContext, this, null);
        switchPluginThemeSkin(mOptionFactory.requireOption(mOptionFactory.defaultTheme()));
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
     * 获取当前正在使用的主题皮肤包
     *
     * @return {@link IThemeSkinPack} 对应context的皮肤包,主进程context, 插件context
     */
    @Override
    public IThemeSkinPack getCurrentThemeSkinPack(Context context) {
        IThemeSkinPack themePack = mSkinPackMap.get(context.getPackageName());
        if ( themePack!= null){
            return themePack;
        }
        return getCurrentThemeSkinPack();
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
        switchPluginThemeSkin(option);

    }

    /**
     * 切换插件主题
     * @param option 主题选项
     */
    private void switchPluginThemeSkin(IThemeSkinOption option){
        //null option 没有配置插件皮肤包,不支持插件换肤
        if (option == null){
            return;
        }
        //option选定主题
        Map<String, List<String>> plugins = option.getPluginPackagesPackPath();
        //没有配置插件选项
        if (plugins == null){
            return;
        }
        Iterator<Map.Entry<String, List<String>>> ite = plugins.entrySet().iterator();
        while (ite.hasNext()){
            Map.Entry<String, List<String>> entry = ite.next();
            String packageName = entry.getKey();
            List<String> packPathList = entry.getValue();
            //插件包名不能为空
            if (packageName != null || !packageName.isEmpty()) {
                //为插件加载皮肤包列表
                try {
                    Context packageContext = mContext.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
                    PrintUtil.getInstance().printD("loadPluginThemeSkinPack: " + packageName);
                    ThemeSkinPackFactory.loadPluginThemeSkinPack(packageContext, this, packPathList);
                }
                catch (PackageManager.NameNotFoundException ignored){
                    PrintUtil.getInstance().printE("loadPluginThemeSkinPack exception: "+ignored.getCause());
                }
            }
        }
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
        //保存插件皮肤包
        if (pack instanceof PluginThemeSkinPack){
            mSkinPackMap.put(((PluginThemeSkinPack) pack).getPackageName(), pack);
        }
        //保存插件默认皮肤包
        else if (pack instanceof PluginDefaultThemePack){
            mSkinPackMap.put(((PluginDefaultThemePack) pack).getPackageName(), pack);
        }
        //主程序皮肤包
        else {
            mSkinPackMap.put(mContext.getPackageName(), pack);
            //兼容原逻辑不变
            mCurrentThemeSkinPack = pack;
        }
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
