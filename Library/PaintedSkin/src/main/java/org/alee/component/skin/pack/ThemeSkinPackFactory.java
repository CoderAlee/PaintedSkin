package org.alee.component.skin.pack;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import org.alee.component.skin.util.task.TaskQueueManager;

import java.util.List;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class ThemeSkinPackFactory {

    private static final BaseThemeSkinPack DEFAULT_PACK = new DefaultThemeSkinPack();

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static void loadThemeSkinPack(@NonNull Context context, ILoadThemeSkinObserver observer, List<String> pathList) {
        initDefaultPack(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _24(context, observer, pathList);
            return;
        }
        TaskQueueManager.getInstance().cancelAll();
        if (null == pathList) {
            observer.onLoadCompleted(DEFAULT_PACK);
            return;
        }
        LoadThemeSkinQueue queue = new LoadThemeSkinQueue(context);
        queue.addTask(DEFAULT_PACK);
        BaseThemeSkinPack underpinPack = DEFAULT_PACK;
        for (String path : pathList) {
            underpinPack = new StandardThemeSkinPack(path, underpinPack);
            queue.addTask(underpinPack);
        }
        queue.performTask(underpinPack, observer);
    }

    /**
     * 加载插件皮肤包
     * @param packageContext 插件context
     * @param observer 创建结果回调观察者
     * @param pathList 插件皮肤包路径
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static void loadPluginThemeSkinPack(@NonNull Context packageContext, ILoadThemeSkinObserver observer, List<String> pathList) {
        PluginDefaultThemePack defaultPack = generateDefaultPack(packageContext);
        //Our project always > 24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LoadThemeSkinQueue24 queue = new LoadThemeSkinQueue24(packageContext);
            if (null == pathList || pathList.isEmpty()) {
                observer.onLoadCompleted(defaultPack);
                return;
            }
            queue.addTask(defaultPack);
            BaseThemeSkinPack underpinPack = defaultPack;
            for (String path : pathList) {
                //生成插件主题包, 如果主题包找不到资源,则使用默认主题包里面的资源, 所以必须传入defaultPack
                underpinPack = new PluginThemeSkinPack(path, defaultPack, packageContext.getPackageName());
                queue.addTask(underpinPack);
            }
            queue.performTask(underpinPack, observer);
        }
    }

    /**
     * 生成插件默认主题包
     * 默认主题: 插件apk自身的主题资源,非主题包的资源
     * @param context 插件context
     * @return 主题包
     */
    private static PluginDefaultThemePack generateDefaultPack(Context context) {
        PluginDefaultThemePack defaultPack = new PluginDefaultThemePack(context.getPackageName());
        defaultPack.onReady(new DefaultSkinResourcesProvider(context));
        return defaultPack;
    }

    private static void initDefaultPack(Context context) {
        if (DEFAULT_PACK.isAvailable()) {
            return;
        }
        DEFAULT_PACK.onReady(new DefaultSkinResourcesProvider(context));
    }

    private static void _24(@NonNull Context context, ILoadThemeSkinObserver observer, List<String> pathList) {
        LoadThemeSkinQueue24 queue = new LoadThemeSkinQueue24(context);
        if (null == pathList) {
            observer.onLoadCompleted(DEFAULT_PACK);
            return;
        }
        queue.addTask(DEFAULT_PACK);
        BaseThemeSkinPack underpinPack = DEFAULT_PACK;
        for (String path : pathList) {
            underpinPack = new StandardThemeSkinPack(path, underpinPack);
            queue.addTask(underpinPack);
        }
        queue.performTask(underpinPack, observer);
    }
}
