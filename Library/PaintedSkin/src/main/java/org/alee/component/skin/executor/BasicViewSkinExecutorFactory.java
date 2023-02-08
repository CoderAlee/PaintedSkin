package org.alee.component.skin.executor;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public final class BasicViewSkinExecutorFactory {

    public static ISkinExecutor requireSkinExecutor(@NonNull View view, @NonNull SkinElement element) {
        if (null == view || null == element) {
            return null;
        }
        if (view instanceof ImageView) {
            return new ImageViewSkinExecutor<>(element);
        } else if (view instanceof ProgressBar) {
            return new ProgressBarSkinExecutor<>(element);
        } else if (view instanceof CompoundButton) {
            return new CompoundButtonSkinExecutor<>(element);
        } else if (view instanceof TextView) {
            return new TextViewSkinExecutor<>(element);
        } else if (view instanceof ListView) {
            return new ListViewSkinExecutor<>(element);
        } else {
            return new ViewSkinExecutor<>(element);
        }
    }
}
