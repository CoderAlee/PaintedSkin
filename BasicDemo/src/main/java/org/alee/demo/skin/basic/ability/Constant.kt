package org.alee.demo.skin.basic.ability

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */

internal const val USE_SPRING_FESTIVAL_SKIN = "Spring Festival Mode"

private val ROOT_PATH = Utils.getApp().getExternalFilesDir(null)?.absolutePath?.run { this + File.separator }
    ?: (PathUtils.getExternalAppDataPath() + "." + AppUtils.getAppPackageName() + File.separator)

val SKIN_PACK_PATH = ROOT_PATH + ".SkinPack" + File.separator

const val NIGHT_SKIN_PACK_NAME = "Night.skin"

const val SPRING_FESTIVAL_SKIN_PACK_NAME = "SpringFestival.skin"

const val ASSETS_NIGHT_SKIN_PACK = "night/Night.skin"

const val ASSETS_SPRING_FESTIVAL_SKIN_PACK = "spring/SpringFestival.skin"