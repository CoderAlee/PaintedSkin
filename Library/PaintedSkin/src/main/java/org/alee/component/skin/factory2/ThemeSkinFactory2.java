package org.alee.component.skin.factory2;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.page.IEnableThemeSkinViewWarehouse;
import org.alee.component.skin.service.Config;
import org.alee.component.skin.util.PrintUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public final class ThemeSkinFactory2 extends InterceptorFactory2 {
    /**
     * 是否使用换肤功能
     */
    private static final String ATTRIBUTE_SKIN_ENABLE = "enable";
    /**
     * 自定义属性命名空间
     */
    private static final String ATTRIBUTE_NAMESPACE = "http://schemas.android.com/android/skin";
    /**
     * {@link IEnableThemeSkinViewWarehouse}
     */
    private final IEnableThemeSkinViewWarehouse mEnableThemeSkinViewWarehouse;

    public ThemeSkinFactory2(@Nullable LayoutInflater.Factory2 originalFactory2, @NonNull ExpandedFactory2Manager factory2Manager, @NonNull IEnableThemeSkinViewWarehouse viewWarehouse) {
        super(originalFactory2, factory2Manager);
        mEnableThemeSkinViewWarehouse = viewWarehouse;
    }

    /**
     * 识别是否支持换肤
     *
     * @param view         view
     * @param attributeSet 属性集
     */
    @Override
    protected void distinguishEnableSkin(@NonNull View view, @NonNull AttributeSet attributeSet) {
        if (!enableReplaceThemeSkin(attributeSet)) {
            return;
        }
        mEnableThemeSkinViewWarehouse.addEnabledThemeSkinView(view, attributeSet);
    }

    /**
     * 校验View 是否支持换肤
     *
     * @param attrs 属性集
     * @return true:支持换肤
     */
    private boolean enableReplaceThemeSkin(@NonNull AttributeSet attrs) {
        if (Config.SkinMode.DO_NOT_REPLACE == Config.getInstance().getSkinMode()) {
            return false;
        }
        try {
            return attrs.getAttributeBooleanValue(ATTRIBUTE_NAMESPACE, ATTRIBUTE_SKIN_ENABLE, Config.SkinMode.REPLACE_MARKED != Config.getInstance().getSkinMode());
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
        return false;
    }


}
