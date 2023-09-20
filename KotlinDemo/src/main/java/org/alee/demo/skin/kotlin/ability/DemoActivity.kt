package org.alee.demo.skin.kotlin.ability

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.alee.component.skin.ThemeSkinService
import org.alee.component.skin.core.pack.IThemeSkinPack
import org.alee.demo.skin.kotlin.ability.R.string
import org.alee.demo.skin.kotlin.ability.basic.activity.ScopedActivity
import org.alee.demo.skin.kotlin.ability.basic.template.IThemeSkinAble
import org.alee.demo.skin.kotlin.ability.basic.template.IUIProcess
import org.alee.demo.skin.kotlin.ability.theme.AppTheme
import org.alee.demo.skin.kotlin.ability.theme.AppThemeManager
import org.alee.demo.skin.kotlin.ability.theme.DayNightMode
import org.alee.demo.skin.kotlin.ability.theme.Theme
import java.util.concurrent.atomic.AtomicInteger

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/13
 *
 */
internal class DemoActivity : ScopedActivity(), IThemeSkinAble, IUIProcess {

    private val mDrawerLayout: DrawerLayout by lazy { findViewById(R.id.layout_drawer) }

    private val mDefaultThemeBtn: CompoundButton by lazy { findViewById(R.id.btn_default_theme) }

    private val mFestivalThemeBtn: CompoundButton by lazy { findViewById(R.id.btn_spring_festival_theme) }

    private val mDayModeBtn: CompoundButton by lazy { findViewById(R.id.btn_day_mode) }

    private val mNightModeBtn: CompoundButton by lazy { findViewById(R.id.btn_night_mode) }

    private val mCustomSettingBtn: View by lazy { findViewById(R.id.btn_custom_setting) }

    private val mPressureTestBtn: View by lazy { findViewById(R.id.btn_pressure_test) }

    private val mCallSwitchCount = AtomicInteger(0)

    private val mLastUsedTheme = AtomicInteger(-1)

    private val mSwitchCount = AtomicInteger(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(requireLayoutId())
        onBindViewValue(savedInstanceState)
        onBindViewListener()
    }

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.activity_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        val toggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            findViewById(R.id.tool_bar),
            string.empty,
            string.empty,
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        refreshSelectedTheme()
        refreshSelectedDayNightMode()
    }

    override fun onBindViewListener() {
        findViewById<View>(R.id.space).setOnClickListener {
            mDrawerLayout.close()
        }
        mDefaultThemeBtn.setOnClickListener { AppThemeManager.switchTheme(Theme.STANDARD) }
        mFestivalThemeBtn.setOnClickListener { AppThemeManager.switchTheme(Theme.SPRING_FESTIVAL) }
        mDayModeBtn.setOnClickListener { AppThemeManager.switchDayNightMode(DayNightMode.DAY) }
        mNightModeBtn.setOnClickListener { AppThemeManager.switchDayNightMode(DayNightMode.NIGHT) }
        mCustomSettingBtn.setOnClickListener { }
        mPressureTestBtn.setOnClickListener { doPressureTest() }
    }

    /**
     * 当主题发生变化时被回调
     * @param usedSkinPack IThemeSkinPack 当前使用的皮肤包
     */
    override fun onThemeSkinChangedRunOnUiThread(usedSkinPack: IThemeSkinPack) {
        refreshSelectedTheme()
        refreshSelectedDayNightMode()
    }

    private fun refreshSelectedTheme() {
        when (AppThemeManager.selectedTheme) {
            Theme.STANDARD -> mDefaultThemeBtn.isChecked = true
            Theme.SPRING_FESTIVAL -> mFestivalThemeBtn.isChecked = true
        }
    }

    private fun refreshSelectedDayNightMode() {
        if (AppThemeManager.usedDayMode) {
            mDayModeBtn.isChecked = true
        } else {
            mNightModeBtn.isChecked = true
        }
    }

    override fun onThemeSkinChanged(theme: Int, usedSkinPack: IThemeSkinPack) {
        mSwitchCount.incrementAndGet()
        super.onThemeSkinChanged(theme, usedSkinPack)
    }

    private fun doPressureTest() {
        launch(Dispatchers.IO) {
            mCallSwitchCount.set(0)
            mSwitchCount.set(0)
            val job1 = async(Dispatchers.Default) {
                test(AppTheme.STANDARD_LIGHT.ordinal)
            }
            val job2 = async(Dispatchers.Default) {
                test(AppTheme.STANDARD_DARK.ordinal)
            }
            val job3 = async(Dispatchers.Default) {
                test(AppTheme.SPRING_FESTIVAL_LIGHT.ordinal)
            }

            val job4 = async(Dispatchers.Default) {
                test(AppTheme.SPRING_FESTIVAL_DARK.ordinal)
            }
            awaitAll(job1, job2, job3, job4)
            launch(Dispatchers.Main) {
                ToastUtils.showLong("共调用换肤${mCallSwitchCount.get()}次,实际换肤${mSwitchCount.get()}次,最终应用的主题为${mLastUsedTheme.get().themeName}")
            }
        }
    }

    private fun test(theme: Int) {
        for (i in 0 until 250) {
            ThemeSkinService.switchTheme(theme)
            mCallSwitchCount.incrementAndGet()
        }
        mLastUsedTheme.set(theme)
    }

    private val Int.themeName: String
        get() = when (this) {
            0 -> "默认白天"
            1 -> "默认黑夜"
            2 -> "春节白天"
            3 -> "春节黑夜"
            else -> "未知"
        }
}
