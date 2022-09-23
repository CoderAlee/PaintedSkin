package org.alee.demo.skin.basic.ability.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */

/**
 * 为ImageView设置矢量图
 * @receiver ImageView
 * @param icon Int 图标资源
 * @param tint Int 图标的着色
 * @param mutate Boolean  是否打破 Drawable内部state对象的共享
 */
internal fun ImageView.setVector(@DrawableRes icon: Int, @ColorRes tint: Int, mutate: Boolean = false) {
    icon.drawableResource?.run {
        setVector(this, tint, mutate)
    }
}


/**
 * 为ImageView设置矢量图
 * @receiver ImageView
 * @param icon Drawable 图标
 * @param tint Int 图标的着色
 * @param mutate Boolean 是否打破 Drawable内部state对象的共享
 */
internal fun ImageView.setVector(
    icon: Drawable,
    @ColorRes tint: Int,
    mutate: Boolean = false
) {
    setImageDrawable(icon.apply {
        if (mutate) {
            mutate()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTint(tint.colorResource)
        }
    })
}