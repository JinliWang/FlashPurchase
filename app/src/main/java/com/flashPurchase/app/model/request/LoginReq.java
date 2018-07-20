package com.flashPurchase.app.model.request;

/**
 * Created by 10951 on 2018/7/11.
 */

public class LoginReq {
    private String urlMapping;
    private Parameter mParameter;

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public void setParameter(Parameter parameter) {
        mParameter = parameter;
    }

    public static class Parameter {
        private String openId;
        private String nickName;
        private String icon;
        private String clientId;
        private String phone;
        private String password;
        private String token;

        public void setToken(String token) {
            this.token = token;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        @Override
        public String toString() {
            return "{" +
                    "openId:'" + openId + '\'' +
                    ", nickName:'" + nickName + '\'' +
                    ", icon:'" + icon + '\'' +
                    ", clientId:'" + clientId + '\'' +
                    '}';
        }

        public String register() {
            return "{" +
                    "phone:'" + phone + '\'' +
                    ", password:'" + password + '\'' +
                    ", clientId:'" + clientId + '\'' +
                    '}';
        }

        public String changePhone() {
            return "{" +
                    "phone:'" + phone + '\'' +
                    ", token:'" + token + '\'' +
                    '}';
        }

        public String changeInfo() {
            return "{" +
                    "nickName:'" + nickName + '\'' +
                    ", token:'" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter +
                '}';
    }

    public String register() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.register() +
                '}';
    }


    public String changePhone() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.changePhone() +
                '}';
    }

    public String changeInfo() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.changeInfo() +
                '}';
    }
}
