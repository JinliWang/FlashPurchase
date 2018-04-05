package com.mls.library.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Description:Activity栈管理类
 * Create By: MLS Co,Ltd
 */

public class ActivityManager {

    private static Stack<Activity> activityStack;

    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static ActivityManager getActivityManager() {
        if (instance == null) {
            instance = new ActivityManager();
            activityStack = new Stack<Activity>();
        }
        return instance;
    }

    /**
     * 栈中移除一个activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前栈顶activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }


    /**
     * 栈中放入一个activity
     *
     * @return
     */
    public void pushActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 移除除了cls以外的activity
     *
     * @return
     */
    public void popAllActivityExceptOne(Class cls) {
        Activity activity;
        while (true) {
            activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                continue;
            }
            popActivity(activity);
        }

    }

    /**
     * 移除cls
     *
     * @return
     */
    public void popActivityByClass(Class cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                popActivity(activity);
                break;
            }
        }
    }

    /**
     * 获取是否存在
     *
     * @return
     */
    public boolean activityIsExist(Class cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 移除所有activity
     */
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }
}
