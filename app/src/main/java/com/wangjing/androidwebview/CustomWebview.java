package com.wangjing.androidwebview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomWebview extends WebView {
    /**
     * 当前首次加载的url
     */
    private String currentUrl = "";
    private CustomWebViewClient customWebViewClient;
    private CustomWebVideoChromeClient customWebVideoChromeClient;
    private CustomWebChromeClient customWebChromeClient;
    private boolean addedJavascriptInterface;

    private String userAgent;
    private int cacheMode = WebSettings.LOAD_DEFAULT;
    private List<JSBean> jsBeanList;

    private OnScrollChangedCallBack onScrollChangedCallBack;
    private OverScrollModeCallBack overScrollModeCallBack;

    private WebviewCallBack webviewCallBack;
    private ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface;
    private ShouldInterceptRequestInterface shouldInterceptRequestInterface;

    private boolean defaultWebViewClient = false;
    private boolean defaultWebChromeClient = false;
    private boolean debug = false;
    private boolean isShowSSLDialog = false;

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
        addedJavascriptInterface = false;
    }


//    public CustomWebview(Context context) {
//        this(context, null);
//    }
//
//    public CustomWebview(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CustomWebview(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        addedJavascriptInterface = false;
//    }

    /**
     * 设置是否使用默认的WebViewClient，不设置则不使用，用户可以自行设置自己的.true则表示使用
     *
     * @param defaultWebViewClient defaultWebViewClient
     * @return CustomWebview
     */
    public CustomWebview setDefaultWebViewClient(boolean defaultWebViewClient) {
        this.defaultWebViewClient = defaultWebViewClient;
        return this;
    }

    /**
     * 设置是否开启Debug模式
     *
     * @param debug 是否开启Debug
     * @return CustomWebview
     */
    public CustomWebview setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 设置是否提示SSL证书失败的Dialog
     *
     * @param showSSLDialog 是否开启提示，默认false
     * @return CustomWebview
     */
    public CustomWebview setShowSSLDialog(boolean showSSLDialog) {
        this.isShowSSLDialog = showSSLDialog;
        return this;
    }

    /**
     * 设置是否使用默认的WebChromeClient，不设置则不使用，用户可以自行设置自己的.true则表示使用
     *
     * @param defaultWebChromeClient defaultWebChromeClient
     * @return CustomWebview
     */
    public CustomWebview setDefaultWebChromeClient(boolean defaultWebChromeClient) {
        this.defaultWebChromeClient = defaultWebChromeClient;
        return this;
    }

    /**
     * 获取OnScrollChangedCallBack
     *
     * @return OnScrollChangedCallBack
     */
    public OnScrollChangedCallBack getOnScrollChangedCallBack() {
        return onScrollChangedCallBack;
    }

    /**
     * 设置Webview的onScrollChanged监听
     *
     * @param onScrollChangedCallBack OnScrollChangedCallBack
     * @return CustomWebview
     */
    public CustomWebview setOnScrollChangedCallBack(OnScrollChangedCallBack onScrollChangedCallBack) {
        this.onScrollChangedCallBack = onScrollChangedCallBack;
        return this;
    }

    public OverScrollModeCallBack getOverScrollModeCallBack() {
        return overScrollModeCallBack;
    }

    public CustomWebview setOverScrollModeCallBack(OverScrollModeCallBack overScrollModeCallBack) {
        this.overScrollModeCallBack = overScrollModeCallBack;
        return this;
    }

    /**
     * 设置Webview的 ShouldOverrideUrlLoadingInterface 监听
     *
     * @param shouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface
     * @return CustomWebview
     */
    public CustomWebview setShouldOverrideUrlLoadingInterface(ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterface = shouldOverrideUrlLoadingInterface;
        return this;
    }

    /**
     * 设置Webview的 shouldInterceptRequestInterface 监听
     *
     * @param shouldInterceptRequestInterface shouldInterceptRequestInterface
     * @return CustomWebview
     */
    public CustomWebview setShouldInterceptRequestInterface(ShouldInterceptRequestInterface shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterface = shouldInterceptRequestInterface;
        return this;
    }


    /**
     * 获取WebviewCallBack
     *
     * @return WebviewCallBack
     */
    public WebviewCallBack getCallBack() {
        return webviewCallBack;
    }

    /**
     * 设置Webview的 WebviewCallBack 监听
     *
     * @param callBack WebviewCallBack
     * @return CustomWebview
     */
    public CustomWebview setWebiewCallBack(WebviewCallBack callBack) {
        this.webviewCallBack = callBack;
        return this;
    }

    /**
     * 设置UserAgent
     *
     * @param userAgent userAgent
     * @return CustomWebview
     */
    public CustomWebview setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置CacheMode
     *
     * @param cacheMode cacheMode
     * @return CustomWebview
     */
    public CustomWebview setCacheMode(int cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    /**
     * webview的onScrollChanged监听
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedCallBack != null) {
            onScrollChangedCallBack.onScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(mode);
        if (overScrollModeCallBack != null) {
            overScrollModeCallBack.setOverScrollMode(mode);
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
                    if (customWebVideoChromeClient != null) {
                        customWebVideoChromeClient.onHideCustomView();
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
        return customWebVideoChromeClient != null && customWebVideoChromeClient.isVideoFullscreen();
    }

//    /**
//     * Pass only a VideoEnabledWebChromeClient instance.
//     */
//    @Override
//    @SuppressLint("SetJavaScriptEnabled")
//    public void setWebChromeClient(WebChromeClient client) {
//        getSettings().setJavaScriptEnabled(true);
//
//        if (client instanceof CustomVideoChromeClient) {
//            this.customVideoChromeClient = (CustomVideoChromeClient) client;
//        } else {
//            this.webChromeClient = client;
//        }
//        super.setWebChromeClient(client);
//    }

    /**
     * 设置 CustomChromeClient
     *
     * @param customWebChromeClient 自定义的WebChromeClient
     * @return CustomWebview
     */
    public CustomWebview setCustomWebChromeClient(CustomWebChromeClient customWebChromeClient) {
        this.customWebChromeClient = customWebChromeClient;
        return this;
    }

    /**
     * 设置 customWebVideoChromeClient
     *
     * @param customWebVideoChromeClient 自定义的customWebVideoChromeClient
     * @return CustomWebview
     */
    public CustomWebview setCustomWebVideoChromeClient(CustomWebVideoChromeClient customWebVideoChromeClient) {
        this.customWebVideoChromeClient = customWebVideoChromeClient;
        return this;
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

    /**
     * 添加js与java互相调用类.
     * SuppressLint("JavascriptInterface") 表示webview的修复漏洞
     *
     * @param mapClazz js方法与java方法映射类
     * @param objName  对象的名字
     * @return CustomWebview
     */
    public CustomWebview addJSInterface(Object mapClazz, String objName) {
        if (jsBeanList == null) {
            jsBeanList = new ArrayList<>();
        }
        JSBean jsBean = new JSBean();
        jsBean.setMapClazz(mapClazz);
        jsBean.setObjName(objName);
        jsBeanList.add(jsBean);
        return this;
    }

    /**
     * 设置customWebViewClient
     *
     * @param customWebViewClient customWebViewClient
     * @return CustomWebview
     */
    public CustomWebview setCustomWebViewClient(CustomWebViewClient customWebViewClient) {
        this.customWebViewClient = customWebViewClient;
        return this;
    }


    public CustomWebview setCurrentUrl(String url) {
        this.currentUrl = url;
        return this;
    }


    /**
     * 构建初始化数据
     */
    @SuppressLint("JavascriptInterface")
    public void build() {
        initWebViewSettings();

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
            }
        }

        if (defaultWebChromeClient) {//如果设置使用默认的CustomChromeClient
            if (customWebChromeClient == null) {
                customWebChromeClient = new CustomWebChromeClient();
                this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
            }
            this.setWebChromeClient(customWebChromeClient);
        } else {
            if (customWebVideoChromeClient != null) {
                this.customWebVideoChromeClient.setWebviewCallBack(webviewCallBack);
                this.setWebChromeClient(customWebVideoChromeClient);
            } else {
                if (customWebChromeClient != null) {
                    this.customWebChromeClient.setWebviewCallBack(webviewCallBack);
                    this.setWebChromeClient(customWebChromeClient);
                }
            }
        }

        if (jsBeanList != null && !jsBeanList.isEmpty()) {
            for (int i = 0; i < jsBeanList.size(); i++) {
                this.addJavascriptInterface(jsBeanList.get(i).getMapClazz(), jsBeanList.get(i).getObjName());
            }
        }
    }

    /**
     * 构建初始化数据，并且loadUrl
     */
    public void buildWithLoadUrl() {
        build();
        if (!TextUtils.isEmpty(currentUrl)) {
            this.loadUrl(currentUrl);
        }
    }
}
