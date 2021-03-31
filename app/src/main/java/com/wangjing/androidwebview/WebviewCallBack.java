package com.wangjing.androidwebview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

public abstract class WebviewCallBack {
    /**
     * 开始加载的时候调用
     *
     * @param view    WebView
     * @param url     url
     * @param favicon Bitmap
     */
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    }

    /**
     * 当页面加载完成的时候，此方法不靠谱，如果页面有跳转会回调多次
     *
     * @param view WebView
     * @param url  url
     */
    public void onPageFinished(WebView view, String url) {
    }

    /**
     * WebView资源加载
     *
     * @param view WebView
     * @param url  url
     */
    public void onLoadResource(WebView view, String url) {
    }

    /**
     * webview加载进度
     *
     * @param view        WebView
     * @param newProgress newProgress
     */
    public void onProgressChanged(WebView view, int newProgress) {
    }

    /**
     * webview onReceivedTitle
     *
     * @param view  WebView
     * @param title title
     */
    public void onReceivedTitle(WebView view, String title) {

    }

    /**
     * webview onJsAlert
     *
     * @param view
     * @param url
     * @param message
     * @param result
     */
    public void onJsAlert(WebView view, String url, String message, final JsResult result) {
    }

    /**
     * webview onReceivedSslError
     *
     * @param view
     * @param handler
     * @param error
     */
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
    }

    /**
     * webview onShowCustomView
     *
     * @param view     View
     * @param callback WebChromeClient.CustomViewCallback
     */
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
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
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
    }
}
