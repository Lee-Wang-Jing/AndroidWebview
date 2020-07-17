package com.wangjing.qfwebview.callbackx5;


import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;

public interface ShouldInterceptRequestInterfaceX5 {
    WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request);

    WebResourceResponse shouldInterceptRequest(WebView view, String url);
}
