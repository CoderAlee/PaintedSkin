package org.alee.component.skin.executor;

import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HINT;
import static org.alee.component.skin.parser.DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_LINK;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    view.setCompoundDrawableTintList(colorStateList);
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
        switch (attrName) {
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_LEFT:
                view.setCompoundDrawablesWithIntrinsicBounds(drawable, view.getCompoundDrawables()[1], loadEndDrawable(view), view.getCompoundDrawables()[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_START:
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, view.getCompoundDrawablesRelative()[1], loadEndDrawable(view), view.getCompoundDrawablesRelative()[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TOP:
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), drawable, loadEndDrawable(view), view.getCompoundDrawablesRelative()[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_RIGHT:
                view.setCompoundDrawablesWithIntrinsicBounds(loadStartDrawable(view), view.getCompoundDrawables()[1], drawable, view.getCompoundDrawables()[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_END:
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), view.getCompoundDrawables()[1], drawable, view.getCompoundDrawables()[3]);
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_BOTTOM:
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), view.getCompoundDrawables()[1], loadEndDrawable(view), drawable);
                break;
            default:
                break;
        }
    }

    private Drawable loadEndDrawable(TextView view) {
        Drawable drawable = view.getCompoundDrawablesRelative()[2];
        return null == drawable ? view.getCompoundDrawables()[2] : drawable;
    }

    @Nullable
    private Drawable loadStartDrawable(TextView view) {
        Drawable drawable = view.getCompoundDrawablesRelative()[0];
        return null == drawable ? view.getCompoundDrawables()[0] : drawable;
    }
}
