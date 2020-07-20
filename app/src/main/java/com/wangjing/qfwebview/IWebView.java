package com.wangjing.qfwebview;

import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;

import androidx.annotation.Nullable;

import com.wangjing.qfwebview.custom.CustomWebview;
import com.wangjing.qfwebview.customx5.CustomWebviewX5;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    String getUserAgentString();

    View getIView();

    CustomWebview getWebView();

    CustomWebviewX5 getX5WebView();

    void setAcceptThirdPartyCookies(boolean isAccept);

    boolean canGoback2();

    void loadUrl2(String url);

    void loadDataWithBaseURL2(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl);

    String getUrl2();

    void reload2();


    void build();

    void buildWithLoadUrl();

    void loadJavaScript(String javascript, ValueCallback valueCallback);

    void loadJavaScriptX5(String javascript, com.tencent.smtt.sdk.ValueCallback valueCallback);

}
