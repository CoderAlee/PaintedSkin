package org.alee.demo.skin.compat;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import org.alee.component.skin.ThemeSkinService;
import org.alee.component.skin.template.IViewCreator;


/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/5/27
 */
public final class AppCompatButtonCompat {
    
    public static void init() {
        ThemeSkinService.INSTANCE.getViewCreatorManager().addCreator(new AppCompatButtonFactory());
    }
    
    
    private static class AppCompatButtonFactory implements IViewCreator {
        
        /**
         * {@link AppCompatButton} 类名
         */
        private final static String CLASS_NAME = AppCompatButton.class.getName();
        
        @Nullable
        @Override
        public View onCreateView(@Nullable View predecessorOutput, @Nullable View parent, @NonNull Context context, @NonNull String viewName, @NonNull AttributeSet attrs) {
            return TextUtils.equals(CLASS_NAME, viewName) ? new AppCompatButton(context, attrs) : predecessorOutput;
        }
    }
}
