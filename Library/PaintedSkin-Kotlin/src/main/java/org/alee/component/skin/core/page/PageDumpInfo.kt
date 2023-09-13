package org.alee.component.skin.core.page

import org.alee.component.skin.util.INotProguard

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/12
 *
 */
internal data class PageDumpInfo(
    val pageName: String,
    val skinnableViewSize: Int,
    val isVisible: Boolean,
    val isNeedApplyThemeSkin: Boolean,
    val subPageInfo: List<PageDumpInfo>,
) : INotProguard
