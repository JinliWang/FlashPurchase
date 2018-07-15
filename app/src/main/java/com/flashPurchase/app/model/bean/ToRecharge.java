package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/14.
 */

public class ToRecharge {

    /**
     * parameter : null
     * response : {"aucTime":1,"goodsId":14,"turnRecharge":1,"needToPay":1}
     * urlMapping : auc-bid
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
         * aucTime : 1
         * goodsId : 14
         * turnRecharge : 1
         * needToPay : 1
         */

        private int aucTime;
        private int goodsId;
        private int turnRecharge;
        private int needToPay;

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

        public int getTurnRecharge() {
            return turnRecharge;
        }

        public void setTurnRecharge(int turnRecharge) {
            this.turnRecharge = turnRecharge;
        }

        public int getNeedToPay() {
            return needToPay;
        }

        public void setNeedToPay(int needToPay) {
            this.needToPay = needToPay;
        }
    }
}
