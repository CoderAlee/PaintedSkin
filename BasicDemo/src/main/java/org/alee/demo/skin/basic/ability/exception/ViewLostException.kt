package org.alee.demo.skin.basic.ability.exception

import androidx.annotation.IdRes

/**
 * 无法找到View异常
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/10/11
 *
 */
class ViewLostException(@IdRes viewId: Int) : RuntimeException("not find view by id [ $viewId ]")