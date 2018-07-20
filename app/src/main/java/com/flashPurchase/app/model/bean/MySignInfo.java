package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/15.
 */

public class MySignInfo {

    /**
     * parameter : null
     * response : {"todaySign":0,"user_id":18,"signCount":0}
     * urlMapping : sign-get
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
         * todaySign : 0
         * user_id : 18
         * signCount : 0
         */

        private int todaySign;
        private int user_id;
        private int signCount;

        public int getTodaySign() {
            return todaySign;
        }

        public void setTodaySign(int todaySign) {
            this.todaySign = todaySign;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }
    }
}
