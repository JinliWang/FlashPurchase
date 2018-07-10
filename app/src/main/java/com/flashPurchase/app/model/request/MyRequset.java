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
        private String userId;
        private String aucSt;
        private String categoryId;
        private String goodsId;

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
                    "userId:'" + userId + '\'' +
                    ", aucSt:'" + aucSt + '\'' +
                    '}';
        }

        //我收藏
        public String myCollect() {
            return "{" +
                    "userId:'" + userId + '\'' +
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
}
