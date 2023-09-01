package org.alee.component.skin.constant

import androidx.annotation.IntDef

/**
 * 性能模式
 *
 * <p> 设置换肤框架以何种形式进行换肤
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
@IntDef(PerformanceMode.PERFORMANCE_PRIORITY, PerformanceMode.EXPERIENCE_FIRST)
@Retention(AnnotationRetention.SOURCE)
annotation class PerformanceMode {
    companion object {
        /**
         * 性能优先
         * <p> 使用此种模式会将在后台不可见的View的换肤操作延迟到View重新可见时执行
         */
        const val PERFORMANCE_PRIORITY = 0x1000

        /**
         * 体验优先
         * <p> 使用此种模式会将在后台不可见的View的换肤操作立即执行
         */
        const val EXPERIENCE_FIRST = 0x1001
    }
}
