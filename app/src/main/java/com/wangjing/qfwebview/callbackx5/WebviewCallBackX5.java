package com.wangjing.qfwebview.callbackx5;

import android.graphics.Bitmap;
import android.view.View;

import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.PermissionRequest;
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
     * @param url url
     */
    public void onPageFinished(String url) {
    }

    /**
     * WebView资源加载
     *
     * @param url url
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
    public void onJsAlert(String url, String message, JsResult result) {
    }

    /**
     * webview onReceivedSslError
     *
     * @param handler SslErrorHandler
     * @param error   SslError
     */
    public void onReceivedSslError(SslErrorHandler handler, SslError error) {
    }

    /**
     * webview onShowCustomView
     *
     * @param view               View
     * @param customViewCallback IX5WebChromeClient.CustomViewCallback
     */
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
    }

    /**
     * webview onHideCustomView
     */
    public void onHideCustomView() {
    }

    /**
     * webview onPermissionRequest
     */
    public void onPermissionRequest(PermissionRequest request) {
    }

    /**
     * webview onPermissionRequestCanceled
     */
    public void onPermissionRequestCanceled(PermissionRequest request) {
    }

    /**
     * webview onGeolocationPermissionsShowPrompt
     */
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissionsCallback callback) {
    }
}
