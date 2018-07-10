package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/9.
 */

public class RecentDeal {

    /**
     * parameter : null
     * response : {"total":4,"goodsId":0,"pageSize":10,"dealRecords":[{"marketPrice":580,"saveRatio":"-3.400%","goodsId":3,"nickname":"张三","finalPrice":600,"time":1,"userId":1,"goodsName":"瓷器","pics":"http://p65n22l5d.bkt.clouddn.com/41023d34-83c4-4c9d-aab3-dc04ad85f08c.jpg"},{"marketPrice":500,"saveRatio":"89.900%","goodsId":1,"nickname":"李四","finalPrice":50.6,"time":2,"userId":2,"goodsName":"和田玉","pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg"},{"marketPrice":500,"saveRatio":"88.000%","goodsId":1,"nickname":"张三","finalPrice":60.2,"time":1,"userId":1,"goodsName":"和田玉","pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg"},{"marketPrice":6999,"saveRatio":"99.600%","goodsId":2,"nickname":"李四","finalPrice":30.2,"time":1,"userId":2,"goodsName":"Apple iPhone 8 pluse 256G 颜色随机","pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg"}],"pageNum":0}
     * urlMapping : goods-recentDeal
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
         * total : 4
         * goodsId : 0
         * pageSize : 10
         * dealRecords : [{"marketPrice":580,"saveRatio":"-3.400%","goodsId":3,"nickname":"张三","finalPrice":600,"time":1,"userId":1,"goodsName":"瓷器","pics":"http://p65n22l5d.bkt.clouddn.com/41023d34-83c4-4c9d-aab3-dc04ad85f08c.jpg"},{"marketPrice":500,"saveRatio":"89.900%","goodsId":1,"nickname":"李四","finalPrice":50.6,"time":2,"userId":2,"goodsName":"和田玉","pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg"},{"marketPrice":500,"saveRatio":"88.000%","goodsId":1,"nickname":"张三","finalPrice":60.2,"time":1,"userId":1,"goodsName":"和田玉","pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg"},{"marketPrice":6999,"saveRatio":"99.600%","goodsId":2,"nickname":"李四","finalPrice":30.2,"time":1,"userId":2,"goodsName":"Apple iPhone 8 pluse 256G 颜色随机","pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg"}]
         * pageNum : 0
         */

        private String total;
        private String goodsId;
        private String pageSize;
        private String pageNum;
        private List<DealRecordsBean> dealRecords;

        public String getTotal() {
            return total;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public String getPageSize() {
            return pageSize;
        }

        public String getPageNum() {
            return pageNum;
        }

        public List<DealRecordsBean> getDealRecords() {
            return dealRecords;
        }

        public static class DealRecordsBean {
            /**
             * marketPrice : 580.0
             * saveRatio : -3.400%
             * goodsId : 3
             * nickname : 张三
             * finalPrice : 600.0
             * time : 1
             * userId : 1
             * goodsName : 瓷器
             * pics : http://p65n22l5d.bkt.clouddn.com/41023d34-83c4-4c9d-aab3-dc04ad85f08c.jpg
             */

            private String marketPrice;
            private String saveRatio;
            private String goodsId;
            private String nickname;
            private String finalPrice;
            private String time;
            private String userId;
            private String goodsName;
            private String pics;

            public String getMarketPrice() {
                return marketPrice;
            }

            public String getSaveRatio() {
                return saveRatio;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public String getNickname() {
                return nickname;
            }

            public String getFinalPrice() {
                return finalPrice;
            }

            public String getTime() {
                return time;
            }

            public String getUserId() {
                return userId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public String getPics() {
                return pics;
            }
        }
    }
}
