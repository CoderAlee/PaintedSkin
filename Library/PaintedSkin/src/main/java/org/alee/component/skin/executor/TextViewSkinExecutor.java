package org.alee.component.skin.executor;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HINT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_LINK;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

import org.alee.component.skin.parser.DefaultExecutorBuilder;


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

    /**
     * 重新为TextViewCompoundDrawables设置Tint，避免出现先设置了Tint后设置TextViewCompoundDrawables导致的Tint失效问题
     *
     * @param view View
     */
    private void resetCompoundDrawablesTint(T view) {
        ColorStateList tint = TextViewCompat.getCompoundDrawableTintList(view);
        if (null == tint) {
            return;
        }
        applyColor(view, tint, DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TINT);
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
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TINT:
                TextViewCompat.setCompoundDrawableTintList(view, colorStateList);
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
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TINT:
                applyColor(view, ColorStateList.valueOf(color), attrName);
                break;
            default:
                break;
        }
    }

    @Override
    protected void applyDrawable(@NonNull T view, @NonNull Drawable drawable, @NonNull String attrName) {
        super.applyDrawable(view, drawable, attrName);
        Drawable[] drawables = TextViewCompat.getCompoundDrawablesRelative(view);
        switch (attrName) {
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_LEFT:
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_START:
                inheritOriginalProperty(drawable, drawables[0]);
                setCompoundDrawables(view, drawable, drawables[1], drawables[2], drawables[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TOP:
                inheritOriginalProperty(drawable, drawables[1]);
                setCompoundDrawables(view, drawables[0], drawable, drawables[2], drawables[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_RIGHT:
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_END:
                inheritOriginalProperty(drawable, drawables[2]);
                setCompoundDrawables(view, drawables[0], drawables[1], drawable, drawables[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_BOTTOM:
                inheritOriginalProperty(drawable, drawables[3]);
                setCompoundDrawables(view, drawables[0], drawables[1], drawables[2], drawable);
                break;
            default:
                break;
        }
    }


    /**
     * 使Drawable 继承原有Drawable 的部分属性
     *
     * @param newDrawable 新的Drawable
     * @param oldDrawable 旧的Drawable
     */
    private void inheritOriginalProperty(Drawable newDrawable, @Nullable Drawable oldDrawable) {
        if (null == oldDrawable) {
            newDrawable.setBounds(0, 0, newDrawable.getIntrinsicWidth(), newDrawable.getIntrinsicHeight());
        } else {
            newDrawable.setBounds(oldDrawable.getBounds());
            newDrawable.setState(oldDrawable.getState());
        }
    }

    /**
     * 以兼容的方式设置TextViewCompoundDrawables
     *
     * @param view   View
     * @param left   左侧Drawable
     * @param top    顶部Drawable
     * @param right  右侧Drawable
     * @param bottom 底部Drawable
     */
    private void setCompoundDrawables(T view, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        TextViewCompat.setCompoundDrawablesRelative(view, left, top, right, bottom);
        resetCompoundDrawablesTint(view);
    }

}
