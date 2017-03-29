package com.wan.college.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.college.R;
import com.wan.college.adapter.CollegeViewPagerAdapter;
import com.wan.college.fragment.FragmentDiscovery;
import com.wan.college.fragment.FragmentHome;
import com.wan.college.fragment.FragmentMy;
import com.wan.college.fragment.FragmentQa;
import com.wan.college.ui.ControlScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    ArrayList<RadioButton> rd_btn;
    RadioGroup radioGroup;
    List<Fragment> contentData;
    ControlScrollViewPager content_viewpager;
    FragmentPagerAdapter content_adapter;
    TextView toolbar_title;
    Toolbar toolbar;
    ArrayList<String> toolbarTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer);

     //   User user = new User(UserMsg.sid, UserMsg.nickname, UserMsg.score, UserMsg.province, UserMsg.subject,UserMsg.gender, UserMsg.avatar);
     //   SQLiteDBUtils sqLiteDBUtils = new SQLiteDBUtils(this, TestModel.class);//实例化对象
     //   sqLiteDBUtils.openDataBase();//打开数据库
        //增删改查操作
      	//sqLiteDBUtils.insertData(user);//增加数据
        //	sqLiteDBUtils.deleteData("sid",UserMsg.sid);//删除数据
        //	sqLiteDBUtils.updateData("sid", user2);//更新数据
        //	sqLiteDBUtils.deleteAllData();//删除所有数据
/*
        List<User> us=sqLiteDBUtils.queryData("sid","qwertyuiop");
        for (User u:us) {
            Log.i("SQ",u.getAvatar()+" "+u.getGender()+" "+u.getNickname()+" "+u.getProvince()+" "+u.getScore());
        }*/
/*
        List<User> us=sqLiteDBUtils.queryDataList();
        for (User u:us) {
            Log.i("SQ",u.getAvatar()+" "+u.getGender()+" "+u.getNickname()+" "+u.getProvince()+" "+u.getScore());
        }
*/
      //  sqLiteDBUtils.closeDataBase();
        initView();
        initListener();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        String[] arr = {"首页", "问答", "发现", "我的"};
        toolbarTitles = new ArrayList<>(Arrays.asList(arr));
        radioGroup = (RadioGroup) findViewById(R.id.footer_menu_rgroup);
        rd_btn = new ArrayList<>();
        rd_btn.add((RadioButton) findViewById(R.id.footer_rbtn_home));
        rd_btn.add((RadioButton) findViewById(R.id.footer_rbtn_category));
        rd_btn.add((RadioButton) findViewById(R.id.footer_rbtn_discovery));
        rd_btn.add((RadioButton) findViewById(R.id.footer_rbtn_my));
        contentData = new ArrayList<>();
        contentData.add(new FragmentHome());
        contentData.add(new FragmentQa());
        contentData.add(new FragmentDiscovery());
        contentData.add(new FragmentMy());
        content_adapter = new CollegeViewPagerAdapter(getSupportFragmentManager(), null, contentData);
        content_viewpager = (ControlScrollViewPager) findViewById(R.id.viewPager_content);
        content_viewpager.setScrollable(false);
        content_viewpager.setOffscreenPageLimit(3);
        content_viewpager.setAdapter(content_adapter);
        content_viewpager.setCurrentItem(0, false);
        toolbar_title.setText(toolbarTitles.get(0));
        toolbar.setVisibility(View.GONE);
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(this);// 当然也可以使用匿名内部类实现
    }

    private void initData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int currentPageIndex = 0;
        toolbar.setVisibility(View.VISIBLE);
        switch (i) {
            case R.id.footer_rbtn_home:
                currentPageIndex = 0;
                toolbar.setVisibility(View.GONE);
                break;
            case R.id.footer_rbtn_category:
                currentPageIndex = 1;
                break;
            case R.id.footer_rbtn_discovery:
                currentPageIndex = 2;
                break;
            case R.id.footer_rbtn_my:
                currentPageIndex = 3;
                toolbar.setVisibility(View.GONE);
                break;
            default:
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                break;
        }
        toolbar_title.setText(toolbarTitles.get(currentPageIndex));
        content_viewpager.setCurrentItem(currentPageIndex, false);
    }


}
