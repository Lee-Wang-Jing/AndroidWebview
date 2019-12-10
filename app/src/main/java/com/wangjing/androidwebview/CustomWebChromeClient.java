package com.wangjing.androidwebview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class CustomWebChromeClient extends WebChromeClient {

    private WebviewCallBack webviewCallBack;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("好", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
        builder.setCancelable(false);
        builder.create();
        builder.show();
        return true;
    }
}
