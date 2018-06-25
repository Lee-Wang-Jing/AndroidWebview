package com.example.administrator.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.wangjing.androidwebview.CustomWebview;
import com.wangjing.androidwebview.OnScrollChangedCallBack;
import com.wangjing.androidwebview.ShouldOverrideUrlLoadingInterface;
import com.wangjing.androidwebview.WebviewCallBack;

public class MainActivity extends AppCompatActivity {

    private CustomWebview webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = findViewById(R.id.webview);

        webview.setDebug(true)//设置Debug模式，正式包建议关闭
                .setCurrentUrl("http://house.shangxiaban.cn/wap/dev.php#/detail/sell/zz/601923")//设置当前加载的Url地址
                .setUserAgent("xxxx")//设置Webview的UserAgent
                .setDefaultWebViewClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
                .setDefaultWebChromeClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
                .addJSInterface(new JsCallJava(), "test")//添加JavascriptInterface，可以添加add多个
                .addJSInterface(new JsCallJava(), "test1")//添加JavascriptInterface，可以添加add多个
                .setOnScrollChangedCallBack(new OnScrollChangedCallBack() {//设置监听Webview是否滚动，可以用于下拉刷新的逻辑判断
                    @Override
                    public void onScrollChanged(int l, int t, int oldl, int oldt) {
                        Log.e("onScrollChanged", "  l==>" + l + "  t==>" + t + "  oldl==>" + oldl + "  oldt==>" + oldt);
                    }
                })
                .setWebiewCallBack(new WebviewCallBack() {//设置Webview加载的一些常用的监听，设置setDefaultWebViewClient和setDefaultWebChromeClient为true，对应的参数才会生效，自定义的无效
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

                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                    }
                })
                //设置ShouldOverrideUrlLoading监听回调，重要 PS：如果设置了此回调，对应的逻辑需要自己处理，比如Android 8.0以上版本的兼容等等
                .setShouldOverrideUrlLoadingInterface(new ShouldOverrideUrlLoadingInterface() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        return false;
                    }
                })
                .buildWithLoadUrl();//build操作放在最后，build之后会直接loadurl，链接为上面设置的setCurrentUrl
    }
}
