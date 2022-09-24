package org.alee.demo.skin.basic.ability.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.alee.component.skin.service.ThemeSkinService

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */


lateinit var context: Context


val @receiver:StringRes Int.stringResource: String
    get() = context.resources.getString(this)

fun @receiver:StringRes Int.stringResource(vararg formats: Any?): String {
    if (formats.isEmpty()) {
        return stringResource
    }
    return context.resources.getString(this, *formats)
}

/**
 * 通过id获取图片资源
 */
val @receiver:DrawableRes Int.drawableResource: Drawable?
    get() = ThemeSkinService.getInstance().currentThemeSkinPack.getDrawable(this) ?: mipmapDrawable

val @receiver:DrawableRes Int.mipmapDrawable: Drawable?
    get() = ThemeSkinService.getInstance().currentThemeSkinPack.getMipmap(this)


/**
 * 通过id获取颜色资源
 */
val @receiver:ColorRes Int.colorResource: Int
    get() = ThemeSkinService.getInstance().currentThemeSkinPack.getColor(this)

val @receiver:ColorRes Int.colorStateListResource: ColorStateList?
    get() = ThemeSkinService.getInstance().currentThemeSkinPack.getColorStateList(this)


/**
 * 通过id获取颜色图片资源
 */
val @receiver:ColorRes Int.colorDrawable
    get() = ColorDrawable(colorResource)


/**
 * 通过id获取距离
 */
val @receiver:DimenRes Int.dimenResource
    get() = context.resources.getDimensionPixelSize(this)


/**
 * 通过id 获取float距离
 */
val @receiver:DimenRes Int.floatDimenResource
    get() = context.resources.getDimension(this)


