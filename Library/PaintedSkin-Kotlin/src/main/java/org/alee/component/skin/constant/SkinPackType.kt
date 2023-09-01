package org.alee.component.skin.constant

import androidx.annotation.IntDef

/**
 * 换肤框架支持的皮肤包类型
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
@IntDef(SkinPackType.STANDARD, SkinPackType.ASSETS, SkinPackType.CUSTOM)
@Retention(AnnotationRetention.SOURCE)
annotation class SkinPackType {
    companion object {
        /**
         * 标准皮肤包 - 以.skin作为后缀的apk
         */
        const val STANDARD = 0x1000

        /**
         * 从Assets目录加载资源的皮肤包
         */
        const val ASSETS = 0x1001

        /**
         * 自定义皮肤包，由开发者自由发挥
         */
        const val CUSTOM = 0x1002
    }
}
