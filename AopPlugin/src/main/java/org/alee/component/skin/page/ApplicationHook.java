package org.alee.component.skin.page;

import android.app.Application;

import androidx.annotation.RestrictTo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/15
 * @description: xxxx
 *
 *********************************************************/
@Aspect
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class ApplicationHook {

    /**
     * Application 类名
     */
    private static final String CLASS_NAME_APPLICATION = "android.app.Application";

    @Pointcut("execution(* " + CLASS_NAME_APPLICATION + "+.onCreate()) ")
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void hookOnCreate() {
    }


    @Before("hookOnCreate()")
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void onCreateProcessor(JoinPoint joinPoint) throws Throwable {
        if (null == joinPoint.getTarget()) {
            return;
        }
        WindowManager.getInstance().init((Application) joinPoint.getTarget(),null);
    }
}
