package com.wangjing.qfwebview;

import com.wangjing.qfwebview.callback.IWebView;

public class WebviewStrategy {

    private IWebView iWebView;

    private WebviewStrategy(IWebView iWebView) {
        this.iWebView = iWebView;
    }

    public void choiceWebview() {
        if (iWebView != null) {
            iWebView.getWebview();
        }
    }
}
