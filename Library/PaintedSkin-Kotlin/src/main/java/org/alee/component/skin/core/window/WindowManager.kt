package org.alee.component.skin.core.window

import android.app.Activity
import android.app.Application
import android.content.Context
import java.util.WeakHashMap

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal object WindowManager {
    /**
     * Activity 生命周期观察者
     */
    private val mActivityLifecycleObserver by lazy { ActivityLifecycleObserver() }

    /**
     * 窗口代理集合
     */
    private val mWindowProxyMap: MutableMap<Context, IWindowProxy> by lazy { WeakHashMap() }

    internal fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(mActivityLifecycleObserver)
        val globalWindow = GlobalWindowProxy()
        onWindowCreated(application, globalWindow)
        globalWindow.onWindowVisible()
    }

    internal fun onWindowCreated(activity: Activity) {
        onWindowCreated(activity, ActivityWindowProxy(activity.javaClass.simpleName))
    }

    internal fun onWindowDestroy(context: Context) {
        synchronized(mWindowProxyMap) {
            mWindowProxyMap.remove(context)?.run {
                onWindowInvisible()
                onWindowDestroyed()
            }
        }
    }

    internal fun fetchWindowProxy(context: Context): IWindowProxy? {
        return synchronized(mWindowProxyMap) { mWindowProxyMap[context] }
    }

    internal suspend fun dump(): String {
        return synchronized(mWindowProxyMap) {
            val sb = StringBuilder()
            mWindowProxyMap.values.forEach {
                sb.append(it.dump())
            }
            sb.toString()
        }
    }

    private fun onWindowCreated(context: Context, proxy: IWindowProxy) {
        synchronized(mWindowProxyMap) {
            if (mWindowProxyMap.containsKey(context)) {
                return
            }
            mWindowProxyMap[context] = proxy
            proxy.onWindowCreated(context)
        }
    }
}
