package com.wangjing.qfwebview;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import java.util.HashMap;

public class InitX5Utils {

    public static void initX5(Context mContext, QbSdk.PreInitCallback preInitCallback) {
        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
//        QbSdk.reset();
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(mContext, preInitCallback);

        webviewSetPath(mContext);

    }

    public static void webviewSetPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(context);

            if (!context.getPackageName().equals(processName)) {
                //X5海外版 没有这个方法设置
//                WebView.setDataDirectorySuffix(getString(processName,"qianfan"));
            }
        }
    }

    private static String getProcessName(Context context) {
        if (null == context) {
            return null;
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

    private static String getString(String value, String defValue) {
        if (value == null || value.trim().length() == 0) {
            return defValue;
        }
        return value;
    }
}
