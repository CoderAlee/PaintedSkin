package org.alee.demo.skin.basic.ability.util.layout

import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.widget.TextViewCompat
import org.alee.component.skin.executor.SkinElement
import org.alee.component.skin.parser.DefaultExecutorBuilder
import org.alee.demo.skin.basic.ability.util.colorResource
import org.alee.demo.skin.basic.ability.util.colorStateListResource
import org.alee.demo.skin.basic.ability.util.dimenResource
import org.alee.demo.skin.basic.ability.util.drawableResource
import org.alee.demo.skin.basic.ability.util.floatDimenResource

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/1/17
 *
 */

inline var View.layout_id: String
    get() {
        return ""
    }
    set(value) {
        id = value.toLayoutId()
    }

fun String.toLayoutId(): Int {
    var id = hashCode()
    if (this == parent_id) id = 0
    return kotlin.math.abs(id)
}

infix fun ImageView.src(@DrawableRes id: Int) {
    src = id
}


inline var ImageView.src: Int
    get() = 0
    set(value) {
        setImageDrawable(value.drawableResource)
        skinSrc(value)
    }

inline var View.background_color: Int
    get() = 0
    set(value) {
        skinBackground(value)
        setBackgroundColor(value.colorResource)
    }

inline var View.background_drawable: Int
    get() = 0
    set(value) {
        background = value.drawableResource
        skinBackground(value)
    }


inline var View.padding_top: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, value.dimenResource, paddingRight, paddingBottom)
    }

inline var View.padding_bottom: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, paddingRight, value.dimenResource)
    }

inline var View.padding_start: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(value.dimenResource, paddingTop, paddingRight, paddingBottom)
    }

inline var View.padding_end: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, value.dimenResource, paddingBottom)
    }

inline var View.padding_horizontal: Int
    get() {
        return -1
    }
    set(value) {
        value.dimenResource.run {
            setPadding(this, paddingTop, this, paddingBottom)
        }
    }

inline var View.padding_vertical: Int
    get() {
        return -1
    }
    set(value) {
        value.dimenResource.run {
            setPadding(paddingLeft, this, paddingRight, this)
        }
    }

inline var View.size: Int
    get() = 0
    set(value) {
        layout_width = value
        layout_height = value
    }

inline var View.layout_width: Int
    get() {
        return 0
    }
    set(value) {
        val w = if (value > 0) value.dimenResource else value
        val h = layoutParams?.height ?: 0
        updateLayoutParams<ViewGroup.LayoutParams> {
            width = w
            height = h
        }
    }

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {
        val w = layoutParams?.width ?: 0
        val h = if (value > 0) value.dimenResource else value
        updateLayoutParams<ViewGroup.LayoutParams> {
            width = w
            height = h
        }
    }

inline var View.layout_weight: Float
    get() = 0F
    set(value) {
        val params = layoutParams
        if (params is LinearLayout.LayoutParams) {
            params.weight = value
        }
    }

inline var View.layout_gravity: Int
    get() {
        return 0
    }
    set(value) {
        updateLayoutParams<LinearLayout.LayoutParams> {
            weight = (layoutParams as? LinearLayout.LayoutParams)?.weight ?: 0f
            gravity = value
        }
    }

inline var TextView.autoSizeType: Int
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.autoSizeTextType
    } else {
        0
    }
    set(value) {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(this, value)
    }

fun TextView.autoSizeConfig(
    autoSizeMinTextSize: Int = 12,
    autoSizeMaxTextSize: Int = 112,
    autoSizeStepGranularity: Int = 1,
    unit: Int = TypedValue.COMPLEX_UNIT_PX
) {
    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
        this,
        autoSizeMinTextSize,
        autoSizeMaxTextSize,
        autoSizeStepGranularity,
        unit
    )
}

inline var TextView.text_color_hint: Int
    get() = currentHintTextColor
    set(value) {
        addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR_HINT, value) }
        setHintTextColor(value.colorResource)
    }

inline var View.margin_start: Int
    get() = margin_start_px
    set(value) {
        margin_start_px = value.dimenResource
    }

inline var View.margin_start_px: Int
    get() = marginStart
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            MarginLayoutParamsCompat.setMarginStart(this@updateLayoutParams, value)
        }
    }

inline var View.margin_end: Int
    get() = margin_end_px
    set(value) {
        margin_end_px = value.dimenResource
    }

inline var View.margin_end_px: Int
    get() = marginEnd
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            MarginLayoutParamsCompat.setMarginEnd(this, value)
        }
    }

inline var View.margin_top: Int
    get() = margin_top_px
    set(value) {
        margin_top_px = value.dimenResource
    }

inline var View.margin_top_px: Int
    get() = marginTop
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = value
        }
    }

inline var View.margin_bottom: Int
    get() = margin_bottom_px
    set(value) {
        margin_bottom_px = value.dimenResource
    }

inline var View.margin_bottom_px: Int
    get() = marginBottom
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = value
        }
    }

inline var View.margin_horizontal: Int
    get() {
        return -1
    }
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            value.dimenResource.run {
                MarginLayoutParamsCompat.setMarginEnd(this@updateLayoutParams, this)
                MarginLayoutParamsCompat.setMarginStart(this@updateLayoutParams, this)
            }
        }
    }

inline var View.margin_vertical: Int
    get() {
        return -1
    }
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            value.dimenResource.run {
                topMargin = this
                bottomMargin = this
            }

        }
    }


inline var View.margin: Int
    get() = 0
    set(value) {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(value.dimenResource)
        }
    }

inline var View.gone_margin_start: Int
    get() = 0
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            goneStartMargin = value.dimenResource
        }
    }

inline var View.gone_margin_end: Int
    get() = 0
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            goneEndMargin = value.dimenResource
        }
    }

inline var View.gone_margin_top: Int
    get() = 0
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            goneTopMargin = value.dimenResource
        }
    }

inline var View.gone_margin_bottom: Int
    get() = 0
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            goneBottomMargin = value.dimenResource
        }
    }

inline var View.padding: Int
    get() = paddingStart
    set(value) {
        value.dimenResource.run {
            setPadding(this, this, this, this)
        }
    }

inline var TextView.text_color: Int
    get() = 0
    set(value) {
        setTextColor(value.colorResource)
        addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR, value) }
    }

inline var TextView.text_color_state_list: Int
    get() = 0
    set(value) {
        setTextColor(value.colorStateListResource)
        addSkin { SkinElement(DefaultExecutorBuilder.ATTRIBUTE_TEXT_COLOR, value) }
    }

inline var View.start_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToStart = value.toLayoutId()
            startToEnd = -1
        }
    }

inline var View.start_toStartViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToStart = value?.id ?: -1
            startToEnd = -1
        }
    }

inline var View.start_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToEnd = value.toLayoutId()
            startToStart = -1
        }
    }

inline var View.start_toEndViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToEnd = value?.id ?: -1
            startToStart = -1
        }
    }

inline var View.top_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = value.toLayoutId()
            topToTop = -1
        }
    }

inline var View.top_toBottomViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = value?.id ?: -1
            topToTop = -1
        }
    }

inline var View.top_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = value.toLayoutId()
            topToBottom = -1
        }
    }

inline var View.top_toTopViewOf: View?
    get() {
        return null
    }
    set(value) {

        updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = value?.id ?: -1
            topToBottom = -1
        }
    }

inline var View.end_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToEnd = value.toLayoutId()
            endToStart = -1
        }
    }

inline var View.end_toEndViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToEnd = value?.id ?: -1
            endToStart = -1
        }
    }

inline var View.end_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToStart = value.toLayoutId()
            endToEnd = -1
        }
    }

inline var View.end_toStartViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToStart = value?.id ?: -1
            endToEnd = -1
        }
    }

inline var View.bottom_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToBottom = value.toLayoutId()
            bottomToTop = -1
        }
    }

inline var View.bottom_toBottomViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToBottom = value?.id ?: -1
            bottomToTop = -1
        }
    }

inline var View.bottom_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToTop = value.toLayoutId()
            bottomToBottom = -1
        }
    }

inline var View.bottom_toTopViewOf: View?
    get() {
        return null
    }
    set(value) {
        updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToTop = value?.id ?: -1
            bottomToBottom = -1
        }
    }

inline var TextView.text_size: Int
    get() = 0
    set(value) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, value.floatDimenResource)
    }

inline fun <reified T : ViewGroup.LayoutParams> View.updateLayoutParams(block: T.() -> Unit) {
    layoutParams = (layoutParams as? T)?.apply(block) ?: kotlin.run {
        val width = layoutParams?.width ?: 0
        val height = layoutParams?.height ?: 0
        val lp = ViewGroup.LayoutParams(width, height)
        new<T>(lp).apply(block).also {
            val original = layoutParams
            if (it is ViewGroup.MarginLayoutParams && original is ViewGroup.MarginLayoutParams) {
                it.topMargin = original.topMargin
                it.bottomMargin = original.bottomMargin
                MarginLayoutParamsCompat.setMarginStart(it, original.marginStart)
                MarginLayoutParamsCompat.setMarginEnd(it, original.marginEnd)
            }
        }
    }
}

inline fun <reified T> new(vararg params: Any): T =
    T::class.java.getDeclaredConstructor(*params.map { it::class.java }.toTypedArray())
        .also { it.isAccessible = true }.newInstance(*params)


