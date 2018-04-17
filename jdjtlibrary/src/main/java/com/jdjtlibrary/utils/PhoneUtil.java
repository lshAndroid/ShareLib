package com.jdjtlibrary.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class PhoneUtil {

    /**
     * @return 客户端唯一标识
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();
    }

    /**
     * @return Sim卡唯一标识
     */
    public static String getIMSI(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getSubscriberId();
    }

    /**
     * @return 手机号码
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tManager.getLine1Number();
        return null;
    }


    //android 获取屏幕分辩率
    public static String getMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        int h = dm.heightPixels;
        int w = dm.widthPixels;
        return  h+ "*" +w;
    }

    //android 获取当前手机品牌
    public static String getPhoneProduct(Context context) {
        return  Build.PRODUCT;
    }


    //android 获取当前手机型号  android.os.Build.VERSION.RELEASE
    public static String getPhoneModel(Context context) {
        return  Build.MODEL;
    }

  /**
   * 获取手机系统版本
   * */
    public static String getPhonVesion(Context context) {
        return  Build.VERSION.RELEASE;
    }


}
