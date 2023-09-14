package org.alee.demo.skin.compat;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import org.alee.component.skin.ThemeSkinService;
import org.alee.component.skin.template.IViewCreator;

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 */
public final class NestedScrollViewCompat {
    
    public static void init() {
        ThemeSkinService.INSTANCE.getViewCreatorManager().addCreator(new NestedScrollViewFactory());
    }
    
    private static class NestedScrollViewFactory implements IViewCreator {
        
        /**
         * {@link NestedScrollView} 类名
         */
        private final static String CLASS_NAME = NestedScrollView.class.getName();
        
        @Nullable
        @Override
        public View onCreateView(@Nullable View predecessorOutput, @Nullable View parent, @NonNull Context context, @NonNull String viewName, @NonNull AttributeSet attrs) {
            return TextUtils.equals(CLASS_NAME, viewName) ? new NestedScrollView(context, attrs) : predecessorOutput;
        }
    }
}
