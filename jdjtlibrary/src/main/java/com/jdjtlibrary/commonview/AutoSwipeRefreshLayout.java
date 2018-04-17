package com.jdjtlibrary.commonview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jdjtlibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class AutoSwipeRefreshLayout extends SwipeRefreshLayout {

    private float mStartX;

    private float mStartY;

    private float mEndX;

    private float mEndY;

    public AutoSwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public AutoSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.light_blue,
                R.color.deep_blue
        );
    }

    /**
     * 自动刷新
     */
    public void AutoRefresh() {
        try {
            Field mCircleView = SwipeRefreshLayout.class.getDeclaredField("mCircleView");
            mCircleView.setAccessible(true);
            View progress = (View) mCircleView.get(this);
            progress.setVisibility(VISIBLE);

            Method setRefreshing = SwipeRefreshLayout.class.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
            setRefreshing.setAccessible(true);
            setRefreshing.invoke(this, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 防止AutoSwipeRefreshLayout 拦截ViewPager事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mEndX = ev.getX();
                mEndY = ev.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
                mStartX = 0;
                mStartY = 0;
                break;
        }
        return super.onInterceptTouchEvent(ev) && (Math.abs(mEndX - mStartX) < Math.abs(mEndY - mStartY));
    }
}
