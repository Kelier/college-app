package com.wan.college.activity.discovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.activity.WebActivity;
import com.wan.college.adapter.RankAdapter;
import com.wan.college.model.Rank;
import com.wan.college.model.RankData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;


import java.util.ArrayList;

public class PaiHangActivity extends AppCompatActivity implements INetResult<String> {
    ArrayList<Rank> data;
    RankAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rv;
    RelativeLayout loading;
    LinearLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_hang);
        HttpServer.getRank(new CallbBackString(this));
        btn_back=(LinearLayout)findViewById(R.id.back_btn);

    }
    private void setupView() {
        loading= (RelativeLayout) findViewById(R.id.loading);
        // 数据源
        /*
        data.add(new Rank("http://paper.lexue.com/zhiyuan/rank/salary_major.html","高薪专业排行榜","http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg"));
        data.add(new Rank("http://paper.lexue.com/zhiyuan/rank/salary_major.html","高薪专业排行榜","http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg"));
        data.add(new Rank("http://paper.lexue.com/zhiyuan/rank/salary_major.html","高薪专业排行榜","http://esfile.lexue.com/file/T11aJTBCKT1RCvBVdK.jpg"));
*/
        // 适配器
        adapter = new RankAdapter(this, data) ;
        layoutManager = new LinearLayoutManager(this) ;

        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv = (RecyclerView) findViewById(R.id.recycler_paihang);
        // 控件调用两个set方法
        rv.setAdapter(adapter);
        rv.setLayoutManager(layoutManager);
    }
    private void addListener() {
        //调用方法,传入一个接口回调
        adapter.setItemClickListener(new RankAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.setClass(PaiHangActivity.this,WebActivity.class);
         //       Toast.makeText(PaiHangActivity.this,data.get(position).getPaper_url().toString(),Toast.LENGTH_SHORT).show();
                intent.putExtra("url",data.get(position).getPaper_url().toString());
                intent.putExtra("title",data.get(position).getPaper_title().toString());
                startActivity(intent);
                //    Toast.makeText(PaiHangActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
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

    @Override
    public void getNetData(String rdata) {
        data = new ArrayList<>() ;

        RankData rankData=new Gson().fromJson(rdata,RankData.class);
/*
        for (RankData.PapersBean papersBean:rankData.getPapers()) {

        }
        */
        for(int i=0;i<rankData.getPapers().size();i++){
            RankData.PapersBean papersBean=rankData.getPapers().get(i);
            RankData.PapersBean.CoverBean coverBean=papersBean.getCover();
            data.add(new Rank(papersBean.getPaper_url(),papersBean.getPaper_title(),coverBean.getUrl()));
        }

        setupView();
        addListener();
        loading.setVisibility(View.GONE);
    }
}
