package org.alee.component.skin.page;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import org.alee.component.skin.plugin.ISkinOptionFactoryLoader;
import org.alee.component.skin.service.ThemeSkinService;
import org.alee.component.skin.util.PrintUtil;

import java.util.WeakHashMap;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/15
 * @description: xxxx
 *
 *********************************************************/
public final class WindowManager {

    private final WeakHashMap<Context, BaseWindowProxy> mWindowProxyWeakHashMap;

    private boolean mIsInitialized;

    private ISkinOptionFactoryLoader mOptionFactoryLoader;

    private WindowManager() {
        mWindowProxyWeakHashMap = new WeakHashMap<>();
        try {
            mOptionFactoryLoader = (ISkinOptionFactoryLoader) getClass().getClassLoader().loadClass("org.alee.component.skin.plugin.SkinOptionFactoryLoader").newInstance();
        } catch (Throwable e) {
            PrintUtil.getInstance().printE(e);
        }
    }

    /**
     * 获取单例对象
     *
     * @return {@link WindowManager}
     */
    public static WindowManager getInstance() {
        return WindowManagerHolder.INSTANCE;
    }

    void init(@NonNull Application application) {
        if (mIsInitialized) {
            return;
        }
        mIsInitialized = true;
        ThemeSkinService.getInstance().init(application.getApplicationContext(), mOptionFactoryLoader.load());
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks());
        onAttachWindow(application.getApplicationContext(), new GlobalWindowProxy());
    }


    private void onAttachWindow(@NonNull Context context, @NonNull BaseWindowProxy proxy) {
        synchronized (mWindowProxyWeakHashMap) {
            if (mWindowProxyWeakHashMap.containsKey(context)) {
                return;
            }
            proxy.bindLayoutInflate(context);
            mWindowProxyWeakHashMap.put(context, proxy);
        }
    }

    void onAttachWindow(@NonNull Context context) {
        onAttachWindow(context, new ActivityWindowProxy());
    }

    void onDetachWindow(Context context) {
        synchronized (mWindowProxyWeakHashMap) {
            BaseWindowProxy windowProxy = mWindowProxyWeakHashMap.remove(context);
            if (null != windowProxy) {
                windowProxy.onDestroy();
            }
        }
    }

    public IWindowProxy getWindowProxy(Context context) {
        return mWindowProxyWeakHashMap.get(context);
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class WindowManagerHolder {
        /**
         * {@link WindowManager}
         */
        private static final WindowManager INSTANCE = new WindowManager();
    }
}
