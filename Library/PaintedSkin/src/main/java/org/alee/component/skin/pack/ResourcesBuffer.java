package org.alee.component.skin.pack;

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
     * 颜色缓存
     */
    private final WeakHashMap<Object, WeakReference<Integer>> mColorCache;

    ResourcesBuffer() {
        mColorCache = new WeakHashMap<>();
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
}
