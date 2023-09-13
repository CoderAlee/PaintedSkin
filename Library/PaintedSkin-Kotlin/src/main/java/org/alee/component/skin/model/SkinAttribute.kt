package org.alee.component.skin.model

import android.content.Context
import org.alee.component.skin.util.INotProguard

/**
 * 此类用于描述需要换肤的属性
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
data class SkinAttribute(
    /**
     * 属性名称；例：textColor
     */
    val attrName: String,
    /**
     * 属性所使用的资源ID; 例 R.color.black
     */
    val resourceId: Int,
) : INotProguard {
    /**
     * 资源类型
     */
    internal var resourceType: String = ""

    /**
     * 资源名称
     */
    internal var resourceName: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SkinAttribute) return false

        if (attrName != other.attrName) return false
        if (resourceId != other.resourceId) return false
        if (resourceType != other.resourceType) return false
        if (resourceName != other.resourceName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attrName.hashCode()
        result = 31 * result + resourceId
        result = 31 * result + resourceType.hashCode()
        result = 31 * result + resourceName.hashCode()
        return result
    }

    override fun toString(): String {
        return "SkinAttribute(attrName='$attrName', resourceId=$resourceId, resourceType='$resourceType', resourceName='$resourceName')"
    }
}

/**
 * 填充属性
 * @receiver SkinAttribute
 * @param context Context
 */
internal fun SkinAttribute.inflate(context: Context) {
    if (resourceType.isNotEmpty()) {
        return
    }
    resourceName = context.resources.getResourceEntryName(resourceId)
    resourceType = context.resources.getResourceTypeName(resourceId)
}
