package com.mls.library.entity;

/**
 * Created by mls on 2016/4/9.
 */
public class CityIndexBean {
    private int position;
    private String firstLetter;

    public CityIndexBean() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public CityIndexBean(int position, String firstLetter) {
        this.position = position;
        this.firstLetter = firstLetter;
    }
}
