package com.testapp726;

import static com.testapp726.MainApplication.MY_MODULE_NAME;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.ReactRootView;

/**
 * @author Darren.eth
 * @title
 */
public class MyReactActivity2 extends BaseReactActivity {

    public static void launch(Context context) {
        context.startActivity(
                new Intent(context, MyReactActivity2.class)
        );
    }

    private ReactRootView reactRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建 ReactRootView
        reactRootView = createReactRootView(this, MY_MODULE_NAME, null);
        setContentView(reactRootView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ReactManager.getInstance().getReactInstanceManager().onHostResume(this, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ReactManager.getInstance().getReactInstanceManager().onHostPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reactRootView.unmountReactApplication();
        ReactManager.getInstance().getReactInstanceManager().onHostDestroy(this);
    }


}
