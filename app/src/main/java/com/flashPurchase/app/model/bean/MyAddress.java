package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/11.
 */

public class MyAddress {

    /**
     * parameter : null
     * response : {"alipayAccount":"188 8888 8888","alipayName":"张三","createTime":{"date":30,"hours":17,"seconds":37,"month":4,"timezoneOffset":-480,"year":118,"minutes":11,"time":1527671497000,"day":3},"id":1,"phone":"188 8888 8888","qq":"888888888","recipient":"张三","region":"云南大理","remark":"","specificAddress":"xx小区","status":1,"street":"xx街","updateTime":{"date":30,"hours":17,"seconds":40,"month":4,"timezoneOffset":-480,"year":118,"minutes":11,"time":1527671500000,"day":3},"userId":1}
     * urlMapping : address-get
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
         * alipayAccount : 188 8888 8888
         * alipayName : 张三
         * createTime : {"date":30,"hours":17,"seconds":37,"month":4,"timezoneOffset":-480,"year":118,"minutes":11,"time":1527671497000,"day":3}
         * id : 1
         * phone : 188 8888 8888
         * qq : 888888888
         * recipient : 张三
         * region : 云南大理
         * remark :
         * specificAddress : xx小区
         * status : 1
         * street : xx街
         * updateTime : {"date":30,"hours":17,"seconds":40,"month":4,"timezoneOffset":-480,"year":118,"minutes":11,"time":1527671500000,"day":3}
         * userId : 1
         */

        private String alipayAccount;
        private String alipayName;
        private CreateTimeBean createTime;
        private String id;
        private String phone;
        private String qq;
        private String recipient;
        private String region;
        private String remark;
        private String specificAddress;
        private String status;
        private String street;
        private UpdateTimeBean updateTime;
        private String userId;

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public String getAlipayName() {
            return alipayName;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public String getId() {
            return id;
        }

        public String getPhone() {
            return phone;
        }

        public String getQq() {
            return qq;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getRegion() {
            return region;
        }

        public String getRemark() {
            return remark;
        }

        public String getSpecificAddress() {
            return specificAddress;
        }

        public String getStatus() {
            return status;
        }

        public String getStreet() {
            return street;
        }

        public UpdateTimeBean getUpdateTime() {
            return updateTime;
        }

        public String getUserId() {
            return userId;
        }

        public static class CreateTimeBean {
            /**
             * date : 30
             * hours : 17
             * seconds : 37
             * month : 4
             * timezoneOffset : -480
             * year : 118
             * minutes : 11
             * time : 1527671497000
             * day : 3
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
             * date : 30
             * hours : 17
             * seconds : 40
             * month : 4
             * timezoneOffset : -480
             * year : 118
             * minutes : 11
             * time : 1527671500000
             * day : 3
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
