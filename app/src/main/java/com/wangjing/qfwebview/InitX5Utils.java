package com.wangjing.qfwebview;

import android.content.Context;

import com.tencent.smtt.sdk.QbSdk;

public class InitX5Utils {

    public static void initX5(Context mContext) {
        QbSdk.PreInitCallback preInitCallback = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        };
        QbSdk.initX5Environment(mContext, preInitCallback);
    }
}
