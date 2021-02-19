package org.alee.component.skin.pack;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/10
 * @description: 资源缓存器
 *
 *********************************************************/
final class ResourcesBuffer {
    /**
     * 状态颜色缓存
     */
    private final WeakHashMap<Object, WeakReference<ColorStateList>> mColorStateListCache;
    /**
     * 颜色缓存
     */
    private final WeakHashMap<Object, WeakReference<Integer>> mColorCache;
    /**
     * 图片缓存
     */
    private final WeakHashMap<Object, WeakReference<Drawable>> mDrawableCache;
    /**
     * 位图缓存
     */
    private final WeakHashMap<Object, WeakReference<Bitmap>> mBitmapCache;

    ResourcesBuffer() {
        mColorStateListCache = new WeakHashMap<>();
        mColorCache = new WeakHashMap<>();
        mDrawableCache = new WeakHashMap<>();
        mBitmapCache = new WeakHashMap<>();
    }

    void addColorStateList(@NonNull Object resIdOrName, @NonNull ColorStateList stateList) {
        synchronized (mColorStateListCache) {
            mColorStateListCache.put(resIdOrName, new WeakReference<>(stateList));
        }
    }

    @Nullable
    ColorStateList getColorStateList(@NonNull Object resIdOrName) {
        synchronized (mColorStateListCache) {
            WeakReference<ColorStateList> reference = mColorStateListCache.get(resIdOrName);
            if (null == reference) {
                return null;
            }
            ColorStateList temp = reference.get();
            if (null == temp) {
                mColorStateListCache.remove(resIdOrName);
            }
            return temp;
        }
    }

    void addColor(@NonNull Object resIdOrName, @NonNull Integer color) {
        synchronized (mColorCache) {
            mColorCache.put(resIdOrName, new WeakReference<>(color));
        }
    }

    @Nullable
    Integer getColor(@NonNull Object resIdOrName) {
        synchronized (mColorCache) {
            WeakReference<Integer> reference = mColorCache.get(resIdOrName);
            if (null == reference) {
                return null;
            }
            Integer color = reference.get();
            if (null == color) {
                mColorCache.remove(resIdOrName);
            }
            return color;
        }
    }

    void addDrawable(@NonNull Object resIdOrName, @NonNull Drawable drawable) {
        synchronized (mDrawableCache) {
            mDrawableCache.put(resIdOrName, new WeakReference<>(drawable));
        }
    }

    @Nullable
    Drawable getDrawable(@NonNull Object resIdOrName) {
        synchronized (mDrawableCache) {
            WeakReference<Drawable> reference = mDrawableCache.get(resIdOrName);
            if (null == reference) {
                return null;
            }
            Drawable drawable = reference.get();
            if (null == drawable) {
                mDrawableCache.remove(resIdOrName);
            }
            return drawable;
        }
    }

    void addBitmap(@NonNull Object resIdOrName, @NonNull Bitmap bitmap) {
        synchronized (mBitmapCache) {
            mBitmapCache.put(resIdOrName, new WeakReference<>(bitmap));
        }
    }

    @Nullable
    Bitmap getBitmap(@NonNull Object resIdOrName) {
        synchronized (mBitmapCache) {
            WeakReference<Bitmap> reference = mBitmapCache.get(resIdOrName);
            if (null == reference) {
                return null;
            }
            Bitmap bitmap = reference.get();
            if (null == bitmap) {
                mBitmapCache.remove(resIdOrName);
            }
            return bitmap;
        }
    }
}
