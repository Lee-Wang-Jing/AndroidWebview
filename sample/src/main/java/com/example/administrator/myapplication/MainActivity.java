package com.example.administrator.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.wangjing.androidwebview.CustomWebview;
import com.wangjing.androidwebview.OnScrollChangedCallBack;
import com.wangjing.androidwebview.WebviewCallBack;

public class MainActivity extends AppCompatActivity {

    private CustomWebview webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = findViewById(R.id.webview);

        webview.setDebug(true)
                .setCurrentUrl("http://www.baidu.com")
                .setDefaultWebViewClient(true)
                .setDefaultChromeClient(true)
                .addJSInterface(new JsCallJava(), "test")
                .addJSInterface(new JsCallJava(), "test1")
                .setOnScrollChangedCallBack(new OnScrollChangedCallBack() {
                    @Override
                    public void onScrollChanged() {

                    }
                })
                .setWebiewCallBack(new WebviewCallBack() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onProgress(int curProgress) {
                        super.onProgress(curProgress);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }

                    @Override
                    public void onLoadResource(WebView view, String url) {
                        super.onLoadResource(view, url);
                    }

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                    }
                })
                .buildWithLoadUrl();

    }
}
