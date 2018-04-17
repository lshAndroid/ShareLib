package com.jdjtlibrary.fitadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jdjtlibrary.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 这是listView 或者GridView 的一个通用ViewHolder
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private static int count = 0;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutId, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public void setOnClickListener(int viewId, final OnCusclickListener cusclickListener) {
        getView(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cusclickListener != null)
                    cusclickListener.onClickListener();
            }
        });
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置颜色
     *
     * @param viewId
     * @param color  如 白 #FFFFFF  黑 #000000
     * @return
     */
    public ViewHolder setTextColor(int viewId, String color) {
        TextView view = getView(viewId);
        view.setTextColor(Color.parseColor(color));
        return this;
    }

    /**
     * @param viewId
     * @param drawId 如 R.drawable.ic
     * @return
     */
    public ViewHolder setBackGround(int viewId, int drawId) {
        getView(viewId).setBackgroundResource(drawId);
        return this;
    }

    /**
     * 设置View是否显示
     *
     * @param viewId
     * @param visibility
     */
    public void setVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }
    public ViewHolder setUrlPicImage(int viewId, String url,Context context) {
        Glide.with(context).load(url).apply(new RequestOptions().error(R.drawable.headpic_error)).into((ImageView) getView(viewId));
        return this;
    }
    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder displayImageByUrl(int viewId, String url, DisplayImageOptions displayImageOptions) {

        if (displayImageOptions == null)
            ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
        else
            ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId), displayImageOptions);
        return this;
    }

    //-------------个人定义图片(开始)----------------------
    DisplayImageOptions displayImage;
    public  DisplayImageOptions getDiplayImage(){
        if (displayImage==null){
            displayImage = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.empty_load) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.empty_load)//设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.empty_load)  //设置图片加载/解码过程中错误时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .displayer(new RoundedBitmapDisplayer(100))//是否设置为圆角，弧度为多少
                    .build();//构建完成
        }
        return displayImage;
    }

    public ViewHolder displayImageByUrl(int viewId, String url) {
        ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId),getDiplayImage());
        return this;
    }
    //-------------个人定义图片(结束)----------------------

    /**
     * 从内存卡中异步加载本地图片
     *
     * @param viewId
     * @param url
     * @param displayImageOptions
     */
    public void displayFromSDCard(int viewId, String url, DisplayImageOptions displayImageOptions) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        if (displayImageOptions == null)
            ImageLoader.getInstance().displayImage("file://" + url, (ImageView) getView(viewId));
        else
            ImageLoader.getInstance().displayImage("file://" + url, (ImageView) getView(viewId), displayImageOptions);
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName           图片名称，带后缀的，例如：1.png
     * @param viewId
     * @param displayImageOptions
     */
    public void dispalyFromAssets(int viewId, String imageName, DisplayImageOptions displayImageOptions) {
        // String imageUri = "assets://image.png"; // from assets
        if (displayImageOptions == null)
            ImageLoader.getInstance().displayImage("assets://" + imageName, (ImageView) getView(viewId));
        else
            ImageLoader.getInstance().displayImage("assets://" + imageName, (ImageView) getView(viewId), displayImageOptions);
    }

    /**
     * 从drawable中异步加载本地图片
     *
     * @param imageId
     * @param viewId
     */
    public void displayFromDrawable(int viewId, int imageId, DisplayImageOptions displayImageOptions) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        if (displayImageOptions == null)
            ImageLoader.getInstance().displayImage("drawable://" + imageId,
                    (ImageView) getView(viewId));
        else
            ImageLoader.getInstance().displayImage("drawable://" + imageId,
                    (ImageView) getView(viewId), displayImageOptions);
    }

    /**
     * 从内容提提供者中抓取图片
     */
    public void displayFromContent(int viewId, String uri, DisplayImageOptions displayImageOptions) {
        // String imageUri = "content://media/external/audio/albumart/13"; //
        // from content provider
        if (displayImageOptions == null)
            ImageLoader.getInstance().displayImage("content://" + uri, (ImageView) getView(viewId));
        else
            ImageLoader.getInstance().displayImage("content://" + uri, (ImageView) getView(viewId), displayImageOptions);

    }

    public int getPosition() {
        return mPosition;
    }

}
