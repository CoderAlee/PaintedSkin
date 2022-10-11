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
/**
 * KV - KEY - 节日模式
 */
internal const val USE_SPRING_FESTIVAL_SKIN = "Spring Festival Mode"

/**
 * 应用存储空间根路径
 */
private val ROOT_PATH = Utils.getApp().getExternalFilesDir(null)?.absolutePath?.run { this + File.separator }
    ?: (PathUtils.getExternalAppDataPath() + "." + AppUtils.getAppPackageName() + File.separator)

/**
 * 皮肤包根路径
 */
val SKIN_PACK_PATH = ROOT_PATH + ".SkinPack" + File.separator

/**
 * 夜色皮肤包包名
 */
const val NIGHT_SKIN_PACK_NAME = "Night.skin"

/**
 * 节日皮肤包包名
 */
const val SPRING_FESTIVAL_SKIN_PACK_NAME = "SpringFestival.skin"

/**
 * 夜色皮肤包在Assets 目录下的位置
 */
const val ASSETS_NIGHT_SKIN_PACK = "night/Night.skin"

/**
 * 节日皮肤包在Assets 目录下的位置
 */
const val ASSETS_SPRING_FESTIVAL_SKIN_PACK = "spring/SpringFestival.skin"