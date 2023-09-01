package android.content.res

import org.alee.component.skin.util.INotProguard
import org.alee.reflex.ReflexClass
import org.alee.reflex.ReflexConstructor
import org.alee.reflex.ReflexMethod
import org.alee.reflex.annotation.MethodParams
import org.alee.reflex.annotation.ReflexMeta

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal class AssetManagerMapping : INotProguard {
    companion object {
        lateinit var constructor: ReflexConstructor<AssetManager>

        @ReflexMeta
        @MethodParams(String::class)
        lateinit var addAssetPath: ReflexMethod<Int>

        init {
            ReflexClass.load(AssetManagerMapping::class.java, AssetManager::class.java)
        }
    }
}
