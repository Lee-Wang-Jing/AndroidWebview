package com.wangjing.androidwebview;

import android.net.Uri;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.wangjing.androidwebview.callback.OnShowFileChooser;

public class CustomWebChromeClient extends WebChromeClient {

    private WebviewCallBack webviewCallBack;
    private OnShowFileChooser onShowFileChooser;


    public OnShowFileChooser getOnShowFileChooser() {
        return onShowFileChooser;
    }

    public void setOnShowFileChooser(OnShowFileChooser onShowFileChooser) {
        this.onShowFileChooser = onShowFileChooser;
    }

    public void setWebviewCallBack(WebviewCallBack webviewCallBack) {
        this.webviewCallBack = webviewCallBack;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (webviewCallBack != null) {
            webviewCallBack.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webviewCallBack != null) {
            webviewCallBack.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        if (webviewCallBack != null) {
            webviewCallBack.onJsAlert(view, url, message, result);
        }
        return true;
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (onShowFileChooser != null) {
            return onShowFileChooser.onShowFileChooser(filePathCallback, fileChooserParams);
        } else {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        if (webviewCallBack != null) {
            webviewCallBack.onShowCustomView(view, callback);
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
    public void onPermissionRequest(PermissionRequest request) {
        if (webviewCallBack != null) {
            webviewCallBack.onPermissionRequest(request);
        }
    }

    @Override
    public void onPermissionRequestCanceled(PermissionRequest request) {
        super.onPermissionRequestCanceled(request);
        if (webviewCallBack != null) {
            webviewCallBack.onPermissionRequestCanceled(request);
        }
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        if (webviewCallBack != null) {
            webviewCallBack.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }
}
