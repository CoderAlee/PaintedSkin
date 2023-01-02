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
        Drawable[] drawables = view.getCompoundDrawables();
        Drawable old;
        switch (attrName) {
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_LEFT:
                old = drawables[0];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawables(drawable, drawables[1], loadEndDrawable(view), drawables[3]);
                } else {
                    view.setCompoundDrawablesWithIntrinsicBounds(drawable, drawables[1], loadEndDrawable(view), drawables[3]);
                }
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_START:
                old = drawables[0];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawablesRelative(drawable, drawables[1], loadEndDrawable(view), drawables[3]);
                } else {
                    view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawables[1], loadEndDrawable(view), drawables[3]);
                }
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_TOP:
                old = drawables[1];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawablesRelative(loadStartDrawable(view), drawable, loadEndDrawable(view), drawables[3]);
                } else {
                    view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), drawable, loadEndDrawable(view), drawables[3]);
                }
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_RIGHT:
                old = drawables[2];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawables(loadStartDrawable(view), drawables[1], drawable, drawables[3]);
                } else {
                    view.setCompoundDrawablesWithIntrinsicBounds(loadStartDrawable(view), drawables[1], drawable, drawables[3]);
                }
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_END:
                old = drawables[2];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawablesRelative(loadStartDrawable(view), drawables[1], drawable, drawables[3]);
                } else {
                    view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), drawables[1], drawable, drawables[3]);
                }
                break;
            case DefaultExecutorBuilder.ATTRIBUTE_DRAWABLE_BOTTOM:
                old = drawables[3];
                if (null != old) {
                    drawable.setBounds(old.getBounds());
                    view.setCompoundDrawablesRelative(loadStartDrawable(view), drawables[1], loadEndDrawable(view), drawable);
                } else {
                    view.setCompoundDrawablesRelativeWithIntrinsicBounds(loadStartDrawable(view), drawables[1], loadEndDrawable(view), drawable);
                }
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
