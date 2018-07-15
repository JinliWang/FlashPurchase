package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/14.
 */

public class Order {

    /**
     * parameter : null
     * response : {"code":200,"message":"操作成功","object":{"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018040202487853&biz_content=%7B%22out_trade_no%22%3A%221018010428467630082%22%2C%22passback_params%22%3A%22%25E5%2585%2585%25E5%2580%25BC%25E6%258B%258D%25E5%258D%2596%25E5%25B8%2581%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.78.204.97%3A8086%2Fauction%2Fpay%2Falipay%2Fnotify&sign=mx23OKBdplJDqtmZEiOApodCHbKD2cIJc5HGuId3x%2BRneICPh7VdSSxx7wu6zuUOPNO3q31S4umBUvreGvqaS%2FfMbBpPAg69jGONp8EbFit4GJQ%2FHTwkTDsTSvK1%2FVOIsPMf36PpdG90yQ23KDOk2aOu6UpHtFuE4RcRw2cvEGBJCD0VGTqqDsRxYxw6csMhj8UK14yOcEF0sBrbxgZ3XBMg9qA4oXOHtcZQqg8jXHyOSLyQBG3a2n1MK80BINqtAnMh9ncaE8nrlTn33M1NqyVimDbejjFbxHLQpZPy9%2BfwwqTDTwEXyvyl44XrkNiAeWAZKr9BDVbvN%2FW8xLcz8w%3D%3D&sign_type=RSA2&timestamp=2018-07-14+13%3A52%3A54&version=1.0","code":"200","out_trade_no":"1018010428467630082","sign":"e082e7fd581229ef7bc2fe475e0093d6"}}
     * urlMapping : pay-money
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
         * code : 200
         * message : 操作成功
         * object : {"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018040202487853&biz_content=%7B%22out_trade_no%22%3A%221018010428467630082%22%2C%22passback_params%22%3A%22%25E5%2585%2585%25E5%2580%25BC%25E6%258B%258D%25E5%258D%2596%25E5%25B8%2581%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.78.204.97%3A8086%2Fauction%2Fpay%2Falipay%2Fnotify&sign=mx23OKBdplJDqtmZEiOApodCHbKD2cIJc5HGuId3x%2BRneICPh7VdSSxx7wu6zuUOPNO3q31S4umBUvreGvqaS%2FfMbBpPAg69jGONp8EbFit4GJQ%2FHTwkTDsTSvK1%2FVOIsPMf36PpdG90yQ23KDOk2aOu6UpHtFuE4RcRw2cvEGBJCD0VGTqqDsRxYxw6csMhj8UK14yOcEF0sBrbxgZ3XBMg9qA4oXOHtcZQqg8jXHyOSLyQBG3a2n1MK80BINqtAnMh9ncaE8nrlTn33M1NqyVimDbejjFbxHLQpZPy9%2BfwwqTDTwEXyvyl44XrkNiAeWAZKr9BDVbvN%2FW8xLcz8w%3D%3D&sign_type=RSA2&timestamp=2018-07-14+13%3A52%3A54&version=1.0","code":"200","out_trade_no":"1018010428467630082","sign":"e082e7fd581229ef7bc2fe475e0093d6"}
         */

        private int code;
        private String message;
        private ObjectBean object;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean {
            /**
             * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018040202487853&biz_content=%7B%22out_trade_no%22%3A%221018010428467630082%22%2C%22passback_params%22%3A%22%25E5%2585%2585%25E5%2580%25BC%25E6%258B%258D%25E5%258D%2596%25E5%25B8%2581%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.78.204.97%3A8086%2Fauction%2Fpay%2Falipay%2Fnotify&sign=mx23OKBdplJDqtmZEiOApodCHbKD2cIJc5HGuId3x%2BRneICPh7VdSSxx7wu6zuUOPNO3q31S4umBUvreGvqaS%2FfMbBpPAg69jGONp8EbFit4GJQ%2FHTwkTDsTSvK1%2FVOIsPMf36PpdG90yQ23KDOk2aOu6UpHtFuE4RcRw2cvEGBJCD0VGTqqDsRxYxw6csMhj8UK14yOcEF0sBrbxgZ3XBMg9qA4oXOHtcZQqg8jXHyOSLyQBG3a2n1MK80BINqtAnMh9ncaE8nrlTn33M1NqyVimDbejjFbxHLQpZPy9%2BfwwqTDTwEXyvyl44XrkNiAeWAZKr9BDVbvN%2FW8xLcz8w%3D%3D&sign_type=RSA2&timestamp=2018-07-14+13%3A52%3A54&version=1.0
             * code : 200
             * out_trade_no : 1018010428467630082
             * sign : e082e7fd581229ef7bc2fe475e0093d6
             */

            private String orderStr;
            private String code;
            private String out_trade_no;
            private String sign;

            public String getOrderStr() {
                return orderStr;
            }

            public void setOrderStr(String orderStr) {
                this.orderStr = orderStr;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
