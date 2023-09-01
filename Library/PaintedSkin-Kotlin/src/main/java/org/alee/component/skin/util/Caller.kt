package org.alee.component.skin.util

import org.alee.component.skin.util.ext.logE

internal inline fun safeCall(print: Boolean = true, runner: () -> Unit) {
    try {
        runner()
    } catch (ex: Throwable) {
        if (print) {
            "Call Failed".logE(throwable = ex)
        }
    }
}
