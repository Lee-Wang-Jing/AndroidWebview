package com.wangjing.androidwebview;

import android.graphics.Bitmap;
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
     * webview shouldInterceptRequest
     *
     * @param view WebView
     * @param url  url
     */
    public void shouldInterceptRequest(WebView view, String url) {
    }

    /**
     * webview shouldInterceptRequest
     *
     * @param view    WebView
     * @param request WebResourceRequest
     */
    public void shouldInterceptRequest(WebView view, WebResourceRequest request) {
    }
}
