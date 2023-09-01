package org.alee.component.skin.core.pack

/**
 * 自定义皮肤包
 * @constructor
 */
internal class CustomThemeSkinPack(skinPackName: String, underpinThemeSkinPack: IThemeSkinPack) :
    BaseThemeSkinPack(skinPackName, underpinThemeSkinPack)

/**
 * Asset 皮肤包
 * @constructor
 */
internal class AssetThemeSkinPack(skinPackName: String, underpinThemeSkinPack: IThemeSkinPack) :
    BaseThemeSkinPack(skinPackName, underpinThemeSkinPack)

/**
 * 默认皮肤包
 */
internal class DefaultThemeSkinPack : BaseThemeSkinPack(DEFAULT_PACK_NAME, null) {
    private companion object {
        private const val DEFAULT_PACK_NAME = "DefaultSkinPack"
    }
}

/**
 * 标准皮肤包
 * @constructor
 */
internal class StandardThemeSkinPack(skinPackName: String, underpinThemeSkinPack: IThemeSkinPack) :
    BaseThemeSkinPack(skinPackName, underpinThemeSkinPack)
