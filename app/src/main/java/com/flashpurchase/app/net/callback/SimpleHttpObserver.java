package com.flashpurchase.app.net.callback;


import com.app.library.util.ToastUtil;

/**
 * Description:只需处理成功回调弹出msg的observer
 * Create By: MLS Co,Ltd
 */

public class SimpleHttpObserver extends HttpObserver<Object> {

    @Override
    public void success(Object o) {

    }

    @Override
    public void success(Object o, String msg) {
        success(o);
        ToastUtil.show(msg);
    }
}
