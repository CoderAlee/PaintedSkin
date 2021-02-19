package org.alee.component.skin.service;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IOptionFactory {

    int defaultTheme();

    IThemeSkinOption requireOption(int theme);
}
