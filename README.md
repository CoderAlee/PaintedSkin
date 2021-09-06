# PaintedSkin
```tex
    一款解决Android App 换肤框架，极低的侵入性与学习成本。
```
[![Gitter](https://badges.gitter.im/Android-PaintedSkin/community.svg)](https://gitter.im/Android-PaintedSkin/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
![build](https://img.shields.io/badge/build-passing-green.svg)
[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)

---

#### 最新版本

|模块|说明|版本|
|---|---|---|
|PaintedSkin|换肤核心包|[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)|
|StandardPlugin|减少代码侵入的插件包|[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)|
|AutoPlugin|全自动插件包|[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)|
|ConstraintLayoutCompat|ConstraintLayout换肤兼容包|[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)|
|TypefacePlugin|替换字体插件|[![](https://jitpack.io/v/CoderAlee/PaintedSkin.svg)](https://jitpack.io/#CoderAlee/PaintedSkin)|

---

#### 版本履历

##### V3.0.0

- [x]   支持AndroidX.

##### V3.0.1

- [x]   修复无法初始化默认皮肤包问题.

##### V3.0.2

- [x]    剥离对于Reflex库的依赖.

##### V3.0.3

- [x]   减少无意义日志输出、优化对于ColorStateList的加载.

##### V3.1.0

- [x]  剥离依赖，上传至公网Maven

##### V3.1.1

- [x]  优化动态添加换肤View设置资源时机

##### V3.1.2

- [x]  修复由于`onThemeSkinSwitch`函数内部逻辑错误导致换肤失败问题

##### V3.1.5

- [x]  JCenter移植至Jitpack

##### V3.1.6

- [x]  移除冗余Log

##### V3.1.7

- [x]  修复由于缓存Drawable与ColorStateList导致得后加载资源状态异常问题

##### V3.1.8

- [x]  修复由于Android Q及更高版本中限制非SDK接口访问导致的初始化崩溃问题
- [x]  适配Android Q及更高版本文件目录分区访问

##### V3.2.0

- [x]  `ISwitchThemeSkinObserver`增加`onThemeSkinSwitchRunOnUiThread` 接口

##### V3.3.2

- [x]  支持`tint`属性换肤
---

## 框架实现原理

---

## 功能介绍

1. [**支持XML全部View换肤**](#运行配置)
2. [**支持XML指定View换肤**](#运行配置)
3. [**支持代码创建View换肤**](#动态创建View换肤)
4. [**支持自定义View、三方库提供的View、自定义属性换肤**](#自定义View、三方库View换肤)
5. **支持绝大部分基础View换肤**
6. **支持差异化换肤(适用于部分View节日换肤)**
7. [**支持全局动态替换字体**](#TypefacePlugin 使用)
8. [**支持通过拦截器拦截View创建过程**](#拦截View创建过程)
9. **支持Androidx、support**
10. **支持定制扩展**
11.  **不会与其他依赖LayoutInflater.Factory 的库冲突**

---

## 使用

#### 添加依赖

1. 在工程的`build.gradle`文件中添加:

``` gradle

buildscript {
    repositories {
		maven { url "https://jitpack.io" } // 必须添加
    }
    dependencies {
        ...
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10' // 如果不使用AutoPlugin可以不添加
    }
    allprojects {
		maven { url "https://jitpack.io" } // 必须添加
    }
}

```

2. 如需使用`AutoPlugin`,在项目`app`的`build.gradle`文件中添加:

``` gradle

apply plugin: 'android-aspectjx' 
android {
	...
}

```
3. 在项目`app`的`build.gradle`文件中添加::

``` gradle

dependencies {
	// 依赖的反射库
 	implementation 'com.github.CoderAlee:Reflex:1.2.0'
 	// 核心库
    implementation 'com.github.CoderAlee.PaintedSkin:PaintedSkin:TAG'
	implementation 'com.github.CoderAlee.PaintedSkin:StandardPlugin:TAG'
	// StandardPlugin 与 AutoPlugin 只需添加一个
	annotationProcessor 'com.github.CoderAlee.PaintedSkin:AopPlugin:TAG'
	implementation 'com.github.CoderAlee.PaintedSkin:AopPlugin:TAG'
	//如果项目中的ConstraintLayout需要换肤则引入
	implementation 'com.github.CoderAlee.PaintedSkin:ConstraintLayoutCompat:TAG'
	// 需要替换字体库时引入
	implementation 'com.github.CoderAlee.PaintedSkin:TypefacePlugin:TAG'
    ...
}
```



#### 运行配置

`PaintedSkin`支持三种换肤模式：

> `SkinMode.REPLACE_ALL`  所有View都参与换肤，添加了**skin:enable="false"** 标签的View 将不参与换肤;
>
> `SkinMode.REPLACE_MARKED` 只有添加了**skin:enable="true"**标签的View才参与换肤；
>
> `SkinMode.DO_NOT_REPLACE` 任何View都不参与换肤

API：

```java
public final class App extends Application {
    static {
        Config.getInstance().setSkinMode(Config.SkinMode.REPLACE_ALL);
    }
}
```

`PaintedSkin` 支持调试模式与严格模式：

> 调试模式下将输出框架内的一些关键节点Log以及换肤任务执行耗时时长；
>
> 严格模式下如果框架内出现错误将直接抛出异常；

API：

```Java
public final class App extends Application {
    static {
         Config.getInstance().setEnableDebugMode(false);
         Config.getInstance().setEnableStrictMode(false);
    }
}
```

####  插件使用

​	 `StandardPlugin` 使用：

```Java
public final class App extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        WindowManager.getInstance().init(this,new OptionFactory());
    }
}
```

```Java
final class OptionFactory implements IOptionFactory {
    @Override
    public int defaultTheme() {
        return 0;
    }

    @Override
    public IThemeSkinOption requireOption(int theme) {
        switch (theme) {
            case 1:
                return new NightOption();
            default:
                return null;
        }
    }
}
```

  `AutoPlugin` 不再需要开发人员调用初始化代码，只需要在实现了`IOptionFactory` 接口的实现类上添加注解**@Skin** 即可：

```java
@Skin
public final class OptionFactory implements IOptionFactory {
    @Override
    public int defaultTheme() {
        return 0;
    }

    @Override
    public IThemeSkinOption requireOption(int theme) {
        switch (theme) {
            case 1:
                return new NightOption();
            default:
                return null;
        }
    }
}
```



#### 主题配置

```java
class NightOption implements IThemeSkinOption {

    @Override
    public LinkedHashSet<String> getStandardSkinPackPath() {
        LinkedHashSet<String> pathSet = new LinkedHashSet<>();
        pathSet.add("/sdcard/night.skin");
        return pathSet;
    }
}
```



#### 换肤

```java
 ThemeSkinService.getInstance().switchThemeSkin(int theme);
```



#### 皮肤包构建

1. 新建Android application工程

2. 皮肤工程包名不能和宿主应用包名相同

3. 将需要换肤的资源放置于res对应目录下

   > 例如 Button 文字颜色
   >
   > APK 中res/values/colors.xml
   >
   > ```xml
   > <color name="textColor">#FFFFFFFF</color>
   > ```
   >
   > 皮肤包中 res/values/colors.xml
   >
   > ```xml
   > <color name="textColor">#FF000000</color>
   > ```
   >
   > 例如 Button 背景图片
   >
   > APK 中 res/mipmap/bg_button.png
   >
   > 皮肤包中 res/mipmap/bg_button.png

4. 在皮肤包工程的`build.gradle`文件中添加：

   ```gradle
     applicationVariants.all { variant ->
           variant.outputs.all { output ->
               outputFileName = "xxx.skin"
           }
       }
   ```



#### 动态创建View换肤

 核心接口**WindowManager.getInstance().getWindowProxy(getContext()).addEnabledThemeSkinView(View,SkinElement);**

```java
  TextView textView = new TextView(getContext());
        textView.setTextColor(getResources().getColor(R.color.textColor));
        textView.setText("动态创建View参与换肤");
        WindowManager.getInstance().getWindowProxy(getContext()).addEnabledThemeSkinView(textView, new SkinElement("textColor", R.color.textColor));
        layout.addView(textView);
```

---


## 进阶用法



#### 拦截View创建过程

```java
        ThemeSkinService.getInstance().getCreateViewInterceptor().add(new LayoutInflater.Factory2() {
            @Nullable
            @Override
            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                return onCreateView(name, context, attrs);
            }

            @Nullable
            @Override
            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                if (TextUtils.equals(name,"TextView")){
                    return new Button(context, attrs);
                }
                return null;
            }
        });
```

  通过拦截View的创建过程其实可以实现很多骚操作，比如上面这段代码就可以将全局的TextView替换成Button。这比在XML中一个一个修改要快捷方便的多。其中Google 就是通过这种方式将Button 替换为AppCompatButton。AppCompatDelegate也是同样的技术方案。



 ####  自定义View、三方库View换肤

当自定义View或使用的三方库View中有自定义属性需要换肤时：

1. 实现**IThemeSkinExecutorBuilder** 接口,用于解析支持换肤属性并创建对应属性的换肤执行器。可以参考框架内自带的DefaultExecutorBuilder：

   ```java
   @RestrictTo(RestrictTo.Scope.LIBRARY)
   public final class DefaultExecutorBuilder implements IThemeSkinExecutorBuilder {
       /**
        * 换肤支持的属性 背景
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_BACKGROUND = "background";
       /**
        * 换肤支持的属性 前景色
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_FOREGROUND = "foreground";
       /**
        * 换肤支持的属性 字体颜色
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_TEXT_COLOR = "textColor";
       /**
        * 换肤支持的属性 暗示字体颜色
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_TEXT_COLOR_HINT = "textColorHint";
       /**
        * 换肤支持的属性 选中时高亮背景颜色
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT = "textColorHighlight";
       /**
        * 换肤支持的属性 链接的颜色
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_TEXT_COLOR_LINK = "textColorLink";
       /**
        * 换肤支持的属性 进度条背景
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_PROGRESS_DRAWABLE = "progressDrawable";
       /**
        * 换肤支持的属性 ListView分割线
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_LIST_VIEW_DIVIDER = "divider";
       /**
        * 换肤支持的属性 填充内容
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_SRC = "src";
       /**
        * 换肤支持的属性 按钮背景
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public static final String ATTRIBUTE_BUTTON = "button";
       private static final Map<Integer, String> SUPPORT_ATTR = new HashMap<>();
   
       static {
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_background, ATTRIBUTE_BACKGROUND);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_foreground, ATTRIBUTE_FOREGROUND);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColor, ATTRIBUTE_TEXT_COLOR);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorHint, ATTRIBUTE_TEXT_COLOR_HINT);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorHighlight, ATTRIBUTE_TEXT_COLOR_HIGH_LIGHT);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_textColorLink, ATTRIBUTE_TEXT_COLOR_LINK);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_progressDrawable, ATTRIBUTE_PROGRESS_DRAWABLE);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_divider, ATTRIBUTE_LIST_VIEW_DIVIDER);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_src, ATTRIBUTE_SRC);
           SUPPORT_ATTR.put(R.styleable.BasicSupportAttr_android_button, ATTRIBUTE_BUTTON);
       }
   
       /**
        * 解析支持换肤的属性
        *
        * @param context      {@link Context}
        * @param attributeSet {@link AttributeSet}
        * @return {@link SkinElement}
        */
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       @Override
       public Set<SkinElement> parse(@NonNull Context context, @NonNull AttributeSet attributeSet) {
           TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BasicSupportAttr);
           if (null == typedArray) {
               return null;
           }
           Set<SkinElement> elementSet = new HashSet<>();
           try {
               for (Integer key : SUPPORT_ATTR.keySet()) {
                   try {
                       if (typedArray.hasValue(key)) {
                           elementSet.add(new SkinElement(SUPPORT_ATTR.get(key), typedArray.getResourceId(key, -1)));
                       }
                   } catch (Throwable ignored) {
                   }
               }
           } catch (Throwable ignored) {
           } finally {
               typedArray.recycle();
           }
           return elementSet;
       }
   
       /**
        * 需要换肤执行器
        *
        * @param view    需要换肤的View
        * @param element 需要执行的元素
        * @return {@link ISkinExecutor}
        */
       @Override
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public ISkinExecutor requireSkinExecutor(@NonNull View view, @NonNull SkinElement element) {
           return BasicViewSkinExecutorFactory.requireSkinExecutor(view, element);
       }
   
       /**
        * 是否支持属性
        *
        * @param view     View
        * @param attrName 属性名称
        * @return true: 支持
        */
       @Override
       @RestrictTo(RestrictTo.Scope.LIBRARY)
       public boolean isSupportAttr(@NonNull View view, @NonNull String attrName) {
           return SUPPORT_ATTR.containsValue(attrName);
       }
   }
   ```

   2. 继承**BaseSkinExecutor** 提供对应属性的换肤执行器：

      ```java
      public class ViewSkinExecutor<T extends View> extends BaseSkinExecutor<T> {
      
          public ViewSkinExecutor(@NonNull SkinElement fullElement) {
              super(fullElement);
          }
      
          @Override
          protected void applyColor(@NonNull T view, @NonNull ColorStateList colorStateList, @NonNull String attrName) {
              switch (attrName) {
                  case ATTRIBUTE_BACKGROUND:
                  case ATTRIBUTE_FOREGROUND:
                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                          applyDrawable(view, new ColorStateListDrawable(colorStateList), attrName);
                      } else {
                          applyColor(view, colorStateList.getDefaultColor(), attrName);
                      }
                      break;
                  default:
                      break;
              }
          }
      
          @Override
          protected void applyColor(@NonNull T view, int color, @NonNull String attrName) {
              switch (attrName) {
                  case ATTRIBUTE_BACKGROUND:
                      view.setBackgroundColor(color);
                      break;
                  case ATTRIBUTE_FOREGROUND:
                      applyDrawable(view, new ColorDrawable(color), attrName);
                      break;
                  default:
                      break;
              }
          }
      
      
          @Override
          protected void applyDrawable(@NonNull T view, @NonNull Drawable drawable, @NonNull String attrName) {
              switch (attrName) {
                  case ATTRIBUTE_BACKGROUND:
                      view.setBackground(drawable);
                      break;
                  case ATTRIBUTE_FOREGROUND:
                      view.setForeground(drawable);
                      break;
                  default:
                      break;
              }
          }
      }
      
      ```

      

3. 将自定义的ThemeSkinExecutorBuilder添加到框架中：

   ```java
    ThemeSkinService.getInstance().addThemeSkinExecutorBuilder(xxx);
   ```

---


## ConstraintLayout换肤兼容包使用



```java
public final class App extends Application {
    static {
        ConstraintLayoutCompat.init();
    }
}
```



---


## TypefacePlugin 使用

```java
public final class App extends Application {
    static {
        TypefacePlugin.init();
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
       TypefacePlugin.getInstance().setEnable(true).switchTypeface(Typeface);
    }
}
```

---

## [License Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0)

```text
Copyright [2018] [MingYu.Liu]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```