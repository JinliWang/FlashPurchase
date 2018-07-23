package com.app.library.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Description:
 *
 */

public class SystemUtil {

    /**
     * 启动一个app
     */
    public static void startAPP(Activity activity, String appPackageName) {
        try {
            Intent intent = activity.getPackageManager().getLaunchIntentForPackage(appPackageName);
            activity.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show("没有安装该应用");
        }
    }
}
