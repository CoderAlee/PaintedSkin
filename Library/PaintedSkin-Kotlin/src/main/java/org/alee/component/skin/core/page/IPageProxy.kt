package org.alee.component.skin.core.page

import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.jetbrains.annotations.TestOnly

/**
 * 此接口用于定义每个页面需要支持的功能
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
internal sealed interface IPageProxy {
    /**
     * 当前页面的View仓库
     */
    val viewWarehouse: ISkinnableViewWarehouse

    /**
     * 当前页面是否可见
     */
    val isVisible: Boolean

    /**
     * 当页面被附加到Activity时调用
     * @param fragment Fragment 当前页面
     */
    fun onPageAttached(fragment: Fragment)

    /**
     * 当页面被创建时调用
     * @param fragment Fragment 当前页面
     */
    fun onPageCreated(fragment: Fragment)

    /**
     * 当页面可见时调用
     */
    fun onPageVisible()

    /**
     * 当页面不可见时调用
     */
    fun onPageInvisible()

    /**
     * 当页面被销毁时调用
     * @param fragment Fragment 当前页面
     */
    fun onPageDestroyed(fragment: Fragment)

    /**
     * 当页面被分离时调用
     * @param fragment Fragment 当前页面
     */
    fun onPageDetached(fragment: Fragment)

    /**
     * 获取页面代理
     * @param fragment Fragment 页面
     * @return IPageProxy? 页面代理
     */
    fun fetchPage(fragment: Fragment): IPageProxy?

    /**
     * Dump页面信息
     * @return PageDumpInfo
     */
    @WorkerThread
    @TestOnly
    fun dump(): PageDumpInfo
}
