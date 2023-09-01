package org.alee.component.skin.template

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AnyThread
import org.alee.component.skin.model.SkinAttribute

/**
 * 换肤执行器的构造器
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
interface ISkinExecutorBuilder {
    /**
     * 实现类需要判断是否可以构建对应属性的换肤执行器
     * @param view View 需要换肤的View
     * @param attrName String 需要换肤的属性名称
     * @return Boolean true: 可以构建 false: 不可以构建
     */
    @AnyThread
    fun canBuildExecute(view: View, attrName: String): Boolean

    /**
     * 从AttributeSet中解析出需要换肤的属性
     * @param context Context [Context]
     * @param attributeSet AttributeSet [AttributeSet]
     * @return Set<SkinAttribute>? [SkinAttribute]
     */
    @AnyThread
    fun parse(context: Context, attributeSet: AttributeSet): Set<SkinAttribute>?

    /**
     * 根据需要换肤的属性与View构建换肤执行器
     * @param view View 需要换肤的View
     * @param attribute SkinAttribute 需要换肤的属性
     * @return ISkinExecutor [ISkinExecutor]
     */
    @AnyThread
    fun buildSkinExecutor(view: View, attribute: SkinAttribute): ISkinExecutor
}
