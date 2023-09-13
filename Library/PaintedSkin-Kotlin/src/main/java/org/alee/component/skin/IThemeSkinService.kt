package org.alee.component.skin

import android.app.Application
import android.view.View
import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.core.view.factory.IViewCreatorManager
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.ISkinExecutorBuilder
import org.alee.component.skin.template.IThemeFactory
import org.alee.component.skin.template.IThemeSkinObserver
import org.alee.component.skin.util.INotProguard
import org.alee.component.skin.util.WeakReference
import org.jetbrains.annotations.TestOnly

/**
 * 换肤服务
 *
 * <p> 换肤库对外暴露的核心服务
 *
 * @author MingYu.Liu
 * created in 2023/7/10
 *
 */
sealed interface IThemeSkinService : INotProguard {
    /**
     * 获取当前正在使用的主题皮肤包
     */
    val currentSkinPack: IThemeSkinPack

    /**
     * [IViewCreatorManager]
     */
    val viewCreatorManager: IViewCreatorManager

    /**
     * 初始化服务
     * <p> 不建议开发者使用此函数进行初始化
     * @param application Application [Application]
     * @param factory IOptionFactory [IThemeFactory]
     * @param theme Int 启动时加载的主题;
     */
    @MainThread
    fun initialize(application: Application, factory: IThemeFactory, theme: Int = factory.defaultTheme)

    /**
     * 订阅主题切换事件
     * <p> 入参实例将会被框架内部以弱引用的形式持有
     * @param observer IThemeSkinObserver [IThemeSkinObserver]
     */
    @AnyThread
    fun subscribe(@WeakReference observer: IThemeSkinObserver)

    /**
     * 取消订阅
     * @param observer IThemeSkinObserver [IThemeSkinObserver]
     */
    @AnyThread
    fun unsubscribe(observer: IThemeSkinObserver)

    /**
     * 切换主题
     * @param theme Int 要切换的主题
     */
    @AnyThread
    fun switchTheme(theme: Int)

    /**
     * 将需要换肤的View委托给换肤框架进行维护。
     * <p> 注意：调用此函数后会立即对View执行一次换肤操作
     * @param skinnableView View 需要换肤的View
     * @param skinAttributes Array<out SkinAttribute> 需要换肤的属性集合
     */
    @AnyThread
    fun entrustSkinnableView(@WeakReference skinnableView: View, vararg skinAttributes: SkinAttribute)

    /**
     * 添加换肤执行者构建器
     * @param builder ISkinExecutorBuilder [ISkinExecutorBuilder]
     */
    @AnyThread
    fun addSkinExecutorBuilder(builder: ISkinExecutorBuilder)

    /**
     * 移除换肤执行者构建器
     * @param builder ISkinExecutorBuilder [ISkinExecutorBuilder]
     */
    fun removeSkinExecutorBuilder(builder: ISkinExecutorBuilder)

    /**
     * dump 换肤信息
     */
    @TestOnly
    fun dump()
}
