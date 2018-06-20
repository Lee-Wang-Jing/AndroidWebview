package com.wangjing.androidwebview;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class CallBackUtil {

    /**
     * 执行js代码
     *
     * @param webView  webView
     * @param js       js
     * @param callback callback
     */
    public static void loadJavaScript(WebView webView, String js, ValueCallback<String> callback) {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(js, callback);
        } else {
            webView.loadUrl(js);
        }
    }
}
