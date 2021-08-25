package com.wangjing.qfwebview;

import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.wangjing.qfwebview.custom.CustomWebview;
import com.wangjing.qfwebview.customx5.CustomWebviewX5;

import java.util.Map;

public interface IWebView {
    void setWebviewBuilder(WebviewBuilder builder);

    void setWebviewBuilderWithBuild(WebviewBuilder builder);

    void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder);

    String getUserAgentString();

    void setUserAgentString(String userAgent);

    View getIView();

    CustomWebview getWebView();

    CustomWebviewX5 getX5WebView();

    void setAcceptThirdPartyCookies(boolean isAccept);

    boolean canGoback2();

    void goBack2();

    int getScrollY2();

    void scrollTo2(int x, int y);

    void loadUrl2(String url);

    void loadUrl2(String url, Map<String, String> additionalHttpHeaders);

    void loadData2(String data, @Nullable String mimeType, @Nullable String encoding);

    void loadDataWithBaseURL2(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl);

    String getUrl2();

    void reload2();

    String getTitle2();

    WebView.HitTestResult getHitTestResult2();

    com.tencent.smtt.sdk.WebView.HitTestResult getHitTestResult2X5();


    void build();

    void buildWithLoadUrl();

    void loadJavaScript(String javascript, ValueCallback valueCallback);

    void loadJavaScriptX5(String javascript, com.tencent.smtt.sdk.ValueCallback valueCallback);

    void destroy2();

}
