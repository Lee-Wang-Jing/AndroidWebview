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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wangjing.androidwebview.CustomWebview;
import com.wangjing.androidwebview.OnScrollChangedCallBack;
import com.wangjing.androidwebview.OverScrollModeCallBack;
import com.wangjing.androidwebview.ShouldInterceptRequestInterface;
import com.wangjing.androidwebview.ShouldOverrideUrlLoadingInterface;
import com.wangjing.androidwebview.WebviewCallBack;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private CustomWebview webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = findViewById(R.id.webview);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setEnabled(false);

        webview.setDebug(true)//设置Debug模式，正式包建议关闭
                .setCurrentUrl("http://www.qianfanyun.com/js_demo.php")//设置当前加载的Url地址
//                .setUserAgent("xxxx")//设置Webview的UserAgent
                .setDefaultWebViewClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
                .setDefaultWebChromeClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
                .addJSInterface(new JsCallJava(), "test")//添加JavascriptInterface，可以添加add多个
                .addJSInterface(new JsCallJava(), "test1")//添加JavascriptInterface，可以添加add多个
                .setOverScrollModeCallBack(new OverScrollModeCallBack() {
                    @Override
                    public void setOverScrollMode(int mode) {

                    }
                })
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

                    @Override
                    public void onJsAlert(WebView view, String url, String message, final JsResult result) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
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
                    }

                    @Override
                    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
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
                //设置setShouldInterceptRequestInterface监听回调
                .setShouldInterceptRequestInterface(new ShouldInterceptRequestInterface() {
                    @Override
                    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                        return null;
                    }

                    @Override
                    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                        return null;
                    }
                })
                .buildWithLoadUrl();//build操作放在最后，build之后会直接loadurl，链接为上面设置的setCurrentUrl

    }
}
