package org.alee.component.skin.exception

import android.view.View
import org.alee.component.skin.model.SkinAttribute
import org.alee.component.skin.util.INotProguard

/**
 * 换肤失败异常
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/1
 *
 */
class ApplySkinException(view: View, attribute: SkinAttribute, throwable: Throwable) :
    RuntimeException(
        """An exception occurred at apply theme skin ! *********
        [ViewId: ${view.id}] [ViewName: ${view.javaClass.name}]
        [AttributeName: ${attribute.attrName}] [ResourceId: ${attribute.resourceId}]
        [ResourceName: ${attribute.resourceName}] [ResourceType: ${attribute.resourceType}]
    """,
        throwable,
    ),
    INotProguard
