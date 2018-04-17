package com.jdjtlibrary.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jdjtlibrary.R;


public class AutoListView extends ListView implements OnScrollListener {

    private View footerView;
    private LinearLayout footer;

    private int totalItem;
    private int lastItem;

    /**
     * 是否正在加载
     */
    private boolean isLoading;

    private OnLoadListener mOnLoadListener;

    private LayoutInflater inflater;

    private boolean mIsNeedMoreLoading = true;

    public AutoListView(Context context) {
        super(context);
        init(context);
    }

    public AutoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void init(Context context) {  //初始化内容
        inflater = LayoutInflater.from(context);
        initFooterView(context);
        this.setOnScrollListener(this);
    }
    LinearLayout footerParent;
    public void initFooterView(Context context) {
        if (getFooterViewsCount() <= 0) {
            footerParent = new LinearLayout(context);
            footerView = inflater.inflate(R.layout.item_view_load_more, null, false);
            footer= (LinearLayout) footerView.findViewById(R.id.foot_lay);
            footerParent.addView(footerView);
            this.addFooterView(footerParent);
            footer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastItem = firstVisibleItem + visibleItemCount;
        this.totalItem = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (this.totalItem == lastItem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                if (mOnLoadListener != null && mIsNeedMoreLoading) {
                    onLoading();
                }
            }
        }
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.mOnLoadListener = onLoadListener;
    }

    /**
     * 加载完成调用此方法
     */
    public void onLoadComplete() {
//        footer.setVisibility(View.GONE);
        isLoading = false;
    }

    public void hideFootView() {
        footer.setVisibility(View.GONE);
    }
    public void removeFootView() {
        if (footer!=null){
            footerParent.removeView(footer);
        }


    }

    public void onLoading() {
        isLoading = true;
        footer.setVisibility(View.VISIBLE);
        mOnLoadListener.onLoad();
    }

    public boolean getIsNeedMoreLoadStatus() {
        return mIsNeedMoreLoading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    /**
     * 是否需要加载更多功能
     *
     * @param isNeed false不需要   true需要
     */
    public void isNeedMoreLoad(boolean isNeed) {
        this.mIsNeedMoreLoading = isNeed;
        dealFootView();
    }

    public void dealFootView() {
        if (mIsNeedMoreLoading)
            footer.setVisibility(View.VISIBLE);
        else {
            footer.setVisibility(View.GONE);
        }

    }

    public interface OnLoadListener {
        void onLoad();
    }
}