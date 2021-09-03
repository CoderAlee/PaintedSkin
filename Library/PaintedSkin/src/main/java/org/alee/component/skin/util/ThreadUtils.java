package org.alee.component.skin.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/9/3
 */
public final class ThreadUtils {

    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    public static void runOnMainThread(@NonNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
            return;
        }
        postOnMainThread(runnable);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void postOnMainThread(@NonNull Runnable runnable) {
        MAIN_THREAD_HANDLER.post(runnable);
    }
}
