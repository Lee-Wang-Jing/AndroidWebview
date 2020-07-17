package com.wangjing.qfwebview.callback;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

public interface ShouldInterceptRequestInterface {
    WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request);

    WebResourceResponse shouldInterceptRequest(WebView view, String url);
}
