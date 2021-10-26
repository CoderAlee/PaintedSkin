package org.alee.component.skin.util.task.template;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/26
 */
public interface ITaskQueue {

    /**
     * 新建状态
     */
    int NEW = 0x1000;
    /**
     * 执行状态
     */
    int RUNNING = 0x1001;
    /**
     * 销毁状态
     */
    int DESTROYED = 0x1002;

    /**
     * 队列ID
     *
     * @return
     */
    String getId();

    /**
     * 第一个任务
     *
     * @param task {@link ITask}
     * @return {@link ITaskQueue}
     */
    ITaskQueue beginTask(ITask task);

    /**
     * 后续任务
     *
     * @param task {@link ITask}
     * @return {@link ITaskQueue}
     */
    ITaskQueue then(ITask task);

    /**
     * 执行任务队列
     *
     * @return {@link ITaskQueue}
     */
    ITaskQueue execute();

    /**
     * 中断队列的执行
     */
    void abort();

    /**
     * 当队列执行结束时回调(队列被中断时不会回调)
     *
     * @param consumer {@link ITaskQueueObserver}
     */
    void whenComplete(ITaskQueueObserver consumer);

}
