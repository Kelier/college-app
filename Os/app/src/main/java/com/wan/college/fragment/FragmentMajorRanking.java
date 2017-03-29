/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wan.college.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.activity.discovery.MajorDetailActivity;
import com.wan.college.model.MajorDetailData;
import com.wan.college.ui.MajorListView;
import com.wan.observablescrollview.ObservableScrollView;
import com.wan.observablescrollview.ScrollUtils;
import com.wan.observablescrollview.Scrollable;

import java.util.ArrayList;
import java.util.List;

public class FragmentMajorRanking extends FlexibleSpaceWithImageBaseFragment<ObservableScrollView> {
    MajorDetailData majorDetailData;
    MajorListView major_ranking_list_view;
    List<Ranking> data;

    class Ranking{
        String college_name;
        String rank;

        public Ranking(String college_name, String rank) {
            this.college_name = college_name;
            this.rank = rank;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_major_ranking, container, false);

        final ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scroll);
        // TouchInterceptionViewGroup should be a parent view other than ViewPager.
        // This is a workaround for the issue #117:
        // https://github.com/ksoichiro/Android-ObservableScrollView/issues/117
        scrollView.setTouchInterceptionViewGroup((ViewGroup) view.findViewById(R.id.fragment_root));

        // Scroll to the specified offset after layout
        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_SCROLL_Y)) {
            final int scrollY = args.getInt(ARG_SCROLL_Y, 0);
            ScrollUtils.addOnGlobalLayoutListener(scrollView, new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, scrollY);
                }
            });
            updateFlexibleSpace(scrollY, view);
        } else {
            updateFlexibleSpace(0, view);
        }
        scrollView.setScrollViewCallbacks(this);

        major_ranking_list_view= (MajorListView) view.findViewById(R.id.major_ranking_list_view);
        if(majorDetailData!=null){
        data = new ArrayList<>();
            if(majorDetailData.getRanking()==null){
                TextView  major_ranking_empty= (TextView) view.findViewById(R.id.major_ranking_empty);
                int newHeight = getScreenHeight() - getResources().getDimensionPixelSize(R.dimen.toolbar_height) - getResources().getDimensionPixelSize(R.dimen.tab_height);
                ViewGroup.LayoutParams layoutParams = major_ranking_empty.getLayoutParams();
                layoutParams.height = newHeight;
                major_ranking_empty.setLayoutParams(layoutParams);
                major_ranking_empty.setVisibility(View.VISIBLE);
            }else {

                for (int i = 0; i < majorDetailData.getRanking().size(); i++) {
                    data.add(new Ranking(majorDetailData.getRanking().get(i).getCollege_name(), majorDetailData.getRanking().get(i).getRank()));
                }
                MyBaseAdapter mybaseadapter = new MyBaseAdapter(getActivity(), data);
                major_ranking_list_view.setAdapter(mybaseadapter);
                final RelativeLayout major_ranking_container = (RelativeLayout) view.findViewById(R.id.major_ranking_container);
                int newHeight = getScreenHeight() - getResources().getDimensionPixelSize(R.dimen.toolbar_height) - getResources().getDimensionPixelSize(R.dimen.tab_height);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) major_ranking_container.getLayoutParams();
                layoutParams.height = newHeight;
                major_ranking_container.setLayoutParams(layoutParams);
                major_ranking_container.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                        int newHeight = getScreenHeight() - getResources().getDimensionPixelSize(R.dimen.toolbar_height) - getResources().getDimensionPixelSize(R.dimen.tab_height);
                        if (major_ranking_list_view.getHeight() < newHeight) {
                            ViewGroup.LayoutParams layoutParams = major_ranking_container.getLayoutParams();
                            layoutParams.height = newHeight;
                            major_ranking_container.setLayoutParams(layoutParams);
                        }
                    }
                });

            }
        }
        return view;
    }

    /**
     * 获取全局数据源
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        majorDetailData=((MajorDetailActivity)context).getMajorDetailData();

    }

    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<Ranking> data;

        public MyBaseAdapter(Context context, List<Ranking> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_major_ranking, null);

            ImageView major_rankingitem_position_icon = (ImageView) view.findViewById(R.id.major_rankingitem_position_icon);
            TextView major_rankingitem_position_text = (TextView) view.findViewById(R.id.major_rankingitem_position_text);
            TextView major_rankingitem_college_name = (TextView) view.findViewById(R.id.major_rankingitem_college_name);
            TextView major_rankingitem_college_ranking = (TextView) view.findViewById(R.id.major_rankingitem_college_ranking);
            major_rankingitem_position_text.setVisibility(View.GONE);
            if(position+1==1){
                major_rankingitem_position_icon.setImageResource(R.drawable.profession_schoolrankings_no1);
            }else if(position+1==2){
                major_rankingitem_position_icon.setImageResource(R.drawable.profession_schoolrankings_no2);
            }else if(position+1==3){
                major_rankingitem_position_icon.setImageResource(R.drawable.profession_schoolrankings_no3);
            }else {
                major_rankingitem_position_text.setText(String.valueOf(position+1));
                major_rankingitem_position_text.setVisibility(View.VISIBLE);
            }
            major_rankingitem_college_name.setText(data.get(position).getCollege_name());
            major_rankingitem_college_ranking.setText(data.get(position).getRank());


            return view;
        }
    }


    @Override
    public void updateFlexibleSpace(int scrollY) {
        // Sometimes scrollable.getCurrentScrollY() and the real scrollY has different values.
        // As a workaround, we should call scrollVerticallyTo() to make sure that they match.
        Scrollable s = getScrollable();
        s.scrollVerticallyTo(scrollY);

        // If scrollable.getCurrentScrollY() and the real scrollY has the same values,
        // calling scrollVerticallyTo() won't invoke scroll (or onScrollChanged()), so we call it here.
        // Calling this twice is not a problem as long as updateFlexibleSpace(int, View) has idempotence.
        updateFlexibleSpace(scrollY, getView());
    }

    @Override
    protected void updateFlexibleSpace(int scrollY, View view) {
        ObservableScrollView scrollView = (ObservableScrollView) view.findViewById(R.id.scroll);

        // Also pass this event to parent Activity
        MajorDetailActivity parentActivity =(MajorDetailActivity) getActivity();
        if (parentActivity != null) {
            parentActivity.onScrollChanged(scrollY, scrollView);
        }
    }
}
