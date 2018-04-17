package com.jdjtlibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void ToastShort(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    public void ToastShort(int msgRes) {
        ToastShort(getResources().getString(msgRes));
    }
    //----------------------Activity中的切换(开始)------------------
    public void IntentUtil(Context context, Class _Class) {
        Intent intent = new Intent(context, _Class);
        context.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_in);
    }
    public void IntentUtil(Context context, Class _Class,
                           String name,String putStr) {
        Intent intent = new Intent(context, _Class);
        intent.putExtra(name,putStr);
        context.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_in);

    }
    public void IntentUtil(Context context, Class _Class,
                           String name,String putStr,
                           String name2,String putStr2) {
        Intent intent = new Intent(context, _Class);
        intent.putExtra(name,putStr);
        intent.putExtra(name2,putStr2);
        context.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_in);

    }
    //----------------------Activity中的切换(结束)------------------
}
