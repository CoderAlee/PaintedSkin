package org.alee.component.skin.core.window

import android.content.Context
import android.view.View
import androidx.annotation.WorkerThread
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.util.WeakReference
import org.jetbrains.annotations.TestOnly

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal sealed interface IWindowProxy {
    /**
     * 当窗口被创建时调用
     * @param context Context
     */
    fun onWindowCreated(context: Context)

    /**
     * 当窗口可见时调用
     */
    fun onWindowVisible()

    /**
     * 当窗口不可见时调用
     */
    fun onWindowInvisible()

    /**
     * 当窗口被销毁时调用
     */
    fun onWindowDestroyed()

    /**
     * 添加需要换肤的View
     * @param view View [View]
     * @param skinAttributes Array<out SkinAttribute> [SkinAttribute]
     */
    fun addSkinnableView(@WeakReference view: View, vararg skinAttributes: SkinAttribute)

    @TestOnly
    @WorkerThread
    fun dump(): String
}
