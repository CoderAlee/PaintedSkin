package android.view

import android.view.LayoutInflater.Factory
import android.view.LayoutInflater.Factory2
import org.alee.component.skin.util.INotProguard
import org.alee.reflex.ReflexBoolean
import org.alee.reflex.ReflexClass
import org.alee.reflex.ReflexObject
import org.alee.reflex.annotation.ReflexMeta

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/11
 *
 */
internal class LayoutInflaterMapping : INotProguard {
    companion object {
        @ReflexMeta
        lateinit var mFactorySet: ReflexBoolean

        @ReflexMeta
        lateinit var mFactory: ReflexObject<Factory>

        @ReflexMeta
        lateinit var mFactory2: ReflexObject<Factory2>

        @ReflexMeta
        lateinit var mPrivateFactory: ReflexObject<Factory2>

        init {
            ReflexClass.load(LayoutInflaterMapping::class.java, LayoutInflater::class.java)
        }
    }
}
