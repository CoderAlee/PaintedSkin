package org.alee.component.skin.util.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @author PG.Xie
 * created on 2020/8/12
 */
public final class TaskPool {

    private final Executor mTaskPool;

    private TaskPool(Executor pool) {
        mTaskPool = pool;
    }

    public static TaskPool main() {
        return MainHolder._INSTANCE;
    }

    /**
     * 创建一个串行的单线程的任务池，单例
     *
     * @return
     */
    public static TaskPool sequential() {
        return SingleHolder._INSTANCE;
    }

    /**
     * 创建一个带并发的抢占式任务池，单例
     *
     * @return
     */
    public static TaskPool concurrent() {
        return MultiHolder._INSTANCE;
    }

    /**
     * 创建一个自定义的任务池，非单例，没调用一次该函数，均创建一个新的任务池
     *
     * @param pool
     *
     * @return
     */
    public static TaskPool custom(Executor pool) {
        return new TaskPool(pool);
    }

    /**
     * 提交任务
     *
     * @param params
     * @param task
     * @param <P>
     * @param <R>
     */
    public <P, R> void post(P params, JustReturnIt<P, R> task) {
        post(params, task, null);
    }

    /**
     * 提交任务
     *
     * @param params
     * @param task
     * @param callback
     * @param <P>
     * @param <R>
     */
    public <P, R> void post(P params, JustReturnIt<P, R> task, JustWithIt<R> callback) {
        new IOTask(task, callback).executeOnExecutor(mTaskPool, params);
    }

    /**
     * 提交任务
     *
     * @param task
     */
    public <R> void post(JustReturnIt<Void, R> task) {
        post(null, task, null);
    }

    /**
     * 提交任务
     *
     * @param task
     */
    public void post(Runnable task) {
        post(null, (JustReturnIt<Void, Void>) params -> {
            task.run();
            return null;
        }, null);
    }

    /**
     * 提交任务
     *
     * @param task
     * @param callback
     */
    public <R> void post(JustReturnIt<Void, R> task, JustWithIt<R> callback) {
        post(null, task, callback);
    }

    private static class IOTask<P, R> extends AsyncTask<P, Integer, R> {

        JustReturnIt<P, R> mTaskSmi;
        JustWithIt<R> mDoneCallback;

        public IOTask(JustReturnIt<P, R> task, JustWithIt<R> doneCallback) {
            this.mTaskSmi = task;
            this.mDoneCallback = doneCallback;
        }


        @Override
        protected R doInBackground(P... voids) {

            boolean main = Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();

            if (main) {
                Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_DISPLAY);
            } else {
                Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);
            }

            return mTaskSmi.onWith(voids[0]);
        }

        @Override
        protected void onPostExecute(R t) {
            if (mDoneCallback != null) {
                mDoneCallback.onWith(t);
            }
        }
    }

    private static final class SingleHolder {
        public static final TaskPool _INSTANCE = new TaskPool(Executors.newSingleThreadExecutor());
    }

    private static final class MultiHolder {
        public static final TaskPool _INSTANCE = new TaskPool(Executors.newCachedThreadPool());
    }

    private static final class MainHolder {
        public static final TaskPool _INSTANCE = new TaskPool(MainExecutor.get());
    }

    private static class MainExecutor implements Executor {

        Handler mHandler = new Handler(Looper.getMainLooper());

        private MainExecutor() {
        }

        public static final MainExecutor get() {
            return MainExecutorHolder._INSTANCE;
        }

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }

        private static final class MainExecutorHolder {
            public static final MainExecutor _INSTANCE = new MainExecutor();
        }
    }

}



