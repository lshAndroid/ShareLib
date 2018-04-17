package com.jdjtlibrary.net.xutils;


import com.jdjtlibrary.net.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lsh on 2017/3/31.
 */
//公共库的网络二次封装，使用需要个人的application中注册（name=".BaseApplication"）
public class NetXutilsWay implements NetManager {
    public static NetXutilsWay mInstance=null;
    private NetXutilsWay(){}
    /*
    * 获取单实例
    * */
    public static NetXutilsWay getXutilsManager(){
        if (mInstance == null) {
            synchronized (NetXutilsWay.class) {
                if (mInstance == null) {
                    mInstance=new NetXutilsWay();
                }
            }
        }
        return mInstance;
    }
    @Override
    public void NetPost(Map<String, String> map, String basePath, final NettingCallBack callBack) {
        if (basePath == null) {
            return;
        }
        final RequestParams requestParams = new RequestParams(basePath);
        if (map == null || map.isEmpty()){

        }else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                requestParams.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("RequestUrlPost:"+requestParams);
        requestParams.setReadTimeout(30000);
        requestParams.setConnectTimeout(30000);
//        requestParams.setConnectTimeout(10000);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("RequestResult:"+s);
                if (callBack != null)
                    callBack.onSuccess(s);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                ToastUtils.showToast("联网失败");
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
                if (callBack != null)
                    callBack.onFinished();
            }
        });
    }

    @Override
    public void NetPost(String basePath,final NettingCallBack callBack) {
        if (basePath == null) {
            return;
        }
        final RequestParams requestParams = new RequestParams(basePath);
        System.out.println("RequestUrlPost:"+requestParams);
        requestParams.setConnectTimeout(10000);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("RequestResult:"+s);
                if (callBack != null)
                    callBack.onSuccess(s);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                ToastUtils.showToast("联网失败");
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
                if (callBack != null)
                    callBack.onFinished();
            }
        });
    }

    @Override
    public void NetGet(Map<String, String> map, String basePath,final NettingCallBack callBack) {
        if (basePath == null) {
            return;
        }
        final RequestParams requestParams = new RequestParams(basePath);
        if (map == null || map.isEmpty()){

        }else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                requestParams.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("RequestUrlPost:"+requestParams);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("RequestResult:"+s);
                if (callBack != null)
                    callBack.onSuccess(s);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                ToastUtils.showToast("联网失败");
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
                if (callBack != null)
                    callBack.onFinished();
            }
        });
    }

    @Override
    public void NetGet(String basePath,final NettingCallBack callBack) {
        if (basePath == null) {
            return;
        }
        final RequestParams requestParams = new RequestParams(basePath);
        System.out.println("RequestUrlPost:"+requestParams);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("RequestResult:"+s);
                if (callBack != null)
                    callBack.onSuccess(s);
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                ToastUtils.showToast("联网失败");
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
                if (callBack != null)
                    callBack.onFinished();
            }
        });
    }

    @Override
    public void TestPost(Map<String, String> map, String basePath,final NettingCallBack callBack) {
        StringBuilder builder = new StringBuilder();
        if (map == null || map.isEmpty() || basePath == null) {
            return;
        }

        final RequestParams requestParams = new RequestParams(basePath);

        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareTo(rhs);
                    }
                });

        sortMap.putAll(map);
        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            builder.append(entry.getValue());
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }
        //加密字段
//        String result = builder.append("1qazSuoMusic2016Ggu168@WSX#EDC").toString();
//        String sign = MD5Utils.MD5Encode(result);
//        requestParams.addBodyParameter("sign", sign);

        requestParams.setConnectTimeout(10000);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("RequestUrl:"+requestParams);

                if (callBack != null)
                    callBack.onSuccess(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                ToastUtils.showToast("联网失败");
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
                if (callBack != null)
                    callBack.onFinished();
            }
        });
    }


}
