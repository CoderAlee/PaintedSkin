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
