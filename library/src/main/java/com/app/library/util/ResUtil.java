package com.app.library.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;

import com.app.library.base.BaseConstant;


/**
 * Created by mls on 2016/10/8.
 */

public class ResUtil {
    private static Context context = BaseConstant.getInstance();

    /**
     * 获取String资源
     */
    public static String getResString(int resId) {
        return context.getString(resId);
    }

    /**
     * 获取String资源
     */
    public static String[] getArrayString(@ArrayRes int resId) {
        return context.getResources().getStringArray(resId);
    }

    /**
     * 获取Color资源
     */
    public static int getResColor(int resId) {
        return context.getResources().getColor(resId);
    }

    /**
     * 获取Color资源
     */
    public static Drawable getResDrawable(int resId) {
        return context.getResources().getDrawable(resId);
    }
}
