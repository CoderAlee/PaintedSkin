package org.alee.component.skin.pack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

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
        LoadThemeSkinQueue queue = new LoadThemeSkinQueue(context);
        BaseThemeSkinPack defaultPack = new DefaultThemeSkinPack();
        if (null == pathList) {
            queue.addTask(defaultPack);
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
