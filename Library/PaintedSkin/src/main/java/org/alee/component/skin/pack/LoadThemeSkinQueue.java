package org.alee.component.skin.pack;

import android.content.Context;

import androidx.annotation.NonNull;

import org.alee.component.skin.util.task.TaskQueue;
import org.alee.component.skin.util.task.template.ITask;
import org.alee.component.skin.util.task.template.ITaskQueue;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
final class LoadThemeSkinQueue {
    private final Context mContext;
    private IThemeSkinPack mThemeSkinPack;
    private ILoadThemeSkinObserver mLoadThemeSkinObserver;
    private ITaskQueue mTaskQueue;

    LoadThemeSkinQueue(@NonNull Context context) {
        mContext = context;
    }

    void addTask(@NonNull BaseThemeSkinPack skinPack) {
        if (null == mTaskQueue) {
            mTaskQueue = new TaskQueue().beginTask(new LoadThemeSkinTask(skinPack, mContext));
            return;
        }
        mTaskQueue.then(new DependentTask(skinPack, mContext));
    }

    void performTask(@NonNull IThemeSkinPack skinPack, ILoadThemeSkinObserver observer) {
        mThemeSkinPack = skinPack;
        mLoadThemeSkinObserver = observer;
        mTaskQueue.execute().whenComplete(success -> {
            if (null == mLoadThemeSkinObserver) {
                return;
            }
            mLoadThemeSkinObserver.onLoadCompleted(mThemeSkinPack);
        });
    }

    private static final class DependentTask implements ITask {
        private final BaseThemeSkinPack mThemeSkinPack;
        private final Context mContext;

        public DependentTask(@NonNull BaseThemeSkinPack skinPack, @NonNull Context context) {
            mThemeSkinPack = skinPack;
            mContext = context;
        }

        /**
         * 执行任务
         *
         * @return 是否执行成功
         * @throws Throwable 一切异常
         */
        @Override
        public boolean doWork() throws Throwable {
            return new LoadThemeSkinTask(mThemeSkinPack, mContext).doWork();
        }
    }
}
