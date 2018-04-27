package com.flashpurchase.app.model;

/**
 * Created by 10951 on 2018/4/27.
 */

public class TimeBean {
    public String name;
    //倒计时长，单位毫秒
    public long expirationTime;

    public TimeBean(String name, long expirationTime) {
        this.name = name;
        this.expirationTime = expirationTime;
    }
}
