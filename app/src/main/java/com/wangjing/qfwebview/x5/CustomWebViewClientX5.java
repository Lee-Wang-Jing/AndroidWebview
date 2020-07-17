package com.wangjing.qfwebview.x5;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;

import androidx.annotation.Nullable;

import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wangjing.qfwebview.callbackx5.ShouldInterceptRequestInterfaceX5;
import com.wangjing.qfwebview.callbackx5.ShouldOverrideUrlLoadingInterfaceX5;
import com.wangjing.qfwebview.callbackx5.WebviewCallBackX5;


public class CustomWebViewClientX5 extends WebViewClient {

    private WebviewCallBackX5 webviewCallBack;
    private ShouldOverrideUrlLoadingInterfaceX5 shouldOverrideUrlLoadingInterface;
    private ShouldInterceptRequestInterfaceX5 shouldInterceptRequestInterface;
    private boolean isShowSSLDialog = false;

    public CustomWebViewClientX5(boolean isShowSSLDialog) {
        this.isShowSSLDialog = isShowSSLDialog;
    }

    public void setShouldOverrideUrlLoadingInterface(ShouldOverrideUrlLoadingInterfaceX5 shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterface = shouldOverrideUrlLoadingInterface;
    }

    public void setShouldInterceptRequestInterface(ShouldInterceptRequestInterfaceX5 shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterface = shouldInterceptRequestInterface;
    }


    public void setWebviewCallBack(WebviewCallBackX5 webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (shouldOverrideUrlLoadingInterface != null) {
            return shouldOverrideUrlLoadingInterface.shouldOverrideUrlLoading(view, url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (shouldOverrideUrlLoadingInterface != null) {
            return shouldOverrideUrlLoadingInterface.shouldOverrideUrlLoading(view, request);
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    public boolean shouldLoadingUrl() {
        /**
         * 低于android 8.0的需要手动loadURL，大于等于android 8.0直接返回false，否则无法重定向
         */
        return Build.VERSION.SDK_INT < 26;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (webviewCallBack != null) {
            webviewCallBack.onPageStarted(url, favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (webviewCallBack != null) {
            webviewCallBack.onPageFinished(url);
        }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (webviewCallBack != null) {
            webviewCallBack.onLoadResource(url);
        }
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (shouldInterceptRequestInterface != null) {
            return shouldInterceptRequestInterface.shouldInterceptRequest(view, url);
        } else {
            return super.shouldInterceptRequest(view, url);
        }
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (shouldInterceptRequestInterface != null) {
            return shouldInterceptRequestInterface.shouldInterceptRequest(view, request);
        } else {
            return super.shouldInterceptRequest(view, request);
        }
    }

    @Override
    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        super.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        super.onReceivedClientCertRequest(view, request);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        super.onUnhandledKeyEvent(view, event);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
        super.onReceivedLoginRequest(view, realm, account, args);
    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        if (!isShowSSLDialog) {
            sslErrorHandler.proceed();//接受证书
        } else {
            if (webviewCallBack != null) {
                webviewCallBack.onReceivedSslError(sslErrorHandler, sslError);
            }
        }
    }
}
