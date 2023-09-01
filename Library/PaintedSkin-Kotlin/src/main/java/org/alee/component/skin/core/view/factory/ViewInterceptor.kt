package org.alee.component.skin.core.view.factory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater.Factory2
import android.view.View
import org.alee.component.skin.util.ext.logI

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal abstract class ViewInterceptor(
    originalFactory2: Factory2?,
    private val mViewCreatorManager: IViewCreatorManager,
) : BaseViewCreator(originalFactory2) {
    /**
     * 替换view
     *
     * @param originalView 父类生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      [Context]
     * @param attributeSet [AttributeSet]
     * @return 替换后的view
     */
    override fun onReplaceView(
        originalView: View?,
        parent: View?,
        name: String,
        context: Context,
        attributeSet: AttributeSet,
    ): View? {
        var view: View? = originalView
        val temp = mViewCreatorManager.onCreateView(view, parent, context, name, attributeSet)
        if (null != temp) {
            view = temp
        }
        if (null == view) {
            "No generator found for creating [$name] View".logI()
            return null
        }
        distinguishEnableSkin(view, attributeSet)
        return view
    }

    /**
     * 识别是否支持换肤
     *
     * @param view         view
     * @param attributeSet 属性集
     */
    protected abstract fun distinguishEnableSkin(view: View, attributeSet: AttributeSet)
}
