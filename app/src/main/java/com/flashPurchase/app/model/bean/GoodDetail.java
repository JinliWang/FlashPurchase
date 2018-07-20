package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/12.
 */

public class GoodDetail {


    /**
     * parameter : null
     * response : {"aucPeopleNum":0,"marketPrice":3199,"currentPrice":null,"showPics":"http://p65n22l5d.bkt.clouddn.com/1e218889-aba1-481c-a177-00cf1c8c54f6.jpg,http://p65n22l5d.bkt.clouddn.com/b161702c-03eb-467a-80fc-633021f5d2b6.jpg,http://p65n22l5d.bkt.clouddn.com/e7e86a58-df9f-43d9-9e3f-7cdb73ac1441.jpg,http://p65n22l5d.bkt.clouddn.com/1d9f0164-6df4-4c89-9e9c-468b2c0822eb.jpg,http://p65n22l5d.bkt.clouddn.com/43f64bc3-4015-44b5-ac4e-0ba524b707ca.jpg,http://p65n22l5d.bkt.clouddn.com/c117d4b8-1337-4744-8bea-a62434ea2db2.jpg,","store":11,"auctioneerId":2,"isTen":0,"lookPeopleNum":10,"recentDeal":[],"collectPeopleNum":1,"auctioneerPic":"http://p65n22l5d.bkt.clouddn.com/1f870e68-b0ba-4e21-8898-88da952d6e2e.jpg","name":"Apple iPad 平板电脑 2018年新款9.7英寸","auctioneerNumber":"2000643","id":6,"time":1,"auctioneerName":"王琴","initCd":0,"aucCd":"00:00:0-61","pics":"http://p65n22l5d.bkt.clouddn.com/3affbed4-ae42-4ba0-aeaf-a6bd8bfb8444.jpg,http://p65n22l5d.bkt.clouddn.com/c43464fc-43b2-46ef-b857-0f3261147d30.jpg,","collect":1,"categoryId":5,"auctioneerLevel":1,"status":1}
     * urlMapping : goods-goodsDetail
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
        /**
         * aucPeopleNum : 0
         * marketPrice : 3199.0
         * currentPrice : null
         * showPics : http://p65n22l5d.bkt.clouddn.com/1e218889-aba1-481c-a177-00cf1c8c54f6.jpg,http://p65n22l5d.bkt.clouddn.com/b161702c-03eb-467a-80fc-633021f5d2b6.jpg,http://p65n22l5d.bkt.clouddn.com/e7e86a58-df9f-43d9-9e3f-7cdb73ac1441.jpg,http://p65n22l5d.bkt.clouddn.com/1d9f0164-6df4-4c89-9e9c-468b2c0822eb.jpg,http://p65n22l5d.bkt.clouddn.com/43f64bc3-4015-44b5-ac4e-0ba524b707ca.jpg,http://p65n22l5d.bkt.clouddn.com/c117d4b8-1337-4744-8bea-a62434ea2db2.jpg,
         * store : 11
         * auctioneerId : 2
         * isTen : 0
         * lookPeopleNum : 10
         * recentDeal : []
         * collectPeopleNum : 1
         * auctioneerPic : http://p65n22l5d.bkt.clouddn.com/1f870e68-b0ba-4e21-8898-88da952d6e2e.jpg
         * name : Apple iPad 平板电脑 2018年新款9.7英寸
         * auctioneerNumber : 2000643
         * id : 6
         * time : 1
         * auctioneerName : 王琴
         * initCd : 0
         * aucCd : 00:00:0-61
         * pics : http://p65n22l5d.bkt.clouddn.com/3affbed4-ae42-4ba0-aeaf-a6bd8bfb8444.jpg,http://p65n22l5d.bkt.clouddn.com/c43464fc-43b2-46ef-b857-0f3261147d30.jpg,
         * collect : 1
         * categoryId : 5
         * auctioneerLevel : 1
         * status : 1
         */

        private int aucPeopleNum;
        private double marketPrice;
        private double currentPrice;
        private String showPics;
        private int store;
        private int auctioneerId;
        private int isTen;
        private int lookPeopleNum;
        private int collectPeopleNum;
        private String auctioneerPic;
        private String name;
        private String auctioneerNumber;
        private int id;
        private int time;
        private String auctioneerName;
        private int initCd;
        private String aucCd;
        private String pics;
        private int collect;
        private int categoryId;
        private int auctioneerLevel;
        private int status;
        private List<?> recentDeal;

        public int getAucPeopleNum() {
            return aucPeopleNum;
        }

        public void setAucPeopleNum(int aucPeopleNum) {
            this.aucPeopleNum = aucPeopleNum;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getShowPics() {
            return showPics;
        }

        public void setShowPics(String showPics) {
            this.showPics = showPics;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public int getAuctioneerId() {
            return auctioneerId;
        }

        public void setAuctioneerId(int auctioneerId) {
            this.auctioneerId = auctioneerId;
        }

        public int getIsTen() {
            return isTen;
        }

        public void setIsTen(int isTen) {
            this.isTen = isTen;
        }

        public int getLookPeopleNum() {
            return lookPeopleNum;
        }

        public void setLookPeopleNum(int lookPeopleNum) {
            this.lookPeopleNum = lookPeopleNum;
        }

        public int getCollectPeopleNum() {
            return collectPeopleNum;
        }

        public void setCollectPeopleNum(int collectPeopleNum) {
            this.collectPeopleNum = collectPeopleNum;
        }

        public String getAuctioneerPic() {
            return auctioneerPic;
        }

        public void setAuctioneerPic(String auctioneerPic) {
            this.auctioneerPic = auctioneerPic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuctioneerNumber() {
            return auctioneerNumber;
        }

        public void setAuctioneerNumber(String auctioneerNumber) {
            this.auctioneerNumber = auctioneerNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getAuctioneerName() {
            return auctioneerName;
        }

        public void setAuctioneerName(String auctioneerName) {
            this.auctioneerName = auctioneerName;
        }

        public int getInitCd() {
            return initCd;
        }

        public void setInitCd(int initCd) {
            this.initCd = initCd;
        }

        public String getAucCd() {
            return aucCd;
        }

        public void setAucCd(String aucCd) {
            this.aucCd = aucCd;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getAuctioneerLevel() {
            return auctioneerLevel;
        }

        public void setAuctioneerLevel(int auctioneerLevel) {
            this.auctioneerLevel = auctioneerLevel;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<?> getRecentDeal() {
            return recentDeal;
        }

        public void setRecentDeal(List<?> recentDeal) {
            this.recentDeal = recentDeal;
        }
    }
}
