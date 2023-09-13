package org.alee.component.skin.core.window

import android.view.View
import androidx.annotation.MainThread
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.ISkinExecutor
import org.alee.component.skin.util.ext.memoryAddress
import org.alee.component.skin.util.safeCall
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/11
 *
 */
internal class SkinnableView(view: View, referenceQueue: ReferenceQueue<View>) {
    /**
     * 弱引用需要换肤的View
     */
    private val mView: WeakView = WeakView(view, referenceQueue)

    /**
     * View的换肤执行器
     */
    private val mSkinExecutors: MutableMap<SkinAttribute, ISkinExecutor> = HashMap()

    internal val isValid: Boolean
        get() {
            return mView.get() != null && mSkinExecutors.isNotEmpty()
        }

    internal val skinAttributes: Set<SkinAttribute>
        get() = mSkinExecutors.keys

    internal fun addSkinExecutor(attribute: SkinAttribute, skinExecutor: ISkinExecutor) {
        synchronized(mSkinExecutors) {
            mSkinExecutors.put(attribute, skinExecutor)
        }
    }

    @MainThread
    internal fun applyThemeSkin() {
        synchronized(mSkinExecutors) {
            val view: View = mView.get()!!
            mSkinExecutors.forEach {
                safeCall {
                    it.value.execute(view, it.key)
                }
            }
        }
    }

    internal fun destroy() {
        mView.clear()
        synchronized(mSkinExecutors) {
            mSkinExecutors.clear()
        }
    }

    internal class WeakView(referent: View, q: ReferenceQueue<in View>) : WeakReference<View>(referent, q) {
        /**
         * 引用View 的内存地址
         */
        val referentAddress = referent.memoryAddress
    }
}
