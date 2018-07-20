package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/20.
 */

public class AucBask {

    /**
     * parameter : null
     * response : {"comments":"12356789","createTime":{"date":20,"hours":15,"seconds":48,"month":6,"timezoneOffset":-480,"year":118,"minutes":55,"time":1532073348657,"day":5},"goodsId":3,"id":1,"name":"小米8 全面屏游戏智能手机","nickname":"王金礼","pics":" ","status":1,"type":0,"updateTime":{"date":20,"hours":15,"seconds":48,"month":6,"timezoneOffset":-480,"year":118,"minutes":55,"time":1532073348657,"day":5},"userId":18}
     * urlMapping : comment-save
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
         * comments : 12356789
         * createTime : {"date":20,"hours":15,"seconds":48,"month":6,"timezoneOffset":-480,"year":118,"minutes":55,"time":1532073348657,"day":5}
         * goodsId : 3
         * id : 1
         * name : 小米8 全面屏游戏智能手机
         * nickname : 王金礼
         * pics :
         * status : 1
         * type : 0
         * updateTime : {"date":20,"hours":15,"seconds":48,"month":6,"timezoneOffset":-480,"year":118,"minutes":55,"time":1532073348657,"day":5}
         * userId : 18
         */

        private String comments;
        private CreateTimeBean createTime;
        private int goodsId;
        private int id;
        private String name;
        private String nickname;
        private String pics;
        private int status;
        private int type;
        private UpdateTimeBean updateTime;
        private int userId;

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
             * hours : 15
             * seconds : 48
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 55
             * time : 1532073348657
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
             * hours : 15
             * seconds : 48
             * month : 6
             * timezoneOffset : -480
             * year : 118
             * minutes : 55
             * time : 1532073348657
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
