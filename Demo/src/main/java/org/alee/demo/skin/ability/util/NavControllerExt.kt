package org.alee.demo.skin.ability.util

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */

internal fun NavController?.go(@IdRes destination: Int, args: Bundle? = null, options: NavOptions? = null) {
    this?.navigate(destination, args, options)
}

internal fun NavController?.go(directions: NavDirections) {
    this.go(directions.actionId, directions.arguments)
}

internal fun NavController?.goWithAnim(
    @IdRes destination: Int,
    args: Bundle? = null,
    @AnimRes @AnimatorRes enter: Int = -1,
    @AnimRes @AnimatorRes exit: Int = -1,
    @AnimRes @AnimatorRes popEnter: Int = -1,
    @AnimRes @AnimatorRes popExit: Int = -1
) {
    this.go(
        destination, args, NavOptions.Builder().setEnterAnim(enter)
            .setExitAnim(exit)
            .setPopEnterAnim(popEnter)
            .setPopExitAnim(popExit)
            .build()
    )
}

internal fun NavController?.back() {
    this?.navigateUp()
}