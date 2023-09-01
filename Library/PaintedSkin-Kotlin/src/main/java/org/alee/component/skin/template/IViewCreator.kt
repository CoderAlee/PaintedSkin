package org.alee.component.skin.template

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.MainThread

/**
 * View创建器/填充器/转换器
 *
 * <p> 主要用于当加载XML布局文件时，将XML中的ViewTree转化为View实例
 * <p> 如XML中存在自定义View，推荐通过添加自定义View的[IViewCreator]实例化View
 * <p> 开发者可以通过实现此接口实现一些骚操作，如将XML中的TextView全部替换为AppCompatTextView
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
fun interface IViewCreator {
    /**
     * 创建View/转换View
     * 开发者可以根据入参决定创建一个View或将View替换为其他View
     * @param predecessorOutput View? 上一任[IViewCreator]的处理结果
     * @param parent View? View的父View
     * @param context Context [Context]
     * @param viewName String View的完整类名
     * @param attrs AttributeSet View的属性集合
     * @return View?
     */
    @MainThread
    fun onCreateView(
        predecessorOutput: View?,
        parent: View?,
        context: Context,
        viewName: String,
        attrs: AttributeSet,
    ): View?
}
