package com.jdjtlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jdjtlibrary.R;
import com.jdjtlibrary.net.NetworkUtils;
import com.jdjtlibrary.utils.StringUtils;

/**
 * Created by lsh on 2017/4/21.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  //在AppCompatActivity中的方法
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getScreenNum();
    }

    public void ToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void ToastShort(int msgRes) {
        ToastShort(getResources().getString(msgRes));
    }
    //----------------获取屏幕的宽度,高度,密度(开始)-----------------
    //屏幕的宽度
    public int mWidth;
    //屏幕的高度
    public int mHeight;
    //屏幕密度
    public float mDensity;
    public void getScreenNum(){
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mDensity = dm.density;
    }
    /**
     * 根据手机的分辨率从 dp  的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        return (int) (dpValue * mDensity + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        return (int) (pxValue / mDensity + 0.5f);
    }
    //----------------获取屏幕的宽度,高度,密度(结束)-----------------

    //-------------输入键盘控制(开始)-------------------
    /**
     * 关闭键盘
     */
    public void keyBoardCancle() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 弹出软件盘
     */
    public void ShowKeyBoard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        }
    }
    //-------------输入键盘控制(结束)-------------------
    //-----------------按钮点击效果更换(开始)-------------
    /**
     * 修改按钮的背景颜色
     * @param bt    Button对象
     * @param color 颜色 如 #BBBBBB
     */
    public void updateButtonBack(Button bt, String color) {
        GradientDrawable myGrad = (GradientDrawable) bt.getBackground();
        myGrad.setColor(Color.parseColor(color));
    }
    //-----------------按钮点击效果更换(结束)-------------

    /**
     * 是否连接上网络
     *
     * @return false未连接上网络 true 连接上网络
     */
    public boolean isConnectNetWork() {
        if (!NetworkUtils.isConnectInternet(this)) {
            ToastShort("网络无法连接");
            return false;
        }
        return true;
    }

//    /**
//     * @return false 未登录账号 true 登录账号
//     */
//    public boolean isLoginAccount() {
//        if (!LoginUtils.isLogin()) {
//            IntentUtil.startActivity(this, LoginActivity.class, getClass().getSimpleName());
//            return false;
//        }
//        return true;
//    }

    /**
     * @return false 输入内容为空 true 输入内容不为空
     */
    public boolean isEmptyString(String content) {
        if (StringUtils.isEmptyString(content)) {
            ToastShort("输入内容不能为空!");
            return true;
        }
        return false;
    }

    //----------------------Activity中的切换(开始)------------------
    public void IntentUtil(Context context, Class _Class) {
        Intent intent = new Intent(context, _Class);
        context.startActivity(intent);
//        overridePendingTransition(R.anim.intent_show_in,
//                R.anim.intent_show_out);
    }
    public void IntentUtil(Context context, Class _Class,
                           String name,String putStr) {
        Intent intent = new Intent(context, _Class);
        intent.putExtra(name,putStr);
        context.startActivity(intent);
        overridePendingTransition(R.anim.intent_show_in,
                R.anim.intent_show_out);
    }
    public void IntentUtil(Context context, Class _Class,
                           String name,String putStr,
                           String name2,String putStr2) {
        Intent intent = new Intent(context, _Class);
        intent.putExtra(name,putStr);
        intent.putExtra(name2,putStr2);
        context.startActivity(intent);
        overridePendingTransition(R.anim.intent_show_in,
                R.anim.intent_show_out);
    }
    //----------------------Activity中的切换(结束)------------------


    //-------------------动画集中(开始)---------------------
    public void scaleAnimal(RelativeLayout layout) {  //下方图片的点击放大效果
        ScaleAnimation sa = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(150);
        layout.startAnimation(sa);
    }
    public void scaleAnimal(ImageView imageView) {  //下方图片的点击放大效果
        ScaleAnimation sa = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(150);
        imageView.startAnimation(sa);
    }
    public void scaleAnimal(TextView layout) {  //下方图片的点击放大效果
        ScaleAnimation sa = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(150);
        layout.startAnimation(sa);
    }
    //-------------------动画集中(结束)---------------------
}
