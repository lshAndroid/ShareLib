package com.jdjtlibrary.utils;

/**
 * Created by Administrator on 2016/7/21.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

;import com.jdjtlibrary.Constantlib;

/**
 * 判断网络连接类型的工具类
 * */
public class NetClassUtil {

    /*
   * 判断网络连接是否可用
   * true 可用	false 不可用
   * */
    public static boolean isNetAvailable(Context context) {
        boolean isWIFI = isWIFIConnectivity(context);        //WIFI连接
        boolean isMobile = isMobileConnectivity(context);    //APNList连接

        if (!isWIFI && !isMobile) {
            return false;
        }

        if (isMobile) {
            readAPN(context);    //读取proxy和port
        } else {
            Constantlib.PROXY_IP = "";
            Constantlib.PROXY_PORT = 0;
        }
        return true;
    }



    /**
     * 判断是否是WIFI连接
     */
    private static boolean isWIFIConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }


    /**
     * 判断是否是APNList连接
     */
    private static boolean isMobileConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 读取代理
     */
    private static void readAPN(Context context) {
        Constantlib.PROXY_IP = android.net.Proxy.getDefaultHost();
        Constantlib.PROXY_PORT = android.net.Proxy.getDefaultPort();
    }



}
