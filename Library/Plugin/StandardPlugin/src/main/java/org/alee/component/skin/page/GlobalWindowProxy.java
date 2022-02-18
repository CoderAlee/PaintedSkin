package org.alee.component.skin.page;

import android.content.Context;
import android.view.LayoutInflater;

import org.alee.component.skin.service.Config;
import org.alee.component.skin.service.ThemeSkinService;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class GlobalWindowProxy extends BaseWindowProxy {

    @Override
    public void bindLayoutInflate(Context context) {
        bindLayoutInflateFactory2(LayoutInflater.from(context), ThemeSkinService.getInstance().getCreateViewInterceptor());
    }

    /**
     * 是否支持在不可见时换肤
     *
     * @return true: 支持
     */
    @Override
    protected boolean isApplyInInvisible() {
        return Config.PerformanceMode.EXPERIENCE_FIRST == Config.getInstance().getPerformanceMode();
    }

}
