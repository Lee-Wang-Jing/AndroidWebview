package com.wangjing.qfwebview.callback;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

public abstract class WebviewCallBack {
    /**
     * 开始加载的时候调用
     *
     * @param url     url
     * @param favicon Bitmap
     */
    public void onPageStarted(String url, Bitmap favicon) {
    }

    /**
     * 当页面加载完成的时候，此方法不靠谱，如果页面有跳转会回调多次
     *
     * @param url  url
     */
    public void onPageFinished(String url) {
    }

    /**
     * WebView资源加载
     *
     * @param url  url
     */
    public void onLoadResource(String url) {
    }

    /**
     * webview加载进度
     *
     * @param newProgress newProgress
     */
    public void onProgressChanged(int newProgress) {
    }

    /**
     * webview onReceivedTitle
     *
     * @param title title
     */
    public void onReceivedTitle(String title) {

    }

    /**
     * webview onJsAlert
     *
     * @param url
     * @param message
     * @param result
     */
    public void onJsAlert(String url, String message, final JsResult result) {
    }

    /**
     * webview onReceivedSslError
     * @param handler
     * @param error
     */
    public void onReceivedSslError(final SslErrorHandler handler, SslError error) {
    }

    /**
     * webview onShowCustomView
     *
     * @param view               View
     * @param callback WebChromeClient.CustomViewCallback
     */
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
    }

    /**
     * webview onHideCustomView
     */
    public void onHideCustomView() {
    }
}
