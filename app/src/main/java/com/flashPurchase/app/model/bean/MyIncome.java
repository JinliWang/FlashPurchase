package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyIncome {


    /**
     * parameter : null
     * response : {"payCoin":3,"income":[{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:36:51","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:36:51","type":1,"userId":18},{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:47:34","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:47:34","type":1,"userId":18},{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:51:53","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:51:53","type":1,"userId":18},{"coinType":2,"amount":8,"pcType":2,"createTime":"2018-07-15 00:05:00","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-16 00:05:00","type":1,"userId":18}],"cost":[],"shopCoin":0,"freeCoin":10,"userId":18}
     * urlMapping : account-myAsset
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
         * payCoin : 3
         * income : [{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:36:51","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:36:51","type":1,"userId":18},{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:47:34","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:47:34","type":1,"userId":18},{"coinType":1,"amount":1,"pcType":2,"createTime":"2018-07-14 16:51:53","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-14 16:51:53","type":1,"userId":18},{"coinType":2,"amount":8,"pcType":2,"createTime":"2018-07-15 00:05:00","type":1,"userId":18},{"coinType":2,"amount":5,"pcType":2,"createTime":"2018-07-16 00:05:00","type":1,"userId":18}]
         * cost : []
         * shopCoin : 0
         * freeCoin : 10
         * userId : 18
         */

        private int payCoin;
        private int shopCoin;
        private int freeCoin;
        private int userId;
        private List<IncomeBean> income;
        private List<CostBean> cost;

        public int getPayCoin() {
            return payCoin;
        }

        public void setPayCoin(int payCoin) {
            this.payCoin = payCoin;
        }

        public int getShopCoin() {
            return shopCoin;
        }

        public void setShopCoin(int shopCoin) {
            this.shopCoin = shopCoin;
        }

        public int getFreeCoin() {
            return freeCoin;
        }

        public void setFreeCoin(int freeCoin) {
            this.freeCoin = freeCoin;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<IncomeBean> getIncome() {
            return income;
        }

        public void setIncome(List<IncomeBean> income) {
            this.income = income;
        }

        public List<CostBean> getCost() {
            return cost;
        }

        public void setCost(List<CostBean> cost) {
            this.cost = cost;
        }

        public static class IncomeBean {
            /**
             * coinType : 1
             * amount : 1
             * pcType : 2
             * createTime : 2018-07-14 16:36:51
             * type : 1
             * userId : 18
             */

            private int coinType;
            private int amount;
            private int pcType;
            private String createTime;
            private int type;
            private int userId;

            public int getCoinType() {
                return coinType;
            }

            public void setCoinType(int coinType) {
                this.coinType = coinType;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getPcType() {
                return pcType;
            }

            public void setPcType(int pcType) {
                this.pcType = pcType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
        }

        public static class CostBean {
            /**
             * coinType : 1
             * amount : 1
             * pcType : 2
             * createTime : 2018-07-14 16:36:51
             * type : 1
             * userId : 18
             */

            private int coinType;
            private int amount;
            private int pcType;
            private String createTime;
            private int type;
            private int userId;

            public int getCoinType() {
                return coinType;
            }

            public void setCoinType(int coinType) {
                this.coinType = coinType;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getPcType() {
                return pcType;
            }

            public void setPcType(int pcType) {
                this.pcType = pcType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
        }
    }
}
