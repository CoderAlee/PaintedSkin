package org.alee.demo.skin.basic.ability.util.layout

import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintProperties

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/1/17
 *
 */

/**
 * 充满
 */
const val match_parent = ViewGroup.LayoutParams.MATCH_PARENT

/**
 *  自适应
 */
const val wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT

/**
 * 可见
 */
const val visible = View.VISIBLE

/**
 * 不可见且不占位
 */
const val gone = View.GONE

/**
 * 不可见但占位
 */
const val invisible = View.INVISIBLE

/**
 * 横向
 */
const val horizontal = LinearLayout.HORIZONTAL

/**
 * 纵向
 */
const val vertical = LinearLayout.VERTICAL

/**
 * 加粗
 */
const val bold = Typeface.BOLD

/**
 * 常规
 */
const val normal = Typeface.NORMAL

/**
 * 斜体
 */
const val italic = Typeface.ITALIC

/**
 * 斜体加粗
 */
const val bold_italic = Typeface.BOLD_ITALIC

/**
 * 居中
 */
const val gravity_center = Gravity.CENTER

/**
 * 居左
 */
const val gravity_left = Gravity.LEFT

/**
 * 居右
 */
const val gravity_right = Gravity.RIGHT

/**
 * 居下
 */
const val gravity_bottom = Gravity.BOTTOM

/**
 * 居上
 */
const val gravity_top = Gravity.TOP

/**
 * 横向居中
 */
const val gravity_center_horizontal = Gravity.CENTER_HORIZONTAL

/**
 * 纵向居中
 */
const val gravity_center_vertical = Gravity.CENTER_VERTICAL

val scale_fit_xy = ImageView.ScaleType.FIT_XY
val scale_center_crop = ImageView.ScaleType.CENTER_CROP
val scale_center = ImageView.ScaleType.CENTER
val scale_center_inside = ImageView.ScaleType.CENTER_INSIDE
val scale_fit_center = ImageView.ScaleType.FIT_CENTER
val scale_fit_end = ImageView.ScaleType.FIT_END
val scale_matrix = ImageView.ScaleType.MATRIX
val scale_fit_start = ImageView.ScaleType.FIT_START

const val constraint_start = ConstraintProperties.START
const val constraint_end = ConstraintProperties.END
const val constraint_top = ConstraintProperties.TOP
const val constraint_bottom = ConstraintProperties.BOTTOM
const val constraint_baseline = ConstraintProperties.BASELINE
const val constraint_parent = ConstraintProperties.PARENT_ID

const val spread = ConstraintLayout.LayoutParams.CHAIN_SPREAD
const val packed = ConstraintLayout.LayoutParams.CHAIN_PACKED
const val spread_inside = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE


val gradient_top_bottom = GradientDrawable.Orientation.TOP_BOTTOM
val gradient_tr_bl = GradientDrawable.Orientation.TR_BL
val gradient_right_left = GradientDrawable.Orientation.RIGHT_LEFT
val gradient_br_tl = GradientDrawable.Orientation.BR_TL
val gradient_bottom_top = GradientDrawable.Orientation.BOTTOM_TOP
val gradient_bl_tr = GradientDrawable.Orientation.BL_TR
val gradient_left_right = GradientDrawable.Orientation.LEFT_RIGHT
val gradient_tl_br = GradientDrawable.Orientation.TL_BR

const val shape_rectangle = GradientDrawable.RECTANGLE
const val shape_oval = GradientDrawable.OVAL
const val shape_line = GradientDrawable.LINE
const val shape_ring = GradientDrawable.RING

const val gradient_type_linear = GradientDrawable.LINEAR_GRADIENT
const val gradient_type_radial = GradientDrawable.RADIAL_GRADIENT
const val gradient_type_sweep = GradientDrawable.SWEEP_GRADIENT

const val state_enable = android.R.attr.state_enabled
const val state_disable = -android.R.attr.state_enabled
const val state_pressed = android.R.attr.state_pressed
const val state_unpressed = -android.R.attr.state_pressed
const val state_focused = android.R.attr.state_focused
const val state_unfocused = -android.R.attr.state_focused
const val state_selected = android.R.attr.state_selected
const val state_unselected = -android.R.attr.state_selected

const val input_type_number = InputType.TYPE_CLASS_NUMBER


val ellipsize_end = TextUtils.TruncateAt.END
val ellipsize_marquee = TextUtils.TruncateAt.MARQUEE
val ellipsize_middle = TextUtils.TruncateAt.MIDDLE
val ellipsize_start = TextUtils.TruncateAt.START

const val parent_id = "0"