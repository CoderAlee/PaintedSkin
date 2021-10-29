package org.alee.demo.skin;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.alee.demo.skin.rv.BaseHolder;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/29
 */
class ListItem extends BaseHolder<String> {
    private TextView mContentTv;

    public ListItem(@NonNull ViewGroup parent) {
        super(parent, R.layout.layout_list_item);
    }

    public ListItem(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * 初始化view
     */
    @Override
    protected void getView() {
        mContentTv = findView(R.id.txt_content);
    }

    /**
     * 设置部分view的默认属性
     */
    @Override
    protected void setViewDefaultValues() {

    }

    /**
     * 绑定数据
     *
     * @param s item 需要展示的数据
     */
    @Override
    public void bindData(@NonNull String s) {
        mContentTv.setText(s);
    }
}
