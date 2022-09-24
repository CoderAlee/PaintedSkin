package org.alee.demo.skin;

import android.app.Application;

import org.alee.component.skin.compat.ConstraintLayoutCompat;
import org.alee.component.skin.page.WindowManager;
import org.alee.component.skin.service.Config;
import org.alee.demo.skin.basic.ability.SkinOptionFactory;
import org.alee.demo.skin.basic.ability.util.ResourceExtKt;
import org.alee.demo.skin.compat.BackButtonCompat;
import org.alee.demo.skin.compat.NestedScrollViewCompat;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/17
 * @description: xxxx
 *
 *********************************************************/
public final class App extends Application {

    static {
        Config.getInstance().setPerformanceMode(Config.PerformanceMode.EXPERIENCE_FIRST);
        ConstraintLayoutCompat.init();
        NestedScrollViewCompat.init();
        BackButtonCompat.init();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WindowManager.getInstance().init(this, new SkinOptionFactory());
        ResourceExtKt.context = getApplicationContext();
    }
}
