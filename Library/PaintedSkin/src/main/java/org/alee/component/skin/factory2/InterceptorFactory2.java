package org.alee.component.skin.factory2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.alee.component.skin.util.PrintUtil;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
abstract class InterceptorFactory2 extends BaseFactory2 {
    /**
     * {@link ExpandedFactory2Manager}
     */
    private final ExpandedFactory2Manager mExpandedFactory2Manager;

    public InterceptorFactory2(@Nullable LayoutInflater.Factory2 originalFactory2, @NonNull ExpandedFactory2Manager factory2Manager) {
        super(originalFactory2);
        mExpandedFactory2Manager = factory2Manager;
    }

    /**
     * 替换view
     *
     * @param originalView 父类生成的View
     * @param parent       父View
     * @param name         名称
     * @param context      {@link Context}
     * @param attributeSet {@link AttributeSet}
     * @return 替换后的view
     */
    @Override
    protected View onReplaceView(@Nullable View originalView, @Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View temp = mExpandedFactory2Manager.onCreateView(originalView, parent, name, context, attributeSet);
        if (null == temp) {
            temp = originalView;
        }
        if (null != temp) {
            distinguishEnableSkin(temp, attributeSet);
        }else {
            PrintUtil.getInstance().printD("ExpandedFactory2Manager 未能成功创建 [ " + name + " ]");
        }
        return temp;
    }

    /**
     * 识别是否支持换肤
     *
     * @param view         view
     * @param attributeSet 属性集
     */
    protected abstract void distinguishEnableSkin(@NonNull View view, @NonNull AttributeSet attributeSet);
}
