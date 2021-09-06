package org.alee.component.skin.pack;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/10
 * @description: 资源类型
 *
 *********************************************************/
public final class ResourcesType {
    /**
     * drawable资源类型
     */
    public static final String DRAWABLE = "drawable";
    /**
     * Mipmap 资源类型
     */
    public static final String MIPMAP = "mipmap";

    /**
     * color 资源类型
     */
    public static final String COLOR = "color";
    /**
     * ColorStateList
     */
    public static final String COLOR_STATE_LIST = "colorStateList";

    /**
     * string 资源类型
     */
    public static final String STRING = "string";

    /**
     * dimen 资源类型
     */
    public static final String DIMEN = "dimen";

    @StringDef({DRAWABLE,MIPMAP, COLOR, COLOR_STATE_LIST, STRING, DIMEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Constraint {

    }

}
