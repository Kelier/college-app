package com.wan.college.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by OO on 2017/1/4.
 */

public class CollegeViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data ;   // 数据源
    private List<String> titles ;   // 标题栏文字  ==> TabLayout的tab中数据集合

    public CollegeViewPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> data) {
        super(fm);
        this.data = data;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    // 该方法返回的页标题将成为TabLayout中Tab的内容
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
