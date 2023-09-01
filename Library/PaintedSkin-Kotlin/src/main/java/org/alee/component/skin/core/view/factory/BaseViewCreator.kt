package org.alee.component.skin.core.view.factory

import android.content.Context
import android.os.Build
import android.util.ArrayMap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.View
import androidx.annotation.RequiresApi
import org.alee.component.skin.template.IViewCreator
import org.alee.component.skin.util.ext.logI
import org.alee.component.skin.util.safeCall
import java.lang.reflect.Constructor

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal abstract class BaseViewCreator(private val mOriginalFactory2: Factory2?) : Factory2 {

    private companion object {
        private val BLACK_BOX: MutableList<String> = ArrayList()
    }

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
        var view: View? = null
        if (BLACK_BOX.contains(name).not()) {
            view = tryCreateViewFromFactory2(parent, name, context, attrs)
            if (null == view) {
                view = ViewInflater.onCreateView(null, parent, context, name, attrs)
            }
            if (null == view) {
                BLACK_BOX.add(name)
                "Failed to create View [$name],Please add generator".logI()
            }
        }
        return onReplaceView(view, parent, name, context, attrs)
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
        return onCreateView(null, name, context, attrs)
    }

    /**
     * 尝试通过其他人设置的Factory2创建View
     * @param parent View?
     * @param name String
     * @param context Context
     * @param attrs AttributeSet
     * @return View?
     */
    private fun tryCreateViewFromFactory2(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        mOriginalFactory2?.run {
            if (null == parent) {
                safeCall(false) {
                    return onCreateView(name, context, attrs)
                }
            } else {
                safeCall(false) {
                    return onCreateView(parent, name, context, attrs)
                }
            }
        }
        return null
    }

    /**
     * 替换view
     *
     * @param originalView 父类生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      [Context]
     * @param attributeSet [AttributeSet]
     * @return 替换后的view
     */
    protected abstract fun onReplaceView(
        originalView: View?,
        parent: View?,
        name: String,
        context: Context,
        attributeSet: AttributeSet,
    ): View?

    private object ViewInflater : IViewCreator {

        /**
         * 自定义view标志
         */
        private const val CUSTOM_VIEW_SIGN = "."

        /**
         * 基础
         */
        private const val BASICS = "View"

        /**
         * 基础view 路径
         */
        private const val BASICS_PREFIX = "android.view."

        /**
         * widget 路径
         */
        private const val WIDGET_PREFIX = "android.widget."

        /**
         * 兼容包
         */
        private const val VIEW_COMPAT = "androidx.appcompat.widget."

        /**
         * webkit 路径
         */
        private const val WEBKIT_PREFIX = "android.webkit."

        /**
         * app 路径
         */
        private const val APP_PREFIX = "android.app."

        /**
         * V7 兼容包
         */
        private const val V7_COMPAT = "android.support.v7.widget."

        /**
         * View 前缀集合
         */
        private val CLASS_PREFIX_ARRAY = arrayOf(
            BASICS_PREFIX,
            WIDGET_PREFIX,
            VIEW_COMPAT,
            WEBKIT_PREFIX,
            APP_PREFIX,
            V7_COMPAT,
        )

        /**
         * View 构造函数参数签名
         */
        private val CONSTRUCTOR_SIGNATURE = arrayOf(Context::class.java, AttributeSet::class.java)

        /**
         * View 构造函数缓存
         */
        private val VIEW_CONSTRUCTOR_MAP: MutableMap<String, Constructor<out View>> = ArrayMap()

        /**
         * 创建View/转换View
         * 开发者可以根据入参决定创建一个View或将View替换为其他View
         * @param predecessorOutput View? 上一任[IViewCreator]的处理结果
         * @param parent View? View的父View
         * @param context Context [Context]
         * @param viewName String View的完整类名
         * @param attrs AttributeSet View的属性集合
         * @return View?
         */
        override fun onCreateView(
            predecessorOutput: View?,
            parent: View?,
            context: Context,
            viewName: String,
            attrs: AttributeSet,
        ): View? {
            val inflater = LayoutInflater.from(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                safeCall(false) {
                    return tryCreateViewFromFrameworkQ(context, inflater, parent, viewName, attrs)
                }
            }
            if (viewName.contains(CUSTOM_VIEW_SIGN)) {
                safeCall(false) {
                    return inflater.createView(viewName, null, attrs)
                }
                return null
            }
            if (viewName == BASICS) {
                return View(context, attrs)
            }
            var view: View? = null
            for (prefix in CLASS_PREFIX_ARRAY) {
                safeCall(false) {
                    view = inflater.createView(viewName, prefix, attrs)
                }
                if (null == view) {
                    safeCall(false) {
                        view = tryCreateView(context, viewName, prefix, attrs)
                    }
                }
                if (null != view) {
                    break
                }
            }
            return view
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun tryCreateViewFromFrameworkQ(
            context: Context,
            inflater: LayoutInflater,
            parent: View?,
            viewName: String,
            attrs: AttributeSet,
        ): View? {
            return if (viewName.contains(CUSTOM_VIEW_SIGN)) {
                inflater.createView(context, viewName, null, attrs)
            } else {
                inflater.onCreateView(context, parent, viewName, attrs)
            }
        }

        private fun tryCreateView(
            context: Context,
            viewName: String,
            prefix: String,
            attrs: AttributeSet,
        ): View? {
            val constructor = VIEW_CONSTRUCTOR_MAP.getOrPut(viewName) {
                context.classLoader
                    .loadClass(prefix + viewName)
                    .asSubclass(View::class.java)
                    .getConstructor(*CONSTRUCTOR_SIGNATURE)
            }
            constructor.isAccessible = true
            return constructor.newInstance(context, attrs)
        }
    }
}
