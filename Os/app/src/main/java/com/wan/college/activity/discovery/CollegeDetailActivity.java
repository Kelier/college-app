package com.wan.college.activity.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.adapter.CollegeListAdapter;
import com.wan.college.adapter.CollegeViewPagerAdapter;
import com.wan.college.fragment.FragmentDiscovery;
import com.wan.college.fragment.FragmentIntro;
import com.wan.college.fragment.FragmentJiuye;
import com.wan.college.fragment.FragmentLuqu;
import com.wan.college.fragment.ViewPagerContentFragment;
import com.wan.college.model.CollegeDetail;
import com.wan.college.model.CollegeDetailData;
import com.wan.college.model.CollegeList;
import com.wan.college.model.CollegeListData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.AppBarStateChangeListener;
import com.wan.college.ui.FastMoveViewPager;
import com.wan.college.ui.SmartScrollView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class CollegeDetailActivity extends AppCompatActivity implements INetResult<String> {
    CollegeDetailData collegeDetailData;
    List<TextView> college_tag;
    TextView college_name;
    TextView college_rank;
    TextView toolbar_title;
    TabLayout tabLayout;
    ImageView college_logo;
    ImageView college_detail_image;
    ImageButton btn_bcak;
    List<Fragment> contentData;
    List<String> tabTitles;
    FastMoveViewPager content_viewPager;
    FragmentPagerAdapter content_adapter;
    SmartScrollView smartScrollView;
    AppBarLayout app_bar;
    Boolean is_app_bar_expand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //     WindowStatus.setColor(this,R.color.color_theme);
        initView();
        initListener();
        initCollegeData();
        HttpServer.getCollegeDetail(new CallbBackString(this));
    }

    public void initView() {
        smartScrollView = (SmartScrollView) findViewById(R.id.smartScrollView);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        college_tag = new ArrayList<>();
        college_tag.add((TextView) findViewById(R.id.college_tag1));
        college_tag.add((TextView) findViewById(R.id.college_tag2));
        college_tag.add((TextView) findViewById(R.id.college_tag3));
        college_tag.add((TextView) findViewById(R.id.college_tag4));
        college_tag.add((TextView) findViewById(R.id.college_tag5));
        college_tag.add((TextView) findViewById(R.id.college_tag6));
        college_tag.add((TextView) findViewById(R.id.college_tag7));
        college_name = (TextView) findViewById(R.id.college_name);
        college_rank = (TextView) findViewById(R.id.college_rank);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        college_logo = (ImageView) findViewById(R.id.college_logo);
        btn_bcak= (ImageButton) findViewById(R.id.btn_back);
        college_detail_image = (ImageView) findViewById(R.id.college_detail_image);
        tabLayout = (TabLayout) findViewById(R.id.college_tab_layout);
        tabTitles = new ArrayList<>();
        tabTitles.add("录取");
        tabTitles.add("简介");
        tabTitles.add("就业");
        content_viewPager = (FastMoveViewPager) findViewById(R.id.viewPager_content);
    }

    public void initCollegeData() {
        CollegeList data = (CollegeList) getIntent().getExtras().get("college_detail");
        if (data != null) {
            UserMsg.cid = String.valueOf(data.getCollege_id());
            college_name.setText(data.getCollege_name());
            if (data.getTags() != null) {
                for (int i = 0; i < data.getTags().size(); i++) {
                    college_tag.get(i).setText(data.getTags().get(i));
                    college_tag.get(i).setVisibility(View.VISIBLE);
                }
            }
            x.image().bind(college_logo, data.getCollege_icon());
        }
    }

    public void initListener() {
        smartScrollView.setScanScrollChangedListener(new SmartScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                //   Toast.makeText(CollegeDetailActivity.this,smartScrollView.isScrolledToTop()+"底部",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolledToTop() {
                //    Toast.makeText(CollegeDetailActivity.this,smartScrollView.isMovedToBottom()+"顶部",Toast.LENGTH_SHORT).show();
                if (!is_app_bar_expand) app_bar.setExpanded(true);

            }
        });
        //返回键监听
        btn_bcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                //   Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    is_app_bar_expand = true;
                    //展开状态
                    toolbar_title.setText(" ");
                } else if (state == State.COLLAPSED) {
                    is_app_bar_expand = false;
                    //折叠状态
                    toolbar_title.setText(collegeDetailData.getCollege_name());
                } else {
                    is_app_bar_expand = true;
                    //中间状态
                    toolbar_title.setText(" ");
                }
            }
        });
    }

    @Override
    public void getNetData(String data) {
        collegeDetailData = new Gson().fromJson(data, CollegeDetailData.class);
    /*    CollegeDetail collegeDetail= new CollegeDetail();
        collegeDetail.setAddress(collegeDetailData.getAddress());
        collegeDetail.setBack_cover(collegeDetailData.getBack_cover().getUrl());
        collegeDetail.setBelong_to(collegeDetailData.getBelong_to());
        collegeDetail.setCollege_description(collegeDetailData.getCollege_description());
        collegeDetail.setCollege_type(collegeDetailData.getCollege_type());
        collegeDetail.setCollege_name(collegeDetailData.getCollege_name());
        collegeDetail.setDoctor_num(collegeDetailData.getDoctor_num());*/
        college_rank.setText("No." + collegeDetailData.getRanking());
        x.image().bind(college_detail_image, collegeDetailData.getBack_cover().getUrl());
        contentData = new ArrayList<>();
        contentData.add(new FragmentLuqu());
        contentData.add(new FragmentIntro());
        contentData.add(new FragmentJiuye());
        content_adapter = new CollegeViewPagerAdapter(getSupportFragmentManager(), tabTitles, contentData);
        if (content_viewPager != null) {
            content_viewPager.setOffscreenPageLimit(2);
            content_viewPager.setAdapter(content_adapter);
        }
        // 将TabLayout和ViewPager的结合
        tabLayout.setupWithViewPager(content_viewPager);
    }

    public CollegeDetailData getDetailData() {
        return collegeDetailData;
    }






/*
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                startActivity(new Intent().setClass(CollegeDetailActivity.this,SearchActivity.class));
                break;
            default:break;
        }

        return true;
    }
    */
}
