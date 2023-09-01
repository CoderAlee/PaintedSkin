package org.alee.component.skin.core.pack

import org.alee.component.skin.constant.SkinPackStatus
import org.alee.component.skin.core.resources.provider.ISkinResourcesProvider

/**
 * 主题皮肤包
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
sealed interface IThemeSkinPack : ISkinResourcesProvider {
    /**
     * 皮肤包名称
     */
    val name: String

    /**
     * 判断 当前主题皮肤包是否可用
     * <p> true: 皮肤包有效且可以使用；false ：皮肤包无效且无法使用
     */
    val isAvailable: Boolean

    /**
     * 皮肤包当前状态
     */
    @get:SkinPackStatus
    val state: Int
}
