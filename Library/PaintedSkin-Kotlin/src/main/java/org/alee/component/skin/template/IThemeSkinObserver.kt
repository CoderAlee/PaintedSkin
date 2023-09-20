package org.alee.component.skin.template

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.component.skin.util.runOnMainThread

/**
 * 主题皮肤观察者
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/1
 *
 */
interface IThemeSkinObserver {

    /**
     * 当主题发生变化时被回调
     * <p>注意此回调可能为非UI线程
     * @param theme Int 当前使用的主题
     * @param usedSkinPack IThemeSkinPack 当前使用的皮肤包
     */
    @AnyThread
    fun onThemeSkinChanged(theme: Int, usedSkinPack: IThemeSkinPack) {
        runOnMainThread {
            // 由于此处会发生线程切换，无法保证入参的usedSkinPack与ThemeSkinService.currentSkinPack一致。
            // 因此此处使用ThemeSkinService.currentSkinPack来确保使用的皮肤包一定是最新主题的皮肤包
            onThemeSkinChangedRunOnUiThread(ThemeSkinService.currentSkinPack)
        }
    }

    /**
     * 当主题发生变化时被回调
     * @param usedSkinPack IThemeSkinPack 当前使用的皮肤包
     */
    @MainThread
    fun onThemeSkinChangedRunOnUiThread(usedSkinPack: IThemeSkinPack)
}
