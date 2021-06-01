package org.alee.demo.skin;

import android.os.Build;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            set.add("/storage/emulated/0/Android/data/org.alee.demo.skin/files/Documents/night.skin");
        } else {
            set.add("/sdcard/night.skin");
        }
        return set;
    }
}
