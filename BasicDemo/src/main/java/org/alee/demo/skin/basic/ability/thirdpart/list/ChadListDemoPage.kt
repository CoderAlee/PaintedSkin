package org.alee.demo.skin.basic.ability.thirdpart.list

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.alee.demo.skin.basic.ability.R
import org.alee.demo.skin.basic.ability.basic.fragment.BasePage
import org.alee.demo.skin.basic.ability.thirdpart.list.adapter.ChadListAdapter
import org.alee.demo.skin.basic.ability.util.SpacesItemDecoration
import org.alee.demo.skin.basic.ability.util.dimenResource

/**
 * 第三方Adapter 换肤 Demo
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/26
 *
 */
class ChadListDemoPage : BasePage() {

    /**
     * 获取布局Id
     * @return Int
     */
    override fun requireLayoutId() = R.layout.page_chad_list_demo


    override fun onBindViewValue(savedInstanceState: Bundle?) {
        findView<RecyclerView>(R.id.rv_list).apply {
            layoutManager = GridLayoutManager(context, 3).apply {
                spanSizeLookup = CustomSpanSizeLookup()
            }
            addItemDecoration(SpacesItemDecoration(R.dimen.tp_6.dimenResource))
            adapter = ChadListAdapter().apply {
                setNewInstance(mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, -1))
            }
        }
    }

}

private class CustomSpanSizeLookup : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return if (9 == position) 2 else 1
    }
}