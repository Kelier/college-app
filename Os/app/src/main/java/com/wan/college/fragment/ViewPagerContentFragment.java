package com.wan.college.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ViewPagerContentFragment extends Fragment implements INetResult<String> {
    public static final int PINDAO_LUQU = 1;
    public static final int PINDAO_JIANJIE = 2;
    public static final int PINDAO_JIUYE = 3;
    public static final int PINDAO_HOME = 4;
    public static final int PINDAO_QA = 5;
    public static final int PINDAO_DISCOVERY = 6;
    public static final int PINDAO_MY = 7;
    public static final int PINDAO_ZIXUN_BIDU = 8;
    public static final int PINDAO_ZIXUN_ZHENGCE = 9;
    public static final int PINDAO_ZIXUN_ZHUANYE = 10;
    public static final int PINDAO_ZIXUN_YUANXIAO = 11;

    private static final String ARG_PARAM1 = "param1";

    // Fragment初始化时传入参数的保存变量
    private int type;


    ArrayList<News> data = new ArrayList<>();
    ZiXunAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rv;


    // 建议不要该函数，即便保留，不描述内容
    public ViewPagerContentFragment() {
    }

    /**
     * 如果当前Fragment需要初始化时传入参数，调用该方法获取Fragment的实例对象
     * 参数列表就是Fragment初始化传参使用
     */

    public static ViewPagerContentFragment newInstance(int type) {
        ViewPagerContentFragment fragment = new ViewPagerContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int i = initView();
        View root;
        root = inflater.inflate(i, container, false);
        initView(root);
        initData();
        initView();
        return root;
    }

    private void initView(View view) {
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
                //    Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
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
    }

    private int initView() {
        int fragment = R.layout.fragment_zixun;
        switch (type) {
            case PINDAO_LUQU:
                fragment = R.layout.fragment_college_adimit;
                break;
            case PINDAO_JIANJIE:
                fragment = R.layout.fragment_college_intro;
                break;
            case PINDAO_JIUYE:
                fragment = R.layout.fragment_college_employment;
                break;
            case PINDAO_HOME:
                fragment = R.layout.content_footer_fragment_home;
                break;
            case PINDAO_QA:
                fragment = R.layout.content_footer_fragment_qa;
                break;
            case PINDAO_DISCOVERY:
                fragment = R.layout.content_footer_fragment_discovery;
                break;
            case PINDAO_MY:
                fragment = R.layout.content_footer_fragment_my;
                break;
            case PINDAO_ZIXUN_BIDU:
                fragment = R.layout.fragment_zixun;
                HttpServer.getBidu(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_ZHENGCE:
                fragment = R.layout.fragment_zixun;
                HttpServer.getZhengce(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_ZHUANYE:
                fragment = R.layout.fragment_zixun;
                HttpServer.getZhuanye(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_YUANXIAO:
                fragment = R.layout.fragment_zixun;
                HttpServer.getYuanxiao(new CallbBackString(this));
                break;
            default:
                fragment = R.layout.content_fragment_404;
        }
        return fragment;
    }
    private void initData(){
        switch (type) {
            case PINDAO_ZIXUN_BIDU:
                HttpServer.getBidu(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_ZHENGCE:
                HttpServer.getZhengce(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_ZHUANYE:
                HttpServer.getZhuanye(new CallbBackString(this));
                break;
            case PINDAO_ZIXUN_YUANXIAO:
                HttpServer.getYuanxiao(new CallbBackString(this));
                break;
            default:
        }
    }
}
