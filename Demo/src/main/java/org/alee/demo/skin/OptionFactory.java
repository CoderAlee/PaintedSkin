package org.alee.demo.skin;

import org.alee.component.skin.plugin.annotation.Skin;
import org.alee.component.skin.service.IOptionFactory;
import org.alee.component.skin.service.IThemeSkinOption;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/19
 * @description: xxxx
 *
 *********************************************************/
@Skin
public final class OptionFactory implements IOptionFactory {
    @Override
    public int defaultTheme() {
        return 0;
    }

    @Override
    public IThemeSkinOption requireOption(int theme) {
        switch (theme) {
            case 1:
                return new NightOption();
            default:
                return null;
        }
    }
}
