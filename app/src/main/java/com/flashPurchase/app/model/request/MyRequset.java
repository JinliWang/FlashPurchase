package com.flashPurchase.app.model.request;

/**
 * Created by 10951 on 2018/7/8.
 */

public class MyRequset {
    private String urlMapping;
    private Parameter mParameter;

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public void setParameter(Parameter parameter) {
        mParameter = parameter;
    }

    public static class Parameter {
        private String pageNum;
        private String pageSize;
        private String token;
        private String aucSt;
        private String categoryId;
        private String goodsId;
        private String aucTime;
        private String time;
        private String type;
        private String price;
        private String orderId;

        public void setType(String type) {
            this.type = type;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setTime(String time) {
            time = time;
        }

        public void setAucTime(String aucTime) {
            this.aucTime = aucTime;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setAucSt(String aucSt) {
            this.aucSt = aucSt;
        }

        public void setPageNum(String pageNum) {
            this.pageNum = pageNum;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "{" +
                    "pageNum:'" + pageNum + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    '}';
        }

        //我在拍
        public String myOrder() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", aucSt:'" + aucSt + '\'' +
                    '}';
        }

        //我收藏
        public String myCollect() {
            return "{" +
                    "token:'" + token + '\'' +
                    '}';
        }

        public String getGoodsById() {
            return "{" +
                    "pageNum:'" + pageNum + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    ", categoryId:'" + categoryId + '\'' +
                    '}';
        }

        public String getRecent() {
            return "{" +
                    "pageNum:'" + pageNum + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    '}';
        }

        public String collect() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    '}';
        }

        public String goodsDetail() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    ", time:'" + time + '\'' +
                    '}';
        }

        public String auc() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    ", aucTime:'" + aucTime + '\'' +
                    '}';
        }

        //充值
        public String recharge() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", type:'" + type + '\'' +
                    ", price:'" + price + '\'' +
                    '}';
        }

        //生成订单
        public String makeOrder() {
            return "{" +
                    "orderId:'" + orderId + '\'' +
                    ", type:'" + type + '\'' +
                    '}';
        }

    }

    public String noPatameter() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + "{}" +
                '}';
    }

    @Override
    public String toString() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.toString() +
                '}';
    }

    //我在拍
    public String myOrder() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.myOrder() +
                '}';
    }

    //我收藏
    public String myCollect() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.myCollect() +
                '}';
    }

    //详情
    public String goodsDetail() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.goodsDetail() +
                '}';
    }

    //收藏或者取消收藏或者详情页
    public String collect() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.collect() +
                '}';
    }

    //分类商品
    public String getGoodsById() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.getGoodsById() +
                '}';
    }

    //最新动态
    public String getRecent() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.getRecent() +
                '}';
    }

    //竞拍
    public String auc() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.auc() +
                '}';
    }

    //充值
    public String recharge() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.recharge() +
                '}';
    }

    //生成订单
    public String makeOrder() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.makeOrder() +
                '}';
    }
}
