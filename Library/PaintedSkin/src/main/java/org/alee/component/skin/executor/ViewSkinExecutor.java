package org.alee.component.skin.executor;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_BACKGROUND;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_BKG_TINT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_FOREGROUND;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_FRG_TINT;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public class ViewSkinExecutor<T extends View> extends BaseSkinExecutor<T> {

    public ViewSkinExecutor(@NonNull SkinElement fullElement) {
        super(fullElement);
    }

    @Override
    protected void applyColor(@NonNull T view, @NonNull ColorStateList colorStateList, @NonNull String attrName) {
        switch (attrName) {
            case ATTRIBUTE_BACKGROUND:
            case ATTRIBUTE_FOREGROUND:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    applyDrawable(view, new ColorStateListDrawable(colorStateList), attrName);
                } else {
                    applyColor(view, colorStateList.getDefaultColor(), attrName);
                }
                break;
            case ATTRIBUTE_BKG_TINT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setBackgroundTintList(colorStateList);
                }
                break;
            case ATTRIBUTE_FRG_TINT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setForegroundTintList(colorStateList);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void applyColor(@NonNull T view, int color, @NonNull String attrName) {
        switch (attrName) {
            case ATTRIBUTE_BACKGROUND:
                view.setBackgroundColor(color);
                break;
            case ATTRIBUTE_FOREGROUND:
                applyDrawable(view, new ColorDrawable(color), attrName);
                break;
            case ATTRIBUTE_BKG_TINT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setBackgroundTintList(ColorStateList.valueOf(color));
                }
                break;
            case ATTRIBUTE_FRG_TINT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setForegroundTintList(ColorStateList.valueOf(color));
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void applyDrawable(@NonNull T view, @NonNull Drawable drawable, @NonNull String attrName) {
        switch (attrName) {
            case ATTRIBUTE_BACKGROUND:
                view.setBackground(drawable);
                break;
            case ATTRIBUTE_FOREGROUND:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setForeground(drawable);
                }
                break;
            default:
                break;
        }
    }
}
