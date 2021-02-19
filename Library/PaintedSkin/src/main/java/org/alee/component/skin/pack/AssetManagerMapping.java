package org.alee.component.skin.pack;

import android.content.res.AssetManager;

import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexMethod;
import org.alee.reflex.annotation.MethodParams;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
final class AssetManagerMapping {
    @MethodParams(String.class)
    public static ReflexMethod<Integer> addAssetPath;

    static {
        ReflexClass.load(AssetManagerMapping.class, AssetManager.class);
    }
}
