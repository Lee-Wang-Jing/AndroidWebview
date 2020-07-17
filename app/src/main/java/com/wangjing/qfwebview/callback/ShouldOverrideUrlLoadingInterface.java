package com.wangjing.qfwebview.callback;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;

public interface ShouldOverrideUrlLoadingInterface {
    boolean shouldOverrideUrlLoading(WebView view, String url);
    boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request);
}
