package org.alee.component.skin.core.resources.provider

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * [SkinPackType.ASSETS] 类型 皮肤资源提供者基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
abstract class BaseAssetSkinResourcesProvider(context: Context) : BaseCustomSkinResourcesProvider(context) {
    /**
     * @see AssetManager
     */
    protected val assetManager: AssetManager = context.assets

    /**
     * 从Asset 目录中加载图片
     * @param fileName String 文件名称
     * @return Bitmap? [Bitmap]
     * @throws Throwable
     */
    @Throws(Throwable::class)
    protected fun loadImage(fileName: String): Bitmap? {
        return assetManager.open(fileName).use {
            BitmapFactory.decodeStream(it)
        }
    }
}
