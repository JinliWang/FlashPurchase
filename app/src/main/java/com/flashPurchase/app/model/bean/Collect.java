package com.flashPurchase.app.model.bean;

/**
 * Created by 10951 on 2018/7/12.
 */

public class Collect {

    /**
     * parameter : null
     * response : 已收藏
     * urlMapping : collect-add
     */

    private Object parameter;
    private String response;
    private String urlMapping;

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }
}
