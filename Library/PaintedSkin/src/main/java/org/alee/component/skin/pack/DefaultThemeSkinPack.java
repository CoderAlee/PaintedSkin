package org.alee.component.skin.pack;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/13
 * @description: xxxx
 *
 *********************************************************/
final class DefaultThemeSkinPack extends BaseThemeSkinPack {
    /**
     * 默认皮肤包名称
     */
    private static final String DEFAULT_NAME = "OriginalSkin";

    DefaultThemeSkinPack() {
        super(SkinPackType.TYPE_DEFAULT, DEFAULT_NAME, null);
    }
}
