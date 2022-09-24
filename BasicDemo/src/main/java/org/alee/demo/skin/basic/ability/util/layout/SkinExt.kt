package org.alee.demo.skin.basic.ability.util.layout

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.page.WindowManager
import org.alee.component.skin.parser.DefaultExecutorBuilder

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/1/17
 *
 */

inline fun View.skinBackground(block: () -> Int) {
    skinBackground(block())
}

fun View.skinSrc(@DrawableRes id: Int) {
    addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_SRC, id) }
}

fun View.skinTextColor(@ColorRes id: Int) {
    addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR, id) }
}

infix fun View.skinBackground(@DrawableRes id: Int) {
    addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_BACKGROUND, id) }
}

inline fun View.addSkin(block: () -> SkinElement) {
    WindowManager.getInstance().getWindowProxy(context)?.addEnabledThemeSkinView(this, block())
}