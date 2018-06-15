package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wangjing.androidwebview.CustomWebview;
import com.wangjing.androidwebview.OnScrollChangedCallBack;

public class MainActivity extends AppCompatActivity {

    private CustomWebview webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = findViewById(R.id.webview);

        webview.addJSInterface(new JsCallJava(), "test")
                .addJSInterface(new JsCallJava(), "test1")
                .setOnScrollChangedCallBack(new OnScrollChangedCallBack() {
                    @Override
                    public void onScrollChanged() {

                    }
                })
                .build();

    }
}
