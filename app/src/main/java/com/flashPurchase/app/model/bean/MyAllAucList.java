package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/13.
 */

public class MyAllAucList {


    /**
     * parameter : null
     * response : {"aucToPay":[],"aucToBask":[],"aucIng":[{"date":"2018-07-14 22:02:45","marketPrice":349,"aucTime":4,"goodsId":1,"currentPrice":1.5,"time":3,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/e15bd098-73fe-4790-8e3c-50416189b92b.jpg","goodsName":"小米（MI）AI音箱蓝牙"},{"date":"2018-07-16 20:33:35","marketPrice":3299,"aucTime":5,"goodsId":3,"currentPrice":138.4,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/3762f7c3-cb7f-4dfd-89c9-3adc5a019e97.jpg","goodsName":"小米8 全面屏游戏智能手机"},{"date":"2018-07-18 00:01:53","marketPrice":3999,"goodsId":9,"currentPrice":0.4,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/1ca7496f-6783-44d9-950b-bbb2a6e7ae35.jpg","goodsName":"vivo NEX"},{"date":"2018-07-14 14:45:18","marketPrice":79,"goodsId":13,"currentPrice":1.5,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/00651962-b59e-4385-947e-1f40968c1a48.jpg","goodsName":"日东红茶"},{"date":"2018-07-14 22:12:55","marketPrice":4199,"aucTime":35,"goodsId":14,"currentPrice":3,"time":1,"userId":18,"pics":"http://p65n22l5d.bkt.clouddn.com/b86a3b0e-12b4-49c5-8c55-b8fa90c42f9b.jpg","goodsName":"苹果Apple Watch3 Series3"}],"aucSuc":[],"aucToReceive":[],"aucOut":[]}
     * urlMapping : goods-myAucIng
     */

    private Object parameter;
    private ResponseBean response;
    private String urlMapping;

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public static class ResponseBean {
        private List<AucIngBean> aucToPay;
        private List<AucIngBean> aucToBask;
        private List<AucIngBean> aucIng;
        private List<AucIngBean> aucSuc;
        private List<AucIngBean> aucToReceive;
        private List<AucIngBean> aucOut;
        private List<AucIngBean> all;

        public void setAll(List<AucIngBean> all) {
            this.all = all;
        }

        public List<AucIngBean> getAucToPay() {
            return aucToPay;
        }

        public List<AucIngBean> getAucToBask() {
            return aucToBask;
        }

        public List<AucIngBean> getAucIng() {
            return aucIng;
        }

        public List<AucIngBean> getAucSuc() {
            return aucSuc;
        }

        public List<AucIngBean> getAucToReceive() {
            return aucToReceive;
        }

        public List<AucIngBean> getAucOut() {
            return aucOut;
        }

        public List<AucIngBean> getAll() {
            return all;
        }

        public static class AucIngBean {
            /**
             * date : 2018-07-14 22:02:45
             * marketPrice : 349.0
             * aucTime : 4
             * goodsId : 1
             * currentPrice : 1.5
             * time : 3
             * userId : 18
             * pics : http://p65n22l5d.bkt.clouddn.com/e15bd098-73fe-4790-8e3c-50416189b92b.jpg
             * goodsName : 小米（MI）AI音箱蓝牙
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
            private String actualPayment;
            private String shopCoin;
            private String id;
            private String type;
            private String aucSt;
            private String dealUser;

            public String getDealUser() {
                return dealUser;
            }

            public String getOrderSt() {
                return orderSt;
            }

            public String getActualPayment() {
                return actualPayment;
            }

            public String getShopCoin() {
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
}
