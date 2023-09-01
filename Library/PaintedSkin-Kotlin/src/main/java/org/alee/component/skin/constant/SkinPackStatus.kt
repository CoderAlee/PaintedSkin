package org.alee.component.skin.constant

import androidx.annotation.IntDef

/**
 * 皮肤包状态
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
@IntDef(SkinPackStatus.NEW, SkinPackStatus.RUNNABLE, SkinPackStatus.DESTROYED)
@Retention(AnnotationRetention.SOURCE)
annotation class SkinPackStatus {
    companion object {
        /**
         * 新建 - 可以获取到皮肤包基础属性,但无法获取到资源
         */
        const val NEW = 0x1000

        /**
         * 就绪 - 皮肤包完全可用
         */
        const val RUNNABLE = 0x1001

        /**
         * 销毁 - 皮肤包完全不可用
         */
        const val DESTROYED = 0x1002
    }
}
