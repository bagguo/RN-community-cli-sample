# React Native Community CLI生成新项目

使用社区模版生成的简易RN工程, RN ver 0.72.6

https://github.com/react-native-community/template/

```
生成新项目
npx @react-native-community/cli@latest init TestApp

生成项目后，cd TestApp，run:
npx react-native run-android
```

# 原理

## 核心组件：ReactRootView

在应用中添加一个ReactRootView。这个ReactRootView正是用来承载你的 React Native 组件的容器

我们还需要添加一些原生代码来启动 React Native 的运行时环境并让它开始渲染。首先需要在一个Activity中创建一个ReactRootView对象，
然后在这个对象之中启动 React Native 应用，并将它设为界面的主视图

## ReactInstanceManager

可以从ReactNativeHost获取

或者像ReactNativeHost源码中一样build ReactInstanceManagerBuilder

ReactNativeHost源码：

```java
ReactInstanceManagerBuilder builder =
        ReactInstanceManager.builder()
                .setApplication(mApplication)
                .setJSMainModulePath(getJSMainModuleName())
                .setUseDeveloperSupport(getUseDeveloperSupport())
                .setDevSupportManagerFactory(getDevSupportManagerFactory())
                .setDevLoadingViewManager(getDevLoadingViewManager())
                .setRequireActivity(getShouldRequireActivity())
                .setSurfaceDelegateFactory(getSurfaceDelegateFactory())
                .setLazyViewManagersEnabled(getLazyViewManagersEnabled())
                .setRedBoxHandler(getRedBoxHandler())
                .setJavaScriptExecutorFactory(getJavaScriptExecutorFactory())
                .setJSIModulesPackage(getJSIModulePackage())
                .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                .setReactPackageTurboModuleManagerDelegateBuilder(
                        getReactPackageTurboModuleManagerDelegateBuilder()); 
```

一个ReactInstanceManager可以在多个 activities 或 fragments 间共享。你将需要创建自己的ReactFragment或ReactActivity，
并拥有一个保存ReactInstanceManager的单例持有者。当你需要ReactInstanceManager（例如，将ReactInstanceManager连接到这些
Activities 或 Fragments 的生命周期）时，请使用单例提供的那个。

# use

研究是否必须用ReactRootView启动rn、多页面下ReactRootView的正确用法