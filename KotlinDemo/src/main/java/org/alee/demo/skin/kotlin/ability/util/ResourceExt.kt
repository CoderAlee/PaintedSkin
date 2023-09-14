package org.alee.demo.skin.kotlin.ability.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.blankj.utilcode.util.Utils
import org.alee.component.skin.ThemeSkinService

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */

val @receiver:StringRes Int.stringResource: String
    get() = Utils.getApp().baseContext.resources.getString(this)

fun @receiver:StringRes Int.stringResource(vararg formats: Any?): String {
    if (formats.isEmpty()) {
        return stringResource
    }
    return Utils.getApp().baseContext.resources.getString(this, *formats)
}

/**
 * 通过id获取图片资源
 */
val @receiver:DrawableRes Int.drawableResource: Drawable?
    get() = mipmapDrawable ?: ThemeSkinService.currentSkinPack.getDrawable(this)

val @receiver:DrawableRes Int.mipmapDrawable: Drawable?
    get() = ThemeSkinService.currentSkinPack.getMipmap(this)

/**
 * 通过id获取颜色资源
 */
val @receiver:ColorRes Int.colorResource: Int
    get() = ThemeSkinService.currentSkinPack.getColor(this) ?: Color.BLACK

val @receiver:ColorRes Int.colorStateListResource: ColorStateList?
    get() = ThemeSkinService.currentSkinPack.getColorStateList(this)

/**
 * 通过id获取颜色图片资源
 */
val @receiver:ColorRes Int.colorDrawable
    get() = ColorDrawable(colorResource)

/**
 * 通过id获取距离
 */
val @receiver:DimenRes Int.dimenResource
    get() = Utils.getApp().baseContext.resources.getDimensionPixelSize(this)

/**
 * 通过id 获取float距离
 */
val @receiver:DimenRes Int.floatDimenResource
    get() = Utils.getApp().baseContext.resources.getDimension(this)
