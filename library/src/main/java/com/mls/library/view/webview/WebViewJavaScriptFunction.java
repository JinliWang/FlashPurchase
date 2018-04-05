package com.mls.library.view.webview;

import android.webkit.JavascriptInterface;

public interface WebViewJavaScriptFunction {

    @JavascriptInterface
    void back(String tag);
}
