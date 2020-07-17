package com.wangjing.qfwebview.callback;

import android.view.View;

import com.wangjing.qfwebview.WebviewBuilder;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    View getWebview();

    boolean canGoback2();

    void loadUrl2(String url);

    void build();

    void buildWithLoadUrl();
}
