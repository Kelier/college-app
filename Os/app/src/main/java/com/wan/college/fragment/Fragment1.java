package com.wan.college.fragment;



import com.wan.college.R;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OnClickListener{


	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter adapter;
	private ProgressDialog progressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.swiper_refresh,null);

		setView(view);
		hander.sendEmptyMessage(0);


		return view;
	}

	private void setView(View view){
		mListView = (ListView) view.findViewById(R.id.listview);
		mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(this);
		// 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
		mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
		mSwipeLayout.setProgressBackgroundColor(R.color.color_theme);
		mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
	}

	private Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 0:



					mListView.deferNotifyDataSetChanged();
					adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,getData());
					adapter.notifyDataSetChanged(); //发送消息通知ListView更新
					mListView.setAdapter(adapter); // 重新设置ListView的数据适配器

					break;
				default:
					//do something
					break;
			}
		}
	};
	private Thread mThreadLoadApps = new Thread(){
		@Override
		public void run() {
			DownloadData();    // 下载数据（耗时的操作）
			hander.sendEmptyMessage(0); // 下载完成后发送处理消息
		//	progressDialog.dismiss();   // 关闭进度条对话框
		}
	};
	/**

	 * 下载网络数据（执行后会更新适配器smAdapter中的数据）
	 */
	private void DownloadData (){
		//do something

	}
	/**
	 * 按钮点击处理事件
	 */
	@Override
	public void onClick(View v) {
		progressDialog = ProgressDialog.show(getActivity(), "请稍等", "正在下载数据...", true);
		// 开始动态加载线程
		mThreadLoadApps.start();
	}
	private ArrayList<String> getData() {
		list.add("测试1");
		list.add("测试2");
		list.add("测试3");
		list.add("测试4");
		list.add("测试5");
		list.add("测试6");
		return list;
	}

	/*
	 * 监听器SwipeRefreshLayout.OnRefreshListener中的方法，当下拉刷新后触发
	 */
	public void onRefresh() {
		// 开始动态加载线程
		//mThreadLoadApps.start();
		DownloadData();    // 下载数据（耗时的操作）
		hander.sendEmptyMessage(0); // 下载完成后发送处理消息
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// 停止刷新


				mSwipeLayout.setRefreshing(false);
			}
		}, 3000); // 3秒后发送消息，停止刷新
	}
	
}



