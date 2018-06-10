package com.flashPurchase.app.model;

/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class CommonResponse<T> {
    String state;
    String message;
    String msg;
    String code;
    T obj;

    public String getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public T getObj() {
        return obj;
    }
}
