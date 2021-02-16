package org.alee.component.skin.exception;

import android.view.View;

import org.alee.component.skin.executor.SkinElement;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
public final class ApplySkinException extends RuntimeException {

    public ApplySkinException(View view, SkinElement element, Throwable throwable) {
        super("An exception occurred at apply theme skin ! *********" +
                " \r\n【 ViewId: " + view.getId() + " 】 【 ViewName: " + view.getClass().getName() + " 】" +
                "\r\n 【 AttributeName: " + element.getAttrName() + " 】 【 ResourceId: " + element.getResourcesId() + " 】 " +
                "【 ResourceName: " + element.getResourcesName() + " 】 【ResourceType: " + element.getResourcesType() + " 】", throwable);
    }
}
