package com.flashPurchase.app.model;

/**
 * Created by 10951 on 2018/5/3.
 */

public class Dynamics {
    private String time;
    private String name;
    private String comsumer;
    private String marketprice;
    private String currentprice;
    private String rate;

    private String dynamicName;

    public String getDynamicName() {
        return dynamicName;
    }

    public void setDynamicName(String dynamicName) {
        this.dynamicName = dynamicName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComsumer() {
        return comsumer;
    }

    public void setComsumer(String comsumer) {
        this.comsumer = comsumer;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public String getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(String currentprice) {
        this.currentprice = currentprice;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
