package com.wangjing.qfwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.smtt.sdk.WebView;
import com.wangjing.qfwebview.callback.IWebView;

public class CustomWebviewX5 extends WebView implements IWebView {

    public CustomWebviewX5(Context context) {
        super(context);
        init();
    }

    public CustomWebviewX5(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomWebviewX5(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
    }

    @Override
    public View getWebview() {
        return this;
    }

    @Override
    public boolean canGoback() {
        return this.canGoBack();
    }
}
