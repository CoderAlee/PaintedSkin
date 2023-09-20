package org.alee.component.skin.core.window

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.IThemeSkinObserver
import org.alee.component.skin.util.ext.measureTimeIfDebug
import org.alee.component.skin.util.ext.memoryAddress
import org.alee.component.skin.util.ext.subscribeThemeSkinIfNeed

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal sealed class BaseWindowProxy(private val mWindowName: String) : IWindowProxy, IThemeSkinObserver {

    protected val mViewWarehouse: ISkinnableViewWarehouse by lazy { SkinnableViewWarehouse(mWindowName) }

    @Volatile
    private var mIsVisible: Boolean = false

    private var mIsNeedApplySkin: Boolean = false

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    protected abstract val isApplyInInvisible: Boolean

    /**
     * 当窗口被创建时调用
     * @param context Context
     */
    @CallSuper
    override fun onWindowCreated(context: Context) {
        ThemeSkinService.subscribe(this)
    }

    /**
     * 当窗口可见时调用
     */
    @CallSuper
    override fun onWindowVisible() {
        mIsVisible = true
        if (mIsNeedApplySkin) {
            onThemeSkinChanged(0, ThemeSkinService.currentSkinPack)
        }
    }

    /**
     * 当窗口不可见时调用
     */
    @CallSuper
    override fun onWindowInvisible() {
        mIsVisible = false
    }

    /**
     * 当窗口被销毁时调用
     */
    @CallSuper
    override fun onWindowDestroyed() {
        ThemeSkinService.unsubscribe(this)
        mViewWarehouse.destroy()
    }

    /**
     * 添加需要换肤的View
     * @param view View [View]
     * @param skinAttributes Array<out SkinAttribute> [SkinAttribute]
     */
    override fun addSkinnableView(view: View, vararg skinAttributes: SkinAttribute) {
        mViewWarehouse.addSkinnableView(view, *skinAttributes)
        view.subscribeThemeSkinIfNeed()
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
            "[ $mWindowName ] Skin resurfacing takes time：$it ms"
        }
    }

    /**
     * 当主题发生变化时被回调
     * @param usedSkinPack IThemeSkinPack 当前使用的皮肤包
     */
    override fun onThemeSkinChangedRunOnUiThread(usedSkinPack: IThemeSkinPack) {
        // ignored
    }

    override fun dump(): String {
        return "$mWindowName visible = [$mIsVisible] waitApplySkin = [$mIsNeedApplySkin] skinnableViewSize = [${mViewWarehouse.skinnableViewSize}]"
    }

    override fun toString(): String {
        return "$mWindowName`Proxy@${this.memoryAddress}"
    }

    protected fun finalize() {
        onWindowDestroyed()
    }
}
