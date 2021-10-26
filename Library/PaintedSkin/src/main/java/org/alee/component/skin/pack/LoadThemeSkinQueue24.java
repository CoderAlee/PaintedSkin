package org.alee.component.skin.pack;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
final class LoadThemeSkinQueue24 {
    private final Context mContext;
    private CompletableFuture<Boolean> mCompletableFuture;
    private IThemeSkinPack mThemeSkinPack;
    private ILoadThemeSkinObserver mLoadThemeSkinObserver;

    LoadThemeSkinQueue24(@NonNull Context context) {
        mContext = context;
    }

    void addTask(@NonNull BaseThemeSkinPack skinPack) {
        if (null == mCompletableFuture) {
            mCompletableFuture = CompletableFuture.supplyAsync(new LoadThemeSkinTask24(skinPack, mContext));
            return;
        }
        mCompletableFuture = mCompletableFuture.handle(new DependentTask(skinPack, mContext));
    }

    void performTask(@NonNull IThemeSkinPack skinPack, ILoadThemeSkinObserver observer) {
        mThemeSkinPack = skinPack;
        mLoadThemeSkinObserver = observer;
        mCompletableFuture.whenComplete((aBoolean, throwable) -> {
            if (null == mLoadThemeSkinObserver) {
                return;
            }
            mLoadThemeSkinObserver.onLoadCompleted(mThemeSkinPack);
        });
    }

    private static final class DependentTask implements BiFunction<Boolean, Throwable, Boolean> {
        private final BaseThemeSkinPack mThemeSkinPack;
        private final Context mContext;

        public DependentTask(@NonNull BaseThemeSkinPack skinPack, @NonNull Context context) {
            mThemeSkinPack = skinPack;
            mContext = context;
        }

        @Override
        public Boolean apply(Boolean aBoolean, Throwable throwable) {
            return new LoadThemeSkinTask24(mThemeSkinPack, mContext).get();
        }
    }
}
