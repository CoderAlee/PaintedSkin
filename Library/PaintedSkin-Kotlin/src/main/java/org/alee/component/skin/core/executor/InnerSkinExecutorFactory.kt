package org.alee.component.skin.core.executor

import android.view.View
import android.widget.AbsListView
import android.widget.CompoundButton
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import org.alee.component.skin.template.ISkinExecutor

internal fun View.fetchSkinExecutor(): ISkinExecutor {
    return when (this) {
        is ExpandableListView -> ExpandableListViewSkinExecutor<ExpandableListView>()
        is ListView -> ListViewSkinExecutor<ListView>()
        is AbsListView -> AbsListViewSkinExecutor<AbsListView>()
        is CompoundButton -> CompoundButtonSkinExecutor<CompoundButton>()
        is TextView -> TextViewSkinExecutor<TextView>()
        is ImageView -> ImageViewSkinExecutor<ImageView>()
        is ProgressBar -> ProgressBarSkinExecutor<ProgressBar>()
        else -> ViewSkinExecutor<View>()
    }
}
