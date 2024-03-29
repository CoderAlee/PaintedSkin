package org.alee.demo.skin.basic.ability.list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.alee.demo.skin.basic.ability.R

/**
 * 网格适配器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/24
 *
 */
class GridViewAdapter(private val data: List<String>) : BaseAdapter() {

    override fun getCount() = data.size

    override fun getItem(position: Int) = data[position]


    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return Item(convertView, parent.context).apply {
            bindData(getItem(position))
        }.rootView
    }

    private class Item(convertView: View?, context: Context) {

        @SuppressLint("InflateParams")
        val rootView: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.layout_grid_view_item, null)

        private val mContentTv = rootView.findViewById<TextView>(R.id.txt_content)

        fun bindData(content: String) {
            mContentTv.text = content
        }
    }
}