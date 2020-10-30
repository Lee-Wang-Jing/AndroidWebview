package com.wangjing.androidwebview;

import android.view.View;
import android.webkit.JsResult;
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

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webviewCallBack != null) {
            webviewCallBack.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        if (webviewCallBack != null) {
            webviewCallBack.onJsAlert(view, url, message, result);
        }
        return true;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        if (webviewCallBack != null) {
            webviewCallBack.onShowCustomView(view, callback);
        }
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        if (webviewCallBack != null) {
            webviewCallBack.onHideCustomView();
        }
    }
}
