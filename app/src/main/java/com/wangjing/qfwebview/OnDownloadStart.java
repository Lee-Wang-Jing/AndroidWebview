package com.wangjing.qfwebview;

public interface OnDownloadStart {
    void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength);
}
