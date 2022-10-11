package org.alee.demo.skin.basic.ability.basic.template

import android.os.Bundle
import androidx.annotation.LayoutRes

/**
 * 通过模板收敛UI处理代码
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/13
 *
 */
interface IUIProcess {
    /**
     * 获取布局Id
     * @return Int
     */
    @LayoutRes
    fun requireLayoutId(): Int

    /**
     * 初始化数据
     * @param savedInstanceState Bundle?
     */
    fun onInitData(savedInstanceState: Bundle?) {}

    /**
     * 在此处执行 view 的获取
     */
    fun onFindView() {}

    /**
     *  在此函数中对View进行属性赋值
     * @param savedInstanceState Bundle?
     */
    fun onBindViewValue(savedInstanceState: Bundle?) {}

    /**
     * 在此函数中对View设置监听器
     */
    fun onBindViewListener() {}

    /**
     * 在此处订阅数据变更
     */
    fun subscribeDataChanged() {}
}