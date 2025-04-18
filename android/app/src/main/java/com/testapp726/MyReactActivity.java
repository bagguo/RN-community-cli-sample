package com.testapp726;

import static com.testapp726.MainApplication.MY_MODULE_NAME;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * @author Darren.eth
 * @title 将全屏 React Native 应用程序作为 Activity 集成到现有 Android 应用程序中
 */
public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler {

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, MyReactActivity.class));
    }

    private static final String TAG = "MyReactActivity";
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // 任写一个值


    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReactRootView = new ReactRootView(this);
        setReactInstanceManager();

        // 注意这里的MyReactNativeApp 必须对应"index.js"中的
        // "AppRegistry.registerComponent()"的第一个参数
        // 在mReactRootView这个对象之中启动 React Native 应用，并将它设为界面的主视图
        mReactRootView.startReactApplication(mReactInstanceManager, MY_MODULE_NAME, null);

        setContentView(mReactRootView);
        requestOverlayPermission();
    }

    private void setReactInstanceManager() {
        // 获取ReactInstanceManager的2种方式：1.从ReactNativeHost获取 2.像ReactNativeHost源码中一样自行build
        mReactInstanceManager = MainApplication.instance.getReactNativeHost().getReactInstanceManager();

        // List<ReactPackage> packages = new PackageList(getApplication()).getPackages();
        // 有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
        // packages.add(new MyReactNativePackage());
        // 同时需要手动把他们添加到`settings.gradle`和 `app/build.gradle`配置文件中。

//                mReactInstanceManager = ReactInstanceManager.builder()
//                .setJavaScriptExecutorFactory(new HermesExecutorFactory()) // 解决java.lang.NoClassDefFoundError: com.facebook.react.jscexecutor.JSCExecutor报错
//                .setApplication(getApplication())
//                .setCurrentActivity(this)
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModulePath("index")
//                .addPackages(packages)
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                }
            }
        }
        mReactInstanceManager.onActivityResult(this, requestCode, resultCode, data);
    }

    private void requestOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }
}
