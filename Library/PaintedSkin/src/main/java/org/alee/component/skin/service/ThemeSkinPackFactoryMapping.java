package org.alee.component.skin.service;

import android.content.Context;

import org.alee.component.skin.pack.ILoadThemeSkinObserver;
import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexStaticMethod;
import org.alee.reflex.annotation.MethodParams;

import java.util.List;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class ThemeSkinPackFactoryMapping {
    @MethodParams({Context.class, ILoadThemeSkinObserver.class, List.class})
    public static ReflexStaticMethod<Void> loadThemeSkinPack;

    static {
        ReflexClass.load(ThemeSkinPackFactoryMapping.class, "org.alee.component.skin.pack.ThemeSkinPackFactory");
    }
}
