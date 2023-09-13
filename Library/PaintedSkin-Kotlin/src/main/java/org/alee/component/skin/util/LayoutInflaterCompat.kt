package org.alee.component.skin.util

import android.os.Build
import android.view.LayoutInflater
import android.view.LayoutInflater.Factory2
import android.view.LayoutInflaterMapping
import org.alee.component.skin.core.template.ISkinnableViewWarehouse
import org.alee.component.skin.core.view.factory.IViewCreatorManager
import org.alee.component.skin.core.view.factory.ThemeSkinRecognizer

internal fun LayoutInflater.setFactory2(creatorManager: IViewCreatorManager, viewWarehouse: ISkinnableViewWarehouse) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        setFactory2Q(creatorManager, viewWarehouse)
    } else {
        var originalFactory2: Factory2? = factory2
        LayoutInflaterMapping.mFactorySet.set(this, false)
        LayoutInflaterMapping.mFactory2.set(this, null)
        LayoutInflaterMapping.mFactory.set(this, null)
        if (originalFactory2 is ThemeSkinRecognizer) {
            originalFactory2 = null
        }
        var originalPrivateFactory2: Factory2? = LayoutInflaterMapping.mPrivateFactory.get(this)
        if (originalPrivateFactory2 is ThemeSkinRecognizer) {
            originalPrivateFactory2 = null
        }
        factory2 = ThemeSkinRecognizer(Factory2Zip(originalFactory2, originalPrivateFactory2), creatorManager, viewWarehouse)
    }
}

private fun LayoutInflater.setFactory2Q(creatorManager: IViewCreatorManager, viewWarehouse: ISkinnableViewWarehouse) {
    var originalFactory2: Factory2? = factory2
    LayoutInflaterMapping.mFactory2.set(this, null)
    LayoutInflaterMapping.mFactory.set(this, null)
    if (originalFactory2 is ThemeSkinRecognizer) {
        originalFactory2 = null
    }
    var originalPrivateFactory2: Factory2? = LayoutInflaterMapping.mPrivateFactory.get(this)
    if (originalPrivateFactory2 is ThemeSkinRecognizer) {
        originalPrivateFactory2 = null
    }
    factory2 = ThemeSkinRecognizer(Factory2Zip(originalFactory2, originalPrivateFactory2), creatorManager, viewWarehouse)
}
