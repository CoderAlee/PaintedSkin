package org.alee.component.skin.core.resources.provider

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import org.alee.component.skin.constant.ResourcesType
import java.io.File

/**
 * [SkinPackType.CUSTOM] 类型 皮肤资源提供者基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
abstract class BaseCustomSkinResourcesProvider(protected val context: Context) : BaseSkinResourcesProvider() {
    /**
     * 包名
     */
    private val packageName: String = context.packageName

    /**
     * App [Resources]
     */
    protected val resources: Resources by lazy { context.resources }

    override val theme: Theme?
        get() = context.theme

    /**
     * 获取状态颜色集合
     *
     * @param resName 原始资源名称
     * @param theme         主题
     * @return [ColorStateList]
     * @throws Throwable
     */
    override fun getColorStateList(resName: String, theme: Theme?): ColorStateList? {
        return getColorStateList(resName.targetResourcesId(ResourcesType.COLOR), theme)
    }

    /**
     * 获取色值
     *
     * @param resName 资源名称
     * @param theme   主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    override fun getColor(resName: String, theme: Theme?): Int? {
        return getColor(resName.targetResourcesId(ResourcesType.COLOR), theme)
    }

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return [Drawable]
     * @throws Throwable 任何异常
     */
    override fun getDrawable(resName: String, theme: Theme?): Drawable? {
        return getDrawable(resName.targetResourcesId(ResourcesType.DRAWABLE), theme)
    }

    /**
     * 获取图片
     *
     * @param resName 资源名
     * @param theme   主题
     * @return [Drawable]
     * @throws Throwable 任何异常
     */
    override fun getMipmap(resName: String, theme: Theme?): Drawable? {
        return getMipmap(resName.targetResourcesId(ResourcesType.MIPMAP), theme)
    }

    /**
     * 通过资源名在皮肤包内找到对应的资源id
     * @receiver String 资源名
     * @param type String 资源类型
     * @return Int
     */
    protected fun String.targetResourcesId(@ResourcesType type: String): Int {
        if (isEmpty()) {
            return -1
        }
        return resources.getIdentifier(this, type, packageName)
    }

    /**
     * 从SDK中加载图片
     * @param filepath String 文件路径
     * @return Drawable? [Drawable]
     */
    protected fun loadImageFromSDCard(filepath: String): Drawable? {
        if (File(filepath).exists().not()) {
            return defaultDrawable
        }
        return BitmapDrawable(resources, filepath)
    }

    /**
     * 将[Bitmap] 转换成 [Drawable]
     */
    protected inline val Bitmap?.toDrawable: Drawable?
        get() = this?.run { BitmapDrawable(resources, this) }
}
