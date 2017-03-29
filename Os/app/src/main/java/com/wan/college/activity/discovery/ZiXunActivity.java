package com.wan.college.activity.discovery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.entity.TabEntity;
import com.wan.college.fragment.ViewPagerContentFragment;
import com.wan.college.fragment.ViewPagerNewsFragment;
import com.wan.college.model.NewsTagsData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.utils.ViewFindUtils;
import java.util.ArrayList;
import java.util.List;

public class ZiXunActivity extends AppCompatActivity implements INetResult<String>{

    View mDecorView;
    ViewPager mViewPager;
    CommonTabLayout zixun_tab;
    ImageButton btn_back;
    ArrayList<Fragment> mFragments;
    ArrayList<CustomTabEntity> mTabEntities;
    NewsTagsData newsTagsData;
 //   String[] mTitles = {"必读", "政策", "专业", "院校"};
    List<String> mTags;
    RelativeLayout loading;
    int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun);
        initView();
        initListener();
        HttpServer.getNewsTitles(new CallbBackString(this));
    }
    public void initView(){
        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.zixun_vp);
        zixun_tab = ViewFindUtils.find(mDecorView, R.id.zixun_tab);
        loading = (RelativeLayout) findViewById(R.id.loading);
        btn_back= (ImageButton) findViewById(R.id.back_btn);
    }
    public void initData(){
        mFragments = new ArrayList<>();
        mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTags.size(); i++) {
            mTabEntities.add(new TabEntity(mTags.get(i), mIconSelectIds[i], mIconUnselectIds[i]));
            mFragments.add(ViewPagerNewsFragment.newInstance(mTags.get(i)));
        }
        /*
        mFragments.add(ViewPagerContentFragment.newInstance(ViewPagerContentFragment.PINDAO_ZIXUN_BIDU));
        mFragments.add(ViewPagerContentFragment.newInstance(ViewPagerContentFragment.PINDAO_ZIXUN_ZHENGCE));
        mFragments.add(ViewPagerContentFragment.newInstance(ViewPagerContentFragment.PINDAO_ZIXUN_ZHUANYE));
        mFragments.add(ViewPagerContentFragment.newInstance(ViewPagerContentFragment.PINDAO_ZIXUN_YUANXIAO));
        */
        zixun_tab.setTabData(mTabEntities);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
    }
    private void initListener() {

        zixun_tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                zixun_tab.setCurrentTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getNetData(String data) {
        mTags=new ArrayList<>();
        newsTagsData = new Gson().fromJson(data, NewsTagsData.class);
        mTags.addAll(newsTagsData.getTags());
        loading.setVisibility(View.GONE);
        initData();
        initListener();
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
         MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTags.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
