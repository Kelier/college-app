package com.wan.college.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 万文杰 on 2017/1/10.
 */

public class FastMoveViewPager extends ViewPager{

    private int downX = 0;
    double preX = 0;
    public FastMoveViewPager(Context context) {
        super(context);
    }


    public FastMoveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

/*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:

                downX = (int)ev.getX();

                //注意！这里必须有，否则快速滑动会失灵
                getParent().requestDisallowInterceptTouchEvent(true);

                break;

            case MotionEvent.ACTION_MOVE:

                int moveX = (int) ev.getX();

                //在这里写事件拦截的条件，

                if (downX < 20) {
                    if (moveX > downX) {
                        //请求不中断事件
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else{
                        //请求中断事件
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }else{
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

        }

        return super.dispatchTouchEvent(ev);
    }
*/
/*
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        boolean interceptTouchEvent = super.onInterceptTouchEvent(event);
        preX = 0;
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            preX = event.getX();

        } else {
            if( Math.abs(event.getX() - preX)>4) {
                return true;
            } else {
                preX = event.getX();
            }
        }
        return interceptTouchEvent;
    }
*/


}
