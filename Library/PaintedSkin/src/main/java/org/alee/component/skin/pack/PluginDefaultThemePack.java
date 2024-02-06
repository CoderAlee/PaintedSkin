package org.alee.component.skin.pack;

/**
 * 插件默认主题包,不同插件主题包用插件包名区分
 */
public final class PluginDefaultThemePack extends BaseThemeSkinPack {
    /**
     * 默认皮肤包名称
     */
    private static final String DEFAULT_NAME = "OriginalPluginSkin";

    /**
     * 插件主题包包名
     */
    private String mPackageName;

    PluginDefaultThemePack(String packageName) {
        super(SkinPackType.TYPE_DEFAULT, DEFAULT_NAME, null);
        mPackageName = packageName;
    }

    public String getPackageName() {
        return mPackageName;
    }
}