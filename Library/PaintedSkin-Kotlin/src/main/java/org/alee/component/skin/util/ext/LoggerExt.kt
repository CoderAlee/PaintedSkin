package org.alee.component.skin.util.ext

import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.exception.StrictModeException
import org.alee.component.skin.template.ILogger
import kotlin.system.measureTimeMillis

internal fun Any.logE(tag: String = ILogger.TAG, throwable: Throwable) {
    ThemeSkinService.config.logger.error(tag, this.toString(), throwable)
    if (ThemeSkinService.config.strictMode) {
        throw StrictModeException(toString(), throwable)
    }
}

internal fun Any.logE() {
    ThemeSkinService.config.logger.error(ILogger.TAG, this.toString())
    if (ThemeSkinService.config.strictMode) {
        throw StrictModeException(toString())
    }
}

internal fun Any.logI() {
    ThemeSkinService.config.logger.info(ILogger.TAG, this.toString())
}

internal inline fun measureTimeIfDebug(block: () -> Unit, logBlock: (Long) -> String) {
    if (ThemeSkinService.config.debugMode) {
        val time = measureTimeMillis(block)
        ThemeSkinService.config.logger.debug(ILogger.TAG, logBlock(time))
    } else {
        block()
    }
}

internal inline fun printIfDebug(block: () -> Any) {
    if (ThemeSkinService.config.debugMode) {
        ThemeSkinService.config.logger.debug(ILogger.TAG, block().toString())
    }
}
