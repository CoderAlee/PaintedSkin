package org.alee.demo.skin.kotlin.ability.theme

import org.alee.component.skin.template.IStandardThemeSkin
import org.alee.demo.skin.kotlin.ability.SKIN_PACK_PATH
import org.alee.demo.skin.kotlin.ability.SPRING_FESTIVAL_SKIN_PACK_NAME

/**
 * 春节-亮 主题皮肤
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
class SpringFestivalLight : IStandardThemeSkin {
    /**
     * 标准皮肤包路径 例：/sdcard/Android/org.alee.demo.skin/.SkinPack/Night.skin
     */
    override val standardSkinPackPath: String
        get() = SKIN_PACK_PATH + SPRING_FESTIVAL_SKIN_PACK_NAME
}
