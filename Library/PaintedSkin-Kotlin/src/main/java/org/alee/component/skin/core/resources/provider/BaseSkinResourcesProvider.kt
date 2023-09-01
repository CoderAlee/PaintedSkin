package org.alee.component.skin.core.resources.provider

import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import org.alee.component.skin.constant.ResourcesType
import org.alee.component.skin.util.ext.isValidResourcesId
import org.alee.component.skin.util.safeCall
import java.util.concurrent.ConcurrentHashMap

/**
 * 皮肤资源提供者基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
sealed class BaseSkinResourcesProvider : ISkinResourcesProvider {

    /**
     * 黑盒
     * <p> Key - 资源类型 [ResourcesType]
     * <p> value - List<Any> Any = 资源名称或资源ID
     */
    private val mBlackBoxMap: MutableMap<String, MutableList<Any>> by lazy { ConcurrentHashMap() }

    /**
     * 默认的[ColorStateList]
     */
    protected val defaultColorStateList: ColorStateList? = null

    /**
     * 默认的颜色值
     */
    protected val defaultColor: Int? = null

    /**
     * 默认[Drawable]
     *
     */
    protected val defaultDrawable: Drawable? = null

    /**
     * 获取状态颜色集合
     * @param resId Int 颜色资源id
     * @return ColorStateList?
     */
    final override fun getColorStateList(resId: Int): ColorStateList? {
        if (resId.isValidResourcesId.not()) {
            return defaultColorStateList
        }
        if (resId.isBadResources(ResourcesType.COLOR_STATE_LIST)) {
            return defaultColorStateList
        }
        var temp: ColorStateList? = null
        safeCall {
            temp = getColorStateList(resId, theme).apply {
                cacheIfNeed(resId)
            }
        }
        return if (temp.isValid) temp else defaultColorStateList
    }

    /**
     * 获取状态颜色集合
     * @param resNames Array<out String> 资源名称
     * @return ColorStateList?
     */
    final override fun getColorStateList(vararg resNames: String): ColorStateList? {
        val resName = resNames.composite
        if (resName.isEmpty()) {
            return defaultColorStateList
        }
        if (resName.isBadResources(ResourcesType.COLOR_STATE_LIST)) {
            return defaultColorStateList
        }
        var temp: ColorStateList? = null
        safeCall {
            temp = getColorStateList(resName, theme).apply {
                cacheIfNeed(resName)
            }
        }
        return if (temp.isValid) temp else defaultColorStateList
    }

    /**
     * 根据资源id 获取颜色资源
     * @param resId Int 资源id
     * @return Int?
     */
    final override fun getColor(resId: Int): Int? {
        if (resId.isValidResourcesId.not()) {
            return defaultColor
        }
        if (resId.isBadResources(ResourcesType.COLOR)) {
            return defaultColor
        }
        var temp: Int? = null
        safeCall {
            temp = getColor(resId, theme).apply {
                cacheColorIfNeed(resId)
            }
        }
        return if (temp.isValidColor) temp else defaultColor
    }

    /**
     * 根据资源名 获取颜色值
     * @param resNames Array<out String> 资源名称
     * @return Int?
     */
    final override fun getColor(vararg resNames: String): Int? {
        val resName = resNames.composite
        if (resName.isEmpty()) {
            return defaultColor
        }
        if (resName.isBadResources(ResourcesType.COLOR)) {
            return defaultColor
        }
        var temp: Int? = null
        safeCall {
            temp = getColor(resName, theme).apply {
                cacheColorIfNeed(resName)
            }
        }
        return if (temp.isValidColor) temp else defaultColor
    }

    /**
     * 根据资源id 获取可拉伸资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    final override fun getDrawable(resId: Int): Drawable? {
        if (resId.isValidResourcesId.not()) {
            return defaultDrawable
        }
        if (resId.isBadResources(ResourcesType.DRAWABLE)) {
            return defaultDrawable
        }
        var temp: Drawable? = null
        safeCall {
            temp = getDrawable(resId, theme).apply {
                if (isValid.not()) {
                    addBadResources(ResourcesType.DRAWABLE, resId)
                }
            }
        }
        return if (temp.isValid) temp else defaultDrawable
    }

    /**
     * 根据名称 获取可拉伸资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    final override fun getDrawable(vararg resNames: String): Drawable? {
        val resName = resNames.composite
        if (resName.isEmpty()) {
            return defaultDrawable
        }
        if (resName.isBadResources(ResourcesType.DRAWABLE)) {
            return defaultDrawable
        }
        var temp: Drawable? = null
        safeCall {
            temp = getDrawable(resName, theme).apply {
                if (isValid.not()) {
                    addBadResources(ResourcesType.DRAWABLE, resName)
                }
            }
        }
        return if (temp.isValid) temp else defaultDrawable
    }

    /**
     * 根据资源id 获取mipmap目录下的图片资源
     * @param resId Int 资源id
     * @return Drawable?
     */
    final override fun getMipmap(resId: Int): Drawable? {
        if (resId.isValidResourcesId.not()) {
            return defaultDrawable
        }
        if (resId.isBadResources(ResourcesType.MIPMAP)) {
            return defaultDrawable
        }
        var temp: Drawable? = null
        safeCall {
            temp = getMipmap(resId, theme).apply {
                if (isValid.not()) {
                    addBadResources(ResourcesType.MIPMAP, resId)
                }
            }
        }
        return if (temp.isValid) temp else defaultDrawable
    }

    /**
     * 根据名称 获取mipmap目录下的图片资源
     * @param resNames Array<out String> 资源名称
     * @return Drawable?
     */
    final override fun getMipmap(vararg resNames: String): Drawable? {
        val resName = resNames.composite
        if (resName.isEmpty()) {
            return defaultDrawable
        }
        if (resName.isBadResources(ResourcesType.MIPMAP)) {
            return defaultDrawable
        }
        var temp: Drawable? = null
        safeCall {
            temp = getMipmap(resName, theme).apply {
                if (isValid.not()) {
                    addBadResources(ResourcesType.MIPMAP, resName)
                }
            }
        }
        return if (temp.isValid) temp else defaultDrawable
    }

    /**
     * 获取状态颜色集合
     *
     * @param resId 原始资源Id
     * @param theme 主题
     * @return [ColorStateList]
     * @throws Throwable
     */
    @Throws(Throwable::class)
    protected abstract fun getColorStateList(@ColorRes resId: Int, theme: Theme?): ColorStateList?

    /**
     * 获取状态颜色集合
     *
     * @param resName 原始资源名称
     * @param theme         主题
     * @return [ColorStateList]
     * @throws Throwable
     */
    @Throws(Throwable::class)
    protected abstract fun getColorStateList(resName: String, theme: Theme?): ColorStateList?

    /**
     * 获取色值
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getColor(@ColorRes resId: Int, theme: Theme?): Int?

    /**
     * 获取色值
     *
     * @param resName 资源名称
     * @param theme   主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getColor(resName: String, theme: Theme?): Int?

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getDrawable(@DrawableRes resId: Int, theme: Theme?): Drawable?

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return [Drawable]
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getDrawable(resName: String, theme: Theme?): Drawable?

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return [Drawable]
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getMipmap(resName: String, theme: Theme?): Drawable?

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    @Throws(Throwable::class)
    protected abstract fun getMipmap(@DrawableRes resId: Int, theme: Theme?): Drawable?

    /**
     * 判断Any是否对应了一个无效资源
     * @receiver Any ResourcesId 或 ResourcesName
     * @param type String 资源类型[ResourcesType]
     * @return Boolean true - 无效 false - 有效
     */
    private fun Any.isBadResources(@ResourcesType type: String): Boolean {
        val value = mBlackBoxMap[type]
        if (value.isNullOrEmpty()) {
            return false
        }
        return value.contains(this)
    }

    /**
     * 判断[ColorStateList] 是否有效
     */
    protected inline val ColorStateList?.isValid: Boolean
        get() = null != this

    private fun ColorStateList?.cacheIfNeed(nameOrId: Any) {
        if (isValid) {
            return
        }
        addBadResources(ResourcesType.COLOR_STATE_LIST, nameOrId)
    }

    /**
     * 判断一个色值是否有效
     */
    protected inline val Int?.isValidColor: Boolean
        get() = null != this && this in Color.BLACK..Color.TRANSPARENT

    private fun Int?.cacheColorIfNeed(nameOrId: Any) {
        if (isValidColor) {
            return
        }
        addBadResources(ResourcesType.COLOR, nameOrId)
    }

    /**
     * 判断[Drawable] 是否有效
     */
    protected inline val Drawable?.isValid: Boolean
        get() = null != this

    /**
     * 记录一条无法获取到的资源
     *
     * @param type     资源类型
     * @param nameOrId 资源名称
     */
    private fun addBadResources(@ResourcesType type: String, nameOrId: Any) {
        mBlackBoxMap.getOrPut(type) { ArrayList() }.add(nameOrId)
    }

    /**
     * 合并资源名称
     */
    private inline val Array<out String>.composite: String
        get() = if (isEmpty()) "" else reduce { a, b -> a + b }
}
