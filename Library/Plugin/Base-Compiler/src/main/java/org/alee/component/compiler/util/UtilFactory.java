package org.alee.component.compiler.util;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public class UtilFactory {
    /**
     * 写入目录
     */
    private Filer mWriteDirectory;
    /**
     * 元素
     */
    private Elements mElements;
    /**
     * 类型接口
     */
    private Types mTypes;

    private TypeUtils mTypeUtils;

    private LogUtil mLogUtil;

    private UtilFactory() {

    }

    /**
     * 获取单例对象
     *
     * @return {@link UtilFactory}
     */
    public static UtilFactory getInstance() {
        return UtilFactoryHolder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param environment Processor的运行环境
     */
    public void init(ProcessingEnvironment environment) {
        if (null == environment) {
            return;
        }
        mWriteDirectory = environment.getFiler();
        mElements = environment.getElementUtils();
        mTypes = environment.getTypeUtils();
        mTypeUtils = new TypeUtils(mTypes, mElements);
        mLogUtil = new LogUtil(environment.getMessager());
    }

    public Filer getWriteDirectory() {
        return mWriteDirectory;
    }

    public Elements getElements() {
        return mElements;
    }

    public Types getTypes() {
        return mTypes;
    }

    public TypeUtils getTypeUtils() {
        return mTypeUtils;
    }

    public LogUtil getLogUtil() {
        return mLogUtil;
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class UtilFactoryHolder {
        private static UtilFactory INSTANCE = new UtilFactory();
    }
}
