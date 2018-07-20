package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/8.
 */

public class RecommendMoreResponse {

    /**
     * parameter : null
     * response : {"total":1,"pageSize":10,"goods":[{"aucPeopleNum":0,"name":"和田玉","currentPrice":0.1,"id":1,"time":3,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","aucSt":1}],"pageNum":0}
     * urlMapping : goods-recommen
     */

    private String parameter;
    private ResponseBean response;
    private String urlMapping;

    public String getParameter() {
        return parameter;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public static class ResponseBean {
        /**
         * total : 1
         * pageSize : 10
         * goods : [{"aucPeopleNum":0,"name":"和田玉","currentPrice":0.1,"id":1,"time":3,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","aucSt":1}]
         * pageNum : 0
         */

        private String total;
        private String pageSize;
        private String pageNum;
        private List<GoodsBean> goods;

        public String getTotal() {
            return total;
        }

        public String getPageSize() {
            return pageSize;
        }

        public String getPageNum() {
            return pageNum;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public static class GoodsBean {
            /**
             * aucPeopleNum : 0
             * name : 和田玉
             * currentPrice : 0.1
             * id : 1
             * time : 3
             * pics : http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg
             * aucSt : 1
             */

            private String aucPeopleNum;
            private String name;
            private String currentPrice;
            private String id;
            private String time;
            private String pics;
            private String aucSt;
            private String goodsId;

            public String getGoodsId() {
                return goodsId;
            }

            public String getAucPeopleNum() {
                return aucPeopleNum;
            }

            public String getName() {
                return name;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public String getId() {
                return id;
            }

            public String getTime() {
                return time;
            }

            public String getPics() {
                return pics;
            }

            public String getAucSt() {
                return aucSt;
            }
        }
    }
}
