package org.alee.component.skin.service;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public interface IThemeSkinOption {

    LinkedHashSet<String> getStandardSkinPackPath();

    /**
     * 插件包名和皮肤包列表的映射结果
     * @return k-v, k:插件包名,v:插件的皮肤包列表
     */
    @Nullable
    default Map<String, List<String>> getPluginPackagesPackPath(){
        return null;
    }
}
