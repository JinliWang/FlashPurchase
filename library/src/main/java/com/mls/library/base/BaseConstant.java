package com.mls.library.base;

import android.app.Application;

/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class BaseConstant {

    public static boolean isDebug = true;  // 会议系统是否debug模式

    public static boolean CrmDebug = false;  // crm系统是否debug模式  true  debug   false  notdebug

    public static boolean isTest = true;  //kaiguan

    public static boolean isDidiDebug = true;  //kaiguan

    public static boolean isJYDebug = false;  //是否给行政部测试   如果是   携程userId  和账号密码 用 jsti@crm  //如果密码用jsti@crm登陆 会自动改为true

    public static Application getInstance() {
        return BaseActivity.baseApplication;
    }

}
