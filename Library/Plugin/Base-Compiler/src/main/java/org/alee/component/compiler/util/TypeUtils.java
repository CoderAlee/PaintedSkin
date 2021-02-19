package org.alee.component.compiler.util;

import org.alee.component.compiler.Constant;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public class TypeUtils {
    private Types mTypes;
    private TypeMirror mParcelableType;
    private TypeMirror mSerializableType;

    public TypeUtils(Types types, Elements elements) {
        this.mTypes = types;
        mParcelableType = elements.getTypeElement(Constant.Android_OS.PARCELABLE).asType();
        mSerializableType = elements.getTypeElement(Constant.Android_OS.SERIALIZABLE).asType();
    }

    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        switch (typeMirror.toString()) {
            case Constant.Java_Type.BYTE:
                return TypeKind.BYTE.ordinal();
            case Constant.Java_Type.SHORT:
                return TypeKind.SHORT.ordinal();
            case Constant.Java_Type.INTEGER:
                return TypeKind.INT.ordinal();
            case Constant.Java_Type.LONG:
                return TypeKind.LONG.ordinal();
            case Constant.Java_Type.FLOAT:
                return TypeKind.FLOAT.ordinal();
            case Constant.Java_Type.DOUBEL:
                return TypeKind.DOUBLE.ordinal();
            case Constant.Java_Type.BOOLEAN:
                return TypeKind.BOOLEAN.ordinal();
            case Constant.Java_Type.CHAR:
                return TypeKind.CHAR.ordinal();
            case Constant.Java_Type.STRING:
                return TypeKind.STRING.ordinal();
            default:
                if (mTypes.isSubtype(typeMirror, mParcelableType)) {
                    return TypeKind.PARCELABLE.ordinal();
                } else if (mTypes.isSubtype(typeMirror, mSerializableType)) {
                    return TypeKind.SERIALIZABLE.ordinal();
                } else {
                    return TypeKind.OBJECT.ordinal();
                }
        }
    }

    public TypeMirror generateType(String fullName) {
        if (StringUtils.isEmpty(fullName)) {
            return null;
        }
        return UtilFactory.getInstance().getElements().getTypeElement(fullName).asType();
    }

}
