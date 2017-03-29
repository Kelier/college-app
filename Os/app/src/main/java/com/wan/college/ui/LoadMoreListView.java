package com.wan.college.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.AbsListView;

import com.wan.college.R;

/**
 * Created by asus on 2017/2/22.
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private View footer;

    private int totalItem;
    private int lastItem;

    private boolean isLoading;

    private OnLoadMore onLoadMore;

    private LayoutInflater inflater;

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    @SuppressLint("InflateParams")
    private void init(Context context) {
        inflater=LayoutInflater.from(context);
//        footer=inflater.inflate(R.layout.load_more_footer,null,false);
        footer.setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }
//下拉显示下方布局
    @Override
    public void onScrollStateChanged(AbsListView view, int i) {
        if(this.totalItem==lastItem&&i==SCROLL_STATE_IDLE){
            Log.v("isLoading","yes");
            if(!isLoading){
                isLoading=true;
                footer.setVisibility(View.VISIBLE);
                onLoadMore.loadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int i, int i1, int i2) {
        this.lastItem=i+i1;
        this.totalItem=i2;
    }
    public void setLoadMoreListen(OnLoadMore onLoadMore){
        this.onLoadMore = onLoadMore;
    }
    public void onLoadComplete(){
        footer.setVisibility(View.GONE);
        isLoading = false;

    }
    public interface OnLoadMore{
        public void loadMore();
    }

}
