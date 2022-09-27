package org.alee.component.skin.parser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import org.alee.component.skin.executor.ISkinExecutor;
import org.alee.component.skin.executor.SkinElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class ThemeSkinExecutorBuilderManager {
    /**
     * {@link IThemeSkinExecutorBuilder}
     */
    private final List<IThemeSkinExecutorBuilder> mThemeSkinExecutorBuilders;
    /**
     * {@link ThreadLocal}
     */
    private final ThreadLocal<List<IThemeSkinExecutorBuilder>> mThreadLocal;

    private ThemeSkinExecutorBuilderManager() {
        mThemeSkinExecutorBuilders = new ArrayList<>();
        mThreadLocal = new ThreadLocal<List<IThemeSkinExecutorBuilder>>() {
            @Nullable
            @Override
            protected List<IThemeSkinExecutorBuilder> initialValue() {
                return new ArrayList<>();
            }
        };
    }

    /**
     * 获取单例对象
     *
     * @return {@link ThemeSkinExecutorBuilderManager}
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static ThemeSkinExecutorBuilderManager getInstance() {
        return ThemeSkinExecutorBuilderManagerHolder.INSTANCE;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void addThemeSkinExecutorBuilder(@NonNull IThemeSkinExecutorBuilder builder) {
        synchronized (mThemeSkinExecutorBuilders) {
            if (mThemeSkinExecutorBuilders.contains(builder)) {
                return;
            }
            mThemeSkinExecutorBuilders.add(builder);
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void removeThemeSkinExecutorBuilder(IThemeSkinExecutorBuilder builder) {
        synchronized (mThemeSkinExecutorBuilders) {
            mThemeSkinExecutorBuilders.remove(builder);
        }
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public SkinElement[] parseElement(Context context, AttributeSet attributeSet) {
        if (null == context || null == attributeSet || 0 >= attributeSet.getAttributeCount()) {
            return null;
        }
        List<IThemeSkinExecutorBuilder> tempList = mThreadLocal.get();
        synchronized (mThemeSkinExecutorBuilders) {
            tempList.clear();
            tempList.addAll(mThemeSkinExecutorBuilders);
        }
        Set<SkinElement> tempSet = new HashSet<>();
        for (int i = tempList.size() - 1; i >= 0; i--) {
            IThemeSkinExecutorBuilder temp = tempList.get(i);
            if (null == temp) {
                continue;
            }
            Set<SkinElement> supportElement = temp.parse(context, attributeSet);
            if (null == supportElement || supportElement.isEmpty()) {
                continue;
            }
            tempSet.addAll(supportElement);
        }
        return tempSet.toArray(new SkinElement[tempSet.size()]);
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void appendAttr(Context context, SkinElement element) {
        element.setResourcesName(context.getResources().getResourceEntryName(element.getResourcesId()));
        element.setResourcesType(context.getResources().getResourceTypeName(element.getResourcesId()));
    }

    public boolean isValidResources(int resourcesId) {
        if (0 >= resourcesId) {
            return false;
        }
        return !Integer.toHexString(resourcesId).startsWith("1");
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public ISkinExecutor obtainSkinExecutor(View view, SkinElement element) {
        if (null == view || null == element) {
            return null;
        }
        List<IThemeSkinExecutorBuilder> tempList = mThreadLocal.get();
        synchronized (mThemeSkinExecutorBuilders) {
            tempList.clear();
            tempList.addAll(mThemeSkinExecutorBuilders);
        }
        for (int i = tempList.size() - 1; i >= 0; i--) {
            IThemeSkinExecutorBuilder temp = tempList.get(i);
            if (null == temp) {
                continue;
            }
            if (!temp.isSupportAttr(view, element.getAttrName())) {
                continue;
            }
            ISkinExecutor executor = temp.requireSkinExecutor(view, element);
            if (null == executor) {
                continue;
            }
            return executor;
        }
        return null;
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class ThemeSkinExecutorBuilderManagerHolder {
        /**
         * {@link ThemeSkinExecutorBuilderManager}
         */
        private static final ThemeSkinExecutorBuilderManager INSTANCE = new ThemeSkinExecutorBuilderManager();
    }
}
