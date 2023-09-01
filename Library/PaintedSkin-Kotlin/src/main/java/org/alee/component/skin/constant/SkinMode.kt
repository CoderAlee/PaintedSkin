package org.alee.component.skin.constant

import androidx.annotation.IntDef

/**
 * 换肤模式定义
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
@IntDef(SkinMode.REPLACE_ALL, SkinMode.REPLACE_MARKED, SkinMode.DO_NOT_REPLACE)
@Retention(AnnotationRetention.SOURCE)
annotation class SkinMode {
    companion object {
        /**
         * 所有View都参与换肤
         */
        const val REPLACE_ALL = 0

        /**
         * 只有添加了[skin:enable = true]属性的View才参与换肤
         */
        const val REPLACE_MARKED = 1

        /**
         * 是有View均不参与换肤
         */
        const val DO_NOT_REPLACE = 2
    }
}
