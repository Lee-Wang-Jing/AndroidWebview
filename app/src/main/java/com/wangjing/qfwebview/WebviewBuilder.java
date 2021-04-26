package com.wangjing.qfwebview;

import android.text.TextUtils;
import android.webkit.WebSettings;

import com.wangjing.qfwebview.callback.OnShowFileChooser;
import com.wangjing.qfwebview.callback.ShouldInterceptRequestInterface;
import com.wangjing.qfwebview.callback.ShouldOverrideUrlLoadingInterface;
import com.wangjing.qfwebview.callback.WebviewCallBack;
import com.wangjing.qfwebview.callbackx5.OnShowFileChooserX5;
import com.wangjing.qfwebview.callbackx5.ShouldInterceptRequestInterfaceX5;
import com.wangjing.qfwebview.callbackx5.ShouldOverrideUrlLoadingInterfaceX5;
import com.wangjing.qfwebview.callbackx5.WebviewCallBackX5;
import com.wangjing.qfwebview.custom.CustomWebChromeClient;
import com.wangjing.qfwebview.custom.CustomWebViewClient;
import com.wangjing.qfwebview.customx5.CustomWebChromeClientX5;
import com.wangjing.qfwebview.customx5.CustomWebViewClientX5;

import java.util.ArrayList;
import java.util.List;

/**
 * Webview配置类
 */
public class WebviewBuilder {
    private String tag = "";
    private String currentUrl = "";
    private boolean debug = false;
    private boolean isAllowFileAccess = false;
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


    private CustomWebViewClientX5 customWebViewClientX5;
    private WebviewCallBackX5 webviewCallBackX5;
    private ShouldOverrideUrlLoadingInterfaceX5 shouldOverrideUrlLoadingInterfaceX5;
    private ShouldInterceptRequestInterfaceX5 shouldInterceptRequestInterfaceX5;
    private CustomWebChromeClientX5 customWebChromeClientX5;

    private OnShowFileChooser onShowFileChooser;
    private OnShowFileChooserX5 onShowFileChooserX5;
    private OnDownloadStart onDownloadStart;

    private OnScrollChangedCallBack onScrollChangedCallBack;

    public WebviewBuilder() {
    }

    /**
     * 需要设置的tag
     *
     * @param tag 需要设置的tag
     * @return WebviewBuilder
     */
    public WebviewBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * 设置需要加载的url
     *
     * @param currentUrl 需要加载的url
     * @return WebviewBuilder
     */
    public WebviewBuilder setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
        return this;
    }

    /**
     * 设置是否开启Debug模式
     *
     * @param debug 是否开启Debug
     * @return WebviewBuilder
     */
    public WebviewBuilder setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 设置是否开启AllowFileAccess
     *
     * @param isAllowFileAccess 是否开启AllowFileAccess
     * @return CustomWebview
     */
    public WebviewBuilder setAllowFileAccess(boolean isAllowFileAccess) {
        this.isAllowFileAccess = isAllowFileAccess;
        return this;
    }

    /**
     * 设置UserAgent
     *
     * @param userAgent userAgent
     * @return WebviewBuilder
     */
    public WebviewBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置CacheMode
     *
     * @param cacheMode cacheMode
     * @return WebviewBuilder
     */
    public WebviewBuilder setCacheMode(int cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    /**
     * 设置是否提示SSL证书失败的Dialog
     *
     * @param showSSLDialog 是否开启提示，默认false
     * @return WebviewBuilder
     */
    public WebviewBuilder setShowSSLDialog(boolean showSSLDialog) {
        this.isShowSSLDialog = showSSLDialog;
        return this;
    }

    /**
     * 设置是否使用默认的WebViewClient，不设置则不使用，用户可以自行设置自己的.true则表示使用
     *
     * @param defaultWebViewClient defaultWebViewClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setDefaultWebViewClient(boolean defaultWebViewClient) {
        this.defaultWebViewClient = defaultWebViewClient;
        return this;
    }

    /**
     * 设置页面的文本缩放百分比。默认值为100
     *
     * @param textZoom textZoom
     * @return WebviewBuilder
     */
    public WebviewBuilder setTextZoom(int textZoom) {
        this.textZoom = textZoom;
        return this;
    }

    /**
     * 设置customWebViewClient
     *
     * @param customWebViewClient customWebViewClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setCustomWebViewClient(CustomWebViewClient customWebViewClient) {
        this.customWebViewClient = customWebViewClient;
        return this;
    }

    /**
     * 设置customWebViewClient
     *
     * @param customWebViewClient customWebViewClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setCustomWebViewClientX5(CustomWebViewClientX5 customWebViewClient) {
        this.customWebViewClientX5 = customWebViewClient;
        return this;
    }

    /**
     * 设置Webview的 WebviewCallBack 监听
     *
     * @param callBack WebviewCallBack
     * @return WebviewBuilder
     */
    public WebviewBuilder setWebiewCallBack(WebviewCallBack callBack) {
        this.webviewCallBack = callBack;
        return this;
    }

    /**
     * 设置Webview的 WebviewCallBack 监听
     *
     * @param callBack WebviewCallBack
     * @return WebviewBuilder
     */
    public WebviewBuilder setWebiewCallBackX5(WebviewCallBackX5 callBack) {
        this.webviewCallBackX5 = callBack;
        return this;
    }

    /**
     * 设置Webview的 ShouldOverrideUrlLoadingInterface 监听
     *
     * @param shouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface
     * @return WebviewBuilder
     */
    public WebviewBuilder setShouldOverrideUrlLoadingInterface(ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterface = shouldOverrideUrlLoadingInterface;
        return this;
    }

    /**
     * 设置Webview的 ShouldOverrideUrlLoadingInterface 监听
     *
     * @param shouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface
     * @return WebviewBuilder
     */
    public WebviewBuilder setShouldOverrideUrlLoadingInterfaceX5(ShouldOverrideUrlLoadingInterfaceX5 shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterfaceX5 = shouldOverrideUrlLoadingInterface;
        return this;
    }

    /**
     * 设置Webview的 shouldInterceptRequestInterface 监听
     *
     * @param shouldInterceptRequestInterface shouldInterceptRequestInterface
     * @return WebviewBuilder
     */
    public WebviewBuilder setShouldInterceptRequestInterface(ShouldInterceptRequestInterface shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterface = shouldInterceptRequestInterface;
        return this;
    }

    /**
     * 设置Webview的 shouldInterceptRequestInterface 监听
     *
     * @param shouldInterceptRequestInterface shouldInterceptRequestInterface
     * @return WebviewBuilder
     */
    public WebviewBuilder setShouldInterceptRequestInterfaceX5(ShouldInterceptRequestInterfaceX5 shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterfaceX5 = shouldInterceptRequestInterface;
        return this;
    }

    /**
     * 设置是否使用默认的WebChromeClient，不设置则不使用，用户可以自行设置自己的.true则表示使用
     *
     * @param defaultWebChromeClient defaultWebChromeClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setDefaultWebChromeClient(boolean defaultWebChromeClient) {
        this.defaultWebChromeClient = defaultWebChromeClient;
        return this;
    }

    /**
     * 设置 CustomChromeClient
     *
     * @param customWebChromeClient 自定义的WebChromeClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setCustomWebChromeClient(CustomWebChromeClient customWebChromeClient) {
        this.customWebChromeClient = customWebChromeClient;
        return this;
    }

    /**
     * 设置 CustomWebChromeClientX5
     *
     * @param customWebChromeClientX5 自定义的X5WebChromeClient
     * @return WebviewBuilder
     */
    public WebviewBuilder setCustomWebChromeClientX5(CustomWebChromeClientX5 customWebChromeClientX5) {
        this.customWebChromeClientX5 = customWebChromeClientX5;
        return this;
    }

    /**
     * 添加js与java互相调用类.
     * SuppressLint("JavascriptInterface") 表示webview的修复漏洞
     *
     * @param mapClazz js方法与java方法映射类
     * @param objName  对象的名字
     * @return WebviewBuilder
     */
    public WebviewBuilder addJSInterface(Object mapClazz, String objName) {
        if (jsBeanList == null) {
            jsBeanList = new ArrayList<>();
        }
        JSBean jsBean = new JSBean();
        jsBean.setMapClazz(mapClazz);
        jsBean.setObjName(objName);
        jsBeanList.add(jsBean);
        return this;
    }

    public WebviewBuilder setOnShowFileChooser(OnShowFileChooser onShowFileChooser) {
        this.onShowFileChooser = onShowFileChooser;
        return this;
    }

    public WebviewBuilder setOnShowFileChooserX5(OnShowFileChooserX5 onShowFileChooserX5) {
        this.onShowFileChooserX5 = onShowFileChooserX5;
        return this;
    }

    public WebviewBuilder setOnDownloadStart(OnDownloadStart onDownloadStart) {
        this.onDownloadStart = onDownloadStart;
        return this;
    }

    public WebviewBuilder setOnScrollChangedCallBack(OnScrollChangedCallBack onScrollChangedCallBack) {
        this.onScrollChangedCallBack = onScrollChangedCallBack;
        return this;
    }

    @Override
    public String toString() {
        return "WebviewBuilder{" +
                "currentUrl='" + currentUrl + '\'' +
                ", debug=" + debug +
                ", userAgent='" + userAgent + '\'' +
                ", cacheMode=" + cacheMode +
                ", isShowSSLDialog=" + isShowSSLDialog +
                ", defaultWebViewClient=" + defaultWebViewClient +
                ", textZoom=" + textZoom +
                ", customWebViewClient=" + customWebViewClient +
                ", webviewCallBack=" + webviewCallBack +
                ", shouldOverrideUrlLoadingInterface=" + shouldOverrideUrlLoadingInterface +
                ", shouldInterceptRequestInterface=" + shouldInterceptRequestInterface +
                ", defaultWebChromeClient=" + defaultWebChromeClient +
                ", customWebChromeClient=" + customWebChromeClient +
                ", jsBeanList=" + jsBeanList +
                ", customWebViewClientX5=" + customWebViewClientX5 +
                ", webviewCallBackX5=" + webviewCallBackX5 +
                ", shouldOverrideUrlLoadingInterfaceX5=" + shouldOverrideUrlLoadingInterfaceX5 +
                ", shouldInterceptRequestInterfaceX5=" + shouldInterceptRequestInterfaceX5 +
                ", customWebChromeClientX5=" + customWebChromeClientX5 +
                ", onShowFileChooser=" + onShowFileChooser +
                ", onShowFileChooserX5=" + onShowFileChooserX5 +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public String getCurrentUrl() {
        return TextUtils.isEmpty(currentUrl) ? "" : currentUrl;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isAllowFileAccess() {
        return isAllowFileAccess;
    }

    public String getUserAgent() {
        return TextUtils.isEmpty(userAgent) ? "" : userAgent;
    }

    public int getCacheMode() {
        return cacheMode;
    }

    public boolean isShowSSLDialog() {
        return isShowSSLDialog;
    }

    public boolean isDefaultWebViewClient() {
        return defaultWebViewClient;
    }

    public CustomWebViewClient getCustomWebViewClient() {
        return customWebViewClient;
    }

    public WebviewCallBack getWebviewCallBack() {
        return webviewCallBack;
    }

    public ShouldOverrideUrlLoadingInterface getShouldOverrideUrlLoadingInterface() {
        return shouldOverrideUrlLoadingInterface;
    }

    public ShouldInterceptRequestInterface getShouldInterceptRequestInterface() {
        return shouldInterceptRequestInterface;
    }

    public boolean isDefaultWebChromeClient() {
        return defaultWebChromeClient;
    }

    public CustomWebChromeClient getCustomWebChromeClient() {
        return customWebChromeClient;
    }

    public List<JSBean> getJsBeanList() {
        return jsBeanList;
    }

    public CustomWebChromeClientX5 getCustomWebChromeClientX5() {
        return customWebChromeClientX5;
    }

    public CustomWebViewClientX5 getCustomWebViewClientX5() {
        return customWebViewClientX5;
    }

    public WebviewCallBackX5 getWebviewCallBackX5() {
        return webviewCallBackX5;
    }

    public ShouldOverrideUrlLoadingInterfaceX5 getShouldOverrideUrlLoadingInterfaceX5() {
        return shouldOverrideUrlLoadingInterfaceX5;
    }

    public ShouldInterceptRequestInterfaceX5 getShouldInterceptRequestInterfaceX5() {
        return shouldInterceptRequestInterfaceX5;
    }

    public int getTextZoom() {
        return textZoom;
    }

    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    public void setJsBeanList(List<JSBean> jsBeanList) {
        this.jsBeanList = jsBeanList;
    }

    public void setWebviewCallBackX5(WebviewCallBackX5 webviewCallBackX5) {
        this.webviewCallBackX5 = webviewCallBackX5;
    }

    public OnShowFileChooser getOnShowFileChooser() {
        return onShowFileChooser;
    }

    public OnShowFileChooserX5 getOnShowFileChooserX5() {
        return onShowFileChooserX5;
    }

    public OnDownloadStart getOnDownloadStart() {
        return onDownloadStart;
    }

    public OnScrollChangedCallBack getOnScrollChangedCallBack() {
        return onScrollChangedCallBack;
    }
}
