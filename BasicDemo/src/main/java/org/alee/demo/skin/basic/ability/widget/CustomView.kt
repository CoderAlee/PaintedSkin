package org.alee.demo.skin.basic.ability.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.util.colorDrawable
import org.alee.demo.skin.basic.ability.util.colorResource
import org.alee.demo.skin.basic.ability.util.dimenResource
import org.alee.demo.skin.basic.ability.util.floatDimenResource


/**
 * 包含自定义属性的自定义View
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 点颜色
     */
    var pointColor = R.color.transparent.colorResource
        set(value) {
            if (field == value) {
                return
            }
            field = value
            mPointPaint.color = value
            invalidate()
        }

    /**
     * 圆环颜色
     */
    var circleBorderColor = R.color.transparent.colorResource
        set(value) {
            if (field == value) {
                return
            }
            field = value
            mCirclePaint.color = value
            invalidate()
        }

    /**
     * 矩形填充色
     */
    var rectangleFillColor = R.color.transparent.colorResource
        set(value) {
            if (field == value) {
                return
            }
            field = value
            mRectanglePaint.color = value
            invalidate()
        }

    /**
     * icon
     */
    var icon: Drawable = R.color.transparent.colorDrawable
        set(value) {
            if (field == value) {
                return
            }
            field = value
            invalidate()
        }

    /**
     * 文本颜色
     */
    var textColor = ColorStateList.valueOf(R.color.transparent.colorResource)
        set(value) {
            if (field.defaultColor == value.defaultColor) {
                return
            }
            field = value
            mTextPaint.color = value.defaultColor
            invalidate()
        }

    private val mPointPaint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = R.dimen.tp_4.floatDimenResource
        isAntiAlias = true
        isDither = true
    }

    private val mCirclePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = R.dimen.tp_2.floatDimenResource
        isAntiAlias = true
        isDither = true
    }

    private val mRectanglePaint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = R.dimen.tp_1.floatDimenResource
        isAntiAlias = true
        isDither = true
    }

    private val mDrawablePaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        isFilterBitmap = true
    }

    private val mTextPaint = Paint().apply {
        style = Paint.Style.FILL
        textSize = R.dimen.tp_16.floatDimenResource
        isAntiAlias = true
        isDither = true
    }

    private var mViewWidth = 0

    private var mViewHeight = 0

    init {
        loadAttribute(context, attrs)
    }


    /**
     * 加载自定义属性
     * @param context  [Context]
     * @param attrs {@linkAttributeSet }
     */
    private fun loadAttribute(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0).run {
            pointColor = getColor(R.styleable.CustomView_point_color, pointColor)
            circleBorderColor = getColor(R.styleable.CustomView_circle_color, circleBorderColor)
            rectangleFillColor = getColor(R.styleable.CustomView_rectangle_color, rectangleFillColor)
            icon = getDrawable(R.styleable.CustomView_icon) ?: icon
            textColor = getColorStateList(R.styleable.CustomView_android_textColor) ?: ColorStateList.valueOf(
                getColor(
                    R.styleable.CustomView_android_textColor,
                    textColor.defaultColor
                )
            )
            recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewWidth = measuredWidth
        mViewHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val x = mViewWidth / 2F
        var y = (mViewHeight - R.dimen.tp_312.dimenResource) / 2F
        // 画实心圆
        y += R.dimen.tp_5.floatDimenResource
        canvas?.drawCircle(x, y, R.dimen.tp_5.floatDimenResource, mPointPaint)

        y += R.dimen.tp_25.floatDimenResource
        // 画空心圆
        y += R.dimen.tp_25.floatDimenResource
        canvas?.drawCircle(x, y, R.dimen.tp_25.floatDimenResource, mCirclePaint)

        y += R.dimen.tp_45.floatDimenResource
        // 画矩形
        canvas?.drawRect(
            x - R.dimen.tp_50.floatDimenResource,
            y,
            x + R.dimen.tp_50.floatDimenResource,
            y + R.dimen.tp_40.floatDimenResource,
            mRectanglePaint
        )
        y += R.dimen.tp_80.floatDimenResource
        // 画文字
        canvas?.drawText("作者真帅!", x - R.dimen.tp_30.floatDimenResource, y, mTextPaint)
        y += R.dimen.tp_30.floatDimenResource
        // 画Icon
        canvas?.drawBitmap(
            icon.toBitmap(R.dimen.tp_200.dimenResource, R.dimen.tp_100.dimenResource),
            x - R.dimen.tp_100.floatDimenResource,
            y,
            mDrawablePaint
        )
    }


}