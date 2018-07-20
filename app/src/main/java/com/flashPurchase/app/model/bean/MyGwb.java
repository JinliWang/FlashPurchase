package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyGwb {

    /**
     * parameter : null
     * response : {"jaExpire":[],"jaUse":[{"shopCoin":120,"goodsId":1,"name":"和田玉","id":1,"userId":1,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","status":1},{"shopCoin":120,"goodsId":1,"name":"和田玉","id":2,"userId":1,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","status":1}],"totalCoin":240,"userId":7}
     * urlMapping : account-myShopCoin
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
         * jaExpire : []
         * jaUse : [{"shopCoin":120,"goodsId":1,"name":"和田玉","id":1,"userId":1,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","status":1},{"shopCoin":120,"goodsId":1,"name":"和田玉","id":2,"userId":1,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","status":1}]
         * totalCoin : 240
         * userId : 7
         */

        private int totalCoin;
        private int userId;
        private List<JaExpireBean> jaExpire;
        private List<JaUseBean> jaUse;

        public int getTotalCoin() {
            return totalCoin;
        }

        public void setTotalCoin(int totalCoin) {
            this.totalCoin = totalCoin;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<JaExpireBean> getJaExpire() {
            return jaExpire;
        }

        public void setJaExpire(List<JaExpireBean> jaExpire) {
            this.jaExpire = jaExpire;
        }

        public List<JaUseBean> getJaUse() {
            return jaUse;
        }

        public void setJaUse(List<JaUseBean> jaUse) {
            this.jaUse = jaUse;
        }

        public static class JaUseBean {
            /**
             * shopCoin : 120
             * goodsId : 1
             * name : 和田玉
             * id : 1
             * userId : 1
             * pics : http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg
             * status : 1
             */

            private int shopCoin;
            private int goodsId;
            private String name;
            private int id;
            private String createTime;
            private int userId;
            private String pics;
            private int status;

            public String getCreateTime() {
                return createTime;
            }

            public int getShopCoin() {
                return shopCoin;
            }

            public void setShopCoin(int shopCoin) {
                this.shopCoin = shopCoin;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPics() {
                return pics;
            }

            public void setPics(String pics) {
                this.pics = pics;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class JaExpireBean {
            /**
             * shopCoin : 120
             * goodsId : 1
             * name : 和田玉
             * id : 1
             * userId : 1
             * pics : http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg
             * status : 1
             */

            private int shopCoin;
            private int goodsId;
            private String name;
            private int id;
            private int userId;
            private String pics;
            private String createTime;
            private int status;

            public String getCreateTime() {
                return createTime;
            }

            public int getShopCoin() {
                return shopCoin;
            }

            public void setShopCoin(int shopCoin) {
                this.shopCoin = shopCoin;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPics() {
                return pics;
            }

            public void setPics(String pics) {
                this.pics = pics;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
