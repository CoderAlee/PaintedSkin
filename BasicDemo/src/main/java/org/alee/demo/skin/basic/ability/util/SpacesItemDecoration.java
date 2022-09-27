package org.alee.demo.skin.basic.ability.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * RecyclerView Item 装饰器
 *
 * <p> 用于设置item间距
 *
 * @author MingYu.Liu
 * <p>
 * created in 2021/5/12
 */
public final class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * 两条item横向间距
     */
    private final int mHorizontalSpace;
    /**
     * 两条item纵向间距
     */
    private final int mVerticalSpace;

    public SpacesItemDecoration(int space) {
        this(space, space);
    }

    public SpacesItemDecoration(int horizontalSpace, int verticalSpace) {
        mHorizontalSpace = horizontalSpace;
        mVerticalSpace = verticalSpace;
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            int orientation = linearLayoutManager.getOrientation();
            if (orientation == RecyclerView.HORIZONTAL) {
                setHorizontalSpace(parent.getChildAdapterPosition(view), outRect);
            } else {
                setVerticalSpace(parent.getChildAdapterPosition(view), outRect);
            }
        }
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            int orientation = gridLayoutManager.getOrientation();
            int spanCount = gridLayoutManager.getSpanCount();
            int position = parent.getChildAdapterPosition(view);
            if (orientation == RecyclerView.HORIZONTAL) {

            } else {
                int row = position / spanCount;
                int line = position % spanCount;
                setHorizontalSpace(row, line, outRect);
                setVerticalSpace(row, line, outRect);
            }
        }
    }

    /**
     * 设置横向间距
     *
     * @param position item 位置
     * @param rect     矩形
     */
    private void setHorizontalSpace(int position, Rect rect) {
        rect.left = mHorizontalSpace;
        if (0 == position) {
            rect.left = 0;
        }
    }

    /**
     * 设置纵向间距
     *
     * @param position item 位置
     * @param rect     矩形
     */
    private void setVerticalSpace(int position, Rect rect) {
        rect.top = mVerticalSpace;
        if (0 == position) {
            rect.top = 0;
        }
    }

    private void setHorizontalSpace(int row, int line, Rect rect) {
        rect.left = mHorizontalSpace;
        if (0 == line) {
            rect.left = 0;
        }
    }

    private void setVerticalSpace(int row, int line, Rect rect) {
        rect.top = mVerticalSpace;
        if (0 == row) {
            rect.top = 0;
        }
    }

}
