package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/7.
 */

public class HomeBean {

    /**
     * parameter : null
     * response : {"preferGoods":[{"auctioneerId":2,"categoryId":4,"createTime":null,"currentPrice":0.1,"id":1,"initCd":0,"marketPrice":500,"name":"和田玉","pics":"http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg,","reservePrice":0,"status":1,"store":100,"ten":0,"time":3,"type":1,"updateTime":null}],"computerGoods":[{"auctioneerId":2,"categoryId":5,"createTime":null,"currentPrice":0,"id":6,"initCd":5,"marketPrice":12,"name":"ttt","pics":"http://p65n22l5d.bkt.clouddn.com/123dc763-7c13-4335-8a2b-ec2ee63709af.jpg,http://p65n22l5d.bkt.clouddn.com/3f2f6eff-ce83-45de-86c4-534f26e12561.jpg,","reservePrice":0,"status":1,"store":111,"ten":1,"time":1,"type":0,"updateTime":null},{"auctioneerId":1,"categoryId":5,"createTime":null,"currentPrice":100,"id":4,"initCd":3,"marketPrice":2000,"name":"惠普 暗夜精灵X 点睛主机 （参数。。。）","pics":"http://p65n22l5d.bkt.clouddn.com/46d082d2-8cb2-4d27-baa6-258859913f59.jpg,","reservePrice":0,"status":1,"store":100,"ten":0,"time":1,"type":0,"updateTime":null}],"phoneGoods":[{"auctioneerId":3,"categoryId":2,"createTime":null,"currentPrice":100,"id":2,"initCd":3,"marketPrice":6999,"name":"Apple iPhone 8 pluse 256G 颜色随机","pics":"http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg,http://p65n22l5d.bkt.clouddn.com/42b77d48-242e-4175-94b5-5c7b45260fb2.jpg,http://p65n22l5d.bkt.clouddn.com/87b819b7-bb8a-49e1-ba24-f9a03174d24a.jpg,","reservePrice":7500,"status":1,"store":999,"ten":0,"time":2,"type":0,"updateTime":null}],"goodsCategories":[{"createTime":null,"id":5,"name":"电脑平板","status":1,"updateTime":null},{"createTime":null,"id":4,"name":"珠宝配饰","status":1,"updateTime":null},{"createTime":null,"id":3,"name":"紫砂瓷器","status":1,"updateTime":null},{"createTime":null,"id":2,"name":"手机专区","status":1,"updateTime":null},{"createTime":null,"id":1,"name":"十元专区","status":1,"updateTime":null}]}
     * urlMapping : goods-index
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
        private List<PreferGoodsBean> preferGoods;
        private List<ComputerGoodsBean> computerGoods;
        private List<PhoneGoodsBean> phoneGoods;
        private List<GoodsCategoriesBean> goodsCategories;

        public List<PreferGoodsBean> getPreferGoods() {
            return preferGoods;
        }

        public List<ComputerGoodsBean> getComputerGoods() {
            return computerGoods;
        }

        public List<PhoneGoodsBean> getPhoneGoods() {
            return phoneGoods;
        }

        public List<GoodsCategoriesBean> getGoodsCategories() {
            return goodsCategories;
        }

        public static class PreferGoodsBean {
            /**
             * auctioneerId : 2
             * categoryId : 4
             * createTime : null
             * currentPrice : 0.1
             * id : 1
             * initCd : 0
             * marketPrice : 500.0
             * name : 和田玉
             * pics : http://p65n22l5d.bkt.clouddn.com/59798154-8d16-4246-b86b-f84a4966419c.jpg,
             * reservePrice : 0.0
             * status : 1
             * store : 100
             * ten : 0
             * time : 3
             * type : 1
             * updateTime : null
             */

            private String auctioneerId;
            private String categoryId;
            private String createTime;
            private String currentPrice;
            private String id;
            private String initCd;
            private String marketPrice;
            private String name;
            private String pics;
            private String reservePrice;
            private String status;
            private String store;
            private String ten;
            private String time;
            private String type;
            private String updateTime;

