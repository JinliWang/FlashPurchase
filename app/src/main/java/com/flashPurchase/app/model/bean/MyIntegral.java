package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyIntegral {


    /**
     * parameter : null
     * response : {"income":[{"coinType":4,"amount":5,"pcType":4,"createTime":"2018-07-15 17:29:38","type":1,"userId":18}],"cost":[],"userId":18,"point":5}
     * urlMapping : account-myPoint
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
         * income : [{"coinType":4,"amount":5,"pcType":4,"createTime":"2018-07-15 17:29:38","type":1,"userId":18}]
         * cost : []
         * userId : 18
         * point : 5
         */

        private int userId;
        private int point;
        private List<IncomeBean> income;
        private List<CostBean> cost;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
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
             * coinType : 4
             * amount : 5
             * pcType : 4
             * createTime : 2018-07-15 17:29:38
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
             * coinType : 4
             * amount : 5
             * pcType : 4
             * createTime : 2018-07-15 17:29:38
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
