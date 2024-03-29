package org.alee.demo.skin.basic.ability.list

import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ListView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.basic.recycler.OnItemClickListener
import org.alee.demo.skin.basic.ability.basic.template.IThemeSkinAble
import org.alee.demo.skin.basic.ability.list.adapter.GridViewAdapter
import org.alee.demo.skin.basic.ability.list.adapter.ListViewAdapter
import org.alee.demo.skin.basic.ability.list.adapter.RecyclerViewAdapter
import org.alee.demo.skin.basic.ability.util.bindView
import org.alee.demo.skin.basic.ability.util.drawableResource
import org.alee.demo.skin.basic.ability.util.lazyInUI
import org.alee.demo.skin.basic.ability.util.stringResource

/**
 * 各类列表换肤Demo
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/23
 *
 */
class ListDemoPage : BasePage(), OnItemClickListener, IThemeSkinAble {

    private val mRecyclerView by bindView<RecyclerView>(R.id.rv_list)

    /**
     * RecyclerView 的装饰器 此处用作绘制item分割线
     */
    private val mRecyclerViewDecoration by lazyInUI {
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(R.drawable.shape_list_divider.drawableResource!!)
        }
    }

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_list_demo

    override fun onBindViewValue(savedInstanceState: Bundle?) {
        findView<ListView>(R.id.list_view).init()
        findView<GridView>(R.id.grid_view).init()
        mRecyclerView.init()
    }


    private fun ListView.init() {
        adapter = ListViewAdapter(context.resources.getStringArray(R.array.chicken_soup).toList())
        setOnItemClickListener { _, _, position, _ ->
            position.showToast()
        }
    }

    private fun GridView.init() {
        adapter = GridViewAdapter(context.resources.getStringArray(R.array.province_name).toList())
        setOnItemClickListener { _, _, position, _ ->
            position.showToast()
        }
    }

    private fun RecyclerView.init() {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(mRecyclerViewDecoration)
        adapter = RecyclerViewAdapter(this@ListDemoPage).apply {
            notifyData((1..10).toList())
        }
    }

    /**
     * item点击事件回调
     *
     * @param view     item
     * @param position 位置
     */
    override fun onItemClick(view: View?, position: Int) {
        position.showToast()
    }

    override fun onThemeSkinSwitch() {
        // FixMe 此处是为了处理 通过DividerItemDecoration设置的分割线样式
        mRecyclerViewDecoration.setDrawable(R.drawable.shape_list_divider.drawableResource!!)
        mRecyclerView.requestLayout()
    }

    private fun Int.showToast() {
        ToastUtils.showShort(R.string.list_demo_toast.stringResource(this))
    }
}