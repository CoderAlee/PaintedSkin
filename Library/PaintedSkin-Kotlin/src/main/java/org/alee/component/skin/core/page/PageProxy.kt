package org.alee.component.skin.core.page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.constant.PerformanceMode
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.alee.component.skin.core.window.SkinnableViewWarehouse
import org.alee.component.skin.template.IThemeSkinObserver
import org.alee.component.skin.util.ext.measureTimeIfDebug
import org.alee.component.skin.util.ext.subscribeThemeSkinIfNeed
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
internal class PageProxy(private val mPageName: String) : FragmentLifecycleCallbacks(), IPageProxy, IThemeSkinObserver {

    private val mViewWarehouse: ISkinnableViewWarehouse by lazy { SkinnableViewWarehouse(mPageName) }

    /**
     * 子页面
     */
    private val mSubPageProxyMap: MutableMap<Fragment, IPageProxy> by lazy { WeakHashMap() }

    @Volatile
    private var mIsVisible = false

    /**
     * 标识 在后台切前台后，是否有待执行的换肤任务
     */
    private var mIsNeedApplySkin: Boolean = false

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    private val isApplyInInvisible: Boolean
        get() = PerformanceMode.EXPERIENCE_FIRST == ThemeSkinService.config.performanceMode

    override val viewWarehouse: ISkinnableViewWarehouse
        get() = mViewWarehouse

    /**
     * 当前页面是否可见
     */
    override val isVisible: Boolean
        get() = mIsVisible

    /**
     * 当页面被附加到Activity时调用
     * @param fragment Fragment 当前页面
     */
    override fun onPageAttached(fragment: Fragment) {
        fragment.childFragmentManager.registerFragmentLifecycleCallbacks(this, false)
    }

    /**
     * 当页面被创建时调用
     * @param fragment Fragment 当前页面
     */
    override fun onPageCreated(fragment: Fragment) {
        ThemeSkinService.subscribe(this)
        fragment.subscribeThemeSkinIfNeed()
    }

    override fun onPageVisible() {
        mIsVisible = true
        if (mIsNeedApplySkin) {
            onThemeSkinChanged(0, ThemeSkinService.currentSkinPack)
        }
    }

    override fun onPageInvisible() {
        mIsVisible = false
    }

    /**
     * 当页面被销毁时调用
     * @param fragment Fragment 当前页面
     */
    override fun onPageDestroyed(fragment: Fragment) {
        ThemeSkinService.unsubscribe(this)
        if (fragment is IThemeSkinObserver) {
            ThemeSkinService.unsubscribe(this)
        }
        mViewWarehouse.destroy()
        synchronized(mSubPageProxyMap) {
            mSubPageProxyMap.clear()
        }
    }

    /**
     * 当页面被分离时调用
     * @param fragment Fragment 当前页面
     */
    override fun onPageDetached(fragment: Fragment) {
        fragment.childFragmentManager.unregisterFragmentLifecycleCallbacks(this)
    }

    /**
     * 获取页面代理
     * @param fragment Fragment 页面
     * @return IPageProxy? 页面代理
     */
    override fun fetchPage(fragment: Fragment): IPageProxy? {
        var pageProxy: IPageProxy? = null
        var f: Fragment? = fragment
        while (null != f) {
            pageProxy = getSubPage(f)
            if (null != pageProxy) {
                break
            }
            f = fragment.parentFragment
        }
        if (f == fragment) {
            return pageProxy
        }
        return pageProxy?.fetchPage(fragment)
    }

    /**
     * Dump页面信息
     * @return PageDumpInfo
     */
    override fun dump(): PageDumpInfo {
        return PageDumpInfo(
            mPageName,
            mViewWarehouse.skinnableViewSize,
            isVisible,
            isApplyInInvisible,
            mSubPageProxyMap.values.map { it.dump() },
        )
    }

    private fun getSubPage(fragment: Fragment): IPageProxy? {
        return synchronized(mSubPageProxyMap) {
            mSubPageProxyMap[fragment]
        }
    }

    override fun onThemeSkinChanged(theme: Int, usedSkinPack: IThemeSkinPack) {
        if (mIsVisible.not() && isApplyInInvisible.not()) {
            mIsNeedApplySkin = true
            return
        }
        mIsNeedApplySkin = false
        measureTimeIfDebug({
            mViewWarehouse.applyThemeSkin()
        }) {
            "$mPageName 执行换肤耗时：[$it] ms"
        }
    }

    /**
     * 当主题发生变化时被回调
     * @param usedSkinPack IThemeSkinPack 当前使用的皮肤包
     */
    override fun onThemeSkinChangedRunOnUiThread(usedSkinPack: IThemeSkinPack) {
        // ignored
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        synchronized(mSubPageProxyMap) {
            mSubPageProxyMap[f] = PageProxy(f.javaClass.simpleName)
        }
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        getSubPage(f)?.onPageAttached(f)
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        getSubPage(f)?.onPageCreated(f)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        getSubPage(f)?.onPageVisible()
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        getSubPage(f)?.onPageInvisible()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        getSubPage(f)?.onPageDestroyed(f)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        getSubPage(f)?.onPageDetached(f)
        synchronized(mSubPageProxyMap) {
            mSubPageProxyMap.remove(f)
        }
    }
}
