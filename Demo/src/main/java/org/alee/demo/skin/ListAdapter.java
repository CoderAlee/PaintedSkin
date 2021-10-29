package org.alee.demo.skin;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.alee.demo.skin.rv.BaseRecyclerAdapter;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/29
 */
class ListAdapter extends BaseRecyclerAdapter<String, ListItem> {
    public ListAdapter() {
        super(null);
    }

    /**
     * 两条数据是否相同
     *
     * @param first  第一条数据
     * @param second 第二条数据
     * @return 结果
     */
    @Override
    protected boolean isEquals(String first, Object second) {
        return false;
    }

    /**
     * 获取当前header的数量
     *
     * @return header的数量
     */
    @Override
    protected int getHeaderSize() {
        return 0;
    }

    @Override
    public void onBindViewHolder(ListItem holder, int position) {
        holder.bindData(getItem(position));
    }

    @NonNull
    @Override
    public ListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItem(parent);
    }
}
