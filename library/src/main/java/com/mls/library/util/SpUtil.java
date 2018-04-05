package com.mls.library.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mls.library.base.BaseConstant;

import java.util.Map;


/**
 * Description: SharedPreferences帮助类
 * Create By: MLS Co,Ltd
 */
public class SpUtil {
    static SharedPreferences mShared = PreferenceManager.getDefaultSharedPreferences(BaseConstant.getInstance());

    public static boolean getBoolean(String key, boolean defValue) {
        return mShared.getBoolean(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return mShared.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return mShared.getLong(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return mShared.getFloat(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return mShared.getString(key, defValue);
    }

    public static Map<String, ?> getAll() {
        return mShared.getAll();
    }

    /**
     * 保存一个键值对
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        if (value instanceof Boolean) {
            mShared.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer ) {
            mShared.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Long) {
            mShared.edit().putLong(key, (Long) value).apply();
        } else if (value instanceof Float) {
            mShared.edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Double) {
            mShared.edit().putFloat(key, Float.parseFloat(value.toString())).apply();
        } else if (value instanceof String) {
            mShared.edit().putString(key, (String) value).apply();
        } else {
            if (null == value) {
                value = "";
            }
            mShared.edit().putString(key, value.toString()).apply();
        }
    }

    public static void remove(String key) {
        mShared.edit().remove(key).apply();
    }

    public static void clear() {
        mShared.edit().clear().apply();
    }

    public static boolean contains(String key) {
        return mShared.contains(key);
    }

}
