package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/11.
 */

public class UserInfo {

    /**
     * parameter : null
     * response : {"code":"200","registerTime":"2018-07-11 22:55:48","message":"登录成功！","token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiIxbTQybnAiLCJzdWIiOiI3IiwiZXhwIjoxNTM3MzY4OTQ4LCJpYXQiOjE1MzEzMjA5NDh9.3AtOe_htfca_DfCPuCXAySNSngQn2bHOFM2AgOA-BWenTn_CJ2eHeWCfqs1lD51kTrGdY48q333r9yARaZFwgA"}
     * urlMapping : user-login
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
         * code : 200
         * registerTime : 2018-07-11 22:55:48
         * message : 登录成功！
         * token : eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiIxbTQybnAiLCJzdWIiOiI3IiwiZXhwIjoxNTM3MzY4OTQ4LCJpYXQiOjE1MzEzMjA5NDh9.3AtOe_htfca_DfCPuCXAySNSngQn2bHOFM2AgOA-BWenTn_CJ2eHeWCfqs1lD51kTrGdY48q333r9yARaZFwgA
         */

        private String code;
        private String registerTime;
        private String message;
        private String token;
        private String nickName;
        private String icon;
        private String account;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
