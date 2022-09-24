package org.alee.demo.skin.basic.ability.basic.recycler

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import org.alee.component.skin.service.ISwitchThemeSkinObserver
import org.alee.component.skin.service.ThemeSkinService

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/28
 *
 */
abstract class BaseHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        /**
         * Tag - key
         */
        private val KEY_TAG = "Holder_Default_Tag".hashCode()
    }

    /**
     * [Context]
     */
    private val mContext: Context = itemView.context

    /**
     * 所有注册的view
     */
    private val mViewCache: SparseArray<View> = SparseArray()

    constructor(
        parent: ViewGroup,
        @LayoutRes layoutId: Int
    ) : this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

    init {
        this.findView()
        this.bindViewDefaultValues()
    }

    /**
     * 初始化view
     */
    protected abstract fun findView()

    /**
     * 设置部分view的默认属性
     */
    protected open fun bindViewDefaultValues() {
        val rootView = getRootView()
        if (rootView is ISwitchThemeSkinObserver) {
            ThemeSkinService.getInstance().subscribeSwitchThemeSkin(rootView)
        }
    }

    /**
     * 绑定数据
     *
     * @param data       item 需要展示的数据
     * @param position   item 位置
     * @param isSelected item 是否选中
     */
    abstract fun bindData(data: DATA, position: Int = -1, isSelected: Boolean = false)

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
    </E> */
    protected fun <E : View> findView(viewId: Int): E {
        var view = mViewCache[viewId]
        if (null == view) {
            view = getRootView().findViewById(viewId)
            mViewCache.put(viewId, view)
        }
        if (null == view) {
            throw RuntimeException("未找到View,id$viewId")
        }
        return view!! as E
    }

    fun getRootView(): View {
        return itemView
    }

    /**
     * 绑定数据
     *
     * @param data 要绑定的数据
     */
    fun bindTag(data: DATA) {
        getRootView().setTag(KEY_TAG, data)
    }

    fun getDefaultTag(): DATA? {
        val tag = getRootView().getTag(KEY_TAG) ?: return null
        return tag as DATA
    }

    protected fun getContext(): Context {
        return mContext
    }
}