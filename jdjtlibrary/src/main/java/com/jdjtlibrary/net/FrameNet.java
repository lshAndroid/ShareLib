package com.jdjtlibrary.net;

/**
 * Created by lsh on 2017/3/31.
 */

import com.jdjtlibrary.net.xutils.NetManager;
import com.jdjtlibrary.net.xutils.NetXutilsWay;

/**
 * 工厂解耦合
 * */
public class FrameNet {

    /**
     *获取当前使用的联网框架，默认是xutils
     * */
    public static NetManager getNetManger(){
        return NetXutilsWay.getXutilsManager();
    }
}
