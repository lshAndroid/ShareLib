package com.jdjtlibrary.net;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.jdjtlibrary.base.BaseApplication;

/**
 * 显示自定义的Toast
 */
public class ToastUtils {

    public static Toast mToast;

    public static LinearLayout mToastLayout;

    public static void showToast(int res) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getApp(), "", Toast.LENGTH_SHORT);
            mToastLayout = (LinearLayout) mToast.getView();
        }
        mToast.setText(res);
        mToast.show();
    }

    public static void showToast(String res) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getApp(), "", Toast.LENGTH_SHORT);
            mToastLayout = (LinearLayout) mToast.getView();
        }
        mToast.setText(res);
        mToast.show();
    }

}
