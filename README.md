# AndroidWebview [![](https://ci.novoda.com/buildStatus/icon?job=bintray-release)](https://ci.novoda.com/job/bintray-release/lastBuild/console) [![Download](https://api.bintray.com/packages/wangjinggm/maven/androidwebview/images/download.svg) ](https://bintray.com/wangjinggm/maven/androidwebview/_latestVersion) [![license](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/Lee-Wang-Jing/androidwebview/blob/master/LICENSE)

AndroidWebview技术交流QQ群：598403807       
![image](https://raw.githubusercontent.com/Lee-Wang-Jing/AndroidWebview/master/image/QQ.png)  
AndroidWebview技术交流钉钉群，钉钉群里面可以收到Github实时的更新提醒哦：  
![image](https://raw.githubusercontent.com/Lee-Wang-Jing/AndroidWebview/master/image/DingD.png)

加群前请务必阅读[群行为规范](https://github.com/Lee-Wang-Jing/GroupStandard)
有问题或者某种需求欢迎加群或者提issues，Thanks

AndroidWebview是Webview的封装工具类，主要兼容了Webview中全屏播放视频的问题以及一些Webview的默认配置，开发使用，方便快捷

# Dependencies
* Gradle
```
implementation 'com.wangjing:androidwebview:0.0.11'
```
* Maven
```xml
<dependency>
  <groupId>com.wangjing</groupId>
  <artifactId>androidwebview</artifactId>
  <version>0.0.11</version>
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
- 如果需要设置WebSettings，CustomWebview内部已经默认初始化了一些常用的WebSettings，具体如下：

```
@SuppressLint("SetJavaScriptEnabled")
private void initWebViewSettings() {
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
      webSettings.setLoadWithOverviewMode(true);
    webSettings.setAppCacheEnabled(true);
    webSettings.setDatabaseEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setGeolocationEnabled(true);
    webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
    webSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
    webSettings.setDatabasePath(getContext().getDir("databases", 0).getPath());
    webSettings.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
            .getPath());
    webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
    webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
}
```
##### 如果您需要在单独设置webSettings、或者覆盖以上初始化的设置：
比如设置Debug模式
```
WebSettings webSettings = customWebview.getSettings();
if (BuildConfig.DEBUG || MyApplication.getInstance().isWebViewDebug()) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        WebView.setWebContentsDebuggingEnabled(true);
    }
}
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

#### 设置下拉刷新的控件SwipeRefreshLayout，监听有没有滑动到顶部

```
webview.setSwipeRefreshLayout(swipeRefreshLayout);
```



### 版本树
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



        
