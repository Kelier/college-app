package com.wan.college.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.college.R;
import com.wan.college.Tools.SharedTool;
import com.wan.college.activity.discovery.PaiHangActivity;
import com.wan.college.activity.my.PersonInfoActivity;
import com.wan.college.login.LoginActivity;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.Const;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.CustomToast;
import com.wan.college.ui.PanelLnChart;
import com.wan.college.ui.ScoreDialog;
import com.wan.college.ui.popupwindow.PopupWheelSelector;

/**
 * Created by 万文杰 on 2017/2/21.
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener,INetResult<String>{
    RelativeLayout item_province;
    RelativeLayout score_container;
    LinearLayout science_container;
    LinearLayout art_container;
    TextView my_province;
    TextView my_score;
    ImageView science_select;
    ImageView art_select;
    ImageView guide_1_select_college;
    String temp_province=null;
    String temp_score=null;
    String temp_subject="2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(new PanelLnChart(this)); //折线图
        setContentView(R.layout.guide_1);
        initView();
    }
    private void initView(){
        item_province= (RelativeLayout) findViewById(R.id.item_province);
        score_container= (RelativeLayout) findViewById(R.id.score_container);
        science_container= (LinearLayout) findViewById(R.id.science_container);
        art_container= (LinearLayout) findViewById(R.id.art_container);
        science_select= (ImageView) findViewById(R.id.science_select);
        art_select= (ImageView) findViewById(R.id.art_select);
        guide_1_select_college= (ImageView) findViewById(R.id.guide_1_select_college);
        my_province= (TextView) findViewById(R.id.my_province);
        my_score= (TextView) findViewById(R.id.my_score);
        my_province.setText(UserMsg.province);
        item_province.setOnClickListener(this);
        science_container.setOnClickListener(this);
        score_container.setOnClickListener(this);
        art_container.setOnClickListener(this);
        guide_1_select_college.setOnClickListener(this);
        science_container.setSelected(true);
        science_select.setImageResource(R.drawable.guide_select);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_province:
                new PopupWheelSelector(this, Const.getProvinceData(this), UserMsg.province,new PopupWheelSelector.PopOnClickListener() {
                    @Override
                    public void getPosition(int position, String province) {
                        my_province.setText(province);
                        temp_province=province;
                    }
                }).showAtLocation(findViewById(R.id.guide),
                        Gravity.BOTTOM, 0, 0);
                break;
            case R.id.science_container:
                science_container.setSelected(true);
                art_container.setSelected(false);
                art_select.setImageResource(R.drawable.guide_select_unchooseen);
                science_select.setImageResource(R.drawable.guide_select);
                temp_subject="2";
                break;
            case R.id.art_container:
                science_container.setSelected(false);
                art_container.setSelected(true);
                art_select.setImageResource(R.drawable.guide_select);
                science_select.setImageResource(R.drawable.guide_select_unchooseen);
                temp_subject="1";
                break;
            case R.id.guide_1_select_college:
                if(temp_score==null){
                    CustomToast.showDone(GuideActivity.this,"请输入分数");
                }else if(temp_province==null){
                    CustomToast.showDone(GuideActivity.this,"请选择地区");
                }else{
                    UserMsg.province=temp_province;
                    UserMsg.subject=temp_subject;
                    UserMsg.score=temp_score;
                    SharedTool.recordData(GuideActivity.this,temp_score,temp_province,temp_subject);
                 /*   SharedTool.recordScore(GuideActivity.this,temp_score);
                    SharedTool.recordSubject(this,temp_subject);
                    SharedTool.recordProvince(GuideActivity.this,temp_province);*/
                    if(UserMsg.uid!=null){
                        HttpServer.updateUserInfo(new CallbBackString(GuideActivity.this),"province",temp_province);
                        HttpServer.updateUserInfo(new CallbBackString(GuideActivity.this),"score",temp_score);
                        HttpServer.updateUserInfo(new CallbBackString(GuideActivity.this),"subject",temp_subject);
                    }
                    Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                    intent.putExtra("requestCode",2);
                    this.sendBroadcast(intent);
                    startActivity(new Intent(GuideActivity.this,FooterActivity.class));
                    this.finish();
                }
                break;
            case R.id.score_container:
                new ScoreDialog(this,R.style.dialog,new ScoreDialog.OnDialogButtonClickListener(){
                    @Override
                    public void okButtonClick(int myScore) {
                        if(myScore>100&&myScore<=750){
                            my_score.setText(String.valueOf(myScore));
                            temp_score=String.valueOf(myScore);
                        }else{
                            CustomToast.showDone(GuideActivity.this,"请输入正确的分数");
                        }
                    }
                }).show();
                break;
        }
    }

    @Override
    public void getNetData(String data) {

    }
}
