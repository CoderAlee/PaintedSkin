package org.alee.demo.skin.basic.ability.festive

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.USE_SPRING_FESTIVAL_SKIN
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.util.bindView
import org.alee.demo.skin.basic.ability.util.loadBoolean
import org.alee.demo.skin.basic.ability.util.saveBoolean

/**
 * 节日皮肤Demo
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */
class FestiveSkinDemoPage : BasePage() {

    private companion object {
        private const val SECOND = 1000L
    }

    private val mBtn by bindView<TextView>(R.id.btn_open_festival_mode)

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_festive_skin_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        super.onBindViewValue(savedInstanceState)
        // 从缓存中读取是否需要加载春节皮肤
        updateBtnText(USE_SPRING_FESTIVAL_SKIN.loadBoolean(false), false)
    }


    override fun onBindViewListener() {
        mBtn.setOnClickListener {
            updateBtnText(USE_SPRING_FESTIVAL_SKIN.loadBoolean(false).not())
        }
    }

    /**
     * 更新按钮文言并存储是否加载春节皮肤的状态
     * @param state Boolean true: 加载节日皮肤
     * @param relaunch Boolean true:重启
     */
    private fun updateBtnText(state: Boolean, relaunch: Boolean = true) {
        USE_SPRING_FESTIVAL_SKIN.saveBoolean(state)
        mBtn.text = if (state) "退出春节七天乐" else "进入春节七天乐"
        if (relaunch) {
            lifecycleScope.launch(Dispatchers.Default) {
                ToastUtils.showLong("App即将重启")
                delay(SECOND)
                AppUtils.relaunchApp(true)
            }
        }
    }
}