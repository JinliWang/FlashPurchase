package com.app.library.net.okhttp.listener;

import java.util.ArrayList;

/**
 * Description: 当需要专门处理Cookie时创建此回调接口
 *
 */

public interface DisposeHandleCookieListener extends DisposeDataListener {
    void onCookie(ArrayList<String> cookieStrLists);
}
