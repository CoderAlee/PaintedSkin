package org.alee.component.skin.template

import org.alee.component.skin.core.resources.provider.BaseAssetSkinResourcesProvider
import org.alee.component.skin.core.resources.provider.BaseCustomSkinResourcesProvider
import org.alee.component.skin.core.resources.provider.ISkinResourcesProvider
import org.alee.component.skin.util.Version

/**
 * 主题皮肤 - 开发者实现此接口并配置皮肤包的依赖关系
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
sealed interface IThemeSkin {
    /**
     * 皮肤包的依赖关系。当从当前皮肤包内找不到要加载的资源时，会尝试从所依赖的皮肤包内进行加载。
     * 如果已是最后一个，此处可以返回[EmptyThemeSkin]
     */
    @Version("4.0.0")
    val dependencies: IThemeSkin
        get() = EmptyThemeSkin
}

/**
 * 主题皮肤 - 标准皮肤包(满足大部分需求，推荐使用)
 *
 * <p> 加载存储在存储卡内的 以.skin为后缀的标准皮肤包
 *
 * @author MingYu.Liu
 * created in  2023/7/12
 *
 */
interface IStandardThemeSkin : IThemeSkin {
    /**
     * 标准皮肤包路径 例：/sdcard/Android/org.alee.demo.skin/.SkinPack/Night.skin
     */
    @Version("4.0.0")
    val standardSkinPackPath: String
}

/**
 * 主题皮肤 - 自定义皮肤包(满足你的一切扩展需求，实现各种骚操作最佳选择)
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/7/12
 *
 */
interface ICustomThemeSkin : IThemeSkin {

    private companion object {
        private const val DEFAULT_PACK_PREFIX = "CustomSkinPack_"
    }

    /**
     * 由开发者自由发挥的[ISkinResourcesProvider]
     */
    @Version("4.0.0")
    val customSkinPack: BaseCustomSkinResourcesProvider

    /**
     * 皮肤包名称
     */
    @Version("4.0.0")
    val packName: String
        get() = DEFAULT_PACK_PREFIX + this.javaClass.simpleName
}

/**
 * 主题皮肤 - Asset皮肤包
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in  2023/7/12
 *
 */
interface IAssetThemeSkin : IThemeSkin {

    private companion object {
        private const val DEFAULT_PACK_PREFIX = "AssetSkinPack_"
    }

    /**
     * 从Assets目录读取资源的[ISkinResourcesProvider]
     */
    @Version("4.0.0")
    val assetsSkinPack: BaseAssetSkinResourcesProvider

    /**
     * 皮肤包名称
     */
    @Version("4.0.0")
    val packName: String
        get() = DEFAULT_PACK_PREFIX + this.javaClass.simpleName
}

/**
 * 主题皮肤 - 空皮肤包
 *
 * <p> [IThemeSkin.dependencies]的默认值
 *
 * @author MingYu.Liu
 * created in 2023/2/8
 *
 */
object EmptyThemeSkin : ICustomThemeSkin {
    /**
     * 由开发者自由发挥的[ISkinResourcesProvider]
     */
    override val customSkinPack: BaseCustomSkinResourcesProvider
        get() = TODO("Not yet implemented")

    override val dependencies: IThemeSkin
        get() = TODO("Not yet implemented")
}
