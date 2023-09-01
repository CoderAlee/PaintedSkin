package org.alee.component.skin.core.parser

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.template.ISkinExecutor
import org.alee.component.skin.template.ISkinExecutorBuilder
import org.alee.component.skin.util.SafeArray

/**
 * 换肤执行器的构造器管理类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal object ThemeSkinExecutorBuilderManager {
    /**
     * [ISkinExecutorBuilder] 持有者
     */
    private val mSkinExecutorBuilders by lazy { SafeArray<ISkinExecutorBuilder>() }

    /**
     * 添加一个[ISkinExecutorBuilder]
     * @param builder ISkinExecutorBuilder
     */
    fun addSkinExecutorBuilder(builder: ISkinExecutorBuilder) {
        mSkinExecutorBuilders.add(builder)
    }

    /**
     * 移除一个[ISkinExecutorBuilder]
     * @param builder ISkinExecutorBuilder
     */
    fun removeSkinExecutorBuilder(builder: ISkinExecutorBuilder) {
        mSkinExecutorBuilders.remove(builder)
    }

    /**
     * 从View的属性集中解析出支持换肤的属性
     * @param context Context [Context]
     * @param attributeSet AttributeSet [AttributeSet]
     * @return Set<SkinAttribute> [SkinAttribute]
     */
    fun fetchSkinAttribute(context: Context, attributeSet: AttributeSet): Set<SkinAttribute> {
        if (attributeSet.isEmpty()) {
            return emptySet()
        }
        mSkinExecutorBuilders.use().use {
            return it.mapNotNull { builder -> builder.parse(context, attributeSet) }.flatten().toSet()
        }
    }

    /**
     * 根据View与属性构建换肤执行器
     * @param view View 需要换肤的View
     * @param skinAttribute SkinAttribute 需要换肤的属性
     * @return ISkinExecutor? [ISkinExecutor]
     */
    fun obtainSkinExecutor(view: View, skinAttribute: SkinAttribute): ISkinExecutor? {
        mSkinExecutorBuilders.use().use {
            return it.firstOrNull { builder -> builder.canBuildExecute(view, skinAttribute.attrName) }
                ?.buildSkinExecutor(view, skinAttribute)
        }
        return null
    }

    private fun AttributeSet.isEmpty(): Boolean {
        return 0 >= attributeCount
    }
}
