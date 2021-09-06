package org.alee.demo.skin;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.page.WindowManager;
import org.alee.component.skin.service.ISwitchThemeSkinObserver;
import org.alee.component.skin.service.ThemeSkinService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ISwitchThemeSkinObserver {
    private int mTheme = 0;
    private TextView mTestTv1;
    private LinearLayout mTestLayout1;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this);
        mTestTv1 = findViewById(R.id.tv_test1);
        mTestLayout1 = findViewById(R.id.ll_test1);
        mLinearLayout = findViewById(R.id.lly_root);
        mLinearLayout.removeAllViews();
        ImageView temp = new ImageView(mLinearLayout.getContext());
        temp.setLayoutParams(new LinearLayout.LayoutParams(150,50));
        mLinearLayout.addView(temp);
        WindowManager.getInstance().getWindowProxy(this).addEnabledThemeSkinView(temp,new SkinElement("src",R.mipmap.bg_light_green));
        findViewById(R.id.btn_switch).setOnClickListener(v -> {
            mTheme = 0 == mTheme ? 1 : 0;
            ThemeSkinService.getInstance().switchThemeSkin(mTheme);
        });
        onThemeSkinSwitch();

        findViewById(R.id.txt_label_bkgtint).setOnClickListener(v -> {

        });

        findViewById(R.id.icon_frg).setOnClickListener(v -> {

        });
    }

    @Override
    public void onThemeSkinSwitchRunOnUiThread() {
        mLinearLayout.setBackgroundColor(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getColor(R.color.black));
    }
}