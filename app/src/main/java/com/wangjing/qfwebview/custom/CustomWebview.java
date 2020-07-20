package com.wangjing.qfwebview.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wangjing.qfwebview.JSBean;
import com.wangjing.qfwebview.OnDownloadStart;
import com.wangjing.qfwebview.WebviewBuilder;
import com.wangjing.qfwebview.IWebView;
import com.wangjing.qfwebview.callback.ShouldInterceptRequestInterface;
import com.wangjing.qfwebview.callback.ShouldOverrideUrlLoadingInterface;
import com.wangjing.qfwebview.callback.WebviewCallBack;

import java.util.List;

public class CustomWebview extends WebView implements IWebView {
    private static final String Tag = CustomWebview.class.getSimpleName();

    private String currentUrl = "";
    private boolean debug = false;
    private String userAgent;
    private int cacheMode = WebSettings.LOAD_DEFAULT;
    private boolean isShowSSLDialog = false;
    private boolean defaultWebViewClient = false;
    private int textZoom = 100;

    private CustomWebViewClient customWebViewClient;
    private WebviewCallBack webviewCallBack;
    private ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface;
    private ShouldInterceptRequestInterface shouldInterceptRequestInterface;

    private boolean defaultWebChromeClient = false;
    private CustomWebChromeClient customWebChromeClient;

    private List<JSBean> jsBeanList;

    private OnShowFileChooser onShowFileChooser;
    private OnDownloadStart onDownloadStart;

    public CustomWebview(Context context) {
        super(context);
        init();
    }

    public CustomWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    public void setWebviewBuilder(WebviewBuilder builder) {
        if (builder != null) {
            this.currentUrl = builder.getCurrentUrl();
            this.debug = builder.isDebug();
            this.userAgent = builder.getUserAgent();
            this.cacheMode = builder.getCacheMode();
            this.isShowSSLDialog = builder.isShowSSLDialog();
            this.textZoom = builder.getTextZoom();

            this.defaultWebViewClient = builder.isDefaultWebViewClient();
            this.customWebViewClient = builder.getCustomWebViewClient();
            this.webviewCallBack = builder.getWebviewCallBack();
            this.shouldOverrideUrlLoadingInterface = builder.getShouldOverrideUrlLoadingInterface();
            this.shouldInterceptRequestInterface = builder.getShouldInterceptRequestInterface();

            this.defaultWebChromeClient = builder.isDefaultWebChromeClient();
            this.customWebChromeClient = builder.getCustomWebChromeClient();

            this.jsBeanList = builder.getJsBeanList();

            this.onShowFileChooser = builder.getOnShowFileChooser();
            this.onDownloadStart = builder.getOnDownloadStart();
        }
    }

    @Override
    public void setWebviewBuilderWithBuild(WebviewBuilder builder) {
        setWebviewBuilder(builder);
        build();
    }

    @Override
    public void setWebviewBuilderWithBuildLoadUrl(WebviewBuilder builder) {
        setWebviewBuilder(builder);
        buildWithLoadUrl();
    }

    @Override
    public String getUserAgentString() {
        return this.getSettings().getUserAgentString();
    }

    @Override
    public View getWebview() {
        return this;
    }

    @Override
    public void setAcceptThirdPartyCookies(boolean isAccept) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(this, isAccept);
        }
        cookieManager.setAcceptCookie(isAccept);
    }

    public void initWebViewSettings() {
        if (debug) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }
        WebSettings webSettings = this.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (!TextUtils.isEmpty(userAgent)) {
            webSettings.setUserAgentString(userAgent);
        }
        webSettings.setTextZoom(textZoom);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        //设置为false，设置true，某些手机上某些情况会崩溃 https://bugly.qq.com/v2/crash-reporting/crashes/41f89fb766/7869?pid=1
        webSettings.setSupportMultipleWindows(false);
        //99是否允许WebView度超出以概览的方式载入页面，默认false。即缩小内容以适应屏幕宽度。该项设置在内容宽度超出WebView控件的宽度时生效，例如当getUseWideViewPort() 返回true时。
        //setLoadWithOverviewMode为true后在某些手机上面打开Webview会变形，比如oppo 5.1系统
        webSettings.setLoadWithOverviewMode(false);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        webSettings.setDatabasePath(getContext().getDir("databases", 0).getPath());
        webSettings.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
                .getPath());
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(cacheMode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //在Android 4.2 添加了用户收拾出发音视频播放接口，该接口默认为true，即默认不允许自动播放音视频，只能是用户交互的方式由用户自己促发播放
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        this.requestFocus();//请求获取焦点，防止view不能打开输入法问题
        this.requestFocusFromTouch();//请求获取焦点，防止view不能打开输入法问题
        this.setFocusableInTouchMode(true);
    }

    private void initWebViewClient() {
        if (defaultWebViewClient) {//如果设置使用默认的CustomWebViewClient
            if (customWebViewClient == null) {
                customWebViewClient = new CustomWebViewClient(isShowSSLDialog);
                this.customWebViewClient.setWebviewCallBack(webviewCallBack);
                this.customWebViewClient.setShouldOverrideUrlLoadingInterface(shouldOverrideUrlLoadingInterface);
                this.customWebViewClient.setShouldInterceptRequestInterface(shouldInterceptRequestInterface);
            }
            this.setWebViewClient(customWebViewClient);
        } else {
            if (customWebViewClient != null) {
                this.customWebViewClient.setWebviewCallBack(webviewCallBack);
                this.customWebViewClient.setShouldOverrideUrlLoadingInterface(shouldOverrideUrlLoadingInterface);
                this.customWebViewClient.setShouldInterceptRequestInterface(shouldInterceptRequestInterface);
                this.setWebViewClient(customWebViewClient);
            } else {
                Log.e(Tag, "自定义的 WebViewClient 未设置");
            }
        }
    }

    private void initWebChromeClient() {
        if (defaultWebChromeClient) {//如果设置使用默认的CustomChromeClient
            if (customWebChromeClient == null) {
                customWebChromeClient = new CustomWebChromeClient(webviewCallBack, onShowFileChooser);
                this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
            }
            this.setWebChromeClient(customWebChromeClient);
        } else {
            if (customWebChromeClient != null) {
                this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
                this.setWebChromeClient(customWebChromeClient);
            } else {
                Log.e(Tag, "自定义的 WebChromeClient 未设置");
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initJavascriptInterface() {
        if (jsBeanList != null && !jsBeanList.isEmpty()) {
            for (int i = 0; i < jsBeanList.size(); i++) {
                this.addJavascriptInterface(jsBeanList.get(i).getMapClazz(), jsBeanList.get(i).getObjName());
            }
        }
    }

    @Override
    public boolean canGoback2() {
        return this.canGoBack();
    }

    @Override
    public void loadUrl2(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.loadUrl(url);
        }
    }

    @Override
    public void build() {
        initWebViewSettings();
        initWebViewClient();
        initWebChromeClient();
        initDownLoadListener();
        initJavascriptInterface();
    }

    private void initDownLoadListener() {
        if (onDownloadStart != null) {
            this.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    if (onDownloadStart != null) {
                        onDownloadStart.onDownloadStart(url, userAgent, contentDisposition, mimetype, contentLength);
                    }
                }
            });
        }
    }


    @Override
    public void buildWithLoadUrl() {
        build();
        loadUrl2(currentUrl);
    }

    @Override
    public void loadJavaScript(String javascript, ValueCallback valueCallback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.evaluateJavascript("" + javascript, valueCallback);
        } else {
            this.loadUrl("" + javascript);
        }
    }

    @Override
    public void loadJavaScriptX5(String javascript, com.tencent.smtt.sdk.ValueCallback valueCallback) {
        Log.e(Tag, "now Webview not x5");
    }

}
