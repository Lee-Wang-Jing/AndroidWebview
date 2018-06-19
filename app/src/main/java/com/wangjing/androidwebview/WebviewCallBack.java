package com.wangjing.androidwebview;

import android.graphics.Bitmap;
import android.webkit.WebView;

public abstract class WebviewCallBack {
    /**
     * 开始加载的时候调用
     */
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    }

    /**
     * 加载进度回调
     *
     * @param curProgress 当前进度值[0,100]
     */
    public void onProgress(int curProgress) {
    }

    /**
     * 当页面加载完成的时候，此方法不靠谱，如果页面有跳转会回调多次
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
}
