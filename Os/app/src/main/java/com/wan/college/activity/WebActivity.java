package com.wan.college.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.college.R;

public class WebActivity extends AppCompatActivity {
    TextView toolbar_title;
    LinearLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
     //   Toast.makeText(WebActivity.this,url,Toast.LENGTH_SHORT).show();
        WebView wb;
        wb= (WebView) findViewById(R.id.web_content);
        toolbar_title= (TextView) findViewById(R.id.toolbar_title);
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(wb!=null){
            wb.loadUrl(url);
            toolbar_title.setText(title);
            WebSettings settings = wb.getSettings();
          //  settings.setTextSize(WebSettings.TextSize.LARGEST);
        }
    }
}
