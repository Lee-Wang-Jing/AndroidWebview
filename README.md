# AndroidWebview [![](https://ci.novoda.com/buildStatus/icon?job=bintray-release)](https://ci.novoda.com/job/bintray-release/lastBuild/console) [![Download](https://api.bintray.com/packages/wangjinggm/maven/androidwebview/images/download.svg) ](https://bintray.com/wangjinggm/maven/androidwebview/_latestVersion) [![license](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/Lee-Wang-Jing/androidwebview/blob/master/LICENSE)

AndroidWebview技术交流QQ群：598403807       
<img src="http://apidoc.qianfanyun.com/Public/Uploads/2018-06-21/5b2b477f93bb5.png" width="200">

AndroidWebview技术交流钉钉群，钉钉群里面可以收到Github实时的更新提醒哦：
<img src="http://apidoc.qianfanyun.com/Public/Uploads/2018-06-21/5b2b47773b5c4.png" width="200">

加群前请务必阅读[群行为规范](https://github.com/Lee-Wang-Jing/GroupStandard)
有问题或者某种需求欢迎加群或者提issues，Thanks

AndroidWebview是Webview的封装工具类，主要兼容了Webview中全屏播放视频的问题以及一些Webview的默认配置，开发使用，方便快捷

# Dependencies
* Gradle
```
implementation 'com.wangjing:androidwebview:0.1.4'
```
* Maven
```xml
<dependency>
  <groupId>com.wangjing</groupId>
  <artifactId>androidwebview</artifactId>
  <version>0.1.4</version>
  <type>pom</type>
</dependency>
```
* Eclipse ADT请放弃治疗

## How to Use
- xml替换成CustomWebview

```
<com.wangjing.androidwebview.CustomWebview
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

- findViewById
- 初始化操作

```java
webview.setDebug(true)//设置Debug模式，正式包建议关闭
        .setCurrentUrl("http://www.baidu.com")//设置当前加载的Url地址
        .setUserAgent("xxxx")//设置Webview的UserAgent
        .setDefaultWebViewClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
        .setDefaultWebChromeClient(true)//设置是否使用默认的WebViewClient进行初始化操作，一般使用默认的就够了，默认为false
        .addJSInterface(new JsCallJava(), "test")//添加JavascriptInterface，可以添加add多个
        .addJSInterface(new JsCallJava(), "test1")//添加JavascriptInterface，可以添加add多个
        .setOnScrollChangedCallBack(new OnScrollChangedCallBack() {//设置监听Webview是否滚动，可以用于下拉刷新的逻辑判断
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
            }
        })
        .setWebiewCallBack(new WebviewCallBack(){//设置Webview加载的一些常用的监听，设置setDefaultWebViewClient和setDefaultWebChromeClient为true，对应的参数才会生效，自定义的无效
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
        .build();//build操作放在最后，build之后不会loadurl，可以自己在做相应的处理
        .buildWithLoadUrl();//build操作放在最后，build之后会直接loadurl，链接为上面设置的setCurrentUrl

```

- 如果需要设置WebSettings，CustomWebview内部已经默认初始化了一些常用的WebSettings，具体如下：

```
@SuppressLint("SetJavaScriptEnabled")
private void initWebViewSettings() {
    if (debug) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
    WebSettings webSettings = this.getSettings();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }
    webSettings.setJavaScriptEnabled(true);
    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    webSettings.setAllowFileAccess(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }
    webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    webSettings.setSupportZoom(true);
    webSettings.setBuiltInZoomControls(true);
    webSettings.setUseWideViewPort(true);
    //设置为false，设置true，某些手机上某些情况会崩溃 https://bugly.qq.com/v2/crash-reporting/crashes/41f89fb766/7869?pid=1
    webSettings.setSupportMultipleWindows(false);
    //99是否允许WebView度超出以概览的方式载入页面，默认false。即缩小内容以适应屏幕宽度。该项设置在内容宽度超出WebView控件的宽度时生效，例如当getUseWideViewPort() 返回true时。
    //setLoadWithOverviewMode为true后在某些手机上面打开Webview会变形，比如oppo 5.1系统
    webSettings.setLoadWithOverviewMode(false);
    webSettings.setAppCacheEnabled(true);
    webSettings.setDatabaseEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setGeolocationEnabled(true);
    webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
    webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
    webSettings.setDatabasePath(getContext().getDir("databases", 0).getPath());
    webSettings.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
            .getPath());
    webSettings.setPluginState(WebSettings.PluginState.ON);
    webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    this.requestFocus();//请求获取焦点，防止view不能打开输入法问题
    this.requestFocusFromTouch();//请求获取焦点，防止view不能打开输入法问题
    this.setFocusableInTouchMode(true);
}
```
##### 如果您需要在单独设置webSettings、或者覆盖以上初始化的设置：
比如设置CacheMode
```
WebSettings webSettings = customWebview.getSettings();
webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
```
比如设置UserAgent

```
WebSettings webSettings = customWebview.getSettings();
webSettings.setUserAgentString("xxx");
```
等等

- 如果您需要设置ChromeClient，则需要继承CustomChromeClient，因为我们在CustomChromeClient中对视频的播放做了处理，如下

```
    private class MyCustomChromeCLient extends CustomChromeClient {

        public MyCustomChromeCLient(View activityNonVideoView, ViewGroup activityVideoView) {
            super(activityNonVideoView, activityVideoView);
        }

        public MyCustomChromeCLient(View activityNonVideoView, ViewGroup activityVideoView, View loadingView) {
            super(activityNonVideoView, activityVideoView, loadingView);
        }

        public MyCustomChromeCLient(View activityNonVideoView, ViewGroup activityVideoView, View loadingView, CustomWebview webView) {
            super(activityNonVideoView, activityVideoView, loadingView, webView);
        }
    ｝
```

### 版本树
- 0.1.4
    - 增加UserAgent的设置
- 0.1.3
    - setLoadWithOverviewMode为true后在某些手机上面打开Webview会变形，比如oppo 5.1系统，默认设置成false，如有需要可以自行获取Websetting修改
    - 【优化】适配Android8.0
    - 代码重构，使用更加方便
- 0.0.11
    - setLoadWithOverviewMode为true后在某些手机上面打开Webview会变形，比如oppo 5.1系统，默认设置成false，如有需要可以自行获取Websetting修改








### Thanks
- [VideoEnabledWebView](https://github.com/cprcrack/VideoEnabledWebView)


# License
```text
Copyright 2018 Wang Jing

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



        
