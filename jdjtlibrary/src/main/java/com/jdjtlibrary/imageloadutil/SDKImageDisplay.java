package com.jdjtlibrary.imageloadutil;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jdjtlibrary.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


public class SDKImageDisplay {
    private SDKImageDisplay(){}
    public static SDKImageDisplay mInstance=null;
    /*
    * 获取单实例
    * */
    public static SDKImageDisplay getImageManager(){//imageload自带单例模式,所以没必要浪费运行效率
//        if (mInstance == null) {
//            synchronized (SDKImageDisplay.class) {
                if (mInstance == null) {
                    mInstance=new SDKImageDisplay();
                }
//            }
//        }
        return mInstance;
    }
    private  DisplayImageOptions displayImageNor;
    private  DisplayImageOptions displayImageRound;
    public  DisplayImageOptions getOption(){
        displayImageNor = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.empty_load) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.empty_load)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.empty_load)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .displayer(new RoundedBitmapDisplayer(100))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间(出现闪屏)
                .build();//构建完成
        return displayImageNor;
    }
    private  DisplayImageOptions getOptionRound(){
        displayImageRound = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.empty_load)
                .showImageOnFail(R.drawable.empty_load)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .displayer(new RoundedBitmapDisplayer(360))
                .cacheInMemory(true)
                .build();
        return displayImageRound;
    }

    public  void Display(String url, ImageView imageView){
        ImageLoader.getInstance().displayImage(url, imageView, getOption());
    }
    public void DisplayRound(String url, ImageView imageView){
        ImageLoader.getInstance().displayImage(url, imageView, getOptionRound());
    }

}
