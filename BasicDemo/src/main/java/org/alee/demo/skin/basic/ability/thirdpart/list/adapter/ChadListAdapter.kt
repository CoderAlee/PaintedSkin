package org.alee.demo.skin.basic.ability.thirdpart.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.util.layout.ImageView
import org.alee.demo.skin.basic.ability.util.layout.background_drawable
import org.alee.demo.skin.basic.ability.util.layout.layout_height
import org.alee.demo.skin.basic.ability.util.layout.layout_width
import org.alee.demo.skin.basic.ability.util.layout.match_parent
import org.alee.demo.skin.basic.ability.util.layout.scale_center
import org.alee.demo.skin.basic.ability.util.layout.src

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */
internal class ChadListAdapter : BaseQuickAdapter<Int, BaseViewHolder>(0) {

    private companion object {
        private const val TYPE_NUMBER = 0x1000
        private const val TYPE_DELETE = 0x1001
    }

    class Item(parent: ViewGroup) : BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_chad_list_item, parent, false)) {

        fun bindData(number: Int) {
            (itemView as? TextView)?.text = number.toString()
        }
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * 实现此方法，并使用 helper 完成 item 视图的操作
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(holder: BaseViewHolder, item: Int) {
        if (holder is Item) {
            holder.bindData(item)
        }
    }

    override fun getDefItemViewType(position: Int): Int {
        return if (0 > getItem(position)) TYPE_DELETE else TYPE_NUMBER
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_NUMBER -> Item(parent)
            else -> createBaseViewHolder(buildDeleteBtn(parent))
        }
    }

    private fun buildDeleteBtn(parent: ViewGroup) = parent.context.ImageView {
        layout_width = match_parent
        layout_height = R.dimen.tp_44
        scaleType = scale_center
        background_drawable = R.drawable.shape_bg_black_200_corners_tp_10
        this src R.drawable.ic_keyboard_ic_delete
    }
}

