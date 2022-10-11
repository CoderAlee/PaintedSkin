package org.alee.demo.skin.basic.ability

import org.alee.component.skin.service.IThemeSkinOption
import org.alee.demo.skin.basic.ability.util.loadBoolean

/**
 * 白天模式配置
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */
internal class DayOption : IThemeSkinOption {

    override fun getStandardSkinPackPath(): LinkedHashSet<String>? {
        if (USE_SPRING_FESTIVAL_SKIN.loadBoolean(false)) {
            return LinkedHashSet<String>().apply { add(SKIN_PACK_PATH + SPRING_FESTIVAL_SKIN_PACK_NAME) }
        }
        return null
    }
}