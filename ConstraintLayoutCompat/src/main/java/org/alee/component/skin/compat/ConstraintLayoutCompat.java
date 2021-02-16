package org.alee.component.skin.compat;

import org.alee.component.skin.service.ThemeSkinService;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/15
 * @description: xxxx
 *
 *********************************************************/
public final class ConstraintLayoutCompat {

    public static void init() {
        ThemeSkinService.getInstance().getCreateViewInterceptor().add(new ConstraintLayoutFactory());
    }

}
