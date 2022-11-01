package org.alee.component.skin.util.task;

import org.alee.component.skin.util.task.template.ITask;
import org.alee.component.skin.util.task.template.ITaskQueue;
import org.alee.component.skin.util.task.template.ITaskQueueObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/26
 */
public final class TaskQueue implements ITaskQueue {

    private final List<ITask> mTaskQueue = new ArrayList<>();
    private final String mId;
    private volatile int mState;
    private ITaskQueueObserver mConsumer;
    private volatile boolean mIsAborted;

    public TaskQueue() {
        mState = ITaskQueue.NEW;
        mId = UUID.randomUUID().toString();
    }

    /**
     * 队列ID
     *
     * @return
     */
    @Override
    public String getId() {
        return mId;
    }

    /**
     * 第一个任务
     *
     * @param task {@link ITask}
     * @return {@link ITaskQueue}
     */
    @Override
    public ITaskQueue beginTask(ITask task) {
        mTaskQueue.add(task);
        return this;
    }

    /**
     * 后续任务
     *
     * @param task {@link ITask}
     * @return {@link ITaskQueue}
     */
    @Override
    public ITaskQueue then(ITask task) {
        mTaskQueue.add(task);
        return this;
    }

    /**
     * 执行任务队列
     *
     * @return {@link ITaskQueue}
     */
    @Override
    public ITaskQueue execute() {
        if (mTaskQueue.isEmpty()) {
            return this;
        }
        TaskPool.main().post(this::_execute);
        return this;
    }

    private void _execute() {
        mState = ITaskQueue.RUNNING;
        TaskQueueManager.getInstance().addTaskQueue(this);
        for (ITask task : mTaskQueue) {
            if (ITaskQueue.RUNNING != mState) {
                return;
            }
            try {
                task.doWork();
            } catch (Throwable ignored) {
            }
        }
        onCompleted(false);
    }

    private void onCompleted(boolean isAborted) {
        if (ITaskQueue.DESTROYED == mState) {
            return;
        }
        mState = ITaskQueue.DESTROYED;
        mTaskQueue.clear();
        mIsAborted = isAborted;
        TaskQueueManager.getInstance().removeTaskQueue(this);
        if (isAborted) {
            return;
        }
        if (null == mConsumer) {
            return;
        }
        try {
            mConsumer.accept(true);
        } catch (Throwable ignored) {
        } finally {
            mConsumer = null;
        }
    }

    /**
     * 中断队列的执行
     */
    @Override

    public void abort() {
        if (ITaskQueue.RUNNING != mState) {
            return;
        }
        onCompleted(true);
    }

    /**
     * 当队列执行结束时回调(队列被中断时不会回调)
     *
     * @param consumer {@link ITaskQueueObserver}
     */
    @Override
    public void whenComplete(ITaskQueueObserver consumer) {
        mConsumer = consumer;
        if (ITaskQueue.DESTROYED != mState) {
            return;
        }
        if (mIsAborted) {
            return;
        }
        mConsumer.accept(true);
        mConsumer = null;
    }
}
