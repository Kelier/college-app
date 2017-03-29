package com.wan.college.activity.discovery;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.wan.college.R;
import com.wan.college.fragment.FlexibleSpaceWithImageBaseFragment;
import com.wan.college.fragment.FragmentMajorCourse;
import com.wan.college.fragment.FragmentMajorDescription;
import com.wan.college.fragment.FragmentMajorRanking;
import com.wan.college.model.FollowResultData;
import com.wan.college.model.MajorDetailData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.ui.BaseActivity;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.SlidingTabLayout;
import com.wan.observablescrollview.CacheFragmentStatePagerAdapter;
import com.wan.observablescrollview.ScrollUtils;
import com.wan.observablescrollview.Scrollable;


public class MajorDetailActivity extends BaseActivity implements INetResult<String> {

    protected static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private ViewPager mPager;
    private NavigationAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private int mFlexibleSpaceHeight;
    private int mTabHeight;
    TextView major_name;
    TextView major_id;
    TextView major_years;
    TextView college_detail_follow_txt;
    RelativeLayout major_follow;
    MajorDetailData majorDetailData;
    LinearLayout btn_back;
    int httpStatus;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_detail);
        initView();
        initListener();
        HttpServer.getMajorDetail(new CallbBackString(this));
    }

    private void initView() {
        major_name = (TextView) findViewById(R.id.major_name);
        major_id = (TextView) findViewById(R.id.major_id);
        major_years = (TextView) findViewById(R.id.major_years);
        college_detail_follow_txt= (TextView) findViewById(R.id.college_detail_follow_txt);
        mPager = (ViewPager) findViewById(R.id.pager);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        major_follow= (RelativeLayout) findViewById(R.id.major_detail_follow);
        mFlexibleSpaceHeight = getResources().getDimensionPixelSize(R.dimen.major_detail_header_container_height);
        mTabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.color_theme));
        mSlidingTabLayout.setDistributeEvenly(true);

    }
    private void initListener() {
        //关注专业
        major_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(majorDetailData.isFollowed()){
                httpStatus=2;
                HttpServer.removeInterestedMajor(new CallbBackString(MajorDetailActivity.this));
                //college_detail_follow_txt.setText("关注");
                }
                else{
                    httpStatus=1;
                    HttpServer.setInterestedMajor(new CallbBackString(MajorDetailActivity.this));
                    //college_detail_follow_txt.setText("取消");
                }
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

    private void initData() {
        major_name.setText(majorDetailData.getMajor_name());
        major_id.setText(String.format(getResources().getString(R.string.major_code_format), majorDetailData.getMajor_code()));
        major_years.setText(String.format(getResources().getString(R.string.major_years_format), majorDetailData.getYears()));
        mPagerAdapter = new NavigationAdapter(getSupportFragmentManager(), this);
        mPager.setAdapter(mPagerAdapter);
        mSlidingTabLayout.setViewPager(mPager);
        // Initialize the first Fragment's state when layout is completed.
        ScrollUtils.addOnGlobalLayoutListener(mSlidingTabLayout, new Runnable() {
            @Override
            public void run() {
                translateTab(0, false);
            }
        });
        if(majorDetailData.isFollowed()){
            college_detail_follow_txt.setSelectAllOnFocus(true);
            Drawable drawable=getResources().getDrawable(R.drawable.follow_btn_pressed);
            college_detail_follow_txt.setText("取消");
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            college_detail_follow_txt.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
        }
    }

    @Override
    public void getNetData(String data) {
        if (httpStatus==1){
            FollowResultData followResultData=new Gson().fromJson(data,FollowResultData.class);
            if(followResultData.getError_info().equals("ok")){
                majorDetailData.setFollowed(true);
                college_detail_follow_txt.setSelectAllOnFocus(true);
                Drawable drawable=getResources().getDrawable(R.drawable.follow_btn_pressed);
                college_detail_follow_txt.setText("取消");
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                college_detail_follow_txt.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            }
        }else if(httpStatus==2){
            FollowResultData followResultData=new Gson().fromJson(data,FollowResultData.class);
            if(followResultData.getError_info().equals("ok")){
                majorDetailData.setFollowed(false);
                college_detail_follow_txt.setSelectAllOnFocus(false);
                Drawable drawable=getResources().getDrawable(R.drawable.follow_btn_normal);
                college_detail_follow_txt.setText("关注");
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                college_detail_follow_txt.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            }
        }
        else{
        majorDetailData = new Gson().fromJson(data, MajorDetailData.class);

            initData();
        }
    }

    public MajorDetailData getMajorDetailData() {
        return majorDetailData;
    }

    /**
     * Called by children Fragments when their scrollY are changed.
     * They all call this method even when they are inactive
     * but this Activity should listen only the active child,
     * so each Fragments will pass themselves for Activity to check if they are active.
     *
     * @param scrollY scroll position of Scrollable
     * @param s       caller Scrollable view
     */
    public void onScrollChanged(int scrollY, Scrollable s) {
        FlexibleSpaceWithImageBaseFragment fragment =
                (FlexibleSpaceWithImageBaseFragment) mPagerAdapter.getItemAt(mPager.getCurrentItem());
        if (fragment == null) {
            return;
        }
        View view = fragment.getView();
        if (view == null) {
            return;
        }
        Scrollable scrollable = (Scrollable) view.findViewById(R.id.scroll);
        if (scrollable == null) {
            return;
        }
        if (scrollable == s) {
            // This method is called by not only the current fragment but also other fragments
            // when their scrollY is changed.
            // So we need to check the caller(S) is the current fragment.
            int adjustedScrollY = Math.min(scrollY, mFlexibleSpaceHeight - mTabHeight);
            translateTab(adjustedScrollY, false);
            propagateScroll(adjustedScrollY);
        }
    }

    private void translateTab(int scrollY, boolean animated) {
        int flexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.major_detail_header_container_height);
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        View imageView = findViewById(R.id.major_header_view_container);//R.id.image
        View overlayView = findViewById(R.id.overlay);

        // Translate overlay and image
        float flexibleRange = flexibleSpaceImageHeight - getActionBarSize();
        // float flexibleRange = flexibleSpaceImageHeight - tabHeight;
        int minOverlayTransitionY = tabHeight - overlayView.getHeight();
        ViewHelper.setTranslationY(overlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(imageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        ViewHelper.setAlpha(overlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));


        // If tabs are moving, cancel it to start a new animation.
        ViewPropertyAnimator.animate(mSlidingTabLayout).cancel();
        // Tabs will move between the top of the screen to the bottom of the image.
        float translationY = ScrollUtils.getFloat(-scrollY + mFlexibleSpaceHeight - mTabHeight, 0, mFlexibleSpaceHeight - mTabHeight);
        if (animated) {
            // Animation will be invoked only when the current tab is changed.
            ViewPropertyAnimator.animate(mSlidingTabLayout)
                    .translationY(translationY)
                    .setDuration(200)
                    .start();
        } else {
            // When Fragments' scroll, translate tabs immediately (without animation).
            ViewHelper.setTranslationY(mSlidingTabLayout, translationY);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setPivotXToTitle(View view) {
        final TextView mTitleView = (TextView) view.findViewById(R.id.title);
        Configuration config = getResources().getConfiguration();
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            ViewHelper.setPivotX(mTitleView, view.findViewById(android.R.id.content).getWidth());
        } else {
            ViewHelper.setPivotX(mTitleView, 0);
        }
    }

    private void propagateScroll(int scrollY) {
        // Set scrollY for the fragments that are not created yet
        mPagerAdapter.setScrollY(scrollY);
        // Set scrollY for the active fragments
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            // Skip current item
            if (i == mPager.getCurrentItem()) {
                continue;
            }
            // Skip destroyed or not created item
            FlexibleSpaceWithImageBaseFragment f =
                    (FlexibleSpaceWithImageBaseFragment) mPagerAdapter.getItemAt(i);
            if (f == null) {
                continue;
            }

            View view = f.getView();
            if (view == null) {
                continue;
            }
            f.setScrollY(scrollY, mFlexibleSpaceHeight);
            f.updateFlexibleSpace(scrollY);
        }
    }


    /**
     * This adapter provides three types of fragments as an example.
     * {@linkplain #createItem(int)} should be modified if you use this example for your app.
     */
    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private static String[] TITLES;
        private int mScrollY;

        public NavigationAdapter(FragmentManager fm, Context context) {
            super(fm);
            TITLES = new String[]{context.getResources().getString(R.string.major_tab_info), context.getResources().getString(R.string.major_tab_course), context.getResources().getString(R.string.major_tab_ranking)};
        }

        public void setScrollY(int scrollY) {
            mScrollY = scrollY;
        }

        @Override
        protected Fragment createItem(int position) {
            FlexibleSpaceWithImageBaseFragment f;
            final int pattern = position % 4;
            switch (pattern) {
                case 0: {
                    f = new FragmentMajorDescription();
                    break;
                }
                case 1: {
                    f = new FragmentMajorCourse();
                    break;
                }
                case 2: {
                    f = new FragmentMajorRanking();
                    break;
                }
                default: {
                    f = new FragmentMajorDescription();
                    break;
                }
            }
            f.setArguments(mScrollY);
            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
}
