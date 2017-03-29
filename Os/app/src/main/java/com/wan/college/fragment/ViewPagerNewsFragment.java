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

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.activity.WebActivity;
import com.wan.college.adapter.ZiXunAdapter;
import com.wan.college.animator.MyItemAnimator;
import com.wan.college.model.News;
import com.wan.college.model.NewsData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;

import java.util.ArrayList;

public class ViewPagerNewsFragment extends Fragment implements INetResult<String> {

    public static final int PINDAO_ZIXUN_BIDU = 8;
    public static final int PINDAO_ZIXUN_ZHENGCE = 9;
    public static final int PINDAO_ZIXUN_ZHUANYE = 10;
    public static final int PINDAO_ZIXUN_YUANXIAO = 11;

    private static final String ARG_PARAM1 = "param1";

    // Fragment初始化时传入参数的保存变量
    private String type;


    ArrayList<News> data = new ArrayList<>();
    ZiXunAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rv;
    RelativeLayout loading;

    // 建议不要该函数，即便保留，不描述内容
    public ViewPagerNewsFragment() {
    }

    /**
     * 如果当前Fragment需要初始化时传入参数，调用该方法获取Fragment的实例对象
     * 参数列表就是Fragment初始化传参使用
     */

    public static ViewPagerNewsFragment newInstance(String type) {
        ViewPagerNewsFragment fragment = new ViewPagerNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_zixun, container, false);
        initView(root);
        initData();
        return root;
    }

    private void initView(View view) {
        loading=(RelativeLayout) view.findViewById(R.id.loading);
        rv = (RecyclerView) view.findViewById(R.id.recycler_zixun);
        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new MyItemAnimator());
    }

    private void addListener() {
        //调用方法,传入一个接口回调
        adapter.setItemClickListener(new ZiXunAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra("url", data.get(position).getNews_url());
                intent.putExtra("title", data.get(position).getNew_title());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getNetData(String rdata) {
        NewsData newsData = new Gson().fromJson(rdata, NewsData.class);
        for (int i = 0; i < newsData.getPapers().size(); i++) {
            NewsData.PapersBean papersBean = newsData.getPapers().get(i);
            NewsData.PapersBean.CoverBean coverBean = papersBean.getCover();
            data.add(new News(papersBean.getPaper_url(), papersBean.getPaper_title(), papersBean.getPaper_snippet(), papersBean.getTags().toString(), coverBean.getUrl(), String.valueOf(papersBean.getPaper_views())));
        }
        adapter = new ZiXunAdapter(getContext(), data);
        rv.setAdapter(adapter);
        addListener();
        loading.setVisibility(View.GONE);
    }

    private void initData(){
        loading.setVisibility(View.VISIBLE);
        HttpServer.getNews(new CallbBackString(this),type);
    }
}
