package org.alee.demo.skin.basic.ability.dialog

import android.app.Dialog
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatDialog
import org.alee.component.skin.service.ThemeSkinService
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.SkinOptionFactory
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/5/26
 *
 */
class DialogDemoPage : BasePage(), View.OnClickListener {

    private lateinit var mDialog: Dialog

    override fun requireLayoutId() = R.layout.page_dialog_demo

    override fun onBindViewListener() {
        findView<View>(R.id.btn_show_dialog).setOnClickListener(this)
        findView<View>(R.id.btn_show_app_compat_dialog).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_dialog -> {
                showDialog()
            }

            R.id.btn_show_app_compat_dialog -> {
                showAppCompatDialog()
            }
        }
    }

    private fun showDialog() {
        if (this::mDialog.isInitialized.not()) {
            mDialog = Dialog(requireContext(), R.style.CommonDialogStyle).apply {
                initDialog(this)
            }
        }
        mDialog.show()
    }

    private fun showAppCompatDialog() {
        val dialog = AppCompatDialog(requireContext(), R.style.CommonDialogStyle)
        initDialog(dialog)
        dialog.show()
    }

    private fun initDialog(dialog: Dialog) {
        dialog.setContentView(R.layout.layout_dialog)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            dialog.requireViewById<View>(R.id.btn_day).setOnClickListener {
                ThemeSkinService.getInstance().switchThemeSkin(SkinOptionFactory.MODE_DAY)
            }
            dialog.requireViewById<View>(R.id.btn_night).setOnClickListener {
                ThemeSkinService.getInstance().switchThemeSkin(SkinOptionFactory.MODE_NIGHT)
            }
        } else {
            dialog.findViewById<View>(R.id.btn_day)?.setOnClickListener {
                ThemeSkinService.getInstance().switchThemeSkin(SkinOptionFactory.MODE_DAY)
            }
            dialog.findViewById<View>(R.id.btn_night)?.setOnClickListener {
                ThemeSkinService.getInstance().switchThemeSkin(SkinOptionFactory.MODE_NIGHT)
            }
        }
    }
}
