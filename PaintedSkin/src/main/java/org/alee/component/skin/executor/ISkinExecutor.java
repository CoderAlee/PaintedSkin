package org.alee.component.skin.executor;

import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.skin.exception.ApplySkinException;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: 换肤执行器
 *
 *********************************************************/
public interface ISkinExecutor {
    /**
     * 执行皮肤替换工作
     *
     * @param view 需要替换皮肤的View
     * @throws ApplySkinException 换肤时出现的异常
     */
    void execute(@NonNull View view) throws ApplySkinException;

    /**
     * 获取皮肤属性名称
     *
     * @return 属性名称 例：textColor
     */
    String getSkinAttrName();
}
