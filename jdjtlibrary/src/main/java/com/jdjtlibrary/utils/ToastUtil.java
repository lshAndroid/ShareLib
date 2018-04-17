package com.jdjtlibrary.utils;

import android.widget.Toast;

import com.jdjtlibrary.base.BaseApplication;

/**
 * 控制频繁点击时，toast只弹出一次
 * */
public class ToastUtil {
    private static Toast toast;

    /**
     * 时间较短
     * */
    public static void ToastShort(String str){
        if(toast==null){
            toast=Toast.makeText(BaseApplication.getApp(),str,Toast.LENGTH_SHORT);
        }else{
            toast.setText(str);
        }
        toast.show();

    }

    public static void ToastShort(int id){
        if(toast==null){
            toast=Toast.makeText(BaseApplication.getApp(),id,Toast.LENGTH_SHORT);
        }else{
            toast.setText(id);
        }
        toast.show();
    }

    /**
     * 时间较长
     * */
    public static void ToastLong(String str){
        if(toast==null){
            toast=Toast.makeText(BaseApplication.getApp(),str,Toast.LENGTH_LONG);
        }else{
            toast.setText(str);
        }
        toast.show();
    }


    public static void ToastLong(int  id){
        if(toast==null){
            toast=Toast.makeText(BaseApplication.getApp(),id,Toast.LENGTH_LONG);
        }else{
            toast.setText(id);
        }
        toast.show();
    }
}
