package org.alee.component.skin.page;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.factory2.ExpandedFactory2Manager;
import org.alee.component.skin.factory2.ThemeSkinFactory2;
import org.alee.component.skin.service.ThemeSkinService;
import org.alee.component.skin.util.PrintUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
abstract class BaseWindowProxy implements IWindowProxy {
    /**
     * {@link IEnableThemeSkinViewWarehouse}
     */
    private final IEnableThemeSkinViewWarehouse mEnableThemeSkinViewWarehouse;
    /**
     * 标识 当前代理的窗口是否可见
     */
    private boolean mIsVisible;
    /**
     * 标识 当onResume 时 是否需要进行换肤
     */
    private boolean mIsNeedApplyThemeSkin;

    BaseWindowProxy() {
        mEnableThemeSkinViewWarehouse = new ViewWarehouse();
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this);
    }

    void bindLayoutInflateFactory2(@NonNull LayoutInflater layoutInflater, @NonNull ExpandedFactory2Manager factory2Manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            adaptationQ(layoutInflater, factory2Manager);
            return;
        }
        LayoutInflater.Factory2 originalFactory2 = layoutInflater.getFactory2();
        LayoutInflaterMapping.mFactorySet.set(layoutInflater, false);
        LayoutInflaterMapping.mFactory2.set(layoutInflater, null);
        LayoutInflaterMapping.mFactory.set(layoutInflater, null);
        if (originalFactory2 instanceof ThemeSkinFactory2) {
            originalFactory2 = null;
        }
        LayoutInflater.Factory2 originalPrivateFactory2 = LayoutInflaterMapping.mPrivateFactory.get(layoutInflater);
        if (originalPrivateFactory2 instanceof ThemeSkinFactory2) {
            originalPrivateFactory2 = null;
        }
        layoutInflater.setFactory2(new ThemeSkinFactory2(new OriginalFactory2(originalFactory2, originalFactory2, originalPrivateFactory2, originalPrivateFactory2), factory2Manager, mEnableThemeSkinViewWarehouse));
    }

    private void adaptationQ(@NonNull LayoutInflater layoutInflater, @NonNull ExpandedFactory2Manager factory2Manager) {
        LayoutInflater.Factory2 originalFactory2 = layoutInflater.getFactory2();
        LayoutInflaterMapping.mFactory2.set(layoutInflater, null);
        LayoutInflaterMapping.mFactory.set(layoutInflater, null);
        if (originalFactory2 instanceof ThemeSkinFactory2) {
            originalFactory2 = null;
        }
        LayoutInflater.Factory2 originalPrivateFactory2 = LayoutInflaterMapping.mPrivateFactory.get(layoutInflater);
        if (originalPrivateFactory2 instanceof ThemeSkinFactory2) {
            originalPrivateFactory2 = null;
        }
        ThemeSkinFactory2 factory2 = new ThemeSkinFactory2(new OriginalFactory2(originalFactory2, originalFactory2, originalPrivateFactory2, originalPrivateFactory2), factory2Manager, mEnableThemeSkinViewWarehouse);
        LayoutInflaterMapping.mFactory2.set(layoutInflater, factory2);
        LayoutInflaterMapping.mFactory.set(layoutInflater, factory2);
    }

    @Override
    public final void addEnabledThemeSkinView(@NonNull View view, @NonNull SkinElement... elements) {
        mEnableThemeSkinViewWarehouse.addEnabledThemeSkinView(view, elements);
    }

    @Override
    public final void onVisible() {
        mIsVisible = true;
        if (mIsNeedApplyThemeSkin) {
            onThemeSkinSwitch();
        }
    }

    @Override
    public final void Invisible() {
        mIsVisible = false;
    }

    @Override
    public final void onThemeSkinSwitch() {
        if (!mIsVisible && !isApplyInInvisible()) {
            mIsNeedApplyThemeSkin = true;
            return;
        }
        mIsNeedApplyThemeSkin = false;
        long startTime = System.currentTimeMillis();
        mEnableThemeSkinViewWarehouse.applyThemeSkin();
        PrintUtil.getInstance().printD("Skin resurfacing takes time: [ " + (System.currentTimeMillis() - startTime) + " ] ms");
    }

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    protected abstract boolean isApplyInInvisible();

    @Override
    protected void finalize() {
        onDestroy();
    }

    final void onDestroy() {
        ThemeSkinService.getInstance().unsubscribeSwitchThemeSkin(this);
        mEnableThemeSkinViewWarehouse.gc();
    }

}
