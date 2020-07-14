package com.wangjing.qfwebview.callback;

import android.view.View;

public interface IWebView {
    View getWebview();

    void initWebViewSettings();

    boolean canGoback();
}
