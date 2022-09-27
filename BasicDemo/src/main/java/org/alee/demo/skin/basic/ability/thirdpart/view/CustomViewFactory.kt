package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import org.alee.component.skin.factory2.IExpandedFactory2
import org.alee.demo.skin.basic.ability.widget.CustomView

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CustomViewFactory : IExpandedFactory2 {

    private companion object {
        /**
         * [CustomView] 类名
         */
        private val CLASS_NAME = CustomView::class.java.name
    }


    /**
     * 创建View
     *
     * @param originalView 上一个IExpandedFactory生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      [Context]
     * @param attrs        [AttributeSet]
     * @return 生成的View
     */
    override fun onCreateView(originalView: View?, parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return if (CLASS_NAME == name) CustomView(context, attrs) else originalView
    }
}