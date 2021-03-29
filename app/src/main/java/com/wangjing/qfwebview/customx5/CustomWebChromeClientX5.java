package com.wangjing.qfwebview.customx5;


import android.net.Uri;
import android.view.View;
import android.webkit.PermissionRequest;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.wangjing.qfwebview.callbackx5.OnShowFileChooserX5;
import com.wangjing.qfwebview.callbackx5.WebviewCallBackX5;


public class CustomWebChromeClientX5 extends WebChromeClient {

    private WebviewCallBackX5 webviewCallBack;
    private OnShowFileChooserX5 onShowFileChooserX5;

    private CustomWebChromeClientX5() {
        super();
    }

    public CustomWebChromeClientX5(WebviewCallBackX5 webviewCallBack, OnShowFileChooserX5 onShowFileChooserX5) {
        this.webviewCallBack = webviewCallBack;
        this.onShowFileChooserX5 = onShowFileChooserX5;
    }

    public void setWebviewCallBack(WebviewCallBackX5 webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (webviewCallBack != null) {
            webviewCallBack.onProgressChanged(newProgress);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webviewCallBack != null) {
            webviewCallBack.onReceivedTitle(title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        if (webviewCallBack != null) {
            webviewCallBack.onJsAlert(url, message, result);
        }
        return true;
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        if (onShowFileChooserX5 != null) {
            return onShowFileChooserX5.onShowFileChooser(valueCallback, fileChooserParams);
        } else {
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    }

    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, customViewCallback);
        if (webviewCallBack != null) {
            webviewCallBack.onShowCustomView(view, customViewCallback);
        }
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        if (webviewCallBack != null) {
            webviewCallBack.onHideCustomView();
        }
    }

    @Override
    public void onPermissionRequest(com.tencent.smtt.export.external.interfaces.PermissionRequest permissionRequest) {
        if (webviewCallBack != null) {
            webviewCallBack.onPermissionRequest(permissionRequest);
        }
    }

    @Override
    public void onPermissionRequestCanceled(com.tencent.smtt.export.external.interfaces.PermissionRequest permissionRequest) {
        if (webviewCallBack != null) {
            webviewCallBack.onPermissionRequestCanceled(permissionRequest);
        }
    }
}
