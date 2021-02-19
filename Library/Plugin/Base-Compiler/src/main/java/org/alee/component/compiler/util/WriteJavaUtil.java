package org.alee.component.compiler.util;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public class WriteJavaUtil {

    public static void writeFactory(String packageName, String className, TypeSpec typeSpec) {
        try {
            JavaFile.builder(packageName, typeSpec)
                    .build().writeTo(UtilFactory.getInstance().getWriteDirectory());
            UtilFactory.getInstance().getLogUtil().info("Generating " + className + " Class Successfully");
        } catch (IOException e) {
            UtilFactory.getInstance().getLogUtil().error(e);
        }
    }
}
