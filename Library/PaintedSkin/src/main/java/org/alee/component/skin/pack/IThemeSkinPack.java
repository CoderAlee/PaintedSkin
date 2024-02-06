package org.alee.component.skin.pack;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/10
 * @description: 主题皮肤包
 *
 *********************************************************/
public interface IThemeSkinPack extends ISkinResourcesProvider {
    /**
     * 获取皮肤包 名称
     *
     * @return 一般是用皮肤包绝对路径
     */
    String getName();

    /**
     * 判断 当前主题皮肤包是否可用
     *
     * @return true: 皮肤包有效且可以使用；false ：皮肤包无效且无法使用
     */
    boolean isAvailable();

    /**
     * 获取皮肤包类型
     *
     * @return {@link SkinPackType#TYPE_DEFAULT}{@link SkinPackType#TYPE_STANDARD}{@link SkinPackType#TYPE_CUSTOMIZE}
     */
    @SkinPackType
    int getSkinPackType();

    /**
     * 获取当前状态
     *
     * @return {@link Status#NEW}\{@link Status#RUNNABLE}|{@link Status#DESTROYED}
     */
    @Status
    int getCurrentStatus();


    @IntDef({Status.NEW, Status.RUNNABLE, Status.DESTROYED})
    @Retention(RetentionPolicy.SOURCE)
    @interface Status {
        /**
         * 新建状态
         */
        int NEW = 0x1000;
        /**
         * 就绪状态
         */
        int RUNNABLE = 0x1001;
        /**
         * 销毁状态
         */
        int DESTROYED = 0x1002;
    }

    @IntDef({SkinPackType.TYPE_DEFAULT, SkinPackType.TYPE_STANDARD, SkinPackType.TYPE_CUSTOMIZE, SkinPackType.TYPE_PLUGIN})
    @Retention(RetentionPolicy.SOURCE)
    @interface SkinPackType {
        /**
         * 默认皮肤包
         */
        int TYPE_DEFAULT = 0x1000;
        /**
         * 标准皮肤包 .skin
         */
        int TYPE_STANDARD = 0x1001;
        /**
         * 自定义皮肤包 由外部开发者提供
         */
        int TYPE_CUSTOMIZE = 0x1002;

        /**
         * 插件皮肤包
         */
        int TYPE_PLUGIN = 0x1003;
    }
}
