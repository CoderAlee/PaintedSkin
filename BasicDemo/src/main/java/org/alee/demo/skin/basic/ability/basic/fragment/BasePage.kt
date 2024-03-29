package org.alee.demo.skin.basic.ability.basic.fragment

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.alee.demo.skin.basic.ability.basic.template.IUIProcess
import org.alee.demo.skin.basic.ability.exception.ViewLostException
import org.alee.demo.skin.basic.ability.util.getOrPut

/**
 * Fragment 基类
 *
 * <p> 1.为了减少对View状态的存储，在onCreateView时缓存了根View
 * <p> 2.实现[IUIProcess]接口，在Fragment原有生命周期调用。拓展出更加符合UI的生命周期
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
abstract class BasePage : SkinAblePage(), IUIProcess {

    /**
     * 所有注册的view
     */
    private val mViewCache = SparseArray<View>()

    /**
     *  根View
     */
    private var mFragmentContainerView: View? = null

    /**
     * 标识  是否为第一次创建View
     */
    private var mIsFirstCreate = true


    /**
     * [NavController]
     */
    val navigationController: NavController?
        get() = mFragmentContainerView?.run {
            try {
                return@run Navigation.findNavController(this)
            } catch (ignored: Throwable) {
            }
            return@run null
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        mIsFirstCreate = true
        super.onCreate(savedInstanceState)
        onInitData(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == mFragmentContainerView) {
            mFragmentContainerView = inflater.inflate(requireLayoutId(), container, false)
        }
        return mFragmentContainerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mIsFirstCreate) {
            mIsFirstCreate = false
            onFindView()
            onBindViewValue(savedInstanceState)
            onBindViewListener()
            subscribeDataChanged()
        } else {
            onReEnter(arguments)
        }
    }


    /**
     * 根据id 查找view
     * @param viewId Int
     * @return E
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(ViewLostException::class)
    fun <E : View> findView(@IdRes viewId: Int): E {
        val view: View = mViewCache.getOrPut(viewId) {
            mFragmentContainerView?.findViewById(viewId)
        } ?: throw ViewLostException(viewId)
        return view as E
    }

    /**
     * 当页面重新进入时被调用
     * @param bundle Bundle?
     */
    protected open fun onReEnter(bundle: Bundle?) {
    }

}