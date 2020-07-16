package com.wangjing.qfwebview;

import android.view.View;

import com.wangjing.qfwebview.callback.IWebView;

public class WebviewStrategy {

    private IWebView iWebView;

    public WebviewStrategy(IWebView iWebView) {
        this.iWebView = iWebView;
    }

    public IWebView getiWebView() {
        return iWebView;
    }

    public View choiceWebview() {
        if (iWebView != null) {
            return iWebView.getWebview();
        }
        return null;
    }
}
