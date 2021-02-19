package org.alee.component.skin.page;

import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.exception.ApplySkinException;
import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.util.PrintUtil;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class EnabledThemeSkinView {
    /**
     * 需要换肤的View
     */
    private final WeakReference<View> mView;
    /**
     * 需要换肤的属性执行器
     */
    private final Map<String, ISkinExecutor> mSkinExecutorMap;

    EnabledThemeSkinView(@NonNull View view) {
        mView = new WeakReference<>(view);
        mSkinExecutorMap = new HashMap<>();
    }

    void addSkinExecutor(ISkinExecutor... executors) {
        if (null == executors || 0 >= executors.length) {
            return;
        }
        synchronized (mSkinExecutorMap) {
            for (ISkinExecutor executor : executors) {
                if (null == executor) {
                    continue;
                }
                mSkinExecutorMap.put(executor.getSkinAttrName(), executor);
            }
        }
    }

    void applyThemeSkin() {
        View view = mView.get();
        synchronized (mSkinExecutorMap) {
            for (ISkinExecutor executor : mSkinExecutorMap.values()) {
                if (null == executor) {
                    continue;
                }
                try {
                    executor.execute(view);
                } catch (ApplySkinException exception) {
                    PrintUtil.getInstance().printE(exception);
                }
            }
        }
    }

    boolean isValid() {
        View cache = mView.get();
        if (null == cache) {
            return false;
        }
//        if ((!cache.isAttachedToWindow()) && cache.isDirty()) {
//            return false;
//        }
        return !mSkinExecutorMap.isEmpty();
    }

    void gc() {
        mView.clear();
        synchronized (mSkinExecutorMap) {
            mSkinExecutorMap.clear();
        }
    }
}
