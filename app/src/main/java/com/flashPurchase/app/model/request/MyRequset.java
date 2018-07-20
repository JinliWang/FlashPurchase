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
        private String point;
        private String isNext;
        private String remark;
        private String addressId;
        private String shopCoin;
        private String pics;
        private String comments;
        private String nickname;

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setShopCoin(String shopCoin) {
            this.shopCoin = shopCoin;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setIsNext(String isNext) {
            this.isNext = isNext;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setTime(String time) {
            this.time = time;
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

        //积分兑换
        public String pointForCoin() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", point:'" + point + '\'' +
                    '}';
        }

        //我收藏
        public String myCollect() {
            return "{" +
                    "token:'" + token + '\'' +
                    '}';
        }

        //我收藏
        public String update() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", nickname:'" + nickname + '\'' +
                    '}';
        }

        public String getGoodsById() {
            return "{" +
                    "pageNum:'" + pageNum + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    ", categoryId:'" + categoryId + '\'' +
                    '}';
        }

        public String getTen() {
            return "{" +
                    "pageNum:'" + pageNum + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    ", token:'" + token + '\'' +
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
                    ", isNext:'" + isNext + '\'' +
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

        //物流
        public String wuliu() {
            return "{" +
                    "orderId:'" + orderId + '\'' +
                    '}';
        }

        //新增订单
        public String addOrder() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    ", time:'" + time + '\'' +
                    ", addressId:'" + addressId + '\'' +
                    ", remark:'" + remark + '\'' +
                    ", type:'" + type + '\'' +
                    ", shopCoin:'" + shopCoin + '\'' +
                    '}';
        }

        //新增订单
        public String addBask() {
            return "{" +
                    "token:'" + token + '\'' +
                    ", goodsId:'" + goodsId + '\'' +
                    ", orderId:'" + orderId + '\'' +
                    ", pics:'" + pics + '\'' +
                    ", comments:'" + comments + '\'' +
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

    //积分兑换
    public String pointForCoin() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.pointForCoin() +
                '}';
    }

    //物流
    public String wuliu() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.wuliu() +
                '}';
    }

    //新增订单
    public String addOrder() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.addOrder() +
                '}';
    }

    //限时秒
    public String getTen() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.getTen() +
                '}';
    }

    //晒单
    public String addBask() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.addBask() +
                '}';
    }

    //晒单
    public String update() {
        return "{" +
                "urlMapping:'" + urlMapping + '\'' +
                ", parameter:" + mParameter.update() +
                '}';
    }
}
