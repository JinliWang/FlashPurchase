package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/19.
 */

public class Login {

    /**
     * parameter : null
     * response : {"code":"200","registerTime":"2018-07-19 15:24:38","message":"登录成功！","mark":0,"token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJsdnpqaWoiLCJzdWIiOiIyNSIsImV4cCI6MTUzODAzNTM5OSwiaWF0IjoxNTMxOTg3Mzk5fQ.98ObZ_sVHI27XRUmbuPG70zFFnNq2gQhsXRHCc_gpirjs5cT74LQ9xf7n1fgDRK_lnfyZ5eniYK_SgrSn4U1_w"}
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
         * registerTime : 2018-07-19 15:24:38
         * message : 登录成功！
         * mark : 0
         * token : eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJsdnpqaWoiLCJzdWIiOiIyNSIsImV4cCI6MTUzODAzNTM5OSwiaWF0IjoxNTMxOTg3Mzk5fQ.98ObZ_sVHI27XRUmbuPG70zFFnNq2gQhsXRHCc_gpirjs5cT74LQ9xf7n1fgDRK_lnfyZ5eniYK_SgrSn4U1_w
         */

        private String code;
        private String registerTime;
        private String message;
        private int mark;
        private String token;
        private int success;

        public int getSuccess() {
            return success;
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

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
