package org.alee.component.skin.plugin;

import androidx.annotation.RestrictTo;

import org.alee.component.skin.service.IOptionFactory;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/19
 * @description: xxxx
 *
 *********************************************************/
@RestrictTo(RestrictTo.Scope.LIBRARY)
public interface ISkinOptionFactoryLoader {
    /**
     * 加载皮肤配置工厂
     *
     * @return {@link IOptionFactory}
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    IOptionFactory load();
}
