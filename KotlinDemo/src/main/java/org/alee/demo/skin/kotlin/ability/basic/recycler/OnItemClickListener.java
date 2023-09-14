package org.alee.demo.skin.kotlin.ability.basic.recycler;

import android.view.View;

/**
 * RecyclerView Item 点击监听
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/28
 */
public interface OnItemClickListener {
    /**
     * item点击事件回调
     *
     * @param view     item
     * @param position 位置
     */
    void onItemClick(View view, int position);

    /**
     * item长按事件回调
     *
     * @param view     item
     * @param position 位置
     */
    default void onItemLongClick(View view, int position) {
    }
}

