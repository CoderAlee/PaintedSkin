package org.alee.component.skin.page;

import android.view.LayoutInflater;

import org.alee.reflex.ReflexBoolean;
import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexObject;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class LayoutInflaterMapping {
    public static ReflexBoolean mFactorySet;
    public static ReflexObject<LayoutInflater.Factory> mFactory;
    public static ReflexObject<LayoutInflater.Factory2> mFactory2;
    public static ReflexObject<LayoutInflater.Factory2> mPrivateFactory;

    static {
        ReflexClass.load(LayoutInflaterMapping.class, LayoutInflater.class);
    }
}
