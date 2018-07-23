package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/19.
 */

public class TenLimit {

    /**
     * parameter : null
     * response : {"total":1,"pageSize":10,"goods":[{"currentPrice":null,"id":7,"time":1,"aucSt":1,"pics":"http://p65n22l5d.bkt.clouddn.com/29e9257a-99a6-45e2-ae78-fd7c29e3c233.jpg","collect":1}],"userId":18,"pageNum":1}
     * urlMapping : goods-limitTime
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
         * total : 1
         * pageSize : 10
         * goods : [{"currentPrice":null,"id":7,"time":1,"aucSt":1,"pics":"http://p65n22l5d.bkt.clouddn.com/29e9257a-99a6-45e2-ae78-fd7c29e3c233.jpg","collect":1}]
         * userId : 18
         * pageNum : 1
         */

        private int total;
        private int pageSize;
        private int userId;
        private int pageNum;
        private List<GoodsBean> goods;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * currentPrice : null
             * id : 7
             * time : 1
             * aucSt : 1
             * pics : http://p65n22l5d.bkt.clouddn.com/29e9257a-99a6-45e2-ae78-fd7c29e3c233.jpg
             * collect : 1
             */

            private double currentPrice;
            private int id;
            private int time;
            private int aucSt;
            private String pics;
            private int collect;
            private int status;

            public int getStatus() {
                return status;
            }

            public double getCurrentPrice() {
                return currentPrice;
            }

            public void setCurrentPrice(double currentPrice) {
                this.currentPrice = currentPrice;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getAucSt() {
                return aucSt;
            }

            public void setAucSt(int aucSt) {
                this.aucSt = aucSt;
            }

            public String getPics() {
                return pics;
            }

            public void setPics(String pics) {
                this.pics = pics;
            }

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
            }
        }
    }
}
