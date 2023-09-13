package org.alee.component.skin.util.ext

import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.template.IThemeSkinObserver

internal inline val Any.memoryAddress: Int
    get() = System.identityHashCode(this)

internal fun Any.subscribeThemeSkinIfNeed() {
    if (this is IThemeSkinObserver) {
        ThemeSkinService.subscribe(this)
    }
}
