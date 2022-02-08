package com.example.administrator.myapplication;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;
import com.wangjing.qfwebview.custom.CustomWebview;
import com.wangjing.qfwebview.customx5.CustomWebviewX5;
import com.wangjing.qfwebview.WebviewBuilder;
import com.wangjing.qfwebview.WebviewStrategy;
import com.wangjing.qfwebview.IWebView;
import com.wangjing.qfwebview.callback.WebviewCallBack;
import com.wangjing.qfwebview.callbackx5.WebviewCallBackX5;

import java.util.List;

public class MainActivityNew extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout web_layout;
    private WebviewStrategy webviewStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnew);
        web_layout = findViewById(R.id.web_layout);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setEnabled(false);
        PermissionX.init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                        if (allGranted) {
                            initWebview();
                        } else {
                            onBackPressed();
                        }
                    }
                });
    }


    private void initWebview() {
        web_layout.addView(getIWebview().getIView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        getIWebview()
                .setWebviewBuilderWithBuildLoadUrl(getWebviewBuilder()
                );
    }

    private boolean useX5 = true;

    private WebviewStrategy getWebviewStrategy() {
        if (webviewStrategy == null || webviewStrategy.getiWebView() == null) {
            IWebView iWebView;
            if (useX5) {
                iWebView = new CustomWebviewX5(this);
            } else {
                iWebView = new CustomWebview(this);
            }
            webviewStrategy = new WebviewStrategy(iWebView);
        }
        return webviewStrategy;
    }

    private IWebView getIWebview() {
        return getWebviewStrategy().getiWebView();
    }

    private WebviewBuilder getWebviewBuilder() {
        if (useX5) {
            return new WebviewBuilder()
                    .setDebug(true)
                    .setCurrentUrl("https://xzshare.xizi.com/wap-view/fenlei/home")
                    .setUserAgent("")
                    .setCacheMode(-1)
                    .setShowSSLDialog(true)
                    .setDefaultWebViewClient(true)
                    .setDefaultWebChromeClient(true)
                    .addJSInterface(new JsCallJava(), "test")
                    .addJSInterface(new JsCallJava(), "test1")
                    .setWebiewCallBackX5(new WebviewCallBackX5() {
                        @Override
                        public void onPageStarted(String url, Bitmap favicon) {
                            super.onPageStarted(url, favicon);
                        }

                        @Override
                        public void onPageFinished(String url) {
                            super.onPageFinished(url);
                        }

                        @Override
                        public void onLoadResource(String url) {
                            super.onLoadResource(url);
                        }

                        @Override
                        public void onProgressChanged(int newProgress) {
                            super.onProgressChanged(newProgress);
                        }

                        @Override
                        public void onReceivedTitle(String title) {
                            super.onReceivedTitle(title);
                        }

                        @Override
                        public void onJsAlert(String url, String message, com.tencent.smtt.export.external.interfaces.JsResult result) {
                            super.onJsAlert(url, message, result);
                        }

                        @Override
                        public void onReceivedSslError(com.tencent.smtt.export.external.interfaces.SslErrorHandler handler, com.tencent.smtt.export.external.interfaces.SslError error) {
                            super.onReceivedSslError(handler, error);
                        }
                    })
                    ;
        } else {
            return new WebviewBuilder()
                    .setDebug(true)
                    .setCurrentUrl("https://f.nhzj.com/wap/esf/index")
                    .setUserAgent("")
                    .setCacheMode(-1)
                    .setShowSSLDialog(true)
                    .setDefaultWebViewClient(true)
                    .setDefaultWebChromeClient(true)
                    .addJSInterface(new JsCallJava(), "test")
                    .addJSInterface(new JsCallJava(), "test1")
                    .setWebiewCallBack(new WebviewCallBack() {
                        @Override
                        public void onPageStarted(String url, Bitmap favicon) {
                            super.onPageStarted(url, favicon);
                        }

                        @Override
                        public void onPageFinished(String url) {
                            super.onPageFinished(url);
                        }

                        @Override
                        public void onLoadResource(String url) {
                            super.onLoadResource(url);
                        }

                        @Override
                        public void onProgressChanged(int newProgress) {
                            super.onProgressChanged(newProgress);
                        }

                        @Override
                        public void onReceivedTitle(String title) {
                            super.onReceivedTitle(title);
                        }

                        @Override
                        public void onJsAlert(String url, String message, JsResult result) {
                            super.onJsAlert(url, message, result);
                        }

                        @Override
                        public void onReceivedSslError(SslErrorHandler handler, SslError error) {
                            super.onReceivedSslError(handler, error);
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        if (getIWebview().canGoback2()){
            getIWebview().goBack2();
        }else{
            super.onBackPressed();
        }
    }
}
