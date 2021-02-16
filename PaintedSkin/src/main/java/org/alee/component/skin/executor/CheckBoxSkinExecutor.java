package org.alee.component.skin.executor;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_BUTTON;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public class CheckBoxSkinExecutor<T extends CheckBox> extends TextViewSkinExecutor<T> {


    public CheckBoxSkinExecutor(@NonNull SkinElement fullElement) {
        super(fullElement);
    }

    @Override
    protected void applyColor(@NonNull T view, @NonNull ColorStateList colorStateList, @NonNull String attrName) {
        super.applyColor(view, colorStateList, attrName);
        switch (attrName) {
            case ATTRIBUTE_BUTTON:
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
        super.applyColor(view, color, attrName);
        switch (attrName) {
            case ATTRIBUTE_BUTTON:
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
            case ATTRIBUTE_BUTTON:
                view.setButtonDrawable(drawable);
                break;
            default:
                break;
        }
    }
}
