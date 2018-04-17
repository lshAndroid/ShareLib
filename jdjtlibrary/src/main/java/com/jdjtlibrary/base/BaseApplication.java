package com.jdjtlibrary.base;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.BuildConfig;
import org.xutils.x;
/**
 * Created by lsh on 2017/4/4.
 */

public class BaseApplication extends Application{
    private static BaseApplication mApplication;
    public ImageLoaderConfiguration configuration;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=BaseApplication.this;
        initXutis();
        initImageLoader();
    }

    private void initImageLoader() {
        configuration = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .threadPriority(Thread.NORM_PRIORITY - 3)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(configuration);
    }

    private void initXutis() {//xutils集成
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static BaseApplication getApp() {
        return mApplication;
    }
}
