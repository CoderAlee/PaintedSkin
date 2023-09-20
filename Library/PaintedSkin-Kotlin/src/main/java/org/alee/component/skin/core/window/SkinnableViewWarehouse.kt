package org.alee.component.skin.core.window

import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.alee.component.skin.core.parser.ThemeSkinExecutorBuilderManager
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.alee.component.skin.core.window.SkinnableView.WeakView
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.model.inflate
import org.alee.component.skin.template.ISkinExecutor
import org.alee.component.skin.util.SafeArray
import org.alee.component.skin.util.ThemeSkinCoroutineExceptionHandler
import org.alee.component.skin.util.ext.isValidResourcesId
import org.alee.component.skin.util.ext.memoryAddress
import org.alee.component.skin.util.ext.printIfDebug
import org.alee.component.skin.util.safeCall
import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/8
 *
 */
internal class SkinnableViewWarehouse(private val mPageName: String) : ISkinnableViewWarehouse, CoroutineScope by MainScope() {

    /**
     * 待换肤View引用队列
     */
    private val mReferenceQueue by lazy { ReferenceQueue<View>() }

    /**
     * 待换肤View
     */
    private val mSkinnableViews: SafeArray<SkinnableView> by lazy { SafeArray() }

    init {
        startGcObserving()
    }

    /**
     * 获取当前仓库中支持换肤的View数量
     */
    override val skinnableViewSize: Int
        get() = mSkinnableViews.size

    /**
     * 添加允许换肤的View
     * @param view View 支持换肤的View
     * @param skinAttributes Array<out SkinAttribute> 支持换肤的属性
     */
    override fun addSkinnableView(view: View, vararg skinAttributes: SkinAttribute) {
        if (skinAttributes.isEmpty()) {
            return
        }
        for (skinAttribute in skinAttributes) {
            if (skinAttribute.resourceId.isValidResourcesId.not()) {
                continue
            }
            if (skinAttribute.resourceType.isEmpty()) {
                skinAttribute.inflate(view.context)
            }
            val executor = ThemeSkinExecutorBuilderManager.obtainSkinExecutor(view, skinAttribute) ?: continue
            addSkinExecutor(view, executor, skinAttribute)
        }
    }

    /**
     *  添加允许换肤的View
     * @param view View 支持换肤的View
     * @param attributeSet AttributeSet 属性集
     */
    override fun addSkinnableView(view: View, attributeSet: AttributeSet) {
        addSkinnableView(view, skinAttributes = ThemeSkinExecutorBuilderManager.fetchSkinAttribute(view.context, attributeSet).toTypedArray())
    }

    /**
     * 将一个可换肤View移交到另一个仓库
     * @param view View 可换肤View
     * @param receiver ISkinnableViewWarehouse 接收者
     */
    override fun transferSkinnableView(view: View, receiver: ISkinnableViewWarehouse) {
        mSkinnableViews.remove(view.memoryAddress)?.run {
            receiver.addSkinnableView(view, skinAttributes = skinAttributes.toTypedArray())
        }
    }

    /**
     * 应用当前的主题皮肤
     */
    override fun applyThemeSkin() {
        if (mSkinnableViews.isEmpty().not()) {
            printIfDebug {
                "[ $mPageName ] Start skin changing, number of Views to be changed =${mSkinnableViews.size}"
            }
        }
        mSkinnableViews.use().use { skinnableViews ->
            skinnableViews.filter { it.isValid }
                .forEach { it.safeApplyThemeSkin() }
        }
    }

    /**
     * 销毁仓库并释放资源
     */
    override fun destroy() {
        cancel()
        mSkinnableViews.use().use { skinnableViews ->
            skinnableViews.forEach { it.destroy() }
        }
    }

    private fun startGcObserving() {
        launch(Dispatchers.IO) {
            while (isActive) {
                var released: Reference<out View>? = null
                try {
                    released = mReferenceQueue.remove()
                } catch (ignored: Throwable) {
                }
                released.runIfInstance(WeakView::class.java) {
                    removeInvalidSkinnableView(it.referentAddress)
                }
            }
        }
    }

    private fun removeInvalidSkinnableView(key: Int) {
        mSkinnableViews.remove(key)?.run {
            destroy()
            printIfDebug {
                "a view in [$mPageName] has been recycled. The number of Views to be reskinned on the current page =${mSkinnableViews.size}"
            }
        }
    }

    private fun addSkinExecutor(view: View, executor: ISkinExecutor, skinAttribute: SkinAttribute) {
        mSkinnableViews.getOrPut(view.memoryAddress) {
            SkinnableView(view, mReferenceQueue)
        }.apply {
            addSkinExecutor(skinAttribute, executor)
        }
        executor.safeExecute(view, skinAttribute)
    }

    private inline fun <T> Any?.runIfInstance(real: Class<T>, block: (T) -> Unit) {
        if (null == this) {
            return
        }
        if (real.isInstance(this).not()) {
            return
        }
        block(this as T)
    }

    private inline fun SafeArray<SkinnableView>.getOrPut(key: Int, block: () -> SkinnableView): SkinnableView {
        return get(key) ?: block().also {
            add(key, it)
        }
    }

    private fun ISkinExecutor.safeExecute(view: View, skinAttribute: SkinAttribute) {
        if (isActive.not()) {
            return
        }
        launch(Dispatchers.Main.immediate + ThemeSkinCoroutineExceptionHandler() + SupervisorJob()) {
            if (isActive.not()) {
                return@launch
            }
            safeCall {
                execute(view, skinAttribute)
            }
        }
    }

    private fun SkinnableView.safeApplyThemeSkin() {
        if (isActive.not()) {
            return
        }
        launch(Dispatchers.Main.immediate + ThemeSkinCoroutineExceptionHandler() + SupervisorJob()) {
            if (isActive.not()) {
                return@launch
            }
            applyThemeSkin()
        }
    }
}
