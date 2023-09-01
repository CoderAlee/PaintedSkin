package org.alee.component.skin.core.view.factory

import android.view.LayoutInflater
import androidx.annotation.AnyThread
import org.alee.component.skin.template.IViewCreator
import org.alee.component.skin.util.Version

/**
 * View 填充管理器
 *
 * <p> 使用者通过此接口像框架内注入[IViewCreator] 或 [LayoutInflater.Factory2]
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
sealed interface IViewCreatorManager : IViewCreator {
    /**
     * 添加View创建器
     * @param creator IViewCreator [IViewCreator]
     */
    @Version("4.0.0")
    @AnyThread
    fun addCreator(creator: IViewCreator)

    /**
     * 添加View创建器
     * @param factory2 Factory2 [LayoutInflater.Factory2]
     */
    @Version("4.0.0")
    @AnyThread
    fun addCreator(factory2: LayoutInflater.Factory2)

    /**
     * 移除View创建器
     * @param creator IViewCreator [IViewCreator]
     */
    @Version("4.0.0")
    @AnyThread
    fun removeCreator(creator: IViewCreator)

    /**
     * 移除View创建器
     * @param factory2 Factory2 [LayoutInflater.Factory2]
     */
    @Version("4.0.0")
    @AnyThread
    fun removeCreator(factory2: LayoutInflater.Factory2)
}
