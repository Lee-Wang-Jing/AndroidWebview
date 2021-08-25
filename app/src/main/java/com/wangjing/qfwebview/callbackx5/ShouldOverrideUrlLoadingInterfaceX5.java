package com.wangjing.qfwebview.callbackx5;


import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;

public interface ShouldOverrideUrlLoadingInterfaceX5 {
    boolean shouldOverrideUrlLoading(WebView view, String url);
    boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request);
}
