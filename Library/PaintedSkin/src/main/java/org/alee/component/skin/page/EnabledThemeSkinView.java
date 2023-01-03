package org.alee.component.skin.page;

import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.exception.ApplySkinException;
import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.util.ObjectMemoryAddress;
import org.alee.component.skin.util.PrintUtil;

import java.lang.ref.ReferenceQueue;
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
    private final WeakView mView;
    /**
     * 需要换肤的属性执行器
     */
    private final Map<String, ISkinExecutor> mSkinExecutorMap;

    EnabledThemeSkinView(@NonNull View view, ReferenceQueue<View> referenceQueue) {
        mView = new WeakView(view, referenceQueue);
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
        return !mSkinExecutorMap.isEmpty();
    }

    void gc() {
        mView.clear();
        synchronized (mSkinExecutorMap) {
            mSkinExecutorMap.clear();
        }
    }

    static class WeakView extends WeakReference<View> {
        /**
         * 引用View 的内存地址
         */
        private final int mReferentAddress;

        /**
         * Creates a new weak reference that refers to the given object and is
         * registered with the given queue.
         *
         * @param referent object the new weak reference will refer to
         * @param q        the queue with which the reference is to be registered,
         */
        public WeakView(View referent, ReferenceQueue<? super View> q) {
            super(referent, q);
            mReferentAddress = ObjectMemoryAddress.getAddress(referent);
        }

        public int getReferentAddress() {
            return mReferentAddress;
        }
    }
}
