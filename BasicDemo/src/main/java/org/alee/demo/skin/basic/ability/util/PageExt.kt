package org.alee.demo.skin.basic.ability.util

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
@MainThread
internal fun <T : ViewModel> BasePage.requireActivityViewModel(clazz: Class<T>): T {
    return ViewModelProvider(requireActivity())[clazz]
}

@MainThread
internal fun <T : ViewModel> BasePage.requireViewModel(clazz: Class<T>): T {
    return viewModelOf(clazz, this)
}

@MainThread
internal fun <T : ViewModel> BasePage.requireViewModelByParent(clazz: Class<T>): T {
    return viewModelOf(clazz, requireParentFragment())
}

@MainThread
internal inline fun <reified VM : ViewModel> BasePage.parentViewModels(): Lazy<VM> =
    createViewModelLazy(VM::class, { requireParentFragment().viewModelStore },
        { requireParentFragment().defaultViewModelProviderFactory })


/**
 * 获取一个其他生命周期的VM
 * @receiver PageFragment
 * @param clazz Class<T>
 * @param owner ViewModelStoreOwner
 * @return T
 */
@MainThread
internal fun <T : ViewModel> BasePage.viewModelOf(clazz: Class<T>, owner: ViewModelStoreOwner): T {
    return ViewModelProvider(owner).get(clazz)
}


/**
 * 订阅LiveData 绑定的生命周期与Fragment相同
 * @receiver PageFragment
 * @param data LiveData<T>
 * @param observer Observer<T>
 */
@MainThread
internal fun <T> BasePage.subscribe(data: LiveData<T>, observer: Observer<T>) {
    data.removeObservers(this)
    data.observe(this, observer)
}


/**
 * 订阅LiveData 绑定的生命周期与RootView相同
 * @receiver PageFragment
 * @param data LiveData<T>
 * @param observer Observer<T>
 */
@MainThread
internal fun <T> BasePage.subscribeByView(data: LiveData<T>, observer: Observer<T>) {
    data.removeObservers(viewLifecycleOwner)
    data.observe(viewLifecycleOwner, observer)
}

/**
 * 迁移到某个页面
 *
 * @param destination 目的地id或actionId
 * @param args 参数
 * @param options 跳转配置
 */
@MainThread
internal fun BasePage.go(@IdRes destination: Int, args: Bundle? = null, options: NavOptions? = null) {
    navigationController?.go(destination, args, options)
}

@MainThread
internal fun BasePage.backThenGo(
    @IdRes destination: Int,
    args: Bundle? = null,
    options: NavOptions? = null
) {
    navigationController?.run {
        back()
        go(destination, args, options)
    }
}

/**
 * 迁移到某个页面
 *
 * @param directions [NavDirections]
 */
@MainThread
internal fun BasePage.go(directions: NavDirections) {
    go(directions.actionId, directions.arguments)
}


/**
 * 返回
 *
 */
@MainThread
internal fun BasePage.back() {
    navigationController.back()
}

/**
 * 出栈至某个页面
 *
 * @param destination 目的地id
 * @param inclusive true:将目标页面也出栈再入栈一个新的目标页面
 * @return 是否出栈成功
 */
@MainThread
internal fun BasePage.backTo(@IdRes destination: Int, inclusive: Boolean = false): Boolean {
    return navigationController?.popBackStack(destination, inclusive) ?: false
}

@MainThread
internal fun <E : View> BasePage.bindView(@IdRes viewId: Int): Lazy<E> {
    return lazyInUI { findView(viewId) }
}

