package com.testapp726;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactRootView;

/**
 * @author Darren.eth
 * @title
 */
public class BaseReactActivity extends AppCompatActivity {
    public ReactRootView createReactRootView(Context context,
                                             String moduleName,
                                             Bundle initialProps) {

        ReactRootView reactRootView = new ReactRootView(context);
        reactRootView.startReactApplication(
                ReactManager.getInstance().getReactInstanceManager(),
                moduleName,
                initialProps);
        return reactRootView;
    }

}
