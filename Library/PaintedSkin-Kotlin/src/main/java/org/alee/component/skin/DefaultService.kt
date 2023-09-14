package org.alee.component.skin

import android.app.Application
import android.content.Context
import android.view.View
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.core.pack.loadSkinPack
import org.alee.component.skin.core.parser.DefaultExecutorBuilder
import org.alee.component.skin.core.parser.ThemeSkinExecutorBuilderManager
import org.alee.component.skin.core.view.factory.IViewCreatorManager
import org.alee.component.skin.core.view.factory.ViewCreatorManager
import org.alee.component.skin.core.window.WindowManager
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.EmptyThemeSkin
import org.alee.component.skin.template.ISkinExecutorBuilder
import org.alee.component.skin.template.IThemeFactory
import org.alee.component.skin.template.IThemeSkin
import org.alee.component.skin.template.IThemeSkinObserver
import org.alee.component.skin.util.INotProguard
import org.alee.component.skin.util.Publisher
import org.alee.component.skin.util.ThemeSkinCoroutineExceptionHandler
import org.alee.component.skin.util.ext.logI
import org.alee.component.skin.util.ext.memoryAddress
import org.alee.component.skin.util.ext.printIfDebug
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/1
 *
 */
internal object DefaultService : IThemeSkinService, CoroutineScope by MainScope(), INotProguard {
    /**
     * 用于异步加载皮肤包的调度器
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private val mLoadSkinPackDispatcher by lazy { Dispatchers.IO.limitedParallelism(1) }

    /**
     * 加载皮肤包的协程名称
     */
    private val mLoadSkinPackCoroutineName by lazy { CoroutineName("LoadSkinPack") }

    /**
     * @see IViewCreatorManager
     */
    private val mViewCreatorManager: IViewCreatorManager by lazy { ViewCreatorManager() }

    /**
     * @see Publisher
     * @see IThemeSkinObserver
     */
    private val mSwitchThemePublisher by lazy { Publisher<IThemeSkinObserver>() }

    /**
     * @see Context
     */
    private val mContext by lazy { AtomicReference<Context>() }

    /**
     * @see IThemeFactory
     */
    private lateinit var mOptionFactory: IThemeFactory

    /**
     * @see IThemeSkinPack 当前正在使用的皮肤包
     */
    private lateinit var mCurrentThemeSkinPack: IThemeSkinPack

    /**
     * 当前正在执行的加载皮肤包异步任务
     */
    private var mLoadSkinPackJob: Job? = null

    /**
     * 当前主题
     */
    private val mCurrentTheme by lazy { AtomicInteger(Int.MIN_VALUE) }

    init {
        addSkinExecutorBuilder(DefaultExecutorBuilder())
    }

    /**
     * 获取当前正在使用的主题皮肤包
     */
    override val currentSkinPack: IThemeSkinPack
        get() = mCurrentThemeSkinPack

    /**
     * [IViewCreatorManager]
     */
    override val viewCreatorManager: IViewCreatorManager
        get() = mViewCreatorManager

    /**
     * 初始化服务
     * <p> 不建议开发者使用此函数进行初始化
     * @param application Application [Application]
     * @param factory IOptionFactory [IThemeFactory]
     * @param theme Int 启动时加载的主题;
     */
    override fun initialize(application: Application, factory: IThemeFactory, theme: Int) {
        mContext.set(application.baseContext)
        mOptionFactory = factory
        WindowManager.init(application)
        printIfDebug {
            " service initialization, The initial theme is $theme , the default theme is ${factory.defaultTheme}"
        }
        switchTheme(theme, true)
    }

    /**
     * 订阅主题切换事件
     * <p> 入参实例将会被框架内部以弱引用的形式持有
     * @param observer IThemeSkinObserver [IThemeSkinObserver]
     */
    override fun subscribe(observer: IThemeSkinObserver) {
        mSwitchThemePublisher.subscribe(observer)
    }

    /**
     * 取消订阅
     * @param observer IThemeSkinObserver [IThemeSkinObserver]
     */
    override fun unsubscribe(observer: IThemeSkinObserver) {
        mSwitchThemePublisher.unsubscribe(observer)
    }

    /**
     * 切换主题
     * @param theme Int 要切换的主题
     */
    override fun switchTheme(theme: Int) {
        switchTheme(theme, false)
    }

    /**
     * 将需要换肤的View委托给换肤框架进行维护。
     * <p> 注意：调用此函数后会立即对View执行一次换肤操作
     * @param skinnableView View 需要换肤的View
     * @param skinAttributes Array<out SkinAttribute> 需要换肤的属性集合
     */
    override fun entrustSkinnableView(skinnableView: View, vararg skinAttributes: SkinAttribute) {
        WindowManager.fetchWindowProxy(skinnableView.context)?.addSkinnableView(skinnableView, *skinAttributes)
    }

    /**
     * 添加换肤执行者构建器
     * @param builder ISkinExecutorBuilder [ISkinExecutorBuilder]
     */
    override fun addSkinExecutorBuilder(builder: ISkinExecutorBuilder) {
        ThemeSkinExecutorBuilderManager.addSkinExecutorBuilder(builder)
    }

    /**
     * 移除换肤执行者构建器
     * @param builder ISkinExecutorBuilder [ISkinExecutorBuilder]
     */
    override fun removeSkinExecutorBuilder(builder: ISkinExecutorBuilder) {
        ThemeSkinExecutorBuilderManager.removeSkinExecutorBuilder(builder)
    }

    /**
     * dump 换肤信息
     */
    override fun dump() {
        launch(Dispatchers.IO) {
            WindowManager.dump().logI()
        }
    }

    /**
     * 切换主题
     * @param theme Int 即将切换的主题
     * @param sync Boolean 是否同步切换
     */
    private fun switchTheme(theme: Int, sync: Boolean) {
        if (mCurrentTheme.get() == theme) {
            return
        }
        mLoadSkinPackJob?.cancel()
        if (theme == mOptionFactory.defaultTheme) {
            loadSkinPack(theme, EmptyThemeSkin, sync)
            return
        }
        val themeSKin = mOptionFactory.fetchThemeOption(theme)
        if (themeSKin is EmptyThemeSkin) {
            switchTheme(mOptionFactory.defaultTheme, sync)
            return
        }
        loadSkinPack(theme, themeSKin, sync)
    }

    private fun loadSkinPack(theme: Int, option: IThemeSkin, sync: Boolean) {
        if (sync) {
            runBlocking(mLoadSkinPackCoroutineName + ThemeSkinCoroutineExceptionHandler()) {
                onSkinPackLoadCompleted(theme, loadSkinPack(mContext.get(), option))
            }
            return
        }
        mLoadSkinPackJob = launch(mLoadSkinPackDispatcher + mLoadSkinPackCoroutineName + ThemeSkinCoroutineExceptionHandler()) {
            onSkinPackLoadCompleted(theme, loadSkinPack(mContext.get(), option))
        }
    }

    private fun CoroutineScope.onSkinPackLoadCompleted(theme: Int, pack: IThemeSkinPack) {
        if (isActive.not()) {
            return
        }
        mCurrentTheme.set(theme)
        mCurrentThemeSkinPack = pack
        mSwitchThemePublisher.notifyObservers { it.onThemeSkinChanged(theme, pack) }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return this.memoryAddress
    }
}
