package org.alee.component.skin.core.window

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.constant.PerformanceMode
import org.alee.component.skin.core.page.PageManager
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.util.ext.subscribeThemeSkinIfNeed
import org.alee.component.skin.util.safeCall
import org.alee.component.skin.util.setFactory2

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal class ActivityWindowProxy(activityName: String) :
    BaseWindowProxy(activityName),
    ISkinnableViewWarehouse,
    View.OnAttachStateChangeListener {

    private lateinit var mPageManager: PageManager

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    override val isApplyInInvisible: Boolean
        get() = PerformanceMode.EXPERIENCE_FIRST == ThemeSkinService.config.performanceMode

    override fun onWindowCreated(context: Context) {
        super.onWindowCreated(context)
        LayoutInflater.from(context).setFactory2(ThemeSkinService.viewCreatorManager, this)
        if (context is FragmentActivity) {
            mPageManager = PageManager(context.supportFragmentManager)
        }
        context.subscribeThemeSkinIfNeed()
    }

    /**
     * 获取当前仓库中支持换肤的View数量
     */
    override val skinnableViewSize: Int
        get() = mViewWarehouse.skinnableViewSize

    override fun onWindowDestroyed() {
        super.onWindowDestroyed()
        safeUsePageManager {
            destroy()
        }
    }

    override fun addSkinnableView(view: View, vararg skinAttributes: SkinAttribute) {
        super.addSkinnableView(view, *skinAttributes)
        view.addOnAttachStateChangeListener(this)
    }

    override fun dump(): String {
        return super.dump() + "\r\n" + safeUsePageManager {
            dump()
        }
    }

    /**
     *  添加允许换肤的View
     * @param view View 支持换肤的View
     * @param attributeSet AttributeSet 属性集
     */
    override fun addSkinnableView(view: View, attributeSet: AttributeSet) {
        mViewWarehouse.addSkinnableView(view, attributeSet)
        view.addOnAttachStateChangeListener(this)
    }

    /**
     * 将一个可换肤View移交到另一个仓库
     * @param view View 可换肤View
     * @param receiver ISkinnableViewWarehouse 接收者
     */
    override fun transferSkinnableView(view: View, receiver: ISkinnableViewWarehouse) {
        // ignored
    }

    /**
     * 应用当前的主题皮肤
     */
    override fun applyThemeSkin() {
        mViewWarehouse.applyThemeSkin()
    }

    /**
     * 销毁仓库并释放资源
     */
    override fun destroy() {
        mViewWarehouse.destroy()
    }

    private fun <T> safeUsePageManager(block: PageManager.() -> T): T? {
        if (this::mPageManager.isInitialized.not()) {
            return null
        }
        return mPageManager.block()
    }

    /**
     * Called when the view is attached to a window.
     * @param v The view that was attached
     */
    override fun onViewAttachedToWindow(v: View) {
        v.removeOnAttachStateChangeListener(this)
        safeCall(false) {
            val fragment = FragmentManager.findFragment<Fragment>(v)
            safeUsePageManager {
                fetchPageProxy(fragment)?.run {
                    mViewWarehouse.transferSkinnableView(v, viewWarehouse)
                }
            }
        }
    }

    /**
     * Called when the view is detached from a window.
     * @param v The view that was detached
     */
    override fun onViewDetachedFromWindow(v: View) {
        // ignored
    }
}
