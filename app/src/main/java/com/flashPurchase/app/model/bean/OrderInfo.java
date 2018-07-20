package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/20.
 */

public class OrderInfo {

    /**
     * parameter : null
     * response : {"actualPayment":3299,"addressId":8,"createTime":{"date":20,"hours":2,"seconds":47,"month":6,"timezoneOffset":-480,"year":118,"minutes":33,"time":1532025227340,"day":5},"deliveryTime":null,"escrowTrackNo":"","freight":0,"goodsId":3,"goodsTotalPrice":3299,"goodsTypeCount":0,"id":3,"logisticsId":0,"orderNumber":"1020013849576046593","orderStatus":1,"payChannel":0,"payTime":null,"remark":" ","shopCoin":0,"status":1,"time":1,"type":2,"updateTime":{"date":20,"hours":2,"seconds":47,"month":6,"timezoneOffset":-480,"year":118,"minutes":33,"time":1532025227340,"day":5},"userId":18}
     * urlMapping : order-add
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
         * actualPayment : 3299.0
         * addressId : 8
         * createTime : {"date":20,"hours":2,"seconds":47,"month":6,"timezoneOffset":-480,"year":118,"minutes":33,"time":1532025227340,"day":5}
         * deliveryTime : null
         * escrowTrackNo :
         * freight : 0
         * goodsId : 3
         * goodsTotalPrice : 3299.0
         * goodsTypeCount : 0
         * id : 3
         * logisticsId : 0
         * orderNumber : 1020013849576046593
         * orderStatus : 1
         * payChannel : 0
         * payTime : null
         * remark :
         * shopCoin : 0
         * status : 1
         * time : 1
         * type : 2
         * updateTime : {"date":20,"hours":2,"seconds":47,"month":6,"timezoneOffset":-480,"year":118,"minutes":33,"time":1532025227340,"day":5}
         * userId : 18
         */

        private double actualPayment;
        private int addressId;
        private CreateTimeBean createTime;
        private Object deliveryTime;
        private String escrowTrackNo;
        private int freight;
        private int goodsId;
        private double goodsTotalPrice;
        private int goodsTypeCount;
        private int id;
        private int logisticsId;
        private String orderNumber;
        private int orderStatus;
        private int payChannel;
        private Object payTime;
        private String remark;
        private int shopCoin;
        private int status;
        private int time;
        private int type;
        private UpdateTimeBean updateTime;
        private int userId;

        public double getActualPayment() {
            return actualPayment;
        }

        public void setActualPayment(double actualPayment) {
            this.actualPayment = actualPayment;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getEscrowTrackNo() {
            return escrowTrackNo;
        }

        public void setEscrowTrackNo(String escrowTrackNo) {
            this.escrowTrackNo = escrowTrackNo;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public double getGoodsTotalPrice() {
            return goodsTotalPrice;
        }

        public void setGoodsTotalPrice(double goodsTotalPrice) {
            this.goodsTotalPrice = goodsTotalPrice;
        }

        public int getGoodsTypeCount() {
            return goodsTypeCount;
        }

        public void setGoodsTypeCount(int goodsTypeCount) {
            this.goodsTypeCount = goodsTypeCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(int logisticsId) {
            this.logisticsId = logisticsId;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(int payChannel) {
            this.payChannel = payChannel;
        }

        public Object getPayTime() {
            return payTime;
        }

        public void setPayTime(Object payTime) {
            this.payTime = payTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getShopCoin() {
            return shopCoin;
        }

        public void setShopCoin(int shopCoin) {
            this.shopCoin = shopCoin;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public UpdateTimeBean getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(UpdateTimeBean updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class CreateTimeBean {
            /**
             * date : 20
             * hours : 2
             * seconds : 47
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 33
             * time : 1532025227340
             * day : 5
             */

            private int date;
            private int hours;
            private int seconds;
            private int month;
            private int timezoneOffset;
            private int year;
            private int minutes;
            private long time;
            private int day;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }

        public static class UpdateTimeBean {
            /**
             * date : 20
             * hours : 2
             * seconds : 47
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 33
             * time : 1532025227340
             * day : 5
             */

            private int date;
            private int hours;
            private int seconds;
            private int month;
            private int timezoneOffset;
            private int year;
            private int minutes;
            private long time;
            private int day;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }
    }
}
