package com.example.administrator.myapplication;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wangjing.androidwebview.OnScrollChangedCallBack;
import com.wangjing.androidwebview.OverScrollModeCallBack;
import com.wangjing.androidwebview.ShouldInterceptRequestInterface;
import com.wangjing.androidwebview.WebviewCallBack;
import com.wangjing.qfwebview.CustomWebview;
import com.wangjing.qfwebview.CustomWebviewX5;
import com.wangjing.qfwebview.WebviewBuilder;
import com.wangjing.qfwebview.WebviewStrategy;
import com.wangjing.qfwebview.callback.IWebView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivityNew extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout web_layout;
    private WebviewStrategy webviewStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web_layout = findViewById(R.id.web_layout);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setEnabled(false);
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        initWebview();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        onBackPressed();
                    }
                })
                .start();


    }

    private boolean useX5 = false;

    private void initWebview() {
        getIWebview()
                .setWebviewBuilder(new WebviewBuilder()
                        .setDebug(true)
                        .setCurrentUrl("https://www.baidu.com")
                        .setUserAgent("")
                        .setCacheMode(-1)
                );
        getIWebview().initWebViewSettings();
    }

    private WebviewStrategy getWebviewStrategy() {
        if (webviewStrategy == null || webviewStrategy.getiWebView() == null) {
            if (useX5) {
                IWebView iWebView = new CustomWebviewX5(this);
                webviewStrategy = new WebviewStrategy(iWebView);
                iWebView.initWebViewSettings();
            } else {
                IWebView iWebView = new CustomWebview(this);
                webviewStrategy = new WebviewStrategy(iWebView);
            }
        }
        return webviewStrategy;
    }

    private IWebView getIWebview() {
        return getWebviewStrategy().getiWebView();
    }
}
