package org.alee.demo.skin.rv;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/3/11
 * @description: xxxx
 *
 *********************************************************/
public abstract class BaseRecyclerAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements OnItemClickListener {
    /**
     * item点击事件监听
     */
    protected OnItemClickListener mOnItemClickListener;
    /**
     * 数据源
     */
    protected List<E> mDatas = new ArrayList<>();

    public BaseRecyclerAdapter(OnItemClickListener listener) {
        this(listener, new ArrayList<>());
    }

    public BaseRecyclerAdapter(OnItemClickListener listener, @NonNull List<E> datas) {

        if (listener == null) {
            mOnItemClickListener = this;
        } else {
            mOnItemClickListener = listener;
        }

        mDatas.addAll(datas);
    }

    /**
     * 为item绑定点击事件
     *
     * @param view item
     */
    protected void bindClickListener(View view) {
        view.setOnClickListener(view1 -> {
            mOnItemClickListener.onItemClick(view1, getPositionByData((E) view1.getTag()));
        });
        view.setOnLongClickListener(view12 -> {
            mOnItemClickListener.onItemLongClick(view12, getPositionByData((E) view12.getTag()));
            return true;
        });
    }

    /**
     * 根据数据获取位置
     *
     * @param data 数据
     * @return 位置
     */
    public int getPositionByData(E data) {
        int position = mDatas.indexOf(data);
        if (position >= 0) {
            return getHaveHeaderPosition(position);
        }
        for (E item : mDatas) {
            if (item == null) {
                continue;
            }
            if (isEquals(item, data)) {
                position = mDatas.indexOf(item);
                break;
            }
        }
        return getHaveHeaderPosition(position);
    }

    /**
     * 获取带有header后的位置
     *
     * @param position 位置
     * @return 结果
     */
    protected int getHaveHeaderPosition(int position) {
        if (0 > position) {
            return position;
        }
        return position + getHeaderSize();
    }

    /**
     * 两条数据是否相同
     *
     * @param first  第一条数据
     * @param second 第二条数据
     * @return 结果
     */
    protected abstract boolean isEquals(E first, Object second);

    /**
     * 获取当前header的数量
     *
     * @return header的数量
     */
    protected abstract int getHeaderSize();

    /**
     * 添加头数据
     *
     * @param items 数据
     */
    public void addHeaderItem(List<E> items) {
        if (items == null || items.size() <= 0) {
            return;
        }
        mDatas.addAll(0, items);
        updateDataSize();
        notifyItemRangeInserted(getHeaderSize(), getItemCount() - 1);
    }

    /**
     * 更新数据大小
     */
    protected void updateDataSize() {

    }

    /**
     * 删除数据
     *
     * @param position 位置
     */
    public void onDeleteItem(int position) {
        if (position >= getItemCount()) {
            return;
        }
        mDatas.remove(getDataPosition(position));
        updateDataSize();
        notifyItemRemoved(position);
    }

    /**
     * 获取数据的真实位置
     *
     * @param position 在列表中的位置
     * @return 在数据集合中的位置
     */
    protected int getDataPosition(int position) {
        int dataPosition = position - getHeaderSize();
        return Math.max(dataPosition, 0);
    }

    /**
     * 刷新某一条item
     *
     * @param data 数据
     */
    public void onUpdateItem(E data) {
        onUpdateItem(getDatas().indexOf(data), data, new byte[0]);
    }

    /**
     * 刷新某一条item 只更新部分view
     *
     * @param position        位置
     * @param data            数据
     * @param needRefreshView item中需要刷新的view
     */
    public void onUpdateItem(int position, E data, Object... needRefreshView) {
        if (data == null) {
            return;
        }
        if (position < 0) {
            //当position < 0时  此次刷新不执行
            return;
        }
        if (getDataPosition(position) >= mDatas.size()) {
            return;
        }
        mDatas.set(getDataPosition(position), data);
        if (null == needRefreshView || 0 == needRefreshView.length) {
            notifyItemChanged(position);
        } else {
            notifyItemChanged(position, needRefreshView);
        }
    }

    public List<E> getDatas() {
        return mDatas;
    }

    /**
     * 添加尾数据
     *
     * @param items 数据
     */
    public void addFooterItem(List<E> items) {
        if (items == null) {
            return;
        }
        int positionStart = mDatas.size();
        mDatas.addAll(items);
        updateDataSize();
        notifyItemRangeInserted(positionStart, mDatas.size() - 1);
    }

    /**
     * 刷新所有数据
     *
     * @param array 数据
     */
    public void notifyData(E... array) {
        if (null == array) {
            notifyData(new ArrayList<>());
            return;
        }
        List<E> temp = new ArrayList<>(array.length);
        Collections.addAll(temp, array);
        notifyData(temp);
    }

    /**
     * 刷新所有数据
     *
     * @param items 数据
     */
    public void notifyData(List<E> items) {
        clear();
        if (null == items || items.isEmpty()) {
            return;
        }
        mDatas.addAll(items);
        updateDataSize();
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        int dataSize = getItemCount();
        mDatas.clear();
        if (dataSize == 0) {
            return;
        }
        notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder == null) {
            return;
        }
        holder.itemView.setTag(getItem(position));
    }


    @Override
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        if (holder == null) {
            return;
        }
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }
        onRefreshView(holder, position, payloads);
    }


    @Override
    public int getItemCount() {
        if (mDatas.size() == 0) {
            return getHeaderSize();
        }
        return mDatas.size() + getHeaderSize();
    }

    /**
     * 更新item的部分view
     *
     * @param holder   item
     * @param position 位置
     * @param payloads 需要更新的view标识
     */
    protected void onRefreshView(VH holder, int position, List<Object> payloads) {

    }

    /**
     * 获取一条数据
     *
     * @param position 位置
     * @return 数据
     */
    public E getItem(int position) {
        if (position < 0) {
            return null;
        }
        if (position >= getItemCount()) {
            return null;
        }
        // by liumy 2018-10-23
        if (getDataPosition(position) >= mDatas.size()) {
            return null;
        }
        return mDatas.get(getDataPosition(position));
    }


}
