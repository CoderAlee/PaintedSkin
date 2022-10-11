package org.alee.demo.skin.basic.ability.basic.recycler

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble
import java.util.Objects

/**
 * RecyclerView Adapter 基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/28
 *
 */
abstract class BaseRecyclerAdapter<DATA, HOLDER : BaseHolder<DATA>>(
    protected val mListener: OnItemClickListener? = null,
    dataList: List<DATA>? = null
) :
    RecyclerView.Adapter<HOLDER>(), OnItemClickListener {

    private val mDataList: ArrayList<DATA> = ArrayList()

    init {
        dataList?.run {
            mDataList.addAll(this)
        }
    }

    /**
     * 为item绑定点击事件
     *
     * @param view item
     */
    @Suppress("UNCHECKED_CAST")
    protected fun bindClickListener(view: View) {
        view.setOnClickListener {
            (mListener ?: this).run {
                onItemClick(it, getPositionByData(it.tag as DATA))
            }
        }
        view.setOnLongClickListener {
            (mListener ?: this).run {
                onItemLongClick(it, getPositionByData(it.tag as DATA))
            }
            true
        }
    }

    /**
     * 根据数据获取位置
     *
     * @param data 数据
     * @return 位置
     */
    fun getPositionByData(data: DATA): Int {
        var position: Int = mDataList.indexOf(data)
        if (position >= 0) {
            return getHaveHeaderPosition(position)
        }
        for (item in mDataList) {
            if (isEquals(item, data)) {
                position = mDataList.indexOf(item)
                break
            }
        }
        return getHaveHeaderPosition(position)
    }

    /**
     * 获取带有header后的位置
     *
     * @param position 位置
     * @return 结果
     */
    protected fun getHaveHeaderPosition(position: Int): Int {
        return if (0 > position) {
            position
        } else position + getHeaderSize()
    }


    /**
     * 获取当前header的数量
     *
     * @return header的数量
     */
    protected open fun getHeaderSize(): Int = 0

    /**
     * 获取当前footer的数量
     *
     * @return footer
     */
    protected open fun getFooterSize(): Int = 0


    /**
     * 两条数据是否相同
     *
     * @param first  第一条数据
     * @param second 第二条数据
     * @return 结果
     */
    protected open fun isEquals(first: DATA?, second: Any?) = Objects.equals(first, second)

    /**
     * 向头部添加数据
     *
     * @param items 数据
     */
    fun addItemToHeader(items: List<DATA>) {
        if (items.isEmpty()) {
            return
        }
        mDataList.addAll(0, items)
        notifyItemRangeInserted(getHeaderSize(), items.size)
    }


    /**
     * 向尾部添加单条数据
     *
     * @param item 数据
     */
    fun addItemToFooter(item: DATA) {
        if (null == item) {
            return
        }
        mDataList.add(item)
        notifyItemInserted(itemCount - 1)
    }

    /**
     * 删除数据
     *
     * @param position 位置
     */
    fun onDeleteItem(position: Int) {
        if (position >= itemCount) {
            return
        }
        mDataList.removeAt(getDataPosition(position))
        notifyItemRemoved(position)
    }

    /**
     * 获取数据的真实位置
     *
     * @param position 在列表中的位置
     * @return 在数据集合中的位置
     */
    protected fun getDataPosition(position: Int): Int {
        val dataPosition = position - getHeaderSize()
        return dataPosition.coerceAtLeast(0)
    }

    /**
     * 刷新某一条item
     *
     * @param data     数据
     */
    fun onUpdateItem(data: DATA) {
        onUpdateItem(getPositionByData(data), data, 0, 0)
    }

    fun getDatas(): List<DATA> {
        return mDataList
    }

    /**
     * 刷新某一条item 只更新部分view
     *
     * @param position        位置
     * @param data            数据
     * @param needRefreshView item中需要刷新的view
     */
    open fun onUpdateItem(position: Int, data: DATA, vararg needRefreshView: Any) {
        if (position < 0) {
            //当position < 0时  此次刷新不执行
            return
        }
        if (getDataPosition(position) >= mDataList.size) {
            return
        }
        mDataList[getDataPosition(position)] = data
        if (needRefreshView.isEmpty()) {
            notifyItemChanged(position)
        } else {
            notifyItemChanged(position, needRefreshView)
        }
    }

    override fun onItemClick(view: View?, position: Int) {
        // ignored
    }

    override fun onBindViewHolder(holder: HOLDER, position: Int) {
        holder.itemView.tag = getItem(position)
        if (holder is IThemeSkinAble) {
            ThemeSkinService.getInstance().subscribeSwitchThemeSkin(holder)
        }
    }

    override fun onBindViewHolder(holder: HOLDER, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
        onRefreshView(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return if (mDataList.size == 0) {
            getHeaderSize()
        } else mDataList.size + getHeaderSize() + getFooterSize()
    }

    /**
     * 更新item的部分view
     *
     * @param holder   item
     * @param position 位置
     * @param payloads 需要更新的view标识
     */
    protected open fun onRefreshView(holder: HOLDER, position: Int, payloads: List<Any>) {}

    /**
     * 获取一条数据
     *
     * @param position 位置
     * @return 数据
     */
    open fun getItem(position: Int): DATA? {
        if (position < 0) {
            return null
        }
        if (position >= itemCount) {
            return null
        }
        return if (getDataPosition(position) >= mDataList.size) {
            null
        } else mDataList.getOrNull(getDataPosition(position))
    }

    /**
     * 刷新所有数据
     *
     * @param items 数据
     */
    @SuppressLint("NotifyDataSetChanged")
    open fun notifyData(items: List<DATA>?) {
        clear()
        if (items.isNullOrEmpty()) {
            return
        }
        mDataList.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * 刷新部分数据
     *
     * @param items 数据
     */
    fun notifyItemData(items: List<DATA>?, position: Int) {
        clear()
        if (items.isNullOrEmpty()) {
            return
        }
        mDataList.addAll(items)
        notifyItemChanged(position)
    }

    /**
     * 清空数据
     */
    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        val dataSize = itemCount
        mDataList.clear()
        if (0 == dataSize) {
            return
        }
        notifyDataSetChanged()
    }
}