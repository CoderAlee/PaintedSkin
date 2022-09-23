package org.alee.demo.skin.rv;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/3/11
 * @description: xxxx
 *
 *********************************************************/
public abstract class BaseHolder<DATA> extends RecyclerView.ViewHolder {
    /**
     * Tag - key
     */
    private static final int KEY_TAG = "Holder_Default_Tag".hashCode();
    /**
     * {@link Context}
     */
    private final Context mContext;
    /**
     * 所有注册的view
     */
    private final SparseArray<View> mViewCache;

    public BaseHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
    }

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mViewCache = new SparseArray<>();
        getView();
        setViewDefaultValues();
    }

    /**
     * 初始化view
     */
    protected abstract void getView();

    /**
     * 设置部分view的默认属性
     */
    protected abstract void setViewDefaultValues();

    /**
     * 绑定数据
     *
     * @param data       item 需要展示的数据
     * @param position   item 位置
     * @param isSelected item 是否选中
     */
    public void bindData(@NonNull DATA data, int position, boolean isSelected) {
        bindData(data, position);
    }

    /**
     * 绑定数据
     *
     * @param data     item 需要展示的数据
     * @param position item 位置
     */
    public void bindData(@NonNull DATA data, int position) {
        bindData(data);
    }

    /**
     * 绑定数据
     *
     * @param data item 需要展示的数据
     */
    @Deprecated
    public abstract void bindData(@NonNull DATA data);

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
     */
    public final <E extends View> E findView(int viewId) {
        if (null == getRootView()) {
            throw new RuntimeException("Please set the view to be displayed by the ViewHolder");
        }
        E view = (E) mViewCache.get(viewId);
        if (null == view) {
            view = itemView.findViewById(viewId);
            mViewCache.put(viewId, view);
        }
        return view;
    }

    public final View getRootView() {
        return itemView;
    }

    /**
     * 绑定数据
     *
     * @param data 要绑定的数据
     */
    public void bindTag(DATA data) {
        getRootView().setTag(KEY_TAG, data);
    }

    public DATA getDefaultTag() {
        return (DATA) getRootView().getTag(KEY_TAG);
    }


    protected Context getContext() {
        return mContext;
    }

}
