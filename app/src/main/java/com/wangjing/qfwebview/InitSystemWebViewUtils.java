package com.wangjing.qfwebview;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

public class InitSystemWebViewUtils {
    public static void webviewSetPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(context);

            if (!context.getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(getString(processName,"qianfan"));
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
