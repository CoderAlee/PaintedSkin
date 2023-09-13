package org.alee.component.skin.core.page

import android.content.Context
import android.os.Bundle
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.jetbrains.annotations.TestOnly
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
internal class PageManager(private val fm: FragmentManager) : FragmentManager.FragmentLifecycleCallbacks() {

    /**
     * 子页面
     */
    private val mPageProxyMap: MutableMap<Fragment, IPageProxy> by lazy { WeakHashMap() }

    init {
        fm.registerFragmentLifecycleCallbacks(this, false)
    }

    internal fun fetchPageProxy(fragment: Fragment): IPageProxy? {
        val proxy = getPageProxy(fragment)
        if (null != proxy) {
            return proxy
        }
        return getPageProxy(fragment.rootParent)?.fetchPage(fragment)
    }

    internal fun destroy() {
        fm.unregisterFragmentLifecycleCallbacks(this)
        synchronized(mPageProxyMap) {
            mPageProxyMap.clear()
        }
    }

    @TestOnly
    @WorkerThread
    internal fun dump(): String {
        val sb = StringBuilder()
        mPageProxyMap.values.map { it.dump() }
            .forEach {
                sb.customAppend(it, 1)
            }
        return sb.toString()
    }

    private fun getPageProxy(fragment: Fragment): IPageProxy? {
        return synchronized(mPageProxyMap) {
            mPageProxyMap[fragment]
        }
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        synchronized(mPageProxyMap) {
            mPageProxyMap[f] = PageProxy(f.javaClass.name)
        }
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        getPageProxy(f)?.onPageAttached(f)
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        getPageProxy(f)?.onPageCreated(f)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        getPageProxy(f)?.onPageVisible()
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        getPageProxy(f)?.onPageInvisible()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        getPageProxy(f)?.onPageDestroyed(f)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        getPageProxy(f)?.onPageDetached(f)
        synchronized(mPageProxyMap) {
            mPageProxyMap.remove(f)
        }
    }

    private fun StringBuilder.customAppend(pageDumpInfo: PageDumpInfo, depth: Int) {
        append("|-")
        for (i in 0 until depth) {
            append("-")
        }
        append(pageDumpInfo.pageName)
            .append("visible = [${pageDumpInfo.isVisible}]")
            .append("waitApplySkin = [${pageDumpInfo.isNeedApplyThemeSkin}]")
            .append("skinnableViewSize = [${pageDumpInfo.skinnableViewSize}]")
            .append("\r\n")
        pageDumpInfo.subPageInfo.forEach {
            customAppend(it, depth + 1)
        }
    }

    private val Fragment.rootParent: Fragment
        get() {
            val parent = parentFragment
            return parent?.rootParent ?: this
        }
}
