package org.alee.component.skin.constant

/**
 * 框架内所支持的原生View的换肤属性
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/9/4
 *
 */
annotation class DefaultSkinAttribute {
    /**
     * 框架内支持的[View]的换肤属性
     */
    interface ViewAttribute {
        companion object {
            /**
             * 换肤支持的属性 背景
             */
            const val ATTRIBUTE_BACKGROUND = "background"

            /**
             * 换肤支持的属性 前景色
             */
            const val ATTRIBUTE_FOREGROUND = "foreground"

            /**
             * 换肤支持的属性 背景染色
             */
            const val ATTRIBUTE_BKG_TINT = "backgroundTint"

            /**
             * 换肤支持的属性 前景染色
             */
            const val ATTRIBUTE_FRG_TINT = "foregroundTint"

            /**
             * Defines the horizontal scrollbar thumb drawable.
             */
            const val ATTRIBUTE_SCROLLBAR_THUMB_HORIZONTAL = "scrollbarThumbHorizontal"

            /**
             * Defines the vertical scrollbar thumb drawable.
             */
            const val ATTRIBUTE_SCROLLBAR_THUMB_VERTICAL = "scrollbarThumbVertical"

            /**
             * Defines the horizontal scrollbar track drawable.
             */
            const val ATTRIBUTE_SCROLLBAR_TRACK_HORIZONTAL = "scrollbarTrackHorizontal"

            /**
             * Defines the vertical scrollbar track drawable.
             */
            const val ATTRIBUTE_SCROLLBAR_TRACK_VERTICAL = "scrollbarTrackVertical"
        }
    }

    /**
     * 框架内支持的[TextView]的换肤属性
     */
    interface TextViewAttribute {
        companion object {

            /**
             * 换肤支持的属性 字体颜色
             */
            const val ATTRIBUTE_TEXT_COLOR = "textColor"

            /**
             * 换肤支持的属性 暗示字体颜色
             */
            const val ATTRIBUTE_TEXT_COLOR_HINT = "textColorHint"

            /**
             * 换肤支持的属性 选中时高亮背景颜色
             */
            const val ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT = "textColorHighlight"

            /**
             * 换肤支持的属性 链接的颜色
             */
            const val ATTRIBUTE_TEXT_COLOR_LINK = "textColorLink"

            /**
             * Place a blurred shadow of text underneath the text
             */
            const val ATTRIBUTE_SHADOW_COLOR = "shadowColor"

            /**
             * 换肤支持的属性 TextView左侧图片
             */
            const val ATTRIBUTE_DRAWABLE_LEFT = "drawableLeft"

            /**
             * 换肤支持的属性 TextView左侧图片
             */
            const val ATTRIBUTE_DRAWABLE_START = "drawableStart"

            /**
             * 换肤支持的属性 TextView顶部图片
             */
            const val ATTRIBUTE_DRAWABLE_TOP = "drawableTop"

            /**
             * 换肤支持的属性 TextView右侧图片
             */
            const val ATTRIBUTE_DRAWABLE_RIGHT = "drawableRight"

            /**
             * 换肤支持的属性 TextView右侧图片
             */
            const val ATTRIBUTE_DRAWABLE_END = "drawableEnd"

            /**
             * 换肤支持的属性 TextView底部图片
             */
            const val ATTRIBUTE_DRAWABLE_BOTTOM = "drawableBottom"

            /**
             * 换肤支持的属性 TextView内部图片染色
             */
            const val ATTRIBUTE_DRAWABLE_TINT = "drawableTint"
        }
    }

    /**
     * 框架内支持的[CompoundButton]的换肤属性
     */
    interface CompoundButtonAttribute {
        companion object {
            /**
             * 换肤支持的属性 按钮背景
             */
            const val ATTRIBUTE_BUTTON = "button"

            /**
             * 换肤支持的属性 按钮背景染色
             */
            const val ATTRIBUTE_BUTTON_TINT = "buttonTint"
        }
    }

    /**
     * 框架内支持的[ImageView]的换肤属性
     */
    interface ImageViewAttribute {
        companion object {

            /**
             * 换肤支持的属性 填充内容
             */
            const val ATTRIBUTE_SRC = "src"

            /**
             * 换肤支持的属性 染色
             */
            const val ATTRIBUTE_TINT = "tint"
        }
    }

    /**
     * 框架内支持的[ProgressBar]的换肤属性
     */
    interface ProgressBarAttribute {
        companion object {

            /**
             * Drawable used for the progress mode.
             */
            const val ATTRIBUTE_PROGRESS_DRAWABLE = "progressDrawable"

            /**
             * Tint to apply to the progress indicator.
             */
            const val ATTRIBUTE_PROGRESS_TINT = "progressTint"

            /**
             * Drawable used for the indeterminate mode.
             */
            const val ATTRIBUTE_INDETERMINATE_DRAWABLE = "indeterminateDrawable"

            /**
             * Tint to apply to the indeterminate progress indicator.
             */
            const val ATTRIBUTE_INDETERMINATE_TINT = "indeterminateTint"

            /**
             * Tint to apply to the progress indicator background.
             */
            const val ATTRIBUTE_PROGRESS_BACKGROUND_TINT = "progressBackgroundTint"

            /**
             * Tint to apply to the secondary progress indicator.
             */
            const val ATTRIBUTE_SECONDARY_PROGRESS_TINT = "secondaryProgressTint"
        }
    }

    interface AbsListViewAttribute {
        companion object {
            /**
             * Indicates that this list will always be drawn on top of solid, single-color opaque background.
             */
            const val ATTRIBUTE_CACHE_COLOR_HINT = "cacheColorHint"

            /**
             * Drawable used to indicate the currently selected item in the list
             */
            const val ATTRIBUTE_LIST_SELECTOR = "listSelector"
        }
    }

    /**
     * 框架内支持的[ListView]的换肤属性
     */
    interface ListViewAttribute {
        companion object {

            /**
             * 换肤支持的属性 ListView分割线
             */
            const val ATTRIBUTE_LIST_VIEW_DIVIDER = "divider"
        }
    }

    interface ExpandableListViewAttribute {
        companion object {
            /**
             * Indicator shown beside the child View.
             */
            const val ATTRIBUTE_CHILD_INDICATOR = "childIndicator"

            /**
             * Drawable or color that is used as a divider for children.
             */
            const val ATTRIBUTE_CHILD_DIVIDER = "childDivider"

            /**
             * Indicator shown beside the group View.
             */
            const val ATTRIBUTE_GROUP_INDICATOR = "groupIndicator"
        }
    }
}
