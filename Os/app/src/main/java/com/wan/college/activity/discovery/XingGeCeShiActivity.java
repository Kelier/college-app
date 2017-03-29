package com.wan.college.activity.discovery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.adapter.ExpandableAdapter;
import com.wan.college.model.AnalysisResultData;
import com.wan.college.model.ChildArray;
import com.wan.college.model.Question;
import com.wan.college.model.QuestionData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;

import java.util.ArrayList;
import java.util.List;


public class XingGeCeShiActivity extends AppCompatActivity implements INetResult<String>{

    WebView analysis_result;
    ExpandableAdapter expandableAdapter;
    ProgressBar progressbar;
    TextView current_question_num;
    TextView total_question_num;
    TextView btn_back;
    ExpandableListView expand_test;
    Button tijiao;
    int is_answered_num=0;
    int http_request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);
        initView();
        HttpServer.getAnalysis(new CallbBackString(this),getIntent().getIntExtra("id",2),getIntent().getIntExtra("pid",0));
    }

    private void initView() {
        analysis_result=(WebView) findViewById(R.id.analysis_result);
        expand_test= (ExpandableListView) findViewById(R.id.question_list_view);
        progressbar= (ProgressBar) findViewById(R.id.question_progress);
        total_question_num= (TextView) findViewById(R.id.total_question_num);
        current_question_num= (TextView) findViewById(R.id.current_question_num);
        btn_back= (TextView) findViewById(R.id.back_btn);
        current_question_num.setText(String.valueOf(is_answered_num));
        progressbar.setProgress(is_answered_num);
        tijiao= (Button) findViewById(R.id.tijiao);
    }
    public void initListener(){
        expand_test.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for(int i=0,count=expandableAdapter.getGroupCount();i<count;i++){
                    if (groupPosition!=i) {
                        expand_test.collapseGroup(i);
                    }
                }
            }
        });
        expand_test.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int childposition=(int)expandableAdapter.getChildId(groupPosition,childPosition);
                expand_test.setItemChecked(childposition,true);
                if(!expandableAdapter.isGroupAnswered(groupPosition)){
                    expandableAdapter.setGroupIconAndChildAnswer(groupPosition,childPosition);
                  //  System.out.println("第" + groupPosition + "行" + "," + "第" + childPosition + "项");
                    is_answered_num++;
                    progressbar.setProgress(is_answered_num);
                    current_question_num.setText(String.valueOf(is_answered_num));
                }
                if (groupPosition+1>=expandableAdapter.getGroupCount()){
                    expand_test.collapseGroup(groupPosition);
                }else {
                    expand_test.collapseGroup(groupPosition);
                    expand_test.expandGroup(groupPosition+1);
                }

                return true;
            }
        });

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean flag=true;
                String answer_data="";
         //       ArrayList<Question> questions=expandableAdapter
                for(int i=0;i<expandableAdapter.getGroupCount();i++){
                    Question question=(Question)expandableAdapter.getGroup(i);
                    if(question.isAnswered()){
                        if(i+1==expandableAdapter.getGroupCount())answer_data+=(i+1)+":"+question.getIs_answer_id();
                        else answer_data+=(i+1)+":"+question.getIs_answer_id()+",";
                    }else{
                       flag=false;
                    }
                }
                if(flag){
                    //提交操作
                    http_request=1;
                    UserMsg.analysis_answer=answer_data;
                    HttpServer.uploadAnalysis(new CallbBackString(XingGeCeShiActivity.this));
                }else{
                    expandableAdapter.showAllVisible();
                    Toast.makeText(XingGeCeShiActivity.this,"有题目未答完!" ,Toast.LENGTH_LONG).show();
                }
            }
        });
        //返回键监听
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //     Toast.makeText(XingGeCeShiActivity.this,expandableAdapter.getIcon().size()+" ",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getNetData(String data) {
        if(http_request==0){
        QuestionData questionData = new Gson().fromJson(data, QuestionData.class);
        ArrayList<Question> questions=new ArrayList<>();
        for (int i = 0; i < questionData.getQuestions().size(); i++) {
            QuestionData.QuestionsBean questionBean= questionData.getQuestions().get(i);
            Question question=new Question();
            question.setQuestion_id(questionBean.getQuestion_id()+"、");
            question.setQuestion_content(questionBean.getQuestion());
            for(int j=0;j<questionBean.getAnswers().size();j++){
                question.answer_content.add(questionBean.getAnswers().get(j));
                question.answer_id.add(String.valueOf((char)('A'+j)));
            }
            questions.add(question);
        }
        expandableAdapter=new ExpandableAdapter(questions,XingGeCeShiActivity.this);
        //   expand_test.setGroupIndicator(null);//不设置箭头
        expand_test.setAdapter(expandableAdapter);
        progressbar.setMax(questionData.getTotal());
        total_question_num.setText(String.valueOf(questionData.getTotal()));
        initListener();
        expand_test.expandGroup(0);
        }else{
         AnalysisResultData analysisResultData=new Gson().fromJson(data,AnalysisResultData.class);
            analysis_result.setVisibility(View.VISIBLE);
            if(analysis_result!=null){
                analysis_result.loadUrl(analysisResultData.getResult_url());
             //   WebSettings settings = analysis_result.getSettings();
             //   settings.setTextSize(WebSettings.TextSize.LARGEST);
            }

        }
    }

}
