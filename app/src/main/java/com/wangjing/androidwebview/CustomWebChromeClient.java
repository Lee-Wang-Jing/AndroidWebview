package com.wangjing.androidwebview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient {

    private WebviewCallBack webviewCallBack;

    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (webviewCallBack != null) {
            webviewCallBack.onProgressChanged(view, newProgress);
        }
    }
}
