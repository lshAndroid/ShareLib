package com.jdjtlibrary.acmanager;

import android.app.Activity;

import java.util.Stack;

/**
 * activity 管理
 */
public class ScreenManager {
    private static Stack<Activity> activityStack;
    private static ScreenManager instance;

    public static ScreenManager getScreenManager() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * 移除当前activity
     */
    public void popActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activityStack.pop();
            activity.finish();
            activity = null;
        }
    }

    /**
     * 从管理器中移除指定activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 获取当前activity
     *
     * @return activity 当前屏幕正在显示的activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 向管理器中添加指定activity
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 从管理器中移除不包括指定activity的其他activity
     */
    int count = 0;
    public void popAllActivityExceptOne(Class cls) {
        while (true) {

            if (activityStack.isEmpty()) {
                break;
            }
            Activity activity = currentActivity();

            if (activity.getClass().equals(cls)) {
                activityStack.pop();
            } else {
                activityStack.pop();
                activity.finish();
            }
        }
    }

}
