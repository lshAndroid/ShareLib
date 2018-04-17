package com.jdjtlibrary.utils;

/**
 * Created by lsh on 2016/9/7.
 */
public class ClickFastUtil {
    //防止快速点击导致数据出错工具
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1500) {  //1秒
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
