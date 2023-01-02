package org.alee.demo.skin.basic.ability.util

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import org.alee.demo.skin.basic.ability.R

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/1/2
 *
 */


/**
 * 为TextView 设置顶部图标
 * @receiver TextView
 * @param icon Int 图标资源
 * @param size Int 图标大小
 */
fun TextView.setTopDrawable(@DrawableRes icon: Int, @DimenRes size: Int = R.dimen.tp_24) {
    setTopDrawable(icon.drawableResource, size)
}

/**
 *
 * @receiver TextView
 * @param drawable Drawable? 图标
 * @param size Int 图标大小
 */
fun TextView.setTopDrawable(drawable: Drawable?, @DimenRes size: Int = R.dimen.tp_24) {
    setDrawable(top = drawable?.apply {
        size.dimenResource.run {
            setBounds(0, 0, this, this)
        }
    })
}


/**
 * 为TextView 设置左侧图标
 * @receiver TextView
 * @param icon Int 图标资源
 * @param size Int 图标大小
 */
fun TextView.setLeftDrawable(@DrawableRes icon: Int, @DimenRes size: Int = R.dimen.tp_24) {
    setLeftDrawable(icon.drawableResource, size)
}

/**
 * 为TextView 设置左侧图标
 * @receiver TextView
 * @param drawable Drawable? 图标
 * @param size Int 图标大小
 */
fun TextView.setLeftDrawable(drawable: Drawable?, @DimenRes size: Int = R.dimen.tp_24) {
    setDrawable(left = drawable?.apply {
        size.dimenResource.run {
            setBounds(0, 0, this, this)
        }
    })
}

fun TextView.setDrawable(
    left: Drawable? = null,
    top: Drawable? = null,
    right: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawables(left, top, right, bottom)
}