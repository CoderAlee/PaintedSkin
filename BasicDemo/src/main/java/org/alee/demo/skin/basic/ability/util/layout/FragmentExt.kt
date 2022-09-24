package org.alee.demo.skin.basic.ability.util.layout

import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
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

inline fun Fragment.ConstraintLayout(block: ConstraintLayout.() -> Unit): ConstraintLayout? {
    return context?.ConstraintLayout(block)
}

inline fun Fragment.LinearLayout(block: LinearLayout.() -> Unit): LinearLayout? {
    return context?.LinearLayout(block)
}

inline fun Fragment.RelativeLayout(block: RelativeLayout.() -> Unit): RelativeLayout? {
    return context?.RelativeLayout(block)
}

inline fun Fragment.FrameLayout(block: FrameLayout.() -> Unit): FrameLayout? {
    return context?.FrameLayout(block)
}

inline fun Fragment.NestedScrollView(block: NestedScrollView.() -> Unit): NestedScrollView? {
    return context?.NestedScrollView(block)
}

inline fun Fragment.RecyclerView(block: RecyclerView.() -> Unit): RecyclerView? {
    return context?.RecyclerView(block)
}


inline fun Fragment.ImageView(block: ImageView.() -> Unit): ImageView? {
    return context?.ImageView(block)
}

inline fun Fragment.Button(block: Button.() -> Unit): Button? {
    return context?.Button(block)
}

inline fun Fragment.TextView(block: AppCompatTextView.() -> Unit): AppCompatTextView? {
    return context?.TextView(block)
}

inline fun Fragment.View(block: View.() -> Unit): View? {
    return context?.View(block)
}

inline fun Fragment.EditText(block: AppCompatEditText.() -> Unit): AppCompatEditText? {
    return context?.EditText(block)
}

inline fun Fragment.ViewPager2(block: ViewPager2.() -> Unit): ViewPager2? {
    return context?.ViewPager2(block)
}