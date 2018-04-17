package com.jdjtlibrary.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2016/12/20.
 */

public class SdDownJsonUtil {
    public static List<String> getLocaJsonList(Context context) {
        List<String> localJsons=new ArrayList<>();
        boolean isAddEntity = true;
        String jsonr = "";//读取本地的数据
        jsonr = CacheUtils.readJson(context.getApplicationContext(), "downloadjson");  //读取的本地String
        boolean isLocalRead = true;
        if (jsonr != null && !"".equals(jsonr)) {  //转换为集合
            localJsons = new Gson().fromJson(jsonr,
                    new TypeToken<List<String>>() {
                    }.getType());
            System.out.println("本地json读取:" + jsonr);
        }
        return localJsons;
    }
    public static void initJsonSave(Context context, String downloadSdEntity, String fileNameId){  //写入信息
        List<String> localJsons=new ArrayList<>();
        boolean isAddEntity=true;
        String jsonr="";//读取本地的数据
        jsonr= CacheUtils.readJson(context.getApplicationContext(),"downloadjson");  //读取的本地String
        boolean isLocalRead=true;
        if (jsonr!=null&&!"".equals(jsonr)) {  //转换为集合
            localJsons = new Gson().fromJson(jsonr,
                    new TypeToken<List<String>>() {
                    }.getType());
            System.out.println("本地json读取:"+jsonr);
//            for (int i=0;i<localJsons.size();i++){//数据更新
//                if (fileNameId.equals(localJsons.get(i).getMusic_score_id())){
//                    localJsons.set(i,downloadSdEntity);
//                    isAddEntity=false;
//                }
//            }
        }
        if (isAddEntity){//如果存在则数据更新不添加,没有则添加下载
            localJsons.add(downloadSdEntity);
        }
        String jsonw="";//写到本地的数据
        jsonw=new Gson().toJson(localJsons);
        System.out.println("本地json写入:"+jsonw);
        CacheUtils.writeJson(context.getApplicationContext(),jsonw,"downloadjson",false);
    }
    public static void initJsonUpdate(Context context, String downloadSdEntity, String fileNameId){  //写入信息
        List<String> localJsons=new ArrayList<>();
        boolean isAddEntity=true;
        String jsonr="";//读取本地的数据
        jsonr= CacheUtils.readJson(context.getApplicationContext(),"downloadjson");  //读取的本地String
        boolean isLocalRead=true;
        if (jsonr!=null&&!"".equals(jsonr)) {  //转换为集合
            localJsons = new Gson().fromJson(jsonr,
                    new TypeToken<List<String>>() {
                    }.getType());
            System.out.println("本地json读取:"+jsonr);
//            for (int i=0;i<localJsons.size();i++){//数据更新
//                if (fileNameId.equals(localJsons.get(i).getMusic_score_id())){
//                    localJsons.set(i,downloadSdEntity);
//                    isAddEntity=false;
//                }
//            }
        }
        if (isAddEntity){//如果存在则数据更新不添加,没有则添加下载
            localJsons.add(downloadSdEntity);
        }
        String jsonw="";//写到本地的数据
        jsonw=new Gson().toJson(localJsons);
        System.out.println("本地json写入:"+jsonw);
        CacheUtils.writeJson(context.getApplicationContext(),jsonw,"downloadjson",false);
    }
    public static void initJsonReplace(Context context, List<String> localJsons){
        //替换本地json数据
        String jsonw="";//写到本地的数据
        jsonw=new Gson().toJson(localJsons);
        System.out.println("本地json写入:"+jsonw);
        CacheUtils.writeJson(context.getApplicationContext(),jsonw,"downloadjson",false);
    }
    public static void initJsonSuccess(Context context, String fileNameId){  //写入信息
        List<String> localJsons=new ArrayList<>();
        String jsonr="";//读取本地的数据
        jsonr= CacheUtils.readJson(context.getApplicationContext(),"downloadjson");  //读取的本地String
        boolean isLocalRead=true;
        if (jsonr!=null&&!"".equals(jsonr)) {  //转换为集合
            localJsons = new Gson().fromJson(jsonr,
                    new TypeToken<List<String>>() {
                    }.getType());
            System.out.println("本地json读取:"+jsonr);
//            for (int i=0;i<localJsons.size();i++){//数据更新
//                if (fileNameId.equals(localJsons.get(i).getMusic_score_id())){
//                    localJsons.get(i).setDown_state("2");  //下载成功状态为2
//                }
//            }
        }
        String jsonw="";//写到本地的数据
        jsonw=new Gson().toJson(localJsons);
        System.out.println("本地json写入:"+jsonw);
        CacheUtils.writeJson(context.getApplicationContext(),jsonw,"downloadjson",false);
    }
}
