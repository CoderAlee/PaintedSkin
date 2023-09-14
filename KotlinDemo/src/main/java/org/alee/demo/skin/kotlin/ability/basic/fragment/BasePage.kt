package org.alee.demo.skin.kotlin.ability.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import org.alee.demo.skin.kotlin.ability.basic.template.IUIProcess

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitData(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(requireLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFindView()
        onBindViewValue(savedInstanceState)
        onBindViewListener()
        subscribeDataChanged()
    }

    /**
     * 根据id 查找view
     * @param viewId Int
     * @return E
     */
    fun <E : View> findView(@IdRes viewId: Int): E {
        val rootView = view ?: throw RuntimeException("not find root view")
        return rootView.findViewById(viewId) as E
    }
}
