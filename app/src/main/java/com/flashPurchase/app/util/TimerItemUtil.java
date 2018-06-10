package com.flashPurchase.app.util;

import com.flashPurchase.app.model.TimeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10951 on 2018/4/27.
 */

public class TimerItemUtil {
    public static List<TimeBean> getTimerItemList() {
        List<TimeBean> lstTimerItems = new ArrayList<>();
        lstTimerItems.add(new TimeBean("A", System.currentTimeMillis() + 11 * 1000));
        lstTimerItems.add(new TimeBean("B", System.currentTimeMillis() + 22 * 1000));
        lstTimerItems.add(new TimeBean("C", System.currentTimeMillis() + 26 * 1000));
        lstTimerItems.add(new TimeBean("D", System.currentTimeMillis() + 33 * 1000));
        lstTimerItems.add(new TimeBean("E", System.currentTimeMillis() + 24 * 1000));
        lstTimerItems.add(new TimeBean("F", System.currentTimeMillis() + 98 * 1000));
        lstTimerItems.add(new TimeBean("G", System.currentTimeMillis() + 14 * 1000));
        lstTimerItems.add(new TimeBean("H", System.currentTimeMillis() + 36 * 1000));
        lstTimerItems.add(new TimeBean("I", System.currentTimeMillis() + 58 * 1000));
        lstTimerItems.add(new TimeBean("J", System.currentTimeMillis() + 47 * 1000));
        lstTimerItems.add(new TimeBean("K", System.currentTimeMillis() + 66 * 1000));
        lstTimerItems.add(new TimeBean("L", System.currentTimeMillis() + 55 * 1000));
        lstTimerItems.add(new TimeBean("M", System.currentTimeMillis() + 62 * 1000));
        lstTimerItems.add(new TimeBean("N", System.currentTimeMillis() + 45 * 1000));
        lstTimerItems.add(new TimeBean("O", System.currentTimeMillis() + 14 * 1000));

        return lstTimerItems;
    }
}
