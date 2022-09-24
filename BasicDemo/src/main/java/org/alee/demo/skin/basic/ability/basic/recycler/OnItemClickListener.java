package org.alee.demo.skin.basic.ability.basic.recycler;

import android.view.View;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/3/11
 * @description: xxxx
 *
 *********************************************************/
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

