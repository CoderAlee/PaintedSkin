package org.alee.component.skin.constant

import androidx.annotation.StringDef

/**
 * Android 资源类型
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
@StringDef(
    ResourcesType.DRAWABLE,
    ResourcesType.MIPMAP,
    ResourcesType.COLOR,
    ResourcesType.COLOR_STATE_LIST,
    ResourcesType.STRING,
    ResourcesType.DIMEN,
)
@Retention(AnnotationRetention.SOURCE)
annotation class ResourcesType {
    companion object {
        /**
         * drawable资源类型
         */
        const val DRAWABLE = "drawable"

        /**
         * Mipmap 资源类型
         */
        const val MIPMAP = "mipmap"

        /**
         * color 资源类型
         */
        const val COLOR = "color"

        /**
         * ColorStateList
         */
        const val COLOR_STATE_LIST = "colorStateList"

        /**
         * string 资源类型
         */
        const val STRING = "string"

        /**
         * dimen 资源类型
         */
        const val DIMEN = "dimen"
    }
}
