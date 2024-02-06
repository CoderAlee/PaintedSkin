package org.alee.component.skin.pack;

/**
 * 插件主题包, 不同插件主题包用插件包名区分
 */
public final class PluginThemeSkinPack extends BaseThemeSkinPack{

    /**
     * 插件主题包包名
     */
    private String mPackageName;

    PluginThemeSkinPack(String skinPackPath, IThemeSkinPack underpinThemeSkinPack, String packageName){
        super(SkinPackType.TYPE_PLUGIN, skinPackPath, underpinThemeSkinPack);
        mPackageName = packageName;
    }

    public String getPackageName(){
        return mPackageName;
    }

}
