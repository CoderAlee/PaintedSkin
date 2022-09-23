package org.alee.demo.skin.basic.ability.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.findNavController
import org.alee.demo.skin.basic.ability.util.back

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2022/9/14
 *
 */
class BackButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    init {
        setOnClickListener {
            findNavController().back()
        }
    }

}