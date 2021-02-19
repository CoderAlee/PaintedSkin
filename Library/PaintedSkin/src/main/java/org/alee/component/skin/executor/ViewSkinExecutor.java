package org.alee.component.skin.executor;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_BACKGROUND;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_FOREGROUND;

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
                view.setForeground(drawable);
                break;
            default:
                break;
        }
    }
}
