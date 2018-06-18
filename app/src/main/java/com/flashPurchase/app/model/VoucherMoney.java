package com.flashPurchase.app.model;

/**
 * Created by 10951 on 2018/6/14.
 */

public class VoucherMoney {
    //textview布局
    public static final int TWO = 1002;
    //edittext布局
    public static final int THREE = 1003;
    public int type;
    public Object data;

    public VoucherMoney(int type, Object data) {
        this.type = type;
        this.data = data;
    }
}
