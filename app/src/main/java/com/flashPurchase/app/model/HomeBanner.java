package com.flashPurchase.app.model;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class HomeBanner {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * bannerpic : 测试内容6io3
         * rid : 测试内容nn9k
         */

        private String bannerpic;
        private String rid;
        private int pic;

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getBannerpic() {
            return bannerpic;
        }

        public void setBannerpic(String bannerpic) {
            this.bannerpic = bannerpic;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }
    }
}
