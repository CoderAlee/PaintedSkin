package org.alee.demo.skin;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.alee.component.skin.service.ISwitchThemeSkinObserver;
import org.alee.component.skin.service.ThemeSkinService;

public class MainActivity extends AppCompatActivity implements ISwitchThemeSkinObserver {
    private int mTheme = 0;
    private TextView mTestTv1;
    private LinearLayout mTestLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this);
        mTestTv1 = findViewById(R.id.tv_test1);
        mTestLayout1 = findViewById(R.id.ll_test1);
        findViewById(R.id.btn_switch).setOnClickListener(v -> {
            mTheme = 0 == mTheme ? 1 : 0;
            ThemeSkinService.getInstance().switchThemeSkin(mTheme);
        });
    }

    @Override
    public void onThemeSkinSwitch() {
//        mTestTv1.post(() -> {
//            mTestTv1.setTextColor(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getColor(R.color.black));
//            mTestLayout1.setBackground(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getDrawable(R.drawable.bg_test9_selector));
//        });
    }
}