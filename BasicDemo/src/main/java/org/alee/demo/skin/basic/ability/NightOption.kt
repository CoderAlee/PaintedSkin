package org.alee.demo.skin.basic.ability

import android.os.Build
import org.alee.component.skin.service.IThemeSkinOption

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

    private companion object {

        private const val NIGHT_SKIN_PACK_NAME = "night.skin"

        private val SKIN_PACK_PATH = LinkedHashSet<String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                add("/storage/emulated/0/Android/data/org.alee.demo.skin/files/Documents/$NIGHT_SKIN_PACK_NAME")
            } else {
                add("/sdcard/$NIGHT_SKIN_PACK_NAME")
            }
        }
    }

    override fun getStandardSkinPackPath(): LinkedHashSet<String> {
        return SKIN_PACK_PATH
    }
}