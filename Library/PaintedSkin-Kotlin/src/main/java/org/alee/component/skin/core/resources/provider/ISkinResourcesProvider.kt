package org.alee.component.skin.core.resources.provider

import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import androidx.annotation.AnyThread
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import org.alee.component.skin.util.Version

/**
 * 主题资源提供者接口
 *
 * <p> 实现此接口的实现类需要负责提供Android 资源
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
interface ISkinResourcesProvider {
    /**
     * 主题
     */
    @Version("4.0.0")
    val theme: Theme?

    /**
     * 获取状态颜色集合
     * @param resId Int 颜色资源id
     * @return ColorStateList?
     */
    @Version("4.0.0")
    @AnyThread
    fun getColorStateList(@ColorRes resId: Int): ColorStateList?

    /**
     * 获取状态颜色集合
     * @param resNames Array<out String> 资源名称
     * @return ColorStateList?
     */
    @Version("4.0.0")
    @AnyThread
    fun getColorStateList(vararg resNames: String): ColorStateList?

    /**
     *  根据资源id 获取颜色资源
     * @param resId Int 资源id
     * @return Int?
     */
    @Version("4.0.0")
    @AnyThread
    fun getColor(@ColorRes resId: Int): Int?

    /**
     * 根据资源名 获取颜色值
     * @param resNames Array<out String> 资源名称
     * @return Int?
     */
    @Version("4.0.0")
    @AnyThread
    fun getColor(vararg resNames: String): Int?

    /**
     * 根据资源id 获取可拉伸资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    @Version("4.0.0")
    @AnyThread
    fun getDrawable(@DrawableRes resId: Int): Drawable?

    /**
     * 根据名称 获取可拉伸资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    @Version("4.0.0")
    @AnyThread
    fun getDrawable(vararg resNames: String): Drawable?

    /**
     * 根据资源id 获取mipmap目录下的图片资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    @Version("4.0.0")
    @AnyThread
    fun getMipmap(@DrawableRes resId: Int): Drawable?

    /**
     * 根据名称 获取mipmap目录下的图片资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    @Version("4.0.0")
    @AnyThread
    fun getMipmap(vararg resNames: String): Drawable?
}
