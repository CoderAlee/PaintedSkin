package org.alee.demo.skin;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.alee.component.skin.service.ThemeSkinService;

public class MainActivity extends AppCompatActivity {
    private int mTheme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_switch).setOnClickListener(v -> {
            mTheme = 0 == mTheme ? 1 : 0;
            ThemeSkinService.getInstance().switchThemeSkin(mTheme);
        });
    }
}