package org.alee.component.skin.page;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
final class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        WindowManager.getInstance().onAttachWindow(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        WindowManager.getInstance().getWindowProxy(activity).onVisible();
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        WindowManager.getInstance().getWindowProxy(activity).Invisible();
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        WindowManager.getInstance().onDetachWindow(activity);
    }
}
