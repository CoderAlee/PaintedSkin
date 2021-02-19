package org.alee.component.compiler.util;


import org.alee.component.compiler.Constant;

import java.util.Map;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public class ConfigureUtil {
    /**
     * 选项
     */
    private Map<String, String> mOptions;

    public ConfigureUtil(Map<String, String> options) {
        mOptions = options;
    }

    public String checkModuleName() {
        String moduleName = "";
        if (MapUtils.isNotEmpty(mOptions)) {
            moduleName = mOptions.get(Constant.MODULE_NAME_KEY);
        }

        if (StringUtils.isNotEmpty(moduleName)) {
            moduleName = moduleName.replaceAll("[^0-9a-zA-Z_]+", "");
            UtilFactory.getInstance().getLogUtil().info("The user has configuration the module name, it was [" + moduleName + "]");
            return moduleName;
        } else {
            UtilFactory.getInstance().getLogUtil().error(Constant.NO_MODULE_NAME_TIPS);
            throw new RuntimeException(Constant.PREFIX_OF_LOGGER + " >>> No module name, for more information, look at gradle log");
        }
    }
}
