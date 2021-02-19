package org.alee.component.compiler;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/7/21
 * @description: xxxx
 *
 *********************************************************/
public final class Constant {
    /**
     * 用于填写在 annotationProcessorOptions中的key
     */
    public static final String MODULE_NAME_KEY = "COMPONENT_MODULE_NAME";

    public static final String PREFIX_OF_LOGGER =   "::Compiler ";

    public static final String BASE_PACKAGE_NAME = "org.alee.component";

    public static final String PATH_DELIMITER = "/";

    public static final String CLASS_NAME_DELIMITER = "$$";

    public static final String NAME_OF_PARAM_FIELD_MAP = "map";

    public static final String NO_MODULE_NAME_TIPS = "These no module name, at 'build.gradle', like :\n" +
            "android {\n" +
            "    defaultConfig {\n" +
            "        ...\n" +
            "        javaCompileOptions {\n" +
            "            annotationProcessorOptions {\n" +
            "                arguments = [" + MODULE_NAME_KEY + ": project.getName()]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n";

    public static final class Android_OS {
        public static final String PARCELABLE = "android.os.Parcelable";
        public static final String SERIALIZABLE = "java.io.Serializable";
    }

    public static final class Java_Type {
        private static final String LANG = "java.lang";
        public static final String BYTE = LANG + ".Byte";
        public static final String SHORT = LANG + ".Short";
        public static final String INTEGER = LANG + ".Integer";
        public static final String LONG = LANG + ".Long";
        public static final String FLOAT = LANG + ".Float";
        public static final String DOUBEL = LANG + ".Double";
        public static final String BOOLEAN = LANG + ".Boolean";
        public static final String CHAR = LANG + ".Character";
        public static final String STRING = LANG + ".String";
    }
}
