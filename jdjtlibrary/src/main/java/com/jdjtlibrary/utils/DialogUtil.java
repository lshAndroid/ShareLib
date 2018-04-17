package com.jdjtlibrary.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jdjtlibrary.R;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class DialogUtil {
    public final static int LOADING_ID = 0x01;

    /**
     * 显示加载进度的对话框
     *
     * @param context
     * @return
     */
    public static AlertDialog showProgressDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        TextView mTvLoadMsg = (TextView) view.findViewById(R.id.tv_load_message);
        if (!StringUtils.isEmptyString(message)) {
            mTvLoadMsg.setText(message);
        }
        builder.setView(view);
        return builder.show();
    }

//    /**
//     * 城市底部选择dialog类似iOS
//     *
//     * @author 文仲
//     */
//
//    public static CityPickerDialog showCityDialog(Activity activity,
//                                                  WindowManager windowManager, final TextView textView) {
//
//        final CityPickerDialog mDialog = new CityPickerDialog(activity,
//                R.style.Dialog_Transparent_Theme, textView);
//        dialogBottom(mDialog, windowManager);
//
//        return mDialog;
//    }

    /**
     * 控制dialog显示在底部
     */
    private static void dialogBottom(Dialog mDialog, WindowManager windowManager) {
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        mDialog.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        mDialog.show();

        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        mDialog.getWindow().setAttributes(lp);
    }

//    /**
//     * 拨打电话
//     * @param context
//     * @param listener
//     */
//    public static void showCallPhoneDialog(Context context, String message, DialogInterface.OnClickListener listener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
//        builder.setMessage(message);
//        builder.setPositiveButton(Res.getString(R.string.yes), listener);
//        builder.setNegativeButton(Res.getString(R.string.no), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }
//
//    /**
//     * 是否删除自己的评论
//     *
//     * @param context
//     * @param listener
//     */
//    public static void showDeleteCommentDialog(Context context, DialogInterface.OnClickListener listener
//            , final OnDeleteDismissListener onDeleteDismissListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
//        builder.setMessage("是否删除自己的评论？");
//        builder.setPositiveButton(Res.getString(R.string.yes), listener);
//        builder.setNegativeButton(Res.getString(R.string.no), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                if (onDeleteDismissListener != null)
//                    onDeleteDismissListener.onDeleteDismissListener();
//            }
//        });
//        builder.show();
//    }
//
//    /**
//     * 弹出时间dialog  日期限定为1920-1-1 至当前日期的前一年
//     */
//    public static void choiceBirthDay(Context mContext, final TextView mBirthTv) {
//
//        Calendar mCalendar = Calendar.getInstance();
//        final int now_year = mCalendar.get(Calendar.YEAR);
//        final int now_month = mCalendar.get(Calendar.MONTH);
//        final int now_day = mCalendar.get(Calendar.DAY_OF_MONTH);
//        final int month_now = now_month + 1;
//
//        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                monthOfYear = monthOfYear + 1;
//
//                long timeDiff = TimeUtil.dateDiff(now_year - 1 + "-"
//                        + month_now + "-"
//                        + now_day, year
//                        + "-" + monthOfYear + "-"
//                        + dayOfMonth, "yyyy-MM-dd");
//                if (timeDiff >= 0) {
//                    ToastManager.showToast(R.string.please_enter_right_birth);
//                    mBirthTv.setText("");
//                } else {
//                    long dayDiff = TimeUtil.dateDiff(1920 + "-"
//                            + 1 + "-"
//                            + 1, year
//                            + "-" + monthOfYear + "-"
//                            + dayOfMonth, "yyyy-MM-dd");
//                    if (dayDiff <= 0) {
//                        ToastManager.showToast(R.string.please_enter_right_birth);
//                        mBirthTv.setText("");
//                    } else {
//                        mBirthTv.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
//                    }
//                }
//            }
//        }, now_year, now_month, now_day).show();
//    }
//

