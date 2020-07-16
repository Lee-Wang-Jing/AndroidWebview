package com.wangjing.qfwebview;

import android.text.TextUtils;
import android.webkit.WebSettings;

/**
 * Webview配置类
 */
public class WebviewBuilder {
    private String currentUrl = "";
    private boolean debug = false;
    private String userAgent;
    private int cacheMode = WebSettings.LOAD_DEFAULT;

    public WebviewBuilder() {
    }

    public WebviewBuilder setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
        return this;
    }

    /**
     * 设置是否开启Debug模式
     *
     * @param debug 是否开启Debug
     * @return CustomWebview
     */
    public WebviewBuilder setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 设置UserAgent
     *
     * @param userAgent userAgent
     * @return CustomWebview
     */
    public WebviewBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置CacheMode
     *
     * @param cacheMode cacheMode
     * @return CustomWebview
     */
    public WebviewBuilder setCacheMode(int cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    @Override
    public String toString() {
        return "WebviewBuilder{" +
                "currentUrl='" + currentUrl + '\'' +
                ", debug=" + debug +
                ", userAgent='" + userAgent + '\'' +
                ", cacheMode=" + cacheMode +
                '}';
    }

    public String getCurrentUrl() {
        return TextUtils.isEmpty(currentUrl) ? "" : currentUrl;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getUserAgent() {
        return TextUtils.isEmpty(userAgent) ? "" : userAgent;
    }

    public int getCacheMode() {
        return cacheMode;
    }
}
