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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.wan.college.activity.discovery.CollegeDetailActivity;
import com.wan.college.activity.discovery.MajorDetailActivity;
import com.wan.college.model.MajorDetailData;
import com.wan.observablescrollview.ObservableScrollView;
import com.wan.observablescrollview.ScrollUtils;
import com.wan.observablescrollview.Scrollable;
import com.wan.college.R;

public class FragmentMajorDescription extends FlexibleSpaceWithImageBaseFragment<ObservableScrollView> {
    MajorDetailData majorDetailData;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_major_description, container, false);
        this.view=view;
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



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(majorDetailData!=null){
            final TextView tv_major_description= (TextView) view.findViewById(R.id.tv_major_description);
            tv_major_description.setText(majorDetailData.getMajor_description());
            tv_major_description.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                    int newHeight=getScreenHeight()-getResources().getDimensionPixelSize(R.dimen.toolbar_height)-getResources().getDimensionPixelSize(R.dimen.tab_height);
                    LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)tv_major_description.getLayoutParams();

               //     Toast.makeText(getActivity(),"读取"+layoutParams.height+"设置"+newHeight,Toast.LENGTH_SHORT).show();
                    if(tv_major_description.getHeight()<newHeight){
                        tv_major_description.setHeight(newHeight);
                    }
                    layoutParams.height=newHeight;
                    tv_major_description.setLayoutParams(layoutParams);
                }
            });

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        majorDetailData=((MajorDetailActivity)context).getMajorDetailData();
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
