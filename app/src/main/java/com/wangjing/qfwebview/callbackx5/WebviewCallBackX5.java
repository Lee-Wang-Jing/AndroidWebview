package com.wangjing.qfwebview.callbackx5;

import android.graphics.Bitmap;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;

public abstract class WebviewCallBackX5 {
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
}
