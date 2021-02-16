package org.alee.component.skin.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/15
 * @description: xxxx
 *
 *********************************************************/
final class OriginalFactory2 implements LayoutInflater.Factory2 {
    private final LayoutInflater.Factory mOriginalFactory;
    private final LayoutInflater.Factory mReserveOriginalFactory;
    private final LayoutInflater.Factory2 mOriginalFactory2;
    private final LayoutInflater.Factory2 mReserveOriginalFactory2;

    OriginalFactory2(LayoutInflater.Factory originalFactory, LayoutInflater.Factory2 originalFactory2, LayoutInflater.Factory reserveOriginalFactory, LayoutInflater.Factory2 reserveOriginalFactory2) {
        mOriginalFactory = originalFactory;
        mReserveOriginalFactory = reserveOriginalFactory;
        mOriginalFactory2 = originalFactory2;
        mReserveOriginalFactory2 = reserveOriginalFactory2;
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull String name, @NonNull Context context,
                             @NonNull AttributeSet attrs) {
        View temp = null;
        if (null != mOriginalFactory) {
            temp = mOriginalFactory.onCreateView(name, context, attrs);
        }
        if (null != temp) {
            return temp;
        }
        if (null != mReserveOriginalFactory) {
            temp = mReserveOriginalFactory.onCreateView(name, context, attrs);
        }
        return temp;
    }

    @Override
    @Nullable
    public View onCreateView(@Nullable View parent, @NonNull String name,
                             @NonNull Context context, @NonNull AttributeSet attrs) {
        View temp = null;
        if (null != mOriginalFactory2) {
            temp = mOriginalFactory2.onCreateView(parent, name, context, attrs);
        }
        if (null != temp) {
            return temp;
        }
        if (null != mOriginalFactory) {
            temp = mOriginalFactory.onCreateView(name, context, attrs);
        }
        if (null != temp) {
            return temp;
        }
        if (null != mReserveOriginalFactory2) {
            temp = mReserveOriginalFactory2.onCreateView(parent, name, context, attrs);
        }
        if (null != temp) {
            return temp;
        }
        if (null != mReserveOriginalFactory) {
            temp = mReserveOriginalFactory.onCreateView(name, context, attrs);
        }
        return temp;
    }
}
