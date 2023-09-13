package org.alee.component.skin.core.executor

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.MainThread
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.constant.ResourcesType
import org.alee.component.skin.exception.ApplySkinException
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.ISkinExecutor
import org.alee.component.skin.util.ext.logI
import org.alee.component.skin.util.isOnMainThread

/**
 * 换肤执行器 - 基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
sealed class BaseSkinExecutor<T : View> : ISkinExecutor {
    /**
     * 执行换肤工作
     * @param view View 需要换肤的View
     * @param attribute SkinAttribute 换肤属性
     * @throws ApplySkinException 换肤时出现的异常
     */
    final override fun execute(view: View, attribute: SkinAttribute) {
        assertMainThread(view, attribute)
        try {
            applyThemeSkin(view as T, attribute)
        } catch (exception: Throwable) {
            throw ApplySkinException(view, attribute, exception)
        }
    }

    @Throws(Throwable::class)
    @MainThread
    private fun applyThemeSkin(view: T, attribute: SkinAttribute) {
        when (attribute.resourceType) {
            ResourcesType.COLOR -> {
                val colorStateList = ThemeSkinService.currentSkinPack.getColorStateList(attribute.resourceId)
                if (null == colorStateList) {
                    ThemeSkinService.currentSkinPack.getColor(attribute.resourceId)?.run {
                        applyColor(view, this, attribute.attrName)
                    }
                } else {
                    applyColor(view, colorStateList, attribute.attrName)
                }
            }

            ResourcesType.DRAWABLE -> ThemeSkinService.currentSkinPack.getDrawable(attribute.resourceId)?.run {
                applyDrawable(view, this, attribute.attrName)
            }

            ResourcesType.MIPMAP -> ThemeSkinService.currentSkinPack.getMipmap(attribute.resourceId)?.run {
                applyDrawable(view, this, attribute.attrName)
            }

            else -> {
                "Unsupported resource type: ${attribute.resourceType}".logI()
            }
        }
    }

    /**
     * 换肤 - ColorStateList
     * @param view T extends View 需要换肤的View
     * @param colorStateList ColorStateList 颜色状态集合
     * @param attrName String 属性名称 例 textColor
     * @throws Throwable 换肤时出现的异常
     */
    @Throws(Throwable::class)
    @MainThread
    protected abstract fun applyColor(view: T, colorStateList: ColorStateList, attrName: String)

    /**
     * 换肤 - Color
     * @param view T extends View 需要换肤的View
     * @param color Int 色值
     * @param attrName String 属性名称 例 textColor
     * @throws Throwable 换肤时出现的异常
     */
    @Throws(Throwable::class)
    @MainThread
    protected abstract fun applyColor(view: T, @ColorInt color: Int, attrName: String)

    /**
     * 换肤 - Drawable
     * @param view T extends View 需要换肤的View
     * @param drawable Drawable 图片
     * @param attrName String 属性名称 例 background
     * @throws Throwable 换肤时出现的异常
     */
    @Throws(Throwable::class)
    @MainThread
    protected abstract fun applyDrawable(view: T, drawable: Drawable, attrName: String)

    private fun assertMainThread(view: View, attribute: SkinAttribute) {
        if (isOnMainThread) {
            return
        }
        throw ApplySkinException(view, attribute, RuntimeException("Skin change must be performed on the main thread!"))
    }
}
