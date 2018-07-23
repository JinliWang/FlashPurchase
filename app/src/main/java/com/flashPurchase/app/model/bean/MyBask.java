package com.flashPurchase.app.model.bean;

import java.util.List;

/**
 * Created by 10951 on 2018/7/20.
 */

public class MyBask {


    /**
     * parameter : null
     * response : {"total":3,"comments":[{"comments":"123456789","createTime":"2018-07-20 16:13:57","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 16:13:57","id":3,"userId":18,"pics":" "},{"comments":"123456789","createTime":"2018-07-20 16:03:41","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 16:03:41","id":2,"userId":18,"pics":" "},{"comments":"12356789","createTime":"2018-07-20 15:55:48","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 15:55:48","id":1,"userId":18,"pics":" "}],"pageSize":10,"pageNum":1}
     * urlMapping : comment-getMyComments
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
         * total : 3
         * comments : [{"comments":"123456789","createTime":"2018-07-20 16:13:57","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 16:13:57","id":3,"userId":18,"pics":" "},{"comments":"123456789","createTime":"2018-07-20 16:03:41","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 16:03:41","id":2,"userId":18,"pics":" "},{"comments":"12356789","createTime":"2018-07-20 15:55:48","goodsId":3,"name":"小米8 全面屏游戏智能手机","nickname":"","updateTime":"2018-07-20 15:55:48","id":1,"userId":18,"pics":" "}]
         * pageSize : 10
         * pageNum : 1
         */

        private int total;
        private int pageSize;
        private int pageNum;
        private List<CommentsBean> comments;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * comments : 123456789
             * createTime : 2018-07-20 16:13:57
             * goodsId : 3
             * name : 小米8 全面屏游戏智能手机
             * nickname :
             * updateTime : 2018-07-20 16:13:57
             * id : 3
             * userId : 18
             * pics :
             */

            private String comments;
            private String createTime;
            private int goodsId;
            private String name;
            private String nickname;
            private String updateTime;
            private int id;
            private int userId;
            private String pics;
            private int status;
            private String time;

            public int getStatus() {
                return status;
            }

            public String getTime() {
                return time;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPics() {
                return pics;
            }

            public void setPics(String pics) {
                this.pics = pics;
            }
        }
    }
}
