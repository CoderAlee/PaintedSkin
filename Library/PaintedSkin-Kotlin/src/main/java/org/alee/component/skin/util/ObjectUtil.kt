package org.alee.component.skin.util

internal inline val Any.memoryAddress: Int
    get() = System.identityHashCode(this)
