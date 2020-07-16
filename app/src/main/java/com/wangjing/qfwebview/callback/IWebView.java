package com.wangjing.qfwebview.callback;

import android.view.View;

import com.wangjing.qfwebview.WebviewBuilder;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);
    View getWebview();

    void initWebViewSettings();

    boolean canGoback();
}
