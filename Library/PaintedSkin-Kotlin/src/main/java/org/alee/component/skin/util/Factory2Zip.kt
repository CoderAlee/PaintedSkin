package org.alee.component.skin.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater.Factory
import android.view.LayoutInflater.Factory2
import android.view.View

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/11
 *
 */
internal class Factory2Zip(
    private val mProxyFactory2: Factory2?,
    private val mProxyPrivateFactory2: Factory2?,
    private val mProxyFactory: Factory? = mProxyFactory2,
    private val mProxyPrivateFactory: Factory? = mProxyPrivateFactory2,
) : Factory2 {
    /**
     * Version of [.onCreateView]
     * that also supplies the parent that the view created view will be
     * placed in.
     *
     * @param parent The parent that the created view will be placed
     * in; *note that this may be null*.
     * @param name Tag name to be inflated.
     * @param context The context the view is being created in.
     * @param attrs Inflation attributes as specified in XML file.
     *
     * @return View Newly created view. Return null for the default
     * behavior.
     */
    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        var temp: View? = mProxyFactory2?.onCreateView(parent, name, context, attrs)
        if (null != temp) {
            return temp
        }
        temp = mProxyFactory?.onCreateView(name, context, attrs)
        if (null != temp) {
            return temp
        }
        temp = mProxyPrivateFactory2?.onCreateView(parent, name, context, attrs)
        if (null != temp) {
            return temp
        }
        return mProxyPrivateFactory?.onCreateView(name, context, attrs)
    }

    /**
     * Hook you can supply that is called when inflating from a LayoutInflater.
     * You can use this to customize the tag names available in your XML
     * layout files.
     *
     *
     *
     * Note that it is good practice to prefix these custom names with your
     * package (i.e., com.coolcompany.apps) to avoid conflicts with system
     * names.
     *
     * @param name Tag name to be inflated.
     * @param context The context the view is being created in.
     * @param attrs Inflation attributes as specified in XML file.
     *
     * @return View Newly created view. Return null for the default
     * behavior.
     */
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val temp: View? = mProxyFactory?.onCreateView(name, context, attrs)
        if (null != temp) {
            return temp
        }
        return mProxyPrivateFactory?.onCreateView(name, context, attrs)
    }
}
