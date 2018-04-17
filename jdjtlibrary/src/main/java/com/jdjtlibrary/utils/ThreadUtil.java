package com.jdjtlibrary.utils;

/**
 * Created by Administrator on 2016/8/4.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程管理工具类--一个项目的线程应该能够控制,不应该随便起线程
 * */
public class ThreadUtil {
    private static ExecutorService mService;
    private static final int THREAD_NUMBER=5;  //整个项目子线程数量
    public static ExecutorService getThreadPool(){ //创建一个程序所需的线程池
        if (mService == null) {  //效率提升
            synchronized (ThreadUtil.class) { //线程安全
                if (mService == null) {
                    mService = Executors.newFixedThreadPool(THREAD_NUMBER); //从线程池中获取
                }
            }
        }
        return mService;
    }
}
