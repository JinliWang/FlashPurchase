package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/9.
 */

public class GoodClassification {

    /**
     * parameter : null
     * response : {"total":6,"pageSize":10,"goods":[{"aucNum":0,"name":"ttt","currentPrice":null,"id":6,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/123dc763-7c13-4335-8a2b-ec2ee63709af.jpg","aucSt":1,"categoryId":5},{"aucNum":0,"name":"test","currentPrice":10,"id":5,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/52a7e53d-327d-48cb-a7f2-b6bba9c294fe.jpg","aucSt":1,"categoryId":1},{"aucNum":0,"name":"惠普 暗夜精灵X 点睛主机 （参数。。。）","currentPrice":100,"id":4,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/46d082d2-8cb2-4d27-baa6-258859913f59.jpg","aucSt":1,"categoryId":5},{"aucNum":1,"name":"瓷器","currentPrice":50,"id":3,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/41023d34-83c4-4c9d-aab3-dc04ad85f08c.jpg","aucSt":1,"categoryId":3},{"aucNum":1,"name":"Apple iPhone 8 pluse 256G 颜色随机","currentPrice":100,"id":2,"time":2,"pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg","aucSt":1,"categoryId":2},{"aucNum":0,"name":"和田玉","currentPrice":0.1,"id":1,"time":3,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","aucSt":1,"categoryId":4}],"pageNum":0,"goodsCategories":[{"createTime":null,"id":5,"name":"电脑平板","status":1,"updateTime":null},{"createTime":null,"id":4,"name":"珠宝配饰","status":1,"updateTime":null},{"createTime":null,"id":3,"name":"紫砂瓷器","status":1,"updateTime":null},{"createTime":null,"id":2,"name":"手机专区","status":1,"updateTime":null},{"createTime":null,"id":1,"name":"十元专区","status":1,"updateTime":null},{"createTime":null,"id":0,"name":"全部","status":1,"updateTime":null}]}
     * urlMapping : goods-goodsList
     */

    private Object parameter;
    private ResponseBean response;
    private String urlMapping;

    public Object getParameter() {
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
         * total : 6
         * pageSize : 10
         * goods : [{"aucNum":0,"name":"ttt","currentPrice":null,"id":6,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/123dc763-7c13-4335-8a2b-ec2ee63709af.jpg","aucSt":1,"categoryId":5},{"aucNum":0,"name":"test","currentPrice":10,"id":5,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/52a7e53d-327d-48cb-a7f2-b6bba9c294fe.jpg","aucSt":1,"categoryId":1},{"aucNum":0,"name":"惠普 暗夜精灵X 点睛主机 （参数。。。）","currentPrice":100,"id":4,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/46d082d2-8cb2-4d27-baa6-258859913f59.jpg","aucSt":1,"categoryId":5},{"aucNum":1,"name":"瓷器","currentPrice":50,"id":3,"time":1,"pics":"http://p65n22l5d.bkt.clouddn.com/41023d34-83c4-4c9d-aab3-dc04ad85f08c.jpg","aucSt":1,"categoryId":3},{"aucNum":1,"name":"Apple iPhone 8 pluse 256G 颜色随机","currentPrice":100,"id":2,"time":2,"pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg","aucSt":1,"categoryId":2},{"aucNum":0,"name":"和田玉","currentPrice":0.1,"id":1,"time":3,"pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg","aucSt":1,"categoryId":4}]
         * pageNum : 0
         * goodsCategories : [{"createTime":null,"id":5,"name":"电脑平板","status":1,"updateTime":null},{"createTime":null,"id":4,"name":"珠宝配饰","status":1,"updateTime":null},{"createTime":null,"id":3,"name":"紫砂瓷器","status":1,"updateTime":null},{"createTime":null,"id":2,"name":"手机专区","status":1,"updateTime":null},{"createTime":null,"id":1,"name":"十元专区","status":1,"updateTime":null},{"createTime":null,"id":0,"name":"全部","status":1,"updateTime":null}]
         */

        private String total;
        private String pageSize;
        private String pageNum;
        private List<GoodsBean> goods;
        private List<GoodsCategoriesBean> goodsCategories;

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

        public List<GoodsCategoriesBean> getGoodsCategories() {
            return goodsCategories;
        }

        public static class GoodsBean {
            /**
             * aucNum : 0
             * name : ttt
             * currentPrice : null
             * id : 6
             * time : 1
             * pics : http://p65n22l5d.bkt.clouddn.com/123dc763-7c13-4335-8a2b-ec2ee63709af.jpg
             * aucSt : 1
             * categoryId : 5
             */

            private String aucNum;
            private String name;
            private String currentPrice;
            private String id;
            private String time;
            private String pics;
            private String aucSt;
            private String categoryId;
            private String status;

            public String getStatus() {
                return status;
            }

            public String getAucNum() {
                return aucNum;
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

            public String getCategoryId() {
                return categoryId;
            }
        }

        public static class GoodsCategoriesBean {
            /**
             * createTime : null
             * id : 5
             * name : 电脑平板
             * status : 1
             * updateTime : null
             */

            private String createTime;
            private String id;
            private String name;
            private String status;
            private String updateTime;

            public String getCreateTime() {
                return createTime;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getStatus() {
                return status;
            }

            public String getUpdateTime() {
                return updateTime;
            }
        }
    }
}
