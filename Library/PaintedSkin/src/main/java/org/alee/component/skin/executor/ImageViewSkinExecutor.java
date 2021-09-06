package org.alee.component.skin.executor;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TINT;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public class ImageViewSkinExecutor<T extends ImageView> extends ViewSkinExecutor<T> {
    /**
     * 换肤支持的属性 填充内容
     */
    protected static final String ATTRIBUTE_SRC = "src";

    public ImageViewSkinExecutor(@NonNull SkinElement fullElement) {
        super(fullElement);
    }

    @Override
    protected void applyColor(@NonNull T view, @NonNull ColorStateList colorStateList, @NonNull String attrName) {
        super.applyColor(view, colorStateList, attrName);
        switch (attrName) {
            case ATTRIBUTE_SRC:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    applyDrawable(view, new ColorStateListDrawable(colorStateList), attrName);
                } else {
                    applyColor(view, colorStateList.getDefaultColor(), attrName);
                }
                break;
            case ATTRIBUTE_TINT:
                view.setImageTintList(colorStateList);
                break;
            default:
                break;
        }

    }

    @Override
    protected void applyColor(@NonNull T view, int color, @NonNull String attrName) {
        super.applyColor(view, color, attrName);
        switch (attrName) {
            case ATTRIBUTE_SRC:
                applyDrawable(view, new ColorDrawable(color), attrName);
                break;
            default:
                break;
        }
    }

    @Override
    protected void applyDrawable(@NonNull T view, @NonNull Drawable drawable, @NonNull String attrName) {
        super.applyDrawable(view, drawable, attrName);
        switch (attrName) {
            case ATTRIBUTE_SRC:
                view.setImageDrawable(drawable);
                break;
            default:
                break;
        }
    }
}
