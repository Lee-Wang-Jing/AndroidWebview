package com.wangjing.qfwebview;

import android.view.View;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    String getUserAgentString();

    View getWebview();

    boolean canGoback2();

    void loadUrl2(String url);

    void build();

    void buildWithLoadUrl();
}
