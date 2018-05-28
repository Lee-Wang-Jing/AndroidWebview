package com.wangjing.androidwebview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Map;

public class CustomWebview extends WebView {

    private CustomChromeClient customChromeClient;
    private boolean addedJavascriptInterface;
    private SwipeRefreshLayout swipeRefreshLayout;


    public CustomWebview(Context context) {
        this(context, null);
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addedJavascriptInterface = false;
        initWebViewSettings();
        this.setWebViewClient(new CustomWebViewClient());
    }

    /**
     * 设置下拉刷新的SwipeRefreshLayout
     *
     * @param swipeRefreshLayout swipeRefreshLayout
     */
    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (swipeRefreshLayout != null) {
            if (this.getScrollY() == 0) {
                swipeRefreshLayout.setEnabled(true);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }
        }
    }

    public class JavascriptInterface {
        @android.webkit.JavascriptInterface
        public void notifyVideoEnd() // Must match Javascript interface method of VideoEnabledWebChromeClient
        {
            Log.d("___", "GOT IT");
            // This code is not executed in the UI thread, so we must force that to happen
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (customChromeClient != null) {
                        customChromeClient.onHideCustomView();
                    }
                }
            });
        }
    }

    /**
     * Indicates if the video is being displayed using a custom view (typically full-screen)
     *
     * @return true it the video is being displayed using a custom view (typically full-screen)
     */
    public boolean isVideoFullscreen() {
        return customChromeClient != null && customChromeClient.isVideoFullscreen();
    }

    /**
     * Pass only a VideoEnabledWebChromeClient instance.
     */
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    public void setWebChromeClient(WebChromeClient client) {
        getSettings().setJavaScriptEnabled(true);

        if (client instanceof CustomChromeClient) {
            this.customChromeClient = (CustomChromeClient) client;
        }
        super.setWebChromeClient(client);
    }

    @Override
    public void loadData(String data, String mimeType, String encoding) {
        addJavascriptInterface();
        super.loadData(data, mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        addJavascriptInterface();
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public void loadUrl(String url) {
        addJavascriptInterface();
        super.loadUrl(url);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        addJavascriptInterface();
        super.loadUrl(url, additionalHttpHeaders);
    }

    private void addJavascriptInterface() {
        if (!addedJavascriptInterface) {
            // Add javascript interface to be called when the video ends (must be done before page load)
            //noinspection all
            addJavascriptInterface(new JavascriptInterface(), "_VideoEnabledWebView"); // Must match Javascript interface name of VideoEnabledWebChromeClient

            addedJavascriptInterface = true;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSettings = this.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        //设置为false，设置true，某些手机上某些情况会崩溃 https://bugly.qq.com/v2/crash-reporting/crashes/41f89fb766/7869?pid=1
        webSettings.setSupportMultipleWindows(false);
        //99是否允许WebView度超出以概览的方式载入页面，默认false。即缩小内容以适应屏幕宽度。该项设置在内容宽度超出WebView控件的宽度时生效，例如当getUseWideViewPort() 返回true时。
//        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        webSettings.setDatabasePath(getContext().getDir("databases", 0).getPath());
        webSettings.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
                .getPath());
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }
}
