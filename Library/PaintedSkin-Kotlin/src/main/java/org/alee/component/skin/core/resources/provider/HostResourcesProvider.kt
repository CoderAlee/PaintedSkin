package org.alee.component.skin.core.resources.provider

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import org.alee.component.skin.constant.ResourcesType
import org.alee.component.skin.util.safeCall

/**
 * 宿主应用资源提供者
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
internal class HostResourcesProvider(hostContext: Context) : BaseStandardSkinResourcesProvider(
    hostContext,
    hostContext.resources,
    hostContext.resources,
    hostContext.packageName,
) {
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

    override fun getColorStateList(resId: Int, theme: Theme?): ColorStateList? {
        if (resId.isValidResourcesId.not()) {
            return defaultColorStateList
        }
        var colorStateList: ColorStateList? = null
        safeCall {
            colorStateList = ResourcesCompat.getColorStateList(mPackResources, resId, theme)
        }
        if (colorStateList.isValid.not()) {
            mPackContext?.run {
                colorStateList = AppCompatResources.getColorStateList(this, resId)
            }
        }
        return colorStateList
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

    override fun getColor(resId: Int, theme: Theme?): Int? {
        if (resId.isValidResourcesId.not()) {
            return defaultColor
        }
        return ResourcesCompat.getColor(mPackResources, resId, theme)
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

    override fun getDrawable(resId: Int, theme: Theme?): Drawable? {
        if (resId.isValidResourcesId.not()) {
            return defaultDrawable
        }
        var drawable: Drawable? = null
        safeCall {
            drawable = ResourcesCompat.getDrawable(mPackResources, resId, theme)
        }
        if (drawable.isValid.not()) {
            mPackContext?.run {
                drawable = AppCompatResources.getDrawable(this, resId)
            }
        }
        return drawable
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

    override fun getMipmap(resId: Int, theme: Theme?): Drawable? {
        return getDrawable(resId, theme)
    }
}
