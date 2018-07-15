package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/14.
 */

public class RechargeOrder {

    /**
     * parameter : null
     * response : {"actualPayment":1,"coin":1,"createTime":{"date":14,"hours":13,"seconds":15,"month":6,"timezoneOffset":-480,"year":118,"minutes":10,"time":1531545015788,"day":6},"id":1,"orderNumber":"1017999696351055873","orderStatus":0,"payChannel":0,"payTime":null,"remark":"","type":"1","updateTime":{"date":14,"hours":13,"seconds":15,"month":6,"timezoneOffset":-480,"year":118,"minutes":10,"time":1531545015788,"day":6},"userId":7}
     * urlMapping : order-recharge
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
         * actualPayment : 1
         * coin : 1
         * createTime : {"date":14,"hours":13,"seconds":15,"month":6,"timezoneOffset":-480,"year":118,"minutes":10,"time":1531545015788,"day":6}
         * id : 1
         * orderNumber : 1017999696351055873
         * orderStatus : 0
         * payChannel : 0
         * payTime : null
         * remark :
         * type : 1
         * updateTime : {"date":14,"hours":13,"seconds":15,"month":6,"timezoneOffset":-480,"year":118,"minutes":10,"time":1531545015788,"day":6}
         * userId : 7
         */

        private int actualPayment;
        private int coin;
        private CreateTimeBean createTime;
        private int id;
        private String orderNumber;
        private int orderStatus;
        private int payChannel;
        private String payTime;
        private String remark;
        private String type;
        private UpdateTimeBean updateTime;
        private int userId;

        public int getActualPayment() {
            return actualPayment;
        }

        public void setActualPayment(int actualPayment) {
            this.actualPayment = actualPayment;
        }

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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
             * date : 14
             * hours : 13
             * seconds : 15
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 10
             * time : 1531545015788
             * day : 6
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
             * date : 14
             * hours : 13
             * seconds : 15
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 10
             * time : 1531545015788
             * day : 6
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