            public String getAuctioneerId() {
                return auctioneerId;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public String getId() {
                return id;
            }

            public String getInitCd() {
                return initCd;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public String getName() {
                return name;
            }

            public String getPics() {
                return pics;
            }

            public String getReservePrice() {
                return reservePrice;
            }

            public String getStatus() {
                return status;
            }

            public String getStore() {
                return store;
            }

            public String getTen() {
                return ten;
            }

            public String getTime() {
                return time;
            }

            public String getType() {
                return type;
            }

            public String getUpdateTime() {
                return updateTime;
            }
        }

        public static class ComputerGoodsBean {
            /**
             * auctioneerId : 2
             * categoryId : 5
             * createTime : null
             * currentPrice : 0
             * id : 6
             * initCd : 5
             * marketPrice : 12.0
             * name : ttt
             * pics : http://p65n22l5d.bkt.clouddn.com/123dc763-7c13-4335-8a2b-ec2ee63709af.jpg,http://p65n22l5d.bkt.clouddn.com/3f2f6eff-ce83-45de-86c4-534f26e12561.jpg,
             * reservePrice : 0
             * status : 1
             * store : 111
             * ten : 1
             * time : 1
             * type : 0
             * updateTime : null
             */

            private String auctioneerId;
            private String categoryId;
            private String createTime;
            private String currentPrice;
            private String id;
            private String initCd;
            private String marketPrice;
            private String name;
            private String pics;
            private String reservePrice;
            private String status;
            private String store;
            private String ten;
            private String time;
            private String type;
            private String updateTime;

            public String getAuctioneerId() {
                return auctioneerId;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public String getId() {
                return id;
            }

            public String getInitCd() {
                return initCd;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public String getName() {
                return name;
            }

            public String getPics() {
                return pics;
            }

            public String getReservePrice() {
                return reservePrice;
            }

            public String getStatus() {
                return status;
            }

            public String getStore() {
                return store;
            }

            public String getTen() {
                return ten;
            }

            public String getTime() {
                return time;
            }

            public String getType() {
                return type;
            }

            public String getUpdateTime() {
                return updateTime;
            }
        }

        public static class PhoneGoodsBean {
            /**
             * auctioneerId : 3
             * categoryId : 2
             * createTime : null
             * currentPrice : 100.0
             * id : 2
             * initCd : 3
             * marketPrice : 6999.0
             * name : Apple iPhone 8 pluse 256G 颜色随机
             * pics : http://p65n22l5d.bkt.clouddn.com/f3818d82-2e9d-44df-9bcc-7ece1617d696.jpg,http://p65n22l5d.bkt.clouddn.com/42b77d48-242e-4175-94b5-5c7b45260fb2.jpg,http://p65n22l5d.bkt.clouddn.com/87b819b7-bb8a-49e1-ba24-f9a03174d24a.jpg,
             * reservePrice : 7500.0
             * status : 1
             * store : 999
             * ten : 0
             * time : 2
             * type : 0
             * updateTime : null
             */

            private String auctioneerId;
            private String categoryId;
            private String createTime;
            private String currentPrice;
            private String id;
            private String initCd;
            private String marketPrice;
            private String name;
            private String pics;
            private String reservePrice;
            private String status;
            private String store;
            private String ten;
            private String time;
            private String type;
            private String updateTime;

            public String getAuctioneerId() {
                return auctioneerId;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public String getId() {
                return id;
            }

            public String getInitCd() {
                return initCd;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public String getName() {
                return name;
            }

            public String getPics() {
                return pics;
            }

            public String getReservePrice() {
                return reservePrice;
            }

            public String getStatus() {
                return status;
            }

            public String getStore() {
                return store;
            }

            public String getTen() {
                return ten;
            }

            public String getTime() {
                return time;
            }

            public String getType() {
                return type;
            }

            public String getUpdateTime() {
                return updateTime;
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
