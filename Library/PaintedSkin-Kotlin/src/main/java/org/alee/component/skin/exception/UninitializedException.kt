package org.alee.component.skin.exception

import org.alee.component.skin.util.INotProguard

/**
 * 服务未初始化异常
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/1
 *
 */
internal class UninitializedException : RuntimeException("ThemeSkinService has not been initialized !"), INotProguard
