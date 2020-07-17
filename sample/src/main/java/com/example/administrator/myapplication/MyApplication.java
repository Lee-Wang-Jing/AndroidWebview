package com.example.administrator.myapplication;

import android.app.Application;

import com.wangjing.qfwebview.InitX5Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitX5Utils.initX5(getApplicationContext());
    }
}
