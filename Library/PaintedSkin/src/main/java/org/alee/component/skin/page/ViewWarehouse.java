package org.alee.component.skin.page;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.collection.SparseStack;
import org.alee.component.skin.exception.ApplySkinException;
import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.parser.ThemeSkinExecutorBuilderManager;
import org.alee.component.skin.util.ObjectMemoryAddress;
import org.alee.component.skin.util.PrintUtil;
import org.alee.component.skin.util.task.TaskPool;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class ViewWarehouse implements IEnableThemeSkinViewWarehouse {
    /**
     * 待换肤View引用队列
     */
    private final ReferenceQueue<View> mReferenceQueue = new ReferenceQueue<>();

    private final SparseStack<EnabledThemeSkinView> mViewStack;

    /**
     * 标识 ViewWarehouse 是否处于活跃状态
     */
    private boolean mIsActive = true;

    ViewWarehouse() {
        mViewStack = new SparseStack<>();
        startGcObserving();
    }

    /**
     * 通过引用队列来逆向释放内存
     * Plan A
     */
    private void startGcObserving() {
        TaskPool.concurrent().post(() -> {
            while (mIsActive) {
                Reference<? extends View> released = null;
                try {
                    released = mReferenceQueue.remove();
                } catch (Throwable ignored) {
                }
                if (null == released) {
                    continue;
                }
                if (!(released instanceof EnabledThemeSkinView.WeakView)) {
                    continue;
                }
                removeInvalidData(((EnabledThemeSkinView.WeakView) released).getReferentAddress());
            }
        });
    }

    private void removeInvalidData(int key) {
        EnabledThemeSkinView skinView = mViewStack.get(key);
        if (null == skinView) {
            return;
        }
        mViewStack.remove(key);
        skinView.gc();
        PrintUtil.getInstance().printI("发现已被回收的View Key = " + key + " 已将其从框架内移除,目前剩余待换肤View = " + mViewStack.size() + " 个");
    }

    private void addSkinExecutor(@NonNull View view, @NonNull ISkinExecutor executor) {
        synchronized (mViewStack) {
            EnabledThemeSkinView skinView = mViewStack.get(ObjectMemoryAddress.getAddress(view));
            if (null == skinView) {
                skinView = new EnabledThemeSkinView(view, mReferenceQueue);
            }
            skinView.addSkinExecutor(executor);
            mViewStack.put(ObjectMemoryAddress.getAddress(view), skinView);
        }
        try {
            executor.execute(view);
        } catch (ApplySkinException e) {
            PrintUtil.getInstance().printE(e);
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
            List<Integer> needRemoveList = new ArrayList();
            for (int i = mViewStack.size() - 1; i >= 0; i--) {
                EnabledThemeSkinView view = mViewStack.valueAt(i);
                if (null == view) {
                    needRemoveList.add(mViewStack.keyAt(i));
                    continue;
                }
                if (!view.isValid()) {
                    needRemoveList.add(mViewStack.keyAt(i));
                    continue;
                }
                view.applyThemeSkin();
            }
            removeInvalidData(needRemoveList);
        }
    }

    /**
     * 移除无效的元素
     *
     * @param needRemoveKeys 需要被移除的元素 Key List
     */
    private void removeInvalidData(List<Integer> needRemoveKeys) {
        if (0 >= needRemoveKeys.size()) {
            return;
        }
        for (int key : needRemoveKeys) {
            EnabledThemeSkinView skinView = mViewStack.get(key);
            mViewStack.remove(key);
            skinView.gc();
        }
        PrintUtil.getInstance().printI("发现已被回收的View：" + needRemoveKeys.size() + " 个，已将其从框架内移除");
    }

    /**
     * 释放并回收资源
     */
    @Override
    public void gc() {
        mIsActive = false;
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
