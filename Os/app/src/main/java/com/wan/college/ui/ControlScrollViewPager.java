package com.wan.college.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 万文杰 on 2017/1/7.
 */

public class ControlScrollViewPager extends ViewPager {

    private boolean scrollable = true;

    public ControlScrollViewPager(Context context) {
        super(context);
    }

    public ControlScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean enable) {
       this.scrollable = enable;
    }

/*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (scrollable) {
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }
    }
*/
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
       return scrollable;//触摸时禁止事件
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return scrollable;//触摸时禁止事件
    }
}
