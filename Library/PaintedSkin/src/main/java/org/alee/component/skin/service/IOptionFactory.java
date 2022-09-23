package org.alee.component.skin.service;

import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IOptionFactory {

    int defaultTheme();

    @Nullable
    IThemeSkinOption requireOption(int theme);
}
