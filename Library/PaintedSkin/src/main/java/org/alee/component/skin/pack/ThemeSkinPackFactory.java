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
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static void loadThemeSkinPack(@NonNull Context context, ILoadThemeSkinObserver observer, List<String> pathList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _24(context, observer, pathList);
            return;
        }
        TaskQueueManager.getInstance().cancelAll();
        LoadThemeSkinQueue queue = new LoadThemeSkinQueue(context);
        BaseThemeSkinPack defaultPack = new DefaultThemeSkinPack();
        queue.addTask(defaultPack);
        if (null == pathList) {
            queue.performTask(defaultPack, observer);
            return;
        }
        BaseThemeSkinPack underpinPack = defaultPack;
        for (String path : pathList) {
            underpinPack = new StandardThemeSkinPack(path, underpinPack);
            queue.addTask(underpinPack);
        }
        queue.performTask(underpinPack, observer);
    }


    private static void _24(@NonNull Context context, ILoadThemeSkinObserver observer, List<String> pathList) {
        LoadThemeSkinQueue24 queue = new LoadThemeSkinQueue24(context);
        BaseThemeSkinPack defaultPack = new DefaultThemeSkinPack();
        queue.addTask(defaultPack);
        if (null == pathList) {
            queue.performTask(defaultPack, observer);
            return;
        }
        BaseThemeSkinPack underpinPack = defaultPack;
        for (String path : pathList) {
            underpinPack = new StandardThemeSkinPack(path, underpinPack);
            queue.addTask(underpinPack);
        }
        queue.performTask(underpinPack, observer);
    }
}
