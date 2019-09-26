package com.wangjing.androidwebview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient {

    private WebviewCallBack webviewCallBack;
    private ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface;
    private ShouldInterceptRequestInterface shouldInterceptRequestInterface;
    private boolean isShowSSLDialog = false;

    public CustomWebViewClient(boolean isShowSSLDialog) {
        this.isShowSSLDialog = isShowSSLDialog;
    }

    public void setShouldOverrideUrlLoadingInterface(ShouldOverrideUrlLoadingInterface shouldOverrideUrlLoadingInterface) {
        this.shouldOverrideUrlLoadingInterface = shouldOverrideUrlLoadingInterface;
    }

    public void setShouldInterceptRequestInterface(ShouldInterceptRequestInterface shouldInterceptRequestInterface) {
        this.shouldInterceptRequestInterface = shouldInterceptRequestInterface;
    }


    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
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
            webviewCallBack.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (webviewCallBack != null) {
            webviewCallBack.onPageFinished(view, url);
        }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        if (webviewCallBack != null) {
            webviewCallBack.onLoadResource(view, url);
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
        }else{
            return super.shouldInterceptRequest(view, url);
        }
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (shouldInterceptRequestInterface != null) {
            return shouldInterceptRequestInterface.shouldInterceptRequest(view, request);
        }else{
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
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        return super.onRenderProcessGone(view, detail);
    }

    @Override
    public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
        super.onSafeBrowsingHit(view, request, threatType, callback);
    }

    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
//        super.onReceivedSslError(view, handler, error);
//        handler.proceed();//接受证书
//        Log.e("onReceivedSslError", "getPrimaryError==>" + error.getPrimaryError()
//                + "\ngetUrl" + error.getUrl()
//                + "\ntoString" + error.toString()
//        );
        if (!isShowSSLDialog) {
            handler.proceed();//接受证书
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("SSL证书验证失败");
            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //接受证书
                    handler.proceed();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
