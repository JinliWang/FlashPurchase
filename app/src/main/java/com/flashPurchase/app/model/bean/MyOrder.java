package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/15.
 */

public class MyOrder {

    /**
     * parameter : null
     * response : [{"date":"2018-07-14 22:02:45","marketPrice":349,"goodsId":1,"currentPrice":0.9,"time":3,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/e15bd098-73fe-4790-8e3c-50416189b92b.jpg","goodsName":"小米（MI）AI音箱蓝牙"},{"date":"2018-07-14 14:45:18","marketPrice":79,"goodsId":13,"currentPrice":0.2,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/00651962-b59e-4385-947e-1f40968c1a48.jpg","goodsName":"日东红茶"},{"date":"2018-07-14 22:12:55","marketPrice":4199,"aucTime":5,"goodsId":14,"currentPrice":1.6,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/b86a3b0e-12b4-49c5-8c55-b8fa90c42f9b.jpg","goodsName":"苹果Apple Watch3 Series3"}]
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
         * date : 2018-07-14 22:02:45
         * marketPrice : 349.0
         * goodsId : 1
         * currentPrice : 0.9
         * time : 3
         * userId : 18
         * pics : http://p65n22l5d.bkt.clouddn.com/e15bd098-73fe-4790-8e3c-50416189b92b.jpg
         * goodsName : 小米（MI）AI音箱蓝牙
         * aucTime : 5
         */

        private String date;
        private double marketPrice;
        private int goodsId;
        private double currentPrice;
        private int time;
        private int userId;
        private String pics;
        private String goodsName;
        private int aucTime;

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

        public int getAucTime() {
            return aucTime;
        }

        public void setAucTime(int aucTime) {
            this.aucTime = aucTime;
        }
    }
}
