package com.app.library;

import android.os.Environment;

/**
 * Description: 常量
 * Create By: MLS Co,Ltd
 */

public class Constant {

    //http请求基础路径
    public static final Integer PAGESIZE = 10;

    public static final String DIR = Environment.getExternalStorageDirectory() + "";
    //文件存放路径
    public final static String DIR_FILE = DIR + "/JSTI/.File";
    //文件存放路径
    public final static String DIR_CACHE = DIR + "/JSTI/Cache";
    //app存放路径
    public final static String DIR_UPDATE_TYPE = "/JSTI/Update";
    public final static String DIR_UPDATE = DIR + DIR_UPDATE_TYPE;
    public final static String DIR_UPDATE_NAME = "jsti.apk";

    //bugly appKey
    public final static String BUGLY_APPKEY = "6ce1a2e78d";

}
