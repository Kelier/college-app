package com.wan.college.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.wan.college.R;
import com.wan.college.activity.discovery.CollegeListActivity;
import com.wan.college.activity.discovery.XingGeCeShiGuideActivity;
import com.wan.college.activity.discovery.ZiXunActivity;
import com.wan.college.model.CirclePieValue;
import com.wan.college.model.SuggestData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.AnimationCirclePie;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.NumPicTextView;
import com.wan.college.ui.ScoreDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/8.
 */

public class FragmentHome extends Fragment implements INetResult<String>,View.OnClickListener {

    /*========= 数据相关 =========*/
    AnimationCirclePie college_num_pie;                    //饼状图控件
    List<CirclePieValue> circlePieValues;                 //饼状图数据

    /*========= 控件相关 =========*/
    ShimmerTextView myShimmerTextView;
    RelativeLayout lin_filter;
    RelativeLayout score_container;
    TextView my_score;
    TextView my_province;
    TextView my_subject;
    TextView dis_title_text_content;
    TextView dis_title_text_content2;
    TextView dis_title_text_content3;
    CardView item_kechongci;
    CardView item_wentuo;
    CardView item_baodi;
    ImageView imageview_left;
    ImageView imageview_right;
    NumPicTextView high_risk_college_count;
    NumPicTextView mid_risk_college_count;
    NumPicTextView low_risk_college_count;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root ;
        root = inflater.inflate(R.layout.content_footer_fragment_home, container, false);
        initView(root);
        initListener();
        initReceiver();
        sendHttp();
        return root ;
    }
//接收我的信息分数设置界面发来的广播
    private void initReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST1");//建议把它写一个公共的变量，这里方便阅读就不写了。
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                my_score.setText(intent.getExtras().getString("score"));
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    public void sendHttp(){
        HttpServer.getHome(new CallbBackString(this));
    }
    public void initView(View view) {
        college_num_pie = (AnimationCirclePie) view.findViewById(R.id.college_num_pie);
        my_score = (TextView) view.findViewById(R.id.my_score);
        my_subject = (TextView) view.findViewById(R.id.my_subject);
        my_province = (TextView) view.findViewById(R.id.my_province);
        score_container= (RelativeLayout) view.findViewById(R.id.score_container);
        dis_title_text_content=(TextView) view.findViewById(R.id.dis_title_text_content);
        dis_title_text_content2=(TextView) view.findViewById(R.id.dis_title_text_content2);
        dis_title_text_content3=(TextView) view.findViewById(R.id.dis_title_text_content3);
        high_risk_college_count=(NumPicTextView)view.findViewById(R.id.high_risk_college_count);
        mid_risk_college_count=(NumPicTextView)view.findViewById(R.id.mid_risk_college_count);
        low_risk_college_count=(NumPicTextView)view.findViewById(R.id.low_risk_college_count);
        item_kechongci=(CardView) view.findViewById(R.id.item_kechongci);
        item_wentuo=(CardView) view.findViewById(R.id.item_wentuo);
        item_baodi=(CardView) view.findViewById(R.id.item_baodi);
        imageview_left=(ImageView) view.findViewById(R.id.imageview_left);
        imageview_right=(ImageView) view.findViewById(R.id.imageview_right);
        myShimmerTextView= (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
        Shimmer shimmer = new Shimmer();
        shimmer.setDuration(2000).setStartDelay(100);
        shimmer.start(myShimmerTextView);
        setData();
    }

    private void setData() {
        my_score.setText(UserMsg.score);
        my_province.setText(UserMsg.province.replace("省","").replace("市","").replace("自治区","").replace("维吾尔","").replace("壮族","").replace("回族",""));
        if(UserMsg.subject.equals("2")) my_subject.setText("理科");
        else my_subject.setText("文科");
    }

    public void initListener(){
        score_container.setOnClickListener(this);
        item_kechongci.setOnClickListener(this);
        item_wentuo.setOnClickListener(this);
        item_baodi.setOnClickListener(this);
        imageview_left.setOnClickListener(this);
        imageview_right.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.score_container:
                new ScoreDialog(getActivity(),R.style.dialog,new ScoreDialog.OnDialogButtonClickListener(){
                    @Override
                    public void okButtonClick(int myScore) {
                        if(myScore>100&&myScore<=750){
                            my_score.setText(String.valueOf(myScore));
                            UserMsg.score=String.valueOf(myScore);
                        }else{
                            CustomToast.showDone(getActivity(),"请输入正确的分数");
                        }
                        //发送广播到我的信息界面
                        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                        intent.putExtra("requestCode",1);
                        getActivity().sendBroadcast(intent);
                        //Toast.makeText(getActivity(), "发送广播成功", Toast.LENGTH_SHORT).show();
                        sendHttp();
                    }
                }).show();
                break;
            case R.id.item_kechongci:
                UserMsg.risk = 3;
                startActivity(new Intent().setClass(getActivity(), CollegeListActivity.class));
                break;
            case R.id.item_wentuo:
                UserMsg.risk = 2;
                startActivity(new Intent().setClass(getActivity(), CollegeListActivity.class));
                break;
            case R.id.item_baodi:
                UserMsg.risk = 1;
                startActivity(new Intent().setClass(getActivity(), CollegeListActivity.class));
                break;
            case R.id.imageview_left:
                startActivity(new Intent().setClass(getActivity(), ZiXunActivity.class));
                break;
            case R.id.imageview_right:
                startActivity(new Intent().setClass(getActivity(), XingGeCeShiGuideActivity.class));
                break;
        }
    }

    @Override
    public void getNetData(String data) {
        SuggestData suggestData=new Gson().fromJson(data,SuggestData.class);
        dis_title_text_content.setText(String.format(getResources().getString(R.string.admission_probility_des),suggestData.getHigh_risk_possibility()));
        dis_title_text_content2.setText(String.format(getResources().getString(R.string.admission_probility_des),suggestData.getMid_risk_possibility()));
        dis_title_text_content3.setText(String.format(getResources().getString(R.string.admission_probility_des),suggestData.getLow_risk_possibility()));
        high_risk_college_count.setNumber(suggestData.getHigh_risk_count());
        mid_risk_college_count.setNumber(suggestData.getMid_risk_count());
        low_risk_college_count.setNumber(suggestData.getLow_risk_count());
        circlePieValues= new ArrayList<>();
        circlePieValues.add(new CirclePieValue(getResources().getString(R.string.recomment_suggest),suggestData.getMid_risk_count(),getResources().getColor(R.color.risk_mid_bg)));
        circlePieValues.add(new CirclePieValue(getResources().getString(R.string.recomment_sprint),suggestData.getHigh_risk_count(),getResources().getColor(R.color.risk_high_bg)));
        circlePieValues.add(new CirclePieValue(getResources().getString(R.string.recomment_safe),suggestData.getLow_risk_count(),getResources().getColor(R.color.risk_low_bg)));
        college_num_pie.setPieData(circlePieValues);

        setData();
    }

}
/*
                lin_filter= (RelativeLayout) dialog.findViewById(R.id.view_dialog_score_container);
                TranslateAnimation showAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
                showAnimation.setDuration(200);
                lin_filter.setAnimation(showAnimation);
                lin_filter.setVisibility(View.VISIBLE);
            TranslateAnimation hiddenAnimation  = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f);
                hiddenAnimation .setDuration(200);
                lin_filter.setAnimation(hiddenAnimation );
                lin_filter.setVisibility(View.GONE);*/