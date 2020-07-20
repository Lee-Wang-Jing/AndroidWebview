package com.wangjing.qfwebview;

import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    String getUserAgentString();

    View getWebview();

    void setAcceptThirdPartyCookies(boolean isAccept);

    boolean canGoback2();

    void loadUrl2(String url);

    void build();

    void buildWithLoadUrl();

    void loadJavaScript(String javascript, ValueCallback valueCallback);

    void loadJavaScriptX5(String javascript, com.tencent.smtt.sdk.ValueCallback valueCallback);

}
