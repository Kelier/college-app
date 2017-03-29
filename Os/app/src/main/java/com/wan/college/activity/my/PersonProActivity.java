package com.wan.college.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.activity.discovery.MajorDetailActivity;
import com.wan.college.model.InterestedMajorData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.Const;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.OOSwipeRefreshLayout;
import com.wan.college.utils.NetUtils;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;


public class PersonProActivity extends AppCompatActivity implements INetResult<String> {
    private ListView listview_interested_major;
    private LinearLayout btn_back;
    private List<Major> data;
    private MyBaseAdapter adapter;
    private SwipeRefreshLayout refresh_layout;
    private int listViewState;
    OOSwipeRefreshLayout srl;
    protected int lastVisibleItem=-1;
    boolean isLastRow = false;
    int recyclerViewState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_pro);
        initView();
        initListener();
        initSrl();
        loadingRecyclerView(Const.RECYCLERVIEW_INIT);
    }

    private void initView() {
        btn_back = (LinearLayout) findViewById(R.id.back_btn);
        data=new ArrayList<>();
        adapter=new MyBaseAdapter(this,this.data);
        listview_interested_major = (ListView) findViewById(R.id.listview_interested_major);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
    }

    private void initListener() {
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listview_interested_major.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                //当滚到最后一行且停止滚动时，执行加载
                if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    loadingRecyclerView(Const.RECYCLERVIEW_DOWNLOAD);
                    isLastRow = false;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                  lastVisibleItem=totalItemCount-1;
                    isLastRow = true;
                }
            }
        });
    }

    private void initSrl() {
        srl = (OOSwipeRefreshLayout) findViewById(R.id.refresh_layout);
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
public void loadingRecyclerView(int recyclerViewState){
    this.recyclerViewState=recyclerViewState;
    srl.setRefreshing(true);//打开加载动画
    if (!NetUtils.isNetworkAvailable(this)) {
        CustomToast.showErrorToast(this,"网络不给力");
        srl.setRefreshing(false);//没有网络时候直接关闭加载动画
        return;
    }
    //当为初始化的时候
    if(recyclerViewState== Const.RECYCLERVIEW_INIT||recyclerViewState==Const.RECYCLERVIEW_REFRESH){
        data.clear();
        listview_interested_major.setAdapter(null);
        HttpServer.getInterestedMajorList(new CallbBackString(this));
    }else
    {
        Log.i("ss","上");
        //当为上滑加载的时候
        isLastRow=false;
        HttpServer.getInterestedMajorList(new CallbBackString(PersonProActivity.this),data.get(lastVisibleItem).getMajor_id());
    }
    srl.setRefreshing(false);//执行完成也要关闭加载动画
}
    public void getNetData(String data) {
        InterestedMajorData interestedmajordata = new Gson().fromJson(data, InterestedMajorData.class);
        if(recyclerViewState==Const.RECYCLERVIEW_INIT||recyclerViewState==Const.RECYCLERVIEW_REFRESH){
            this.data = new ArrayList<>();
            for (int i = 0; i < interestedmajordata.getMajors().size(); i++) {
                this.data.add(new Major(String.valueOf(interestedmajordata.getMajors().get(i).getMajor_id()), interestedmajordata.getMajors().get(i).getMajor_name()));
            }
            adapter = new MyBaseAdapter(this, this.data);
            listview_interested_major.setAdapter(adapter);
        }else {
            for (int i = 0; i < interestedmajordata.getMajors().size(); i++) {
                this.data.add(new Major(String.valueOf(interestedmajordata.getMajors().get(i).getMajor_id()), interestedmajordata.getMajors().get(i).getMajor_name()));
            }
            adapter.notifyDataSetChanged();
        }



    }
    /***
     * 下拉刷新处理
     */
    public void onRecyclerViewRefresh(){
        loadingRecyclerView(Const.RECYCLERVIEW_REFRESH);
    }

    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<Major> data;


        MyBaseAdapter(Context context, List<Major> data) {
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_major_list, null);
            TextView major_name = (TextView) view.findViewById(R.id.item_major);
            major_name.setText((data.get(position)).getMajor_name());
            RelativeLayout item_interested_major;
            item_interested_major = (RelativeLayout) view.findViewById(R.id.item_interested_major);
            item_interested_major.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserMsg.mid = data.get(position).getMajor_id();
                    startActivity(new Intent(PersonProActivity.this, MajorDetailActivity.class));
                }
            });
            return view;
        }
    }

    class Major {
        String major_id;
        String major_name;

        public Major(String major_id, String major_name) {
            this.major_name = major_name;
            this.major_id = major_id;
        }

        public String getMajor_id() {
            return major_id;
        }

        public void setMajor_id(String major_id) {
            this.major_id = major_id;
        }

        public String getMajor_name() {
            return major_name;
        }

        public void setMajor_name(String major_name) {
            this.major_name = major_name;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadingRecyclerView(Const.RECYCLERVIEW_REFRESH);
    }
}
