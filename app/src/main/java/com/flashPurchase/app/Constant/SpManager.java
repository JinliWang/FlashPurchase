package com.flashPurchase.app.Constant;

import com.flashPurchase.app.model.bean.Login;
import com.flashPurchase.app.model.bean.MyAddress;
import com.flashPurchase.app.model.bean.MyInfo;
import com.flashPurchase.app.model.bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.app.library.util.LogUtil;
import com.app.library.util.SpUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Description: SP管理类
 * Create By: MLS Co,Ltd
 * e-mail:wxdingcn@gmail.com
 */

public class SpManager {

    //保存用户token
    public static void setToken(String token) {
        SpUtil.put("token", token);
    }

    public static String getToken() {
        return SpUtil.getString("token", "");
    }

    //保存用户token
    public static void setPhone(String phone) {
        SpUtil.put("phone", phone);
    }

    public static String getPhone() {
        return SpUtil.getString("phone", "");
    }

    //保存clientId
    public static void setClientId(String clientId) {
        SpUtil.put("clientId", clientId);
    }

    public static String getClientId() {
        return SpUtil.getString("clientId", "");
    }

    public static void setIsFirst(boolean isFirst) {
        SpUtil.put("isFirst", isFirst);
    }

    public static boolean getIsFirst() {
        return SpUtil.getBoolean("isFirst",false);
    }

    public static String getHost() {
        return SpUtil.getString("host",  Host.PUBLIC_HOST);
    }

    public static void saveHost(String b) {
        SpUtil.put("host", b);
    }

    public static String getMark() {
        return SpUtil.getString("mark",  "");
    }

    public static void setMark(String b) {
        SpUtil.put("mark", b);
    }

    public static String getRegisterTime() {
        return SpUtil.getString("registerTime",  "");
    }

    public static void setRegisterTime(String b) {
        SpUtil.put("registerTime", b);
    }

    //保存用户token
    public static void setCookie(List<String> token) {
        String a = token == null ? "" : new Gson().toJson(token);
        SpUtil.put("cookie", a);
    }

    public static List<String> getCookie() {
        try {
            Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> orgs = new Gson().fromJson(SpUtil.getString("cookie", ""), type);

            return orgs == null ? new ArrayList<String>() : orgs;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveCurrentVersionCode(String curVersionCode) {
        SpUtil.put("versionCode", curVersionCode);
    }

    public static String getCurrentVersionCode() {
        return SpUtil.getString("versionCode", "");
    }

    public static void saveCurrentVersion(String curVersionName) {
        SpUtil.put("version", curVersionName);
    }

    public static String getCurrentVersion() {
        return SpUtil.getString("version", "");
    }

    public static void saveNewVersion(boolean b) {
        SpUtil.put("isNewVersion", b);
    }

    //保存推送历史消息
    public static void setPushMsg(int num) {
        SpUtil.put("pushMsg", num);
    }

    public static int getPushMsg() {
        return SpUtil.getInt("pushMsg", 0);
    }

    public static void loginOut() {

        setToken("");
        setLastSyStem("");
        setIsFirst(false);
//        DriverApiManager.init();
//        setUserInfo(new DriverUser());
        setUserName(SpManager.getUserName());
        setUserPwd("");
        setOAUserId(0);

    }

    /**
     * 保存上次登陆系统
     *
     * @param sys
     */
    public static void setLastSyStem(String sys) {
        SpUtil.put("lastSyStem", sys);
    }

    public static String getLastSyStem() {
        return SpUtil.getString("lastSyStem", "");
    }


    /**
     * 保存用户名
     *
     * @param userName
     */
    public static void setUserName(String userName) {
        SpUtil.put("sysUserName", userName);
    }

    public static String getUserName() {
        return SpUtil.getString("sysUserName", "");
    }


    /**
     * 保存用户名
     *
     * @param userName
     */
    public static void setName(String userName) {
        SpUtil.put("Name", userName);
    }

    public static String getName() {
        return SpUtil.getString("Name", "");
    }

    /**
     * 保存密码
     *
     * @param pwd
     */
    public static void setUserPwd(String pwd) {
        SpUtil.put("sysUserPwd", pwd);
    }

    public static String getUserPwd() {
        return SpUtil.getString("sysUserPwd", "");
    }

    /**
     * 保存OA系统userId
     */
    public static void setOAUserId(int oaUserId) {
        SpUtil.put("OAUserId", oaUserId);
    }

    public static int getOAUserId() {
        return SpUtil.getInt("OAUserId", 0);
    }


    /**
     * 保存用户app列表 版本号
     *
     * @param appMenuListVersion
     */
    public static void setAppMenuListVersion(int appMenuListVersion) {
        SpUtil.put("appMenuListVersion", appMenuListVersion);
    }

    public static int getAppMenuListVersion() {
        return SpUtil.getInt("appMenuListVersion", 1);
    }


    /**
     * 保存上次登陆时间
     */
    public static void setLastLoadTime() {
        LogUtil.d("setLastLoadTime  " + System.currentTimeMillis());
        SpUtil.put("lastLoadTime", System.currentTimeMillis());

    }

    public static Long getLastLoadTime() {
        return SpUtil.getLong("lastLoadTime", System.currentTimeMillis());
    }

//    //保存用户信息
    public static void setUserInfo(Login user) {
        SpUtil.put("userInfo", new Gson().toJson(user));
    }

    public static Login getUserInfo() {
        try {
            Login user = new Gson().fromJson(SpUtil.getString("userInfo", ""), Login.class);
            return user == null ? new Login() : user;
        } catch (Exception e) {
            return new Login();
        }
    }

    //    //保存用户信息
    public static void setMyInfo(MyInfo user) {
        SpUtil.put("myInfo", new Gson().toJson(user));
    }

    public static MyInfo getMyInfo() {
        try {
            MyInfo user = new Gson().fromJson(SpUtil.getString("myInfo", ""), MyInfo.class);
            return user == null ? new MyInfo() : user;
        } catch (Exception e) {
            return new MyInfo();
        }
    }

    //    //保存用户信息
    public static void setMyAddress(MyAddress user) {
        SpUtil.put("address", new Gson().toJson(user));
    }

    public static MyAddress getMyAddress() {
        try {
            MyAddress address = new Gson().fromJson(SpUtil.getString("address", ""), MyAddress.class);
            return address == null ? new MyAddress() : address;
        } catch (Exception e) {
            return new MyAddress();
        }
    }

}
