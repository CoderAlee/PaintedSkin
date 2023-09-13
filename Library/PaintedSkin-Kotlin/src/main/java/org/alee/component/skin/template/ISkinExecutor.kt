package org.alee.component.skin.template

import android.view.View
import androidx.annotation.MainThread
import org.alee.component.skin.exception.ApplySkinException
import org.alee.component.skin.model.SkinAttribute

/**
 * 换肤执行器
 *
 * <p> 此接口的实现类负责根据换肤属性对View进行换肤操作
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
interface ISkinExecutor {

    /**
     * 执行换肤工作
     * @param view View 需要换肤的View
     * @param attribute SkinAttribute 换肤属性
     * @throws ApplySkinException 换肤时出现的异常
     */
    @Throws(ApplySkinException::class)
    @MainThread
    fun execute(view: View, attribute: SkinAttribute)
}
