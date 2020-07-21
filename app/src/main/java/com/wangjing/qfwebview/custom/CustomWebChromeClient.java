package com.wangjing.qfwebview.custom;

import android.net.Uri;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.wangjing.qfwebview.callback.OnShowFileChooser;
import com.wangjing.qfwebview.callback.WebviewCallBack;


public class CustomWebChromeClient extends WebChromeClient {

    private WebviewCallBack webviewCallBack;
    private OnShowFileChooser onShowFileChooser;

    public CustomWebChromeClient(WebviewCallBack webviewCallBack, OnShowFileChooser onShowFileChooser) {
        this.webviewCallBack = webviewCallBack;
        this.onShowFileChooser = onShowFileChooser;
    }

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

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (onShowFileChooser != null) {
            return onShowFileChooser.onShowFileChooser(filePathCallback, fileChooserParams);
        } else {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }
}
