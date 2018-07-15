package com.flashPurchase.app.event;

/**
 * Created by 10951 on 2018/7/15.
 */

public class LoginSuccessEvent {
    private String openId;
    private String nickName;
    private String icon;

    public LoginSuccessEvent() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
