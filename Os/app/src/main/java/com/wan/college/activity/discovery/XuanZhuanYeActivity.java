package com.wan.college.activity.discovery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.model.XuanZhuanYeData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;

public class XuanZhuanYeActivity extends AppCompatActivity implements INetResult<String>{

    TextView toolbar_title;
    XuanZhuanYeData xuanZhuanYeData;
    LinearLayout btn_back;
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wb= (WebView) findViewById(R.id.web_content);
        toolbar_title= (TextView) findViewById(R.id.toolbar_title);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        HttpServer.getCareer(new CallbBackString(this));
        initListener();
    }

    private void initListener() {
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getNetData(String data) {
        xuanZhuanYeData=new Gson().fromJson(data,XuanZhuanYeData.class);
        if(wb!=null){
            wb.loadUrl(xuanZhuanYeData.getPapers().get(0).getPaper_url());
            toolbar_title.setText(xuanZhuanYeData.getPapers().get(0).getPaper_title());
            WebSettings settings = wb.getSettings();
            settings.setTextSize(WebSettings.TextSize.LARGEST);
        }
    }


}
