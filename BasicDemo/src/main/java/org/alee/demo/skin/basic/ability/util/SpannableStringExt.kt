package org.alee.demo.skin.basic.ability.util

import android.text.ParcelableSpan
import android.text.SpannableStringBuilder
import android.text.Spanned

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */


/**
 * 构建一个 SpannableStringBuilder 作用域
 * @param builderScope [@kotlin.ExtensionFunctionType] Function1<SpannableStringBuilder, Unit>
 * @return SpannableStringBuilder
 */
inline fun spannableString(crossinline builderScope: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    return SpannableStringBuilder().apply(builderScope)
}

/**
 * 对SpannableStringBuilder的扩展 添加一段文本并为其设置一组样式
 * @receiver SpannableStringBuilder
 * @param content String
 * @param spans Array<out ParcelableSpan>
 */
fun SpannableStringBuilder.append(content: String, vararg spans: ParcelableSpan) {
    if (content.isEmpty()) {
        return
    }
    append(content)
    if (spans.isEmpty()) {
        return
    }
    val start = length - content.length
    val end = length
    spans.forEach {
        setSpan(it, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}
