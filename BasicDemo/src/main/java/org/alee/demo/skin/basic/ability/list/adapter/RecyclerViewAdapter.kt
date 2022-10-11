package org.alee.demo.skin.basic.ability.list.adapter

import android.view.ViewGroup
import android.widget.TextView
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.recycler.BaseHolder
import org.alee.demo.skin.basic.ability.basic.recycler.BaseRecyclerAdapter
import org.alee.demo.skin.basic.ability.basic.recycler.OnItemClickListener
import org.alee.demo.skin.basic.ability.util.stringResource

/**
 * RecyclerView 适配器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/24
 *
 */
internal class RecyclerViewAdapter(listener: OnItemClickListener) :
    BaseRecyclerAdapter<Int, BaseHolder<Int>>(listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Int> {
        return Item(parent)
    }

    override fun onBindViewHolder(holder: BaseHolder<Int>, position: Int) {
        getItem(position)?.run {
            super.onBindViewHolder(holder, position)
            holder.bindData(this)
            bindClickListener(holder.itemView)
        }
    }
}

private class Item(parent: ViewGroup) : BaseHolder<Int>(parent, R.layout.layout_recycler_view_item) {

    private lateinit var mNameTv: TextView

    private lateinit var mAddressTv: TextView

    /**
     * 初始化view
     */
    override fun findView() {
        mNameTv = findView(R.id.txt_name)
        mAddressTv = findView(R.id.txt_address)
    }

    /**
     * 绑定数据
     *
     * @param data       item 需要展示的数据
     * @param position   item 位置
     * @param isSelected item 是否选中
     */
    override fun bindData(data: Int, position: Int, isSelected: Boolean) {
        mNameTv.text = R.string.list_demo_recycler_item_nam.stringResource(data)
        mAddressTv.text = R.string.list_demo_recycler_item_address.stringResource(data)
    }

}

