package com.testapp726;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_extend_react_activity).setOnClickListener(v ->
                ExtendReactActivity.launch(this)
        );

        findViewById(R.id.btn_extend_react_activity2).setOnClickListener(v ->
                ExtendReactActivity2.launch(this)
        );

        findViewById(R.id.btn_my_react_activity).setOnClickListener(v ->
                MyReactActivity.launch(this)
        );

        findViewById(R.id.btn_rn_fragment);
    }
}