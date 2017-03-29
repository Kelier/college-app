package com.wan.college.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paojiao.recyclerviewanimators.FadeInAnimator;
import com.paojiao.recyclerviewanimators.adapters.AlphaInAnimationAdapter;
import com.paojiao.recyclerviewanimators.adapters.ScaleInAnimationAdapter;
import com.wan.college.R;
import com.wan.college.activity.discovery.CollegeDetailActivity;
import com.wan.college.activity.discovery.CollegeListActivity;
import com.wan.college.adapter.CollegeListAdapter;
import com.wan.college.adapter.InterestListAdapter;
import com.wan.college.model.CollegeList;
import com.wan.college.model.CollegeListData;
import com.wan.college.model.FollowResultData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.Const;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.NetBroadcastReceiver;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.OOSwipeRefreshLayout;
import com.wan.college.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/2/20.
 */

public class InterestedCollegeActivity extends AppCompatActivity implements INetResult<String>,NetBroadcastReceiver.netEventHandler {
    RecyclerView college_listview;
    LinearLayoutManager layoutManager;
    LinearLayout btn_back;
    InterestListAdapter adapter;
    ScaleInAnimationAdapter scaleAdapter;
    List<CollegeList> data= new ArrayList<>();
    RelativeLayout loading;
    OOSwipeRefreshLayout srl ;
    TextView toolbar_title;
    int recyclerViewState;
    Boolean arriveBottom=true;
    /***
     * 记录最后一项的位置
     */
    protected int lastVisibleItem=-1;
    int removeItemPositon=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_interested);
        NetBroadcastReceiver.mListeners.add(this);
        initView();
        initSrl();
        addListener();
        onNetChange();
    }

    private void initView() {
        loading= (RelativeLayout) findViewById(R.id.loading);
        college_listview = (RecyclerView) findViewById(R.id.college_listview);
        adapter = new InterestListAdapter(this, data);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        college_listview.setLayoutManager(layoutManager);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        //   college_listview.setItemAnimator(new FadeInAnimator());
        college_listview.setHasFixedSize(true);
        toolbar_title= (TextView) findViewById(R.id.toolbar_title);
    }
    private void initSrl() {
        srl = (OOSwipeRefreshLayout) findViewById(R.id.main_swiperefresh);
        //设置刷新时动画的颜色，可以设置4个
        srl.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        // 设置ProgressBar大小
        srl.setSize(SwipeRefreshLayout.DEFAULT);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRecyclerViewRefresh();
            }
        });
    }
    //// TODO: 2017/2/4 http请求的数据处理
    @Override
    public void getNetData(String rdata) {
        if(recyclerViewState==101){
            Log.i("数据请求","删除一行");
            FollowResultData followResultData=new Gson().fromJson(rdata,FollowResultData.class);
            if(followResultData.getError_info().equals("ok")){
                Log.i("数据请求","删除成功");
                adapter.remove(removeItemPositon);
                scaleAdapter.notifyItemRemoved(removeItemPositon);
            }
        }else {
            CollegeListData collegeListData = new Gson().fromJson(rdata, CollegeListData.class);
            for (int i = 0; i < collegeListData.getColleges().size(); i++) {
                CollegeListData.CollegesBean collegesBean = collegeListData.getColleges().get(i);
                CollegeListData.CollegesBean.CollegeIconBean collegeIconBean = collegesBean.getCollege_icon();
                data.add(new CollegeList(collegeIconBean.getUrl(), collegesBean.getCollege_id(), collegesBean.getCollege_name(), collegesBean.getExpect_line(), collegesBean.getPici(), collegesBean.getPossibility(),collegesBean.isFollowed(), collegesBean.getTags()));
            }
            loading.setVisibility(View.GONE);
            //当为初始化的时候
            if(recyclerViewState== Const.RECYCLERVIEW_INIT||recyclerViewState==Const.RECYCLERVIEW_REFRESH){
                adapter = new InterestListAdapter(this, data);
                scaleAdapter = new ScaleInAnimationAdapter(adapter);
                college_listview.setAdapter(scaleAdapter);
            }else{
                //当为上滑加载的时候
                scaleAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        }
        addListener();
        arriveBottom=true;
    }
    private void addListener() {
        //调用方法,传入一个接口回调
        adapter.setItemClickListener(new InterestListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.setClass(InterestedCollegeActivity.this,CollegeDetailActivity.class);
                intent.putExtra("college_detail",data.get(position));
                startActivity(intent);
            }

            @Override
            public void onRemoveItemClick(View view, int position) {
                Log.i("postion:",String.valueOf(position));
                recyclerViewState=101;
                removeItemPositon=position;
                HttpServer.removeInterestedCollege(new CallbBackString(InterestedCollegeActivity.this));
            }
        });
        college_listview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                onRecyclerViewStateChanged(newState);
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
    /***
     * 监听RecyclerView滑动事件
     * @param newState 滑动状态
     */
    public void onRecyclerViewStateChanged(int newState){
        if (data == null || data.size() <= 0) {
            Toast.makeText(this , "没有数据得先下拉刷新" , Toast.LENGTH_LONG).show();
            return;
        }
        //滚动事件结束并且将到达最底端
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
            if (arriveBottom)
             loadingRecyclerView(Const.RECYCLERVIEW_DOWNLOAD);
        }
    }

    /***
     * 下拉刷新处理
     */
    public void onRecyclerViewRefresh(){
        loadingRecyclerView(Const.RECYCLERVIEW_REFRESH);
    }
    /**
     * 加载RecyclerView的数据
     * @param recyclerViewState
     */
    public void loadingRecyclerView(int recyclerViewState){
        this.recyclerViewState=recyclerViewState;
        srl.setRefreshing(true);//打开加载动画
        if (!NetUtils.isNetworkAvailable(this)) {
            CustomToast.showErrorToast(this,"网络不给力");
            srl.setRefreshing(false);//没有网络时候直接关闭加载动画
            return;
        }
        //当为初始化的时候
        if(recyclerViewState== Const.RECYCLERVIEW_INIT){
            HttpServer.getInterestedCollegeList(new CallbBackString(this));
        }else if(recyclerViewState==Const.RECYCLERVIEW_REFRESH){
            //当为下拉刷新的时候
            data.clear();
            college_listview.setAdapter(null);
            Log.i("ss","下");
            HttpServer.getInterestedCollegeList(new CallbBackString(this));
        }else{
            Log.i("ss","上");
            //当为上滑加载的时候
            arriveBottom=false;
            HttpServer.getInterestedCollegeList(new CallbBackString(this),String.valueOf(data.get(lastVisibleItem).getCollege_id()));

            //    if(data.size()< 50){
            //   addStringToList();
            //   }
        }
        srl.setRefreshing(false);//执行完成也要关闭加载动画
    }
    @Override
    public void onNetChange() {
        if (!NetUtils.isNetworkAvailable(this)) {
            Log.i("network","网络状态监听:网络断开了");
            CustomToast.showErrorToast(this,"网络不给力");
        }else {
            Log.i("network","网络状态监听:网络连接上了");
            loadingRecyclerView(Const.RECYCLERVIEW_INIT);//初始化RecyclerView
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetBroadcastReceiver.mListeners.remove(this);
    }
}
