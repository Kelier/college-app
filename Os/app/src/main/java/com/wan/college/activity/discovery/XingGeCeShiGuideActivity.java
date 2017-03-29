package com.wan.college.activity.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.adapter.MyExpandableAdapter;
import com.wan.progressbar.ProgressBar;

import java.util.ArrayList;

import static android.graphics.Color.rgb;

public class XingGeCeShiGuideActivity extends AppCompatActivity {
    TextView start_professional_test;
    TextView start_simple_test;
    LinearLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test_enter);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        start_professional_test= (TextView) findViewById(R.id.start_professional_test);
        start_simple_test= (TextView) findViewById(R.id.start_simple_test);
        start_professional_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(XingGeCeShiGuideActivity.this,XingGeCeShiActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("pid",0);
                startActivity(intent);
            }
        });
        start_simple_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(XingGeCeShiGuideActivity.this,XingGeCeShiActivity.class);
                intent.putExtra("id",2);
                intent.putExtra("pid",0);
                startActivity(intent);
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


}
