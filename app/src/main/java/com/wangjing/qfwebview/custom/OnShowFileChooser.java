package com.wangjing.qfwebview.custom;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;


public interface OnShowFileChooser {
    boolean onShowFileChooser(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);
}
