package org.alee.component.skin.core.resources.provider

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import androidx.annotation.AnyRes
import org.alee.component.skin.constant.ResourcesType

/**
 *  [SkinPackType.STANDARD] 类型 皮肤资源提供者基类
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/30
 *
 */
internal abstract class BaseStandardSkinResourcesProvider(
    /**
     * 当前皮肤包的Context
     */
    protected val mPackContext: Context?,
    /**
     * 宿主应用的[Resources]对象
     */
    private val mHostResources: Resources,
    /**
     * 皮肤包的[Resources]对象
     */
    protected val mPackResources: Resources,
    /**
     * 皮肤包的包名
     */
    private val mPackPackageName: String,

) : BaseSkinResourcesProvider() {
    /**
     * 获取状态颜色集合
     *
     * @param resId 原始资源Id
     * @param theme 主题
     * @return [ColorStateList]
     * @throws Throwable
     */
    override fun getColorStateList(resId: Int, theme: Theme?): ColorStateList? = getColorStateList(resId.resourceName, theme)

    /**
     * 获取色值
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 色值
     * @throws Throwable 任何异常
     */
    override fun getColor(resId: Int, theme: Theme?): Int? = getColor(resId.resourceName, theme)

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    override fun getDrawable(resId: Int, theme: Theme?): Drawable? = getDrawable(resId.resourceName, theme)

    /**
     * 获取图片
     *
     * @param resId 原始资源id
     * @param theme 主题
     * @return 图片
     * @throws Throwable 任何异常
     */
    override fun getMipmap(resId: Int, theme: Theme?): Drawable? = getMipmap(resId.resourceName, theme)

    /**
     * 主题
     */
    override val theme: Theme?
        get() = mPackContext?.theme

    /**
     * 将宿主App内的资源ID转换为资源名称
     */
    private val @receiver:AnyRes Int.resourceName: String
        get() = mHostResources.getResourceEntryName(this)

    /**
     * 通过资源名在皮肤包内找到对应的资源id
     * @receiver String 资源名
     * @param type String 资源类型
     * @return Int
     */
    protected fun String.targetResourcesId(@ResourcesType type: String): Int {
        if (isEmpty()) {
            return -1
        }
        return mPackResources.getIdentifier(this, type, mPackPackageName)
    }
}
