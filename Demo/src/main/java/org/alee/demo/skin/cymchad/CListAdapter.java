package org.alee.demo.skin.cymchad;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.page.WindowManager;
import org.alee.component.skin.service.ThemeSkinService;
import org.alee.demo.skin.R;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2021/10/29
 */
public final class CListAdapter extends BaseQuickAdapter<String, CListItem> {

    private static final int TYPE_A = 0x1000;
    private static final int TYPE_B = 0x1001;

    public CListAdapter() {
        super(0);
    }

    @Override
    protected void convert(@NonNull CListItem listItem, String s) {
        listItem.bindData(s);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return 0 == position % 2 ? TYPE_A : TYPE_B;
    }

    @NonNull
    @Override
    protected CListItem onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_A:
                return new CListItem(parent);
            case TYPE_B:
            default:
                return createBaseViewHolder(createTypeB(parent));
        }
    }

    private View createTypeB(@NonNull ViewGroup parent) {
        FrameLayout root = new FrameLayout(parent.getContext());
        LinearLayout layout = new LinearLayout(root.getContext());
        layout.setBackgroundColor(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getColor(R.color.yellow));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        root.addView(layout, layoutParams);
        TextView tv = new TextView(layout.getContext());
        tv.setId(R.id.txt_content);
        tv.setTextColor(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getColor(R.color.black));
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.gravity = Gravity.CENTER;
        layout.addView(tv);
        WindowManager.getInstance().getWindowProxy(parent.getContext()).addEnabledThemeSkinView(layout, new SkinElement("background", R.color.yellow));
        WindowManager.getInstance().getWindowProxy(parent.getContext()).addEnabledThemeSkinView(tv, new SkinElement("textColor", R.color.black));
        return root;
    }
}
