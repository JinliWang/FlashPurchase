package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyInfo {

    /**
     * parameter : null
     * response : {"payCoin":0,"shopCoin":0,"nickname":"A good name makes it easier...","freeCoin":0,"ID":null,"pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqWuvuicHD7RepdM1JyXXsu6m5OwzBv2qk1xs0u5B12wkgYhscAL22bUWJLjdKBCOYffboO83Lhvg/132","userId":7,"point":0}
     * urlMapping : account-myCenter
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
         * payCoin : 0
         * shopCoin : 0
         * nickname : A good name makes it easier...
         * freeCoin : 0
         * ID : null
         * pic : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqWuvuicHD7RepdM1JyXXsu6m5OwzBv2qk1xs0u5B12wkgYhscAL22bUWJLjdKBCOYffboO83Lhvg/132
         * userId : 7
         * point : 0
         */

        private int payCoin;
        private int shopCoin;
        private String nickname;
        private int freeCoin;
        private String ID;
        private String pic;
        private int userId;
        private int point;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getFreeCoin() {
            return freeCoin;
        }

        public void setFreeCoin(int freeCoin) {
            this.freeCoin = freeCoin;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

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
    }
}
