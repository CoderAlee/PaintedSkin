package org.alee.demo.skin.cymchad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.alee.demo.skin.R;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/29
 */
class CListItem extends BaseViewHolder {

    public CListItem(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false));
    }

    public CListItem(@NonNull View view) {
        super(view);
    }

    void bindData(String content) {
        setText(R.id.txt_content, content);
    }
}
