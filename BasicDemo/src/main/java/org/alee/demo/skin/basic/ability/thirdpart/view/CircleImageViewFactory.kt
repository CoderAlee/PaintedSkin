package org.alee.demo.skin.basic.ability.thirdpart.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import de.hdodenhof.circleimageview.CircleImageView
import org.alee.component.skin.factory2.IExpandedFactory2

/**
 * 自定义View构造工厂
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/27
 *
 */
internal class CircleImageViewFactory : IExpandedFactory2 {

    private companion object {
        /**
         * [CircleImageView] 类名
         */
        private val CLASS_NAME = CircleImageView::class.java.name
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
        return if (CLASS_NAME == name) CircleImageView(context, attrs) else originalView
    }
}