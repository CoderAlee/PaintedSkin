package org.alee.component.skin.core.view.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.View
import org.alee.component.skin.template.IViewCreator
import org.alee.component.skin.util.SafeArray
import org.alee.component.skin.util.memoryAddress
import org.alee.component.skin.util.safeCall

/**
 * [IViewCreatorManager] 实现类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal class ViewCreatorManager : IViewCreatorManager {
    /**
     * [IViewCreator] 对象持有者
     */
    private val mCreatorHolder by lazy { SafeArray<IViewCreator>() }

    /**
     * 添加View创建器
     * @param creator IViewCreator [IViewCreator]
     */
    override fun addCreator(creator: IViewCreator) {
        mCreatorHolder.add(creator)
    }

    /**
     * 添加View创建器
     * @param factory2 Factory2 [LayoutInflater.Factory2]
     */
    override fun addCreator(factory2: Factory2) {
        mCreatorHolder.add(factory2.memoryAddress, Factory2Proxy(factory2))
    }

    /**
     * 移除View创建器
     * @param creator IViewCreator [IViewCreator]
     */
    override fun removeCreator(creator: IViewCreator) {
        mCreatorHolder.remove(creator)
    }

    /**
     * 移除View创建器
     * @param factory2 Factory2 [LayoutInflater.Factory2]
     */
    override fun removeCreator(factory2: Factory2) {
        mCreatorHolder.remove(factory2.memoryAddress)
    }

    /**
     * 创建View/转换View
     * 开发者可以根据入参决定创建一个View或将View替换为其他View
     * @param predecessorOutput View? 上一任[IViewCreator]的处理结果
     * @param parent View? View的父View
     * @param context Context [Context]
     * @param viewName String View的完整类名
     * @param attrs AttributeSet View的属性集合
     * @return View?
     */
    override fun onCreateView(
        predecessorOutput: View?,
        parent: View?,
        context: Context,
        viewName: String,
        attrs: AttributeSet,
    ): View? {
        var returnView: View? = predecessorOutput
        mCreatorHolder.use().use {
            it.forEach {
                safeCall {
                    returnView = it.onCreateView(returnView, parent, context, viewName, attrs)
                }
            }
        }
        return returnView
    }

    private class Factory2Proxy(private val mFactory: LayoutInflater.Factory2) : IViewCreator {
        /**
         * 创建View/转换View
         * 开发者可以根据入参决定创建一个View或将View替换为其他View
         * @param predecessorOutput View? 上一任[IViewCreator]的处理结果
         * @param parent View? View的父View
         * @param context Context [Context]
         * @param viewName String View的完整类名
         * @param attrs AttributeSet View的属性集合
         * @return View?
         */
        override fun onCreateView(
            predecessorOutput: View?,
            parent: View?,
            context: Context,
            viewName: String,
            attrs: AttributeSet,
        ): View? {
            val temp: View? = if (null == parent) {
                mFactory.onCreateView(viewName, context, attrs)
            } else {
                mFactory.onCreateView(parent, viewName, context, attrs)
            }
            return temp ?: predecessorOutput
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Factory2Proxy) return false

            if (mFactory != other.mFactory) return false

            return true
        }

        override fun hashCode(): Int {
            return mFactory.hashCode()
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
