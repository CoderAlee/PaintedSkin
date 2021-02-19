package org.alee.component.skin.plugin.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import org.alee.component.compiler.BaseProcessor;
import org.alee.component.compiler.util.CollectionUtils;
import org.alee.component.compiler.util.UtilFactory;
import org.alee.component.compiler.util.WriteJavaUtil;
import org.alee.component.skin.plugin.annotation.Skin;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Nonnull;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/19
 * @description: xxxx
 *
 *********************************************************/
@AutoService(Processor.class)
@SupportedAnnotationTypes({"org.alee.component.skin.plugin.annotation.Skin"})
public final class SkinOptionFactoryProcessor extends BaseProcessor {
    private static final String PROJECT_NAME = "SkinAutoPlugin";
    private static final String CLASS_NAME_I_OPTION_FACTORY = "org.alee.component.skin.service.IOptionFactory";
    private static final String CLASS_NAME_SKIN_OPTION_FACTORY_LOADER = "org.alee.component.skin.plugin.ISkinOptionFactoryLoader";
    private static final String OUTPUT_FILE_PATH = "org.alee.component.skin.plugin";
    private static final String OUTPUT_FILE_NAME = "SkinOptionFactoryLoader";
    private static final String METHOD_NAME = "load";
    private Map<String, Element> mAnnotatedClassMap;
    private TypeMirror mLoaderType;
    private TypeMirror mReturnType;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mAnnotatedClassMap = new TreeMap<>();
        UtilFactory.getInstance().getLogUtil().setTag(PROJECT_NAME);
        UtilFactory.getInstance().getLogUtil().info("______SkinOptionFactoryProcessor is init______");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mAnnotatedClassMap.clear();
        if (CollectionUtils.isEmpty(set)) {
            return false;
        }
        mAnnotatedClassMap.putAll(screenElement(roundEnvironment.getElementsAnnotatedWith(Skin.class), ElementKind.CLASS));
        if (mAnnotatedClassMap.isEmpty()) {
            UtilFactory.getInstance().getLogUtil().error("Please add [ @" + Skin.class.getName() + " ] annotation to the implementation class of [ " + CLASS_NAME_I_OPTION_FACTORY + " ]");
            return false;
        }
        if (mAnnotatedClassMap.size() > 1) {
            UtilFactory.getInstance().getLogUtil().error("Only one [ @" + Skin.class.getName() + " ] allowed");
            return false;
        }
        try {
            this.parseAnnotated();
        } catch (Throwable e) {
            e.printStackTrace();
            UtilFactory.getInstance().getLogUtil().error(e);
        }
        return 0 < mAnnotatedClassMap.size();
    }

    private void parseAnnotated() {
        initType();
        for (Element element : mAnnotatedClassMap.values()) {
            TypeMirror marked = element.asType();
            if (!UtilFactory.getInstance().getTypes().isSubtype(marked, mReturnType)) {
                UtilFactory.getInstance().getLogUtil().error("[@ " + Skin.class.getName() + " ] Annotations can only be added to the implementation class of [ " + CLASS_NAME_I_OPTION_FACTORY + " ]");
                return;
            }
            writeImplementer(element);
            return;
        }
    }

    private void initType() {
        mLoaderType = UtilFactory.getInstance().getTypeUtils().generateType(CLASS_NAME_SKIN_OPTION_FACTORY_LOADER);
        mReturnType = UtilFactory.getInstance().getTypeUtils().generateType(CLASS_NAME_I_OPTION_FACTORY);
    }

    private void writeImplementer(@Nonnull Element element) {
        TypeSpec.Builder builder = generateClass(OUTPUT_FILE_NAME);
        builder.addMethod(generateMethod(element));
        WriteJavaUtil.writeFactory(OUTPUT_FILE_PATH, OUTPUT_FILE_NAME, builder.build());
    }

    private TypeSpec.Builder generateClass(String className) {
        return TypeSpec.classBuilder(className)
                .addSuperinterface(ClassName.get(mLoaderType))
                .addModifiers(PUBLIC, FINAL);
    }

    private MethodSpec generateMethod(@Nonnull Element element) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addAnnotation(Override.class)
                .addModifiers(PUBLIC)
                .returns(ClassName.get(mReturnType))
                .addStatement("return new $T()", element.asType());
        return builder.build();
    }
}
