package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyIntegral {

    /**
     * parameter : null
     * response : {"income":[],"cost":[],"userId":7,"point":5}
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
         * income : []
         * cost : []
         * userId : 7
         * point : 5
         */

        private int userId;
        private int point;
        private List<?> income;
        private List<?> cost;

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

        public List<?> getIncome() {
            return income;
        }

        public void setIncome(List<?> income) {
            this.income = income;
        }

        public List<?> getCost() {
            return cost;
        }

        public void setCost(List<?> cost) {
            this.cost = cost;
        }
    }
}
