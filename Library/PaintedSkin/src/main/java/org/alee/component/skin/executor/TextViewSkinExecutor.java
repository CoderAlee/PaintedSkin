package org.alee.component.skin.executor;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HINT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_LINK;


/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public class TextViewSkinExecutor<T extends TextView> extends ViewSkinExecutor<T> {


    public TextViewSkinExecutor(@NonNull SkinElement fullElement) {
        super(fullElement);
    }

    @Override
    protected void applyColor(@NonNull T view, @NonNull ColorStateList colorStateList, @NonNull String attrName) {
        super.applyColor(view, colorStateList, attrName);
        switch (attrName) {
            case ATTRIBUTE_TEXT_COLOR:
                view.setTextColor(colorStateList);
                break;
            case ATTRIBUTE_TEXT_COLOR_HINT:
                view.setHintTextColor(colorStateList);
                break;
            case ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT:
                view.setHighlightColor(colorStateList.getDefaultColor());
                break;
            case ATTRIBUTE_TEXT_COLOR_LINK:
                view.setLinkTextColor(colorStateList);
                break;
            default:
                break;
        }
    }

    @Override
    protected void applyColor(@NonNull T view, int color, @NonNull String attrName) {
        super.applyColor(view, color, attrName);
        switch (attrName) {
            case ATTRIBUTE_TEXT_COLOR:
                view.setTextColor(color);
                break;
            case ATTRIBUTE_TEXT_COLOR_HINT:
                view.setHintTextColor(color);
                break;
            case ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT:
                view.setHighlightColor(color);
                break;
            case ATTRIBUTE_TEXT_COLOR_LINK:
                view.setLinkTextColor(color);
                break;
            default:
                break;
        }
    }

    @Override
    protected void applyDrawable(@NonNull T view, @NonNull Drawable drawable, @NonNull String attrName) {
        super.applyDrawable(view, drawable, attrName);
    }
}
