package org.alee.demo.skin.compat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.ThemeSkinService;
import org.alee.component.skin.template.IViewCreator;


/**
 * 返回按钮适兼容
 *
 * <p> 换肤框架无法创建自定义View，需要通过实现{@link IViewCreator} 接口使框架兼容自定义View的创建，否则无法通过XML进行换肤。
 *
 * @author MingYu.Liu
 * created in 2022/9/23
 */
public final class BackButtonCompat {
    
    public static void init() {
        ThemeSkinService.INSTANCE.getViewCreatorManager().addCreator(new BackButtonFactory());
    }
    
    
    private static class BackButtonFactory implements IViewCreator {
        //
        //        /**
        //         * {@link BackButton} 类名
        //         */
        //        private final static String CLASS_NAME = BackButton.class.getName();
        
        
        @Nullable
        @Override
        public View onCreateView(@Nullable View predecessorOutput, @Nullable View parent, @NonNull Context context, @NonNull String viewName, @NonNull AttributeSet attrs) {
            //            return TextUtils.equals(CLASS_NAME, viewName) ? new BackButton(context, attrs) : predecessorOutput;
            return predecessorOutput;
        }
    }
}
