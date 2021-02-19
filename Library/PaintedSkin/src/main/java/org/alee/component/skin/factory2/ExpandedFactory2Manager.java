package org.alee.component.skin.factory2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import org.alee.component.skin.collection.SparseStack;
import org.alee.component.skin.util.ObjectMemoryAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public final class ExpandedFactory2Manager implements IExpandedFactory2 {
    /**
     * {@link IExpandedFactory2}
     */
    private final SparseStack<IExpandedFactory2> mExpandedFactory2Array;
    /**
     * {@link ThreadLocal} 处理多线程并发
     */
    private final ThreadLocal<List<IExpandedFactory2>> mThreadLocal;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public ExpandedFactory2Manager() {
        mExpandedFactory2Array = new SparseStack<>();
        mThreadLocal = new ThreadLocal<List<IExpandedFactory2>>() {
            @Nullable
            @Override
            protected List<IExpandedFactory2> initialValue() {
                return new ArrayList<>();
            }
        };
    }

    public void add(@NonNull LayoutInflater.Factory2 factory2) {
        synchronized (mExpandedFactory2Array) {
            mExpandedFactory2Array.put(ObjectMemoryAddress.getAddress(factory2), new DefaultExpandedFactory2(factory2));
        }
    }

    public void add(@NonNull IExpandedFactory2 factory2) {
        synchronized (mExpandedFactory2Array) {
            mExpandedFactory2Array.put(ObjectMemoryAddress.getAddress(factory2), factory2);
        }
    }

    public void remove(@NonNull LayoutInflater.Factory2 factory2) {
        synchronized (mExpandedFactory2Array) {
            mExpandedFactory2Array.remove(ObjectMemoryAddress.getAddress(factory2));
        }
    }

    public void remove(@NonNull IExpandedFactory2 factory2) {
        synchronized (mExpandedFactory2Array) {
            mExpandedFactory2Array.remove(ObjectMemoryAddress.getAddress(factory2));
        }
    }

    /**
     * 创建View
     *
     * @param originalView 上一个IExpandedFactory生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      {@link Context}
     * @param attrs        {@link AttributeSet}
     * @return 生成的View
     */
    @NonNull
    @Override
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public View onCreateView(@Nullable View originalView, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        List<IExpandedFactory2> temp = mThreadLocal.get();
        synchronized (mExpandedFactory2Array) {
            temp.clear();
            for (int i = 0; i < mExpandedFactory2Array.size(); i++) {
                temp.add(mExpandedFactory2Array.valueAt(i));
            }
        }
        View returnView = originalView;
        for (IExpandedFactory2 factory2 : temp) {
            if (null == factory2) {
                continue;
            }
            View tempView = factory2.onCreateView(returnView, parent, name, context, attrs);
            if (null == tempView) {
                continue;
            }
            returnView = tempView;
        }
        return returnView;
    }

    void gc() {
        mThreadLocal.get().clear();
        mThreadLocal.remove();
        mExpandedFactory2Array.clear();
    }

    private final static class DefaultExpandedFactory2 implements IExpandedFactory2 {
        private final LayoutInflater.Factory2 mOriginFactory;

        private DefaultExpandedFactory2(@NonNull LayoutInflater.Factory2 factory2) {
            mOriginFactory = factory2;
        }

        @Override
        public View onCreateView(@Nullable View view, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            if (null == mOriginFactory) {
                return view;
            }
            View temp = null == parent ? mOriginFactory.onCreateView(name, context, attrs) : mOriginFactory.onCreateView(parent, name, context, attrs);
            return null == temp ? view : temp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(mOriginFactory);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DefaultExpandedFactory2 that = (DefaultExpandedFactory2) o;
            return Objects.equals(mOriginFactory, that.mOriginFactory);
        }
    }
}
