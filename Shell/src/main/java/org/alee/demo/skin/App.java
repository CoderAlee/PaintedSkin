package org.alee.demo.skin;


import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.alee.component.skin.compat.ConstraintLayoutCompat;
import org.alee.component.skin.page.WindowManager;
import org.alee.component.skin.service.Config;
import org.alee.component.skin.service.ISwitchThemeSkinObserver;
import org.alee.component.skin.service.ThemeSkinService;
import org.alee.demo.skin.basic.ability.ConstantKt;
import org.alee.demo.skin.basic.ability.SkinOptionFactory;
import org.alee.demo.skin.basic.ability.util.ResourceExtKt;
import org.alee.demo.skin.compat.AppCompatButtonCompat;
import org.alee.demo.skin.compat.BackButtonCompat;
import org.alee.demo.skin.compat.NestedScrollViewCompat;
import org.alee.demo.skin.compat.RecyclerViewCompat;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/17
 * @description: xxxx
 *
 *********************************************************/
public final class App extends Application implements ISwitchThemeSkinObserver {
    
    static {
        Config.getInstance().setPerformanceMode(Config.PerformanceMode.EXPERIENCE_FIRST);
        ConstraintLayoutCompat.init();
        NestedScrollViewCompat.init();
        BackButtonCompat.init();
        RecyclerViewCompat.init();
        AppCompatButtonCompat.init();
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        copySkinPack();
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this);
        WindowManager.getInstance().init(this, new SkinOptionFactory());
    }
    
    private void copySkinPack() {
        ResourceUtils.copyFileFromAssets(ConstantKt.ASSETS_NIGHT_SKIN_PACK, ConstantKt.getSKIN_PACK_PATH() + ConstantKt.NIGHT_SKIN_PACK_NAME);
        ResourceUtils.copyFileFromAssets(ConstantKt.ASSETS_SPRING_FESTIVAL_SKIN_PACK, ConstantKt.getSKIN_PACK_PATH() + ConstantKt.SPRING_FESTIVAL_SKIN_PACK_NAME);
    }
    
    @Override
    public void onThemeSkinSwitchRunOnUiThread() {
        ToastUtils.getDefaultMaker()
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, ResourceExtKt.getDimenResource(R.dimen.tp_12))
                .setBgColor(ResourceExtKt.getColorResource(R.color.black_300))
                .setTextColor(ResourceExtKt.getColorResource(R.color.text_white_high))
                .setTextSize(ResourceExtKt.getDimenResource(R.dimen.tp_14));
    }
}
