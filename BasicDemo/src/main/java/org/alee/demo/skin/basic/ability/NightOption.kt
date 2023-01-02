package org.alee.demo.skin.basic.ability

import org.alee.component.skin.service.IThemeSkinOption
import org.alee.demo.skin.basic.ability.util.loadBoolean

/**
 * 夜色模式配置
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
internal class NightOption : IThemeSkinOption {

    override fun getStandardSkinPackPath(): LinkedHashSet<String> {
        return LinkedHashSet<String>().apply {
            // 基础黑夜皮肤包
            add(SKIN_PACK_PATH + NIGHT_SKIN_PACK_NAME)
        }.apply {
            // 春节皮肤包
            if (KEY_USE_SPRING_FESTIVAL_SKIN.loadBoolean(false)) {
                add(SKIN_PACK_PATH + SPRING_FESTIVAL_SKIN_PACK_NAME)
            }
        }
    }
}