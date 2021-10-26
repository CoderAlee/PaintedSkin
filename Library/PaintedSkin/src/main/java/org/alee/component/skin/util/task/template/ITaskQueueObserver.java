package org.alee.component.skin.util.task.template;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/26
 */
public interface ITaskQueueObserver {
    /**
     *
     * @param success
     */
    void accept(boolean success);
}
