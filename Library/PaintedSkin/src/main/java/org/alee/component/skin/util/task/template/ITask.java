package org.alee.component.skin.util.task.template;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/26
 */
public interface ITask {
    /**
     * 执行任务
     *
     * @return 是否执行成功
     * @throws Throwable 一切异常
     */
    boolean doWork() throws Throwable;
}
