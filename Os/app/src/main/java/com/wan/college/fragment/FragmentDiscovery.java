package com.wan.college.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.wan.college.R;
import com.wan.college.activity.discovery.CollegeListActivity;
import com.wan.college.activity.discovery.DiscoverySearchActivity;
import com.wan.college.activity.discovery.MajorSelectActivity;
import com.wan.college.activity.discovery.PaiHangActivity;
import com.wan.college.activity.discovery.PiCiActivity;
import com.wan.college.activity.discovery.XingGeCeShiGuideActivity;
import com.wan.college.activity.discovery.XuanZhuanYeActivity;
import com.wan.college.activity.discovery.ZiXunActivity;
import com.wan.college.adapter.DiscoveryAdapter;
import com.wan.college.model.FindBean;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;

import java.util.ArrayList;


public class FragmentDiscovery extends Fragment {
	ArrayList<FindBean> data;
	DiscoveryAdapter adapter;
	RecyclerView.LayoutManager layoutManager;
	RecyclerView rv;
	RelativeLayout discover_head_search;

	public final static int GAOKAOZIXUN=0;
	public final static int YUANXIAO=1;
	public final static int ZHUANYE=2;
	public final static int PICI=3;
	public final static int PAIHANG=4;
	public final static int XUANZHUANYE=5;
	public final static int XINGGECESHI=6;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.content_footer_fragment_discovery,container, false);
		initView(view);
		addListener();
		return view;
	}



	private void initView(View view) {
		discover_head_search= (RelativeLayout) view.findViewById(R.id.discover_head_search);
        // 数据源
		data = new ArrayList<>() ;
		data.add(new FindBean(R.drawable.checkpage_answer_icon ,"高校资讯","最全的高考志愿解读"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_school,"院校查询","各大院校信息查询"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_profession,"专业查询","各大学专业查询"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_patchline,"批次线查询","2016年各省录取批次线查询"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_charts,"排行榜","各类大学的排行榜"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_occupation,"如何选专业","根据职业规划选专业"));
		data.add(new FindBean(R.drawable.checkdata_homepage_img_occupationtest,"职业性格测试","快速了解自己的性格定位"));
		// 适配器
		adapter = new DiscoveryAdapter(view.getContext(), data ) ;
		// 布局管理器
//         线性布局
		layoutManager = new LinearLayoutManager(view.getContext()) ;
		((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
		// 网格布局
//        manager = new GridLayoutManager(this , 3) ;
		// 瀑布布局
//        manager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL) ;
//        // 控件
		rv = (RecyclerView) view.findViewById(R.id.recycler_view1);
		// 控件调用两个set方法
		rv.setAdapter(adapter);
		rv.setLayoutManager(layoutManager);
	}
	private void addListener() {
		//调用方法,传入一个接口回调
		adapter.setItemClickListener(new DiscoveryAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				goToActivity(position);
				//	Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
			}
		});

        discover_head_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), DiscoverySearchActivity.class);
                startActivity(intent);
            }
        });
	}
	public void goToActivity(int position){
		Intent intent=new Intent();
		switch(position){
			case GAOKAOZIXUN:
				intent.setClass(getActivity(), ZiXunActivity.class);
				break;
			case YUANXIAO:
				UserMsg.risk=0;
				intent.setClass(getActivity(), CollegeListActivity.class);
				break;
			case ZHUANYE:
				intent.setClass(getActivity(), MajorSelectActivity.class);
				break;
			case PICI:
				intent.setClass(getActivity(),PiCiActivity.class);
				break;
			case PAIHANG:
				intent.setClass(getActivity(),PaiHangActivity.class);
				break;
			case XUANZHUANYE:
				intent.setClass(getActivity(),XuanZhuanYeActivity.class);
				break;
			case XINGGECESHI:
				intent.setClass(getActivity(),XingGeCeShiGuideActivity.class);
				break;
			default:
				break;
		}
		if(intent.getClass()!=null){
			startActivity(intent);
		}
	}
}
