package com.wan.college.activity.discovery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paojiao.recyclerviewanimators.FadeInAnimator;
import com.paojiao.recyclerviewanimators.adapters.AlphaInAnimationAdapter;
import com.paojiao.recyclerviewanimators.adapters.ScaleInAnimationAdapter;
import com.wan.college.R;
import com.wan.college.adapter.CollegeListAdapter;
import com.wan.college.adapter.ProvinceAdapter;
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
import com.wan.college.utils.JsonUtils;
import com.wan.college.utils.NetUtils;
import com.wan.college.network.NetBroadcastReceiver.netEventHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/15.
 */

public class CollegeListActivity extends AppCompatActivity implements INetResult<String>,netEventHandler {
    public static void actionStart(Context context,String param1){
        Intent intent = new Intent(context,CollegeListActivity.class);
        intent.putExtra("param1",param1);
        context.startActivity(intent);
    }
    RecyclerView college_listview;
    LinearLayoutManager layoutManager;
    CollegeListAdapter adapter;
    ScaleInAnimationAdapter scaleAdapter;
    List<CollegeList> data= new ArrayList<>();
    ListView listview_college_province;
    ListView listView_college_type;
    RelativeLayout loading;
    OOSwipeRefreshLayout srl ;
    TextView toolbar_title;
    TextView college_type_view;
    int recyclerViewState;
    ImageView interest_view;
    TextView interest_text;
    TextView area_view;
    ProvinceAdapter provinceadapter;
    ProvinceAdapter type_adapter;
    String province;
    String type;
    Boolean arriveBottom=true;
    StringBuilder stringBuilder;
    List<String> province_data;
    List<String> type_data;
    LinearLayout btn_back;
    LinearLayout area_select;
    LinearLayout major_select;
    LinearLayout college_type_select;
    LinearLayout more_select;
    FrameLayout view_province_container;
    int interetsedPosition;
    /***
     *
     * 记录最后一项的位置
     */
    protected int lastVisibleItem=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);
        NetBroadcastReceiver.mListeners.add(this);
        initView();
        initSrl();
        addListener();
        onNetChange();
        initData();
    }

    private void initData() {
        province=UserMsg.province;
        type=UserMsg.college_type;
        province_data = new ArrayList<>();
        type_data=new ArrayList<>();
        stringBuilder = JsonUtils.getJson(this, "area.json");
        JsonObject returnData = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        JsonArray jsonArray = returnData.get("province").getAsJsonArray();
        province_data.add("不限");
        for (int i = 0; i < jsonArray.size(); i++) {
            province_data.add(jsonArray.get(i).getAsString());
        }
        provinceadapter = new ProvinceAdapter(this, province_data);
        addTypeData();
        type_adapter=new ProvinceAdapter(this,type_data);
        listView_college_type.setAdapter(type_adapter);
        listview_college_province.setAdapter(provinceadapter);
    }

    private void addTypeData() {
        type_data.add("不限");type_data.add("财经类");type_data.add("军事类");type_data.add("理工类");type_data.add("林业类");
        type_data.add("民族类");type_data.add("农林类");type_data.add("商学院");type_data.add("师范类");type_data.add("体育类");
        type_data.add("医药类");type_data.add("艺术类");type_data.add("语文类");type_data.add("语言类");type_data.add("政法类");
        type_data.add("综合类");
    }

    private void initView() {
        loading= (RelativeLayout) findViewById(R.id.loading);
        view_province_container= (FrameLayout) findViewById(R.id.view_province_container);
        listView_college_type= (ListView) findViewById(R.id.listView_college_type);
        college_listview = (RecyclerView) findViewById(R.id.college_listview);
        area_view= (TextView) findViewById(R.id.area_view);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        area_select= (LinearLayout) findViewById(R.id.area_select);
        major_select= (LinearLayout) findViewById(R.id.major_select);
        college_type_view= (TextView) findViewById(R.id.college_type_view);
        listview_college_province= (ListView) findViewById(R.id.listview_college_province);
        college_type_select= (LinearLayout) findViewById(R.id.college_type_select);
        more_select= (LinearLayout) findViewById(R.id.more_select);
        adapter = new CollegeListAdapter(this, data);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        college_listview.setLayoutManager(layoutManager);
        college_listview.setItemAnimator(new FadeInAnimator());
        college_listview.setHasFixedSize(true);
     //   college_listview.setAdapter(scaleAdapter);
        toolbar_title= (TextView) findViewById(R.id.toolbar_title);
        String str=getResources().getString(R.string.college_recommend);
        if(UserMsg.risk==1)toolbar_title.setText(str+"-"+getResources().getString(R.string.recomment_safe));
        if(UserMsg.risk==2)toolbar_title.setText(str+"-"+getResources().getString(R.string.recomment_suggest));
        if(UserMsg.risk==3)toolbar_title.setText(str+"-"+getResources().getString(R.string.recomment_sprint));
        if(UserMsg.risk==4)toolbar_title.setText("学校查询-"+UserMsg.college);
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
        if(recyclerViewState==100){
            FollowResultData followResultData=new Gson().fromJson(rdata,FollowResultData.class);
            if(followResultData.getError_info().equals("ok")){
                CustomToast.showDone(this,"关注");
                adapter.setFollowed(interetsedPosition,true);
                interest_view .setBackground(getResources().getDrawable(R.drawable.follow_btn_pressed));
                interest_text.setTextColor(getResources().getColor(R.color.orange_btn_normal_background_color));
                interest_text.setText("取消");
            }
        }else if(recyclerViewState==101){
            FollowResultData followResultData=new Gson().fromJson(rdata,FollowResultData.class);
            if(followResultData.getError_info().equals("ok")){
                CustomToast.showDone(this,"取消关注");
                adapter.setFollowed(interetsedPosition,false);
                interest_view .setBackground(getResources().getDrawable(R.drawable.follow_btn_normal));
                interest_text.setTextColor(getResources().getColor(R.color.page_title_bar_normal_text_color));
                interest_text.setText("关注");
            }
        }else {
        CollegeListData collegeListData = new Gson().fromJson(rdata, CollegeListData.class);
        for (int i = 0; i < collegeListData.getColleges().size(); i++) {
            CollegeListData.CollegesBean collegesBean = collegeListData.getColleges().get(i);
            CollegeListData.CollegesBean.CollegeIconBean collegeIconBean = collegesBean.getCollege_icon();
            if(collegeIconBean==null)data.add(new CollegeList("http://esfile.lexue.com/file/T1DaJTB_ET1RCvBVdK.jpg", collegesBean.getCollege_id(), collegesBean.getCollege_name(), collegesBean.getExpect_line(), collegesBean.getPici(), collegesBean.getPossibility(),collegesBean.isFollowed(), collegesBean.getTags()));
            else data.add(new CollegeList(collegeIconBean.getUrl(), collegesBean.getCollege_id(), collegesBean.getCollege_name(), collegesBean.getExpect_line(), collegesBean.getPici(), collegesBean.getPossibility(),collegesBean.isFollowed(), collegesBean.getTags()));
        }
        loading.setVisibility(View.GONE);
        //当为初始化的时候
        if(recyclerViewState== Const.RECYCLERVIEW_INIT||recyclerViewState==Const.RECYCLERVIEW_REFRESH){
            adapter = new CollegeListAdapter(this, data);
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
        loading.setVisibility(View.GONE);
    }

    private void addStringToList() {
        List<String> tags= new ArrayList<>();
        tags.add("11");
        for(int i = 0 ;i < 50 ;i++) {
            //  data.add("item : " + String.format("%02d" , count++));
            data.add(new CollegeList("sss", 1, "sss", 1, 1, 1, tags));
        }
        loading.setVisibility(View.GONE);
    }
    public void visibilityChange(){
        if (view_province_container.getVisibility() == View.GONE) {
            view_province_container.setVisibility(View.VISIBLE);
        } else {
            view_province_container.setVisibility(View.GONE);
        }
    }
    private void addListener() {
        //点击其他地方收起选择框
        view_province_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityChange();
            }
        });
        //地区选择
        area_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listview_college_province.getVisibility() == View.GONE) {
                    view_province_container.setVisibility(View.VISIBLE);
                    listview_college_province.setVisibility(View.VISIBLE);
                    listView_college_type.setVisibility(View.GONE);
                } else {
                    visibilityChange();
                }
            }
        });
        //专业选择
        major_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityChange();
            }
        });
        //类别选择
        college_type_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listView_college_type.getVisibility() == View.GONE) {
                    view_province_container.setVisibility(View.VISIBLE);
                    listView_college_type.setVisibility(View.VISIBLE);
                    listview_college_province.setVisibility(View.GONE);
                } else {
                    visibilityChange();
                }
            }
        });
        //筛选
        more_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityChange();
            }
        });
        //类别选择ListView的Item监听
        listView_college_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                visibilityChange();
                loading.setVisibility(View.VISIBLE);
                if(type_data.get(i).equals("不限")){
                    type=null;
                }else{
                    type=type_data.get(i);
                }
                type_adapter.setColorPosition(i);
                type_adapter.notifyDataSetChanged();
                college_type_view.setText(type_data.get(i));
                loadingRecyclerView(Const.RECYCLERVIEW_REFRESH);
            }
        });
        //地区选择ListView的Item监听
        listview_college_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                visibilityChange();
                loading.setVisibility(View.VISIBLE);
                if(province_data.get(i).equals("不限")){
                    province="北京市";
                }else {
                    province=province_data.get(i);
                }
                provinceadapter.setColorPosition(i);
                provinceadapter.notifyDataSetChanged();
                area_view.setText(province_data.get(i));
                loadingRecyclerView(Const.RECYCLERVIEW_REFRESH);
            }
        });
        //调用方法,传入一个接口回调
        adapter.setItemClickListener(new CollegeListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.setClass(CollegeListActivity.this,CollegeDetailActivity.class);
                intent.putExtra("college_detail",data.get(position));
                startActivity(intent);
                //intent.putExtra("url",data.get(position).getPaper_url().toString());
//                Toast.makeText(CollegeListActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFollowClick(View view, Boolean followed,int position,ImageView imageView, TextView textView) {
                interetsedPosition=position;
                if(followed){
                    recyclerViewState=101;
                    interest_view=imageView;
                    interest_text=textView;
                    HttpServer.removeInterestedCollege(new CallbBackString(CollegeListActivity.this));
                }else {
                    recyclerViewState=100;
                    interest_view=imageView;
                    interest_text=textView;
                    HttpServer.setInterestedCollege(new CallbBackString(CollegeListActivity.this));
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
            if(UserMsg.risk<1&&arriveBottom) loadingRecyclerView(Const.RECYCLERVIEW_DOWNLOAD); //下滑RecyclerView
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
        if(recyclerViewState== Const.RECYCLERVIEW_INIT||UserMsg.risk>0){
            data.clear();
            college_listview.setAdapter(null);
            HttpServer.getCollegelist(new CallbBackString(this));
        }else if(recyclerViewState==Const.RECYCLERVIEW_REFRESH){
            Log.i("ss","下");
            //当为下拉刷新的时候
            data.clear();
            college_listview.setAdapter(null);
            HttpServer.getCollegelist(new CallbBackString(this),province,type,null);

        }else{
            Log.i("ss","上");
            //当为上滑加载的时候
            arriveBottom=false;
            province= UserMsg.province;
            HttpServer.getCollegelist(new CallbBackString(this),province,type,String.valueOf(data.get(lastVisibleItem).getCollege_id()));


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
