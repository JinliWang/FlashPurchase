package com.app.library.view.webview;

import android.webkit.JavascriptInterface;

public interface WebViewJavaScriptFunction {

    @JavascriptInterface
    void back(String tag);
}
