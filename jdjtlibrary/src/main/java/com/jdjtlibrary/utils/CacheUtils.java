package com.jdjtlibrary.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.nio.channels.FileChannel;

/**
 * Created by lsh on 2016/12/2.
 */

public class CacheUtils {

    private static File CacheRoot;

    /**
     * 存储Json文件
     * @param c
     * @param json 字符串
     * @param fileName 存储的文件名
     * @param append true 增加到文件末，false则覆盖掉原来的文件
     */
    public static void writeJson(Context c, String json, String fileName,
                                 boolean append) {

        CacheRoot = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ? c
                .getExternalCacheDir() : c.getCacheDir();
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            File ff = new File(CacheRoot, fileName);
            boolean boo = ff.exists();
            fos = new FileOutputStream(ff, append);
            os = new ObjectOutputStream(fos);
            if (append && boo) {
                FileChannel fc = fos.getChannel();
                fc.truncate(fc.position() - 4);
            }
            os.writeObject(json);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (os != null) {

                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 读取json数据
     *
     * @param c
     * @param fileName
     * @return 返回值为list
     */

    @SuppressWarnings("resource")
    public static String readJson(Context c, String fileName) {

        CacheRoot = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ? c
                .getExternalCacheDir() : c.getCacheDir();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String result ="";
        StringBuilder sb=new StringBuilder();

        File des = new File(CacheRoot, fileName);
        if (des.isFile()){  //自己添加,防止没文件的情况
            try {
                fis = new FileInputStream(des);
                ois = new ObjectInputStream(fis);
                while (fis.available() > 0){
//                    result.add((DownloadSdEntity) ois.readObject());
                    sb.append(ois.readObject());
                }

                result=sb.toString();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }else {
            System.out.println("未找到文件");
        }
        return result;
    }

}