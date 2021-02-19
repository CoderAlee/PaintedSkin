package org.alee.demo.skin;

import org.alee.component.skin.service.IThemeSkinOption;

import java.util.LinkedHashSet;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/19
 * @description: xxxx
 *
 *********************************************************/
final class NightOption implements IThemeSkinOption {
    @Override
    public LinkedHashSet<String> getStandardSkinPackPath() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("/sdcard/night.skin");
        return set;
    }
}
