package com.wangjing.qfwebview.custom;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.wangjing.qfwebview.callback.WebviewCallBack;


public class CustomWebChromeClient extends WebChromeClient {

    private WebviewCallBack webviewCallBack;

    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (webviewCallBack != null) {
            webviewCallBack.onProgressChanged(newProgress);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webviewCallBack != null) {
            webviewCallBack.onReceivedTitle(title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        if (webviewCallBack != null) {
            webviewCallBack.onJsAlert(url, message, result);
        }
        return true;
    }
}
