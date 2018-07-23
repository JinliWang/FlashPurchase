package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/20.
 */

public class WuliuDetail {

    /**
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
         * specificAddress : 铁心桥
         * marketPrice : 3299.0
         * orderSt : 1
         * shopCoin : 0
         * goodsId : 3
         * remark :
         * type : 2
         * userId : 18
         * addressId : 8
         * actualPayment : 3299.0
         * createTime : 2018-07-20 02:44:08
         * phone : 17626011217
         * street :
         * name : 小米8 全面屏游戏智能手机
         * pics : http://p65n22l5d.bkt.clouddn.com/3762f7c3-cb7f-4dfd-89c9-3adc5a019e97.jpg
         */

        private String specificAddress;
        private double marketPrice;
        private int orderSt;
        private int shopCoin;
        private int goodsId;
        private String remark;
        private int type;
        private int userId;
        private int addressId;
        private double actualPayment;
        private String createTime;
        private String phone;
        private String street;
        private String name;
        private String nickname;
        private String recipient;
        private String region;
        private String pics;

        public String getSpecificAddress() {
            return specificAddress;
        }

        public void setSpecificAddress(String specificAddress) {
            this.specificAddress = specificAddress;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getOrderSt() {
            return orderSt;
        }

        public void setOrderSt(int orderSt) {
            this.orderSt = orderSt;
        }

        public int getShopCoin() {
            return shopCoin;
        }

        public void setShopCoin(int shopCoin) {
            this.shopCoin = shopCoin;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public double getActualPayment() {
            return actualPayment;
        }

        public void setActualPayment(double actualPayment) {
            this.actualPayment = actualPayment;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }
    }
}
