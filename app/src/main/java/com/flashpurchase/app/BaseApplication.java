package com.flashpurchase.app;

import android.app.Application;

import com.app.library.base.BaseActivity;
import com.app.library.base.BaseConstant;


/**
 * Description: 基类Application
 * Create By: MLS Co,Ltd
 */

public class BaseApplication extends Application {

    //发正式包 注意事项
    // 1 build.gradle 下极光注册id变更
    // 2 包名 应用名 变更  版本号更新
    // 3 各开关控制
    // 4 登陆验证 控制
    // 5 SPManager token APiManager IndexApiMAnager token

    public static Application application;

    public static boolean isTest = BaseConstant.isTest;  //kaiguan


    {
        application = this;
        BaseActivity.baseApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Application getInstance() {
        return application;
    }

}