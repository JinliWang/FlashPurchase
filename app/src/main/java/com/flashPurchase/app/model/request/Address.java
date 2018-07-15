package com.flashPurchase.app.model.request;

/**
 * Created by 10951 on 2018/7/11.
 */

public class Address {
    private String urlMapping;
    private Parameter mParameter;

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public void setParameter(Parameter parameter) {
        mParameter = parameter;
    }

    public static class Parameter {
        private String token;
        private String recipient;
        private String phone;
        private String qq;
        private String alipayAccount;
        private String alipayName;
        private String region;
        private String street;
        private String specificAddress;
        private String remark;
        private String id;

        public void setId(String id) {
            this.id = id;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public void setAlipayName(String alipayName) {
            this.alipayName = alipayName;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setSpecificAddress(String specificAddress) {
            this.specificAddress = specificAddress;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", recipient:'" + recipient + '\'' +
                    ", phone:'" + phone + '\'' +
                    ", qq:'" + qq + '\'' +
                    ", alipayAccount:'" + alipayAccount + '\'' +
                    ", alipayName:'" + alipayName + '\'' +
                    ", region:'" + region + '\'' +
                    ", street:'" + street + '\'' +
                    ", specificAddress:'" + specificAddress + '\'' +
                    ", remark:'" + remark + '\'' +
                    '}';
        }

        public String update() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", recipient:'" + recipient + '\'' +
                    ", phone:'" + phone + '\'' +
                    ", qq:'" + qq + '\'' +
                    ", alipayAccount:'" + alipayAccount + '\'' +
                    ", alipayName:'" + alipayName + '\'' +
                    ", region:'" + region + '\'' +
                    ", street:'" + street + '\'' +
                    ", specificAddress:'" + specificAddress + '\'' +
                    ", remark:'" + remark + '\'' +
                    ", id:'" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.toString() +
                '}';
    }
    public String update() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.update() +
                '}';
    }
}
