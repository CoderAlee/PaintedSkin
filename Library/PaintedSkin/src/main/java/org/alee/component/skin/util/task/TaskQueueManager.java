package org.alee.component.skin.util.task;

import org.alee.component.skin.util.task.template.ITaskQueue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/26
 */
public final class TaskQueueManager {

    private final List<ITaskQueue> mDoingQueue;

    private TaskQueueManager() {
        mDoingQueue = new CopyOnWriteArrayList<>();
    }

    /**
     * 获取单例对象
     *
     * @return {@link TaskQueueManager}
     */
    public static TaskQueueManager getInstance() {
        return TaskQueueManagerHolder.INSTANCE;
    }

    public void cancelAll() {
        if (mDoingQueue.isEmpty()) {
            return;
        }
        TaskPool.sequential().post(this::_cancelAll);
    }

    private void _cancelAll() {
        Iterator<ITaskQueue> iterator = mDoingQueue.iterator();
        while (iterator.hasNext()) {
            iterator.next().abort();
            iterator.remove();
        }
    }

    void addTaskQueue(ITaskQueue queue) {
        mDoingQueue.add(queue);
    }

    void removeTaskQueue(ITaskQueue queue) {
        mDoingQueue.remove(queue);
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class TaskQueueManagerHolder {
        /**
         * {@link TaskQueueManager}
         */
        private static final TaskQueueManager INSTANCE = new TaskQueueManager();
    }
}
