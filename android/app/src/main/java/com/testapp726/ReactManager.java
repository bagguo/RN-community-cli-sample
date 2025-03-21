package com.testapp726;


import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

/**
 * @author Darren.eth
 * @title
 */
public class ReactManager {
    private static ReactManager instance;

    public static ReactManager getInstance() {
        if (instance == null) {
            instance = new ReactManager();
        }
        return instance;
    }

    private final ReactInstanceManager reactInstanceManager;

    // 私有构造函数，防止外部new
    private ReactManager() {
        reactInstanceManager = ReactInstanceManager.builder()
                .setApplication(MainApplication.instance) // 替换为你的 Application
                .setCurrentActivity(null)// FIXME: 2025/3/21 no null activity
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
    }

    public ReactInstanceManager getReactInstanceManager() {
        return reactInstanceManager;
    }
}