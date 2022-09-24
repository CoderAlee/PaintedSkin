package org.alee.demo.skin.basic.ability.util.layout

import android.content.Context
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/1/17
 *
 */

inline fun Context.ConstraintLayout(block: ConstraintLayout.() -> Unit): ConstraintLayout {
    return ConstraintLayout(this).apply(block)
}

inline fun Context.LinearLayout(block: LinearLayout.() -> Unit): LinearLayout {
    return LinearLayout(this).apply(block)
}

inline fun Context.RelativeLayout(block: RelativeLayout.() -> Unit): RelativeLayout {
    return RelativeLayout(this).apply(block)
}

inline fun Context.FrameLayout(block: FrameLayout.() -> Unit): FrameLayout {
    return FrameLayout(this).apply(block)
}

inline fun Context.NestedScrollView(block: NestedScrollView.() -> Unit): NestedScrollView {
    return NestedScrollView(this).apply(block)
}

inline fun Context.RecyclerView(block: RecyclerView.() -> Unit): RecyclerView {
    return RecyclerView(this).apply(block)
}

inline fun Context.ImageView(block: ImageView.() -> Unit): ImageView {
    return ImageView(this).apply(block)
}

inline fun Context.Button(block: Button.() -> Unit): Button {
    return Button(this).apply(block)
}


inline fun Context.TextView(block: AppCompatTextView.() -> Unit): AppCompatTextView {
    return AppCompatTextView(this).apply(block)
}

inline fun Context.View(block: View.() -> Unit): View {
    return View(this).apply(block)
}

inline fun Context.EditText(block: AppCompatEditText.() -> Unit): AppCompatEditText {
    return AppCompatEditText(this).apply(block)
}

inline fun Context.ViewPager2(block: ViewPager2.() -> Unit): ViewPager2 {
    return ViewPager2(this).apply(block)
}


