package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/13.
 */

public class MyAucList {

    /**
     * parameter : null
     * response : [{"date":"2018-07-13 00:25:53","marketPrice":500,"aucTime":17,"goodsId":1,"currentPrice":0.7,"time":3,"userId":7,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","goodsName":"和田玉"},{"date":"2018-07-13 00:26:38","marketPrice":6999,"aucTime":8,"goodsId":2,"currentPrice":100.2,"time":2,"userId":7,"pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg","goodsName":"Apple iPhone 8 pluse 256G 颜色随机"}]
     * urlMapping : goods-myAucIng
     */

    private Object parameter;
    private String urlMapping;
    private List<ResponseBean> response;

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * date : 2018-07-13 00:25:53
         * marketPrice : 500.0
         * aucTime : 17
         * goodsId : 1
         * currentPrice : 0.7
         * time : 3
         * userId : 7
         * pics : http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg
         * goodsName : 和田玉
         */

        private String date;
        private double marketPrice;
        private int aucTime;
        private int goodsId;
        private double currentPrice;
        private int time;
        private int userId;
        private String pics;
        private String goodsName;
        private String status;
        private String orderSt;
        private double actualPayment;
        private String dealUser;
        private double finalPrice;
        private String orderId;
        private double shopCoin;
        private String id;
        private String type;
        private String aucSt;

        public String getOrderSt() {
            return orderSt;
        }

        public double getActualPayment() {
            return actualPayment;
        }

        public double getShopCoin() {
            return shopCoin;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getAucSt() {
            return aucSt;
        }

        public double getShopcoin() {
            return shopCoin;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getDealUser() {
            return dealUser;
        }

        public double getFinalPrice() {
            return finalPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getAucTime() {
            return aucTime;
        }

        public void setAucTime(int aucTime) {
            this.aucTime = aucTime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }
}
