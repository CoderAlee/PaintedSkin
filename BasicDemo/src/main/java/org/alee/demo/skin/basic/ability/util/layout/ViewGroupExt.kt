package org.alee.demo.skin.basic.ability.util.layout

import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
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

inline fun ViewGroup.ConstraintLayout(block: ConstraintLayout.() -> Unit): ConstraintLayout {
    return context.ConstraintLayout(block).also { addView(it) }
}

inline fun ViewGroup.LinearLayout(block: LinearLayout.() -> Unit): LinearLayout {
    return context.LinearLayout(block).also { addView(it) }
}

inline fun ViewGroup.RelativeLayout(block: RelativeLayout.() -> Unit): RelativeLayout {
    return context.RelativeLayout(block).also { addView(it) }
}

inline fun ViewGroup.FrameLayout(block: FrameLayout.() -> Unit): FrameLayout {
    return context.FrameLayout(block).also { addView(it) }
}

inline fun ViewGroup.NestedScrollView(block: NestedScrollView.() -> Unit): NestedScrollView {
    return context.NestedScrollView(block).also { addView(it) }
}

inline fun ViewGroup.RecyclerView(block: RecyclerView.() -> Unit): RecyclerView {
    return context.RecyclerView(block).also { addView(it) }
}


inline fun ViewGroup.ImageView(block: ImageView.() -> Unit): ImageView {
    return context.ImageView(block).also { addView(it) }
}

inline fun ViewGroup.Button(block: Button.() -> Unit): Button {
    return context.Button(block).also { addView(it) }
}

inline fun ViewGroup.TextView(block: AppCompatTextView.() -> Unit): AppCompatTextView {
    return context.TextView(block).also { addView(it) }
}


inline fun ViewGroup.View(block: View.() -> Unit): View {
    return context.View(block).also { addView(it) }
}


inline fun ViewGroup.EditText(block: AppCompatEditText.() -> Unit): AppCompatEditText {
    return context.EditText(block).also { addView(it) }
}

inline fun ViewGroup.ViewPager2(block: ViewPager2.() -> Unit): ViewPager2 {
    return context.ViewPager2(block).also { addView(it) }
}

fun ViewGroup.getTouchedChildView(ev: MotionEvent?): View? {
    if (ev == null) {
        return null
    }
    val x = ev.x.toInt()
    val y = ev.y.toInt()
    val max = childCount - 1
    for (index in max downTo 0) {
        val child = getChildAt(index)
        if (child != null
            && child.isVisible
            && x >= child.left
            && x < child.right
            && y >= child.top
            && y < child.bottom
        ) {
            return child
        }
    }
    return null
}


