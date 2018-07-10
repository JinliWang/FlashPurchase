package com.flashPurchase.app;

import com.app.library.Constant;
import com.mob.MobSDK;

import java.io.File;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_APPKEY, false);
//        Stetho.initializeWithDefaults(this);
//        //设置该CrashHandler为程序的默认处理器
//        if (!BaseApplication.isTest) {
//            CrashHandler catchExcep = new CrashHandler(this);
//            Thread.setDefaultUncaughtExceptionHandler(catchExcep);
//        }
//        initJPush();
        File file = new File(Constant.DIR_FILE);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(Constant.DIR_UPDATE);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(Constant.DIR_CACHE);
        if (!file3.exists()) {
            file3.mkdirs();
        }

        MobSDK.init(this);
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
//    private void initJPush() {
//        JPushInterface.setDebugMode(BaseApplication.isTest);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);            // 初始化 JPush
//    }
}
