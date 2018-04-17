package com.jdjtlibrary.net.xutils;


import java.util.Map;

/**
 * 用于解耦合，当使用其它的联网框架时，实现该接口即可--指明当前框架需要实现哪些功能
 * */
public interface NetManager {

    /**
     * post请求
     */
    public void NetPost(Map<String, String> map, String basePath, final NettingCallBack callBack);
    public void NetPost(String basePath, final NettingCallBack callBack);
    public void NetGet(Map<String, String> map, String basePath, final NettingCallBack callBack);
    public void NetGet(String basePath, final NettingCallBack callBack);

    public void TestPost(Map<String, String> map, String basePath, final NettingCallBack callBack);


}