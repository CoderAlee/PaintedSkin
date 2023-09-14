package org.alee.demo.skin.kotlin.ability.theme

import org.alee.component.skin.template.IStandardThemeSkin
import org.alee.demo.skin.kotlin.ability.NIGHT_SKIN_PACK_NAME
import org.alee.demo.skin.kotlin.ability.SKIN_PACK_PATH

/**
 *  暗色 主题皮肤
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
class Dark : IStandardThemeSkin {
    /**
     * 标准皮肤包路径 例：/sdcard/Android/org.alee.demo.skin/.SkinPack/Night.skin
     */
    override val standardSkinPackPath: String
        get() = SKIN_PACK_PATH + NIGHT_SKIN_PACK_NAME
}
