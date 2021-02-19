package org.alee.component.compiler;

import org.alee.component.compiler.util.CollectionUtils;
import org.alee.component.compiler.util.ConfigureUtil;
import org.alee.component.compiler.util.UtilFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: 基础处理类
 *
 *********************************************************/
public abstract class BaseProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        UtilFactory.getInstance().init(processingEnvironment);
    }

    /**
     * 查找到指定的元素
     *
     * @param elements
     * @param types
     * @return
     */
    protected Map<String, Element> screenElement(Set<? extends Element> elements, ElementKind... types) {
        Map<String, Element> elementMap = new TreeMap<>();
        if (CollectionUtils.isEmpty(elements)) {
            return elementMap;
        }
        for (Element item : elements) {
            String fullName = "";
            if (item instanceof TypeElement) {
                TypeElement typeElement = (TypeElement) item;
                fullName = typeElement.getQualifiedName().toString();
            } else if (item instanceof VariableElement) {
                VariableElement variableElement = (VariableElement) item;
                fullName = variableElement.asType().toString();
            }
            if (elementMap.containsKey(fullName)) {
                continue;
            }
            List<ElementKind> list = Arrays.asList(types);
            if (list.contains(item.getKind())) {
                elementMap.put(fullName, item);
            }
        }
        return elementMap;
    }
}
