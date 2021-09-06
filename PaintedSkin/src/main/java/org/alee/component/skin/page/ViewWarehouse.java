package org.alee.component.skin.page;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.collection.SparseStack;
import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.pack.IThemeSkinPack;
import org.alee.component.skin.parser.ThemeSkinExecutorBuilderManager;
import org.alee.component.skin.service.ThemeSkinService;
import org.alee.component.skin.util.ObjectMemoryAddress;
import org.alee.component.skin.util.PrintUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class ViewWarehouse implements IEnableThemeSkinViewWarehouse {
    private final SparseStack<EnabledThemeSkinView> mViewStack;

    ViewWarehouse() {
        mViewStack = new SparseStack<>();
    }

    private void addSkinExecutor(@NonNull View view, @NonNull ISkinExecutor executor) {
        synchronized (mViewStack) {
            EnabledThemeSkinView skinView = mViewStack.get(ObjectMemoryAddress.getAddress(view));
            if (null == skinView) {
                skinView = new EnabledThemeSkinView(view);
            }
            skinView.addSkinExecutor(executor);
            mViewStack.put(ObjectMemoryAddress.getAddress(view), skinView);
        }
        if (IThemeSkinPack.SkinPackType.TYPE_DEFAULT != ThemeSkinService.getInstance().getCurrentThemeSkinPack().getSkinPackType()) {
            executor.execute(view);
        }
    }

    /**
     * 动态添加允许换肤的View
     *
     * @param view     可以换肤的View
     * @param elements 动态皮肤属性
     */
    @Override
    public void addEnabledThemeSkinView(@NonNull View view, @NonNull SkinElement... elements) {
        if (null == elements || 0 >= elements.length) {
            return;
        }
        for (SkinElement element : elements) {
            if (null == element) {
                continue;
            }
            if (!ThemeSkinExecutorBuilderManager.getInstance().isValidResources(element.getResourcesId())) {
                continue;
            }
            if (TextUtils.isEmpty(element.getResourcesType())) {
                ThemeSkinExecutorBuilderManager.getInstance().appendAttr(view.getContext(), element);
            }
            ISkinExecutor executor = ThemeSkinExecutorBuilderManager.getInstance().obtainSkinExecutor(view, element);
            if (null == executor) {
                continue;
            }
            addSkinExecutor(view, executor);
        }
    }


    /**
     * 动态添加允许换肤的View
     *
     * @param view         可以换肤的View
     * @param attributeSet 动态皮肤属性
     */
    @Override
    public void addEnabledThemeSkinView(@NonNull View view, @NonNull AttributeSet attributeSet) {
        addEnabledThemeSkinView(view, ThemeSkinExecutorBuilderManager.getInstance().parseElement(view.getContext(), attributeSet));
    }

    /**
     * 应用当前的主题皮肤
     */
    @Override
    public void applyThemeSkin() {
        PrintUtil.getInstance().printD("开始换肤,待换肤View数量：" + mViewStack.size());
        synchronized (mViewStack) {
            for (int i = mViewStack.size() - 1; i >= 0; i--) {
                EnabledThemeSkinView view = mViewStack.valueAt(i);
                if (null == view) {
                    continue;
                }
                if (!view.isValid()) {
                    continue;
                }
                view.applyThemeSkin();
            }
        }
    }

    /**
     * 释放并回收资源
     */
    @Override
    public void gc() {
        synchronized (mViewStack) {
            boolean hasNext = !mViewStack.isEmpty();
            while (hasNext) {
                EnabledThemeSkinView temp = mViewStack.pop();
                hasNext = !mViewStack.isEmpty();
                if (null != temp) {
                    temp.gc();
                }
            }
        }
    }
}
