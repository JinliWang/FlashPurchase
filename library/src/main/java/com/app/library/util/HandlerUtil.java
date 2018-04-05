package com.app.library.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.app.library.base.BaseActivity;


/**
 * Description: Handler 帮助类
 * Create By: MLS Co,Ltd
 */

public class HandlerUtil {

//    private static Application App = BaseConstant.getInstance();

    private static Application App = BaseActivity.baseApplication;

    public static Context getContext() {
        return App;
    }

    public static Handler getMainHandler() {
        return new Handler(App.getMainLooper());
    }

    /**
     * 主线程中延时post事件
     *
     * @param runnable
     * @param delayMillis
     * @return
     */
    public static boolean postMainDelayed(Runnable runnable, long delayMillis) {
        return getMainHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 主线程中立即post事件
     *
     * @param runnable
     * @return
     */
    public static boolean postMain(Runnable runnable) {
        return getMainHandler().post(runnable);
    }

    /**
     * 移除事件
     * @param runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getMainHandler().removeCallbacks(runnable);
    }
}
