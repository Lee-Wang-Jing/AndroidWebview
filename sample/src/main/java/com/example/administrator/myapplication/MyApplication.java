package com.example.administrator.myapplication;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.wangjing.qfwebview.InitX5Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitX5Utils.initX5(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.e("initX5","onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("initX5","onViewInitFinished");
            }
        });
    }
}
