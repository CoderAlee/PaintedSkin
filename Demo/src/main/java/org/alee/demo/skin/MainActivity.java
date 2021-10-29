package org.alee.demo.skin;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.alee.component.skin.executor.SkinElement;
import org.alee.component.skin.page.WindowManager;
import org.alee.component.skin.service.ISwitchThemeSkinObserver;
import org.alee.component.skin.service.ThemeSkinService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISwitchThemeSkinObserver {
    private int mTheme = 0;
    private LinearLayout mLinearLayout;

    private ImageView mOutFace, mOutAr, mOutFaceName, mOutArName;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeSkinService.getInstance().subscribeSwitchThemeSkin(this);
        mLinearLayout = findViewById(R.id.lly_root);
        mOutFace = findViewById(R.id.out_face);
        mOutAr = findViewById(R.id.ar_face);
        mOutFaceName = findViewById(R.id.out_face_name);
        mOutArName = findViewById(R.id.ar_face_name);
        mRecyclerView = findViewById(R.id.rv);
        mLinearLayout.removeAllViews();
        ImageView temp = new ImageView(mLinearLayout.getContext());
        temp.setLayoutParams(new LinearLayout.LayoutParams(150, 50));
        mLinearLayout.addView(temp);
        WindowManager.getInstance().getWindowProxy(this).addEnabledThemeSkinView(temp, new SkinElement("src", R.mipmap.bg_light_green));
        displayList();
        findViewById(R.id.btn_switch).setOnClickListener(v -> {
            mTheme = 0 == mTheme ? 1 : 0;
            ThemeSkinService.getInstance().switchThemeSkin(mTheme);
        });
        onThemeSkinSwitch();
    }

    private void displayList() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter();
        mRecyclerView.setAdapter(adapter);
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            temp.add("第" + i + "条");
        }
        adapter.notifyData(temp);
    }

    @Override
    public void onThemeSkinSwitchRunOnUiThread() {
        mLinearLayout.setBackgroundColor(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getColor(R.color.black));
        updateFace();
    }

    private void updateFace() {
        mOutFace.setImageDrawable(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getDrawable(R.drawable.outline_face));
        mOutFaceName.setImageDrawable(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getDrawable("outline_face"));
        mOutAr.setImageDrawable(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getMipmap(R.mipmap.outline_view_in_ar));
        mOutArName.setImageDrawable(ThemeSkinService.getInstance().getCurrentThemeSkinPack().getMipmap("outline_view_in_ar"));
    }
}