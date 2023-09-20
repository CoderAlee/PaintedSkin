package org.alee.demo.skin;


import android.app.Application;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.alee.component.skin.ThemeSkinService;
import org.alee.component.skin.constant.PerformanceMode;
import org.alee.component.skin.core.pack.IThemeSkinPack;
import org.alee.component.skin.template.IThemeSkinObserver;
import org.alee.demo.skin.compat.AppCompatButtonCompat;
import org.alee.demo.skin.compat.BackButtonCompat;
import org.alee.demo.skin.compat.NestedScrollViewCompat;
import org.alee.demo.skin.compat.RecyclerViewCompat;
import org.alee.demo.skin.kotlin.ability.ConstantKt;
import org.alee.demo.skin.kotlin.ability.theme.AppThemeManager;
import org.alee.demo.skin.kotlin.ability.util.ResourceExtKt;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/17
 * @description: xxxx
 *
 *********************************************************/
public final class App extends Application implements IThemeSkinObserver {
    
    static {
        ThemeSkinService.Config config = ThemeSkinService.INSTANCE.getConfig();
        config.setDebugMode(BuildConfig.DEBUG);
        config.setPerformanceMode(PerformanceMode.EXPERIENCE_FIRST);
        config.setStrictMode(true);
        //        ConstraintLayoutCompat.init();
        NestedScrollViewCompat.init();
        BackButtonCompat.init();
        RecyclerViewCompat.init();
        AppCompatButtonCompat.init();
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        copySkinPack();
        ThemeSkinService.INSTANCE.subscribe(this);
        AppThemeManager.INSTANCE.init(this);
    }
    
    private void copySkinPack() {
        ResourceUtils.copyFileFromAssets(ConstantKt.ASSETS_NIGHT_SKIN_PACK, ConstantKt.getSKIN_PACK_PATH() + ConstantKt.NIGHT_SKIN_PACK_NAME);
        ResourceUtils.copyFileFromAssets(ConstantKt.ASSETS_SPRING_FESTIVAL_SKIN_PACK, ConstantKt.getSKIN_PACK_PATH() + ConstantKt.SPRING_FESTIVAL_SKIN_PACK_NAME);
    }
    
    @Override
    public void onThemeSkinChanged(int theme, @NonNull IThemeSkinPack usedSkinPack) {
        ThreadUtils.runOnUiThread(() -> onThemeSkinChangedRunOnUiThread(ThemeSkinService.INSTANCE.getCurrentSkinPack()));
    }
    
    @Override
    public void onThemeSkinChangedRunOnUiThread(@NonNull IThemeSkinPack usedSkinPack) {
        ToastUtils.getDefaultMaker()
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, ResourceExtKt.getDimenResource(R.dimen.tp_12))
                .setBgColor(ResourceExtKt.getColorResource(R.color.black_800))
                .setTextColor(ResourceExtKt.getColorResource(R.color.white_800))
                .setTextSize(ResourceExtKt.getDimenResource(R.dimen.tp_14));
    }
}