//    /**
//     * 设置添加屏幕的背景透明度
//     *
//     * @param bgAlpha
//     */
//    private static void backgroundAlpha(Activity context, float bgAlpha) {
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        context.getWindow().setAttributes(lp);
//    }
//
//
//    public static Dialog showLoadDialog(Context context
//            , int content
//            , boolean isCancelable) {
//
//        Dialog mDialog = new Dialog(context, R.style.Dialog_Transparent);
//        View view = LayoutInflater.from(context).inflate(R.layout.load_dialog,
//                null);
//        mDialog.setContentView(view);
//
//        ImageView iv = (ImageView) view.findViewById(R.id.dialog_iv);
//        TextView tv = (TextView) view.findViewById(R.id.dialog_tv);
//
//        tv.setText(content);
//        AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
//        ad.start();
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.setCancelable(isCancelable);
//
//        return mDialog;
//    }
//
//    /**
//     * 显示当前网络状态提醒
//     *
//     * @param context
//     * @param mPositiveListener 确定监听,默认取消监听为消失dialog
//     */
//    public static void showNetStatusDialog(Context context
//            , String message
//            , DialogInterface.OnClickListener mPositiveListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
//        builder.setMessage(message);
//        builder.setPositiveButton(Res.getString(R.string.yes), mPositiveListener);
//        builder.setNegativeButton(Res.getString(R.string.no), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }
//
//
//    /**
//     * 我的消息 评论中的弹出框
//     *
//     * @param context
//     * @return
//     */
//    public static Dialog showCommentDialog(Context context
//            , int mWidth
//            , final OnSelectedCommentListener listener) {
//
//        final Dialog mDialog = new Dialog(context, R.style.Dialog_Transparent_Theme);
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_choice_comment, null);
//        view.findViewById(R.id.tv_feedback).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.getUserSelectedComment("回复");
//                mDialog.dismiss();
//            }
//        });
//        view.findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.getUserSelectedComment("查看详情");
//                mDialog.dismiss();
//            }
//        });
//        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.setContentView(view);
//        mDialog.setCanceledOnTouchOutside(true);
//
//        Window window = mDialog.getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.gravity = Gravity.BOTTOM;
//        params.width = mWidth * 9 / 10;
//        window.setWindowAnimations(R.style.popupAnimation);
//        window.setAttributes(params);
//
//        mDialog.show();
//        return mDialog;
//    }
//
//    /**
//     * 显示是否删除提醒
//     *
//     * @param context
//     * @param listener 确定监听,默认取消监听为消失dialog
//     */
//    public static void showDeleDialog(Context context
//            , String message
//            , DialogInterface.OnClickListener listener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
//        builder.setMessage(message);
//        builder.setPositiveButton(Res.getString(R.string.yes), listener);
//        builder.setNegativeButton(Res.getString(R.string.no), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }
//
//    /**
//     * 此 dialog 效果从底部弹出，可以添加任意多个Item。
//     *
//     * @param context
//     * @param mWidth   需要的dialog的宽度
//     * @param listener 选择监听
//     * @param contents 任意多个Item选项内容（从最顶端到最低端）
//     * @return
//     */
//    public static Dialog showItemSelectDialog(Context context
//            , int mWidth
//            , final OnItemSelectedListener listener
//            , final String... contents) {
//
//        final Dialog mDialog = new Dialog(context, R.style.Dialog_Transparent_Theme);
//        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_choice, null);
//
//        LinearLayout contentsView = (LinearLayout) rootView.findViewById(R.id.dialogContent);
//        if (contents.length == 1) {
//            View topView = LayoutInflater.from(context).inflate(R.layout.dialog_one_item, null);
//            TextView topText = (TextView) topView.findViewById(R.id.dialog_one);
//            topText.setText(contents[0]);
//            topText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    mDialog.dismiss();
//                    if (listener != null)
//                        listener.getSelectedItem(contents[0]);
//                }
//            });
//            contentsView.addView(topView);
//        } else
//            for (int i = 0; i < contents.length; i++) {
//                if (i == 0) {
//                    View topView = LayoutInflater.from(context).inflate(R.layout.dialog_top_item, null);
//                    TextView topText = (TextView) topView.findViewById(R.id.dialog_top);
//                    topText.setText(contents[0]);
//                    topText.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            mDialog.dismiss();
//                            if (listener != null)
//                                listener.getSelectedItem(contents[0]);
//                        }
//                    });
//                    contentsView.addView(topView);
//                } else if (i == contents.length - 1) {
//                    View bottomView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_item, null);
//                    TextView boottomTv = (TextView) bottomView.findViewById(R.id.dialog_bottom);
//                    boottomTv.setText(contents[contents.length - 1]);
//                    boottomTv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            mDialog.dismiss();
//                            if (listener != null)
//                                listener.getSelectedItem(contents[contents.length - 1]);
//                        }
//                    });
//                    contentsView.addView(bottomView);
//                } else {
//                    View centerView = LayoutInflater.from(context).inflate(R.layout.dialog_center_item, null);
//                    TextView centTv = (TextView) centerView.findViewById(R.id.dialog_center_item);
//                    final int finalI = i;
//                    centTv.setText(contents[finalI]);
//                    centTv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            mDialog.dismiss();
//                            if (listener != null)
//                                listener.getSelectedItem(contents[finalI]);
//                        }
//                    });
//                    contentsView.addView(centerView);
//                }
//            }
//        rootView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.setContentView(rootView);
//        mDialog.setCanceledOnTouchOutside(true);
//
//
//        Window window = mDialog.getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.gravity = Gravity.BOTTOM;
//        params.width = mWidth * 9 / 10;
//        window.setWindowAnimations(R.style.popupAnimation);
//        window.setAttributes(params);
//
//        mDialog.show();
//
//        return mDialog;
//    }
}
