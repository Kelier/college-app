package com.wan.college.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 解决scollview嵌套listview滑动冲突的方法
 * 1，重写listview的onMeasure方法，使其可以全部显示
 * 2，重写listview的onTouchEvent方法，使其失去焦点
 * 3，布局xml中，使用scollview嵌套scollview，listview放入小的scollview中
 * 转换为解决scollview嵌套的方法
 * @author Administrator
 *
 */
public class MajorListView extends ListView{

	public MajorListView(Context context) {
		super(context);
	}
	public MajorListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MajorListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * 重写该方法适应scrollview,能使listview全部显示
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	/**
	 * listview失去焦点
	 * @param ev
	 * @return
	 */

	@Override

	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}

}
