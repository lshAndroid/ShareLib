package com.jdjtlibrary.net.xutils;

/**
 * 联网回调接口
 */
public interface NettingCallBack {

    /**
     * 联网成功执行此方法
     */
    void onSuccess(String s);

    /**
     * 联网结束执行此方法（并不一定代表着联网成功，失败也是有可能的，联网结束）
     */
    void onFinished();

}
