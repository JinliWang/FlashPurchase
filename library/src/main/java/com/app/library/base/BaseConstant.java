package com.app.library.base;

import android.app.Application;

/**
 * Description:
 *
 */

public class BaseConstant {

    public static boolean isTest = true;  //kaiguan

    public static Application getInstance() {
        return BaseActivity.baseApplication;
    }

}
