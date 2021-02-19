package org.alee.demo.skin;

import android.app.Application;

import org.alee.component.skin.compat.ConstraintLayoutCompat;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/17
 * @description: xxxx
 *
 *********************************************************/
public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ConstraintLayoutCompat.init();
    }
}
