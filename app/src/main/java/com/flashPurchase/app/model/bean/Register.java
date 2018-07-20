package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/19.
 */

public class Register {

    /**
     * parameter : null
     * response : {"msg":"成功","registerTime":"2018-07-19 15:34:56","success":1,"token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJuanNucjIiLCJzdWIiOiIyNiIsImV4cCI6MTUzODAzMzY5NiwiaWF0IjoxNTMxOTg1Njk2fQ.Eh5KB-4tb89LF1XEEGQlDH8ALbNdYnHsvjV5SdmYgKY22_2q0VE72sRKztfCmvOO5_Y4EJgo5lITcfYgEbZU5Q"}
     * urlMapping : user-register
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
         * msg : 成功
         * registerTime : 2018-07-19 15:34:56
         * success : 1
         * token : eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJuanNucjIiLCJzdWIiOiIyNiIsImV4cCI6MTUzODAzMzY5NiwiaWF0IjoxNTMxOTg1Njk2fQ.Eh5KB-4tb89LF1XEEGQlDH8ALbNdYnHsvjV5SdmYgKY22_2q0VE72sRKztfCmvOO5_Y4EJgo5lITcfYgEbZU5Q
         */

        private String msg;
        private String registerTime;
        private int success;
        private String token;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
