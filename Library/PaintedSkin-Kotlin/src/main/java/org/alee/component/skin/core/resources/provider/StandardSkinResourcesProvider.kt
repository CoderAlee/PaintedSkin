package org.alee.component.skin.core.resources.provider

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Build.VERSION
import androidx.core.content.res.ResourcesCompat
import org.alee.component.skin.constant.ResourcesType

/**
 * 标准皮肤包资源提供者
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
internal class StandardSkinResourcesProvider(
    mPackContext: Context?,
    mHostResources: Resources,
    mPackResources: Resources,
    mPackPackageName: String,
) :
    BaseStandardSkinResourcesProvider(
        mPackContext,
        mHostResources,
        mPackResources,
        mPackPackageName,
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
        val resId = resName.targetResourcesId(ResourcesType.COLOR)
        if (resId.isValidResourcesId.not()) {
            return defaultColorStateList
        }
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mPackResources.getColorStateList(resId, null)
        } else {
            ResourcesCompat.getColorStateList(mPackResources, resId, theme)
        }
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
        val resId = resName.targetResourcesId(ResourcesType.COLOR)
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
        val resId = resName.targetResourcesId(ResourcesType.DRAWABLE)
        if (resId.isValidResourcesId.not()) {
            return defaultDrawable
        }
        return ResourcesCompat.getDrawable(mPackResources, resId, theme)
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
        val resId = resName.targetResourcesId(ResourcesType.MIPMAP)
        if (resId.isValidResourcesId.not()) {
            return defaultDrawable
        }
        return ResourcesCompat.getDrawable(mPackResources, resId, theme)
    }
}
