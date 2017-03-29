package com.wan.college.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.activity.discovery.CollegeDetailActivity;
import com.wan.college.model.CollegeAdimitData;
import com.wan.college.model.CollegeDetailData;
import com.wan.college.model.LineValue;
import com.wan.college.model.MajorLines;
import com.wan.college.model.User;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;
import com.wan.college.ui.MajorListView;
import com.wan.college.ui.PanelLnChart;
import com.wan.college.ui.ScoreStripView;
import com.wan.progressbar.CircleProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by 万文杰 on 2017/1/10.
 */

public class FragmentLuqu extends Fragment implements INetResult<String>{
    /*=========== 控件相关 ==========*/
    private PanelLnChart mLineChartView;               //线性图表控件
    /*=========== 数据相关 ==========*/
    private List<LineValue> lineValue;                    //图表数据


    /*=========== 其他相关 ==========*/
    ScoreStripView expect_score_stripview;
    ScoreStripView my_score_stripview;
    MajorListView listView;
    List<MajorLines> data;
    MyBaseAdapter mybaseadapter;
    CollegeAdimitData collegeAdimitData;
    /*==============进度条以及提示==============*/
    CircleProgressBar circleProgressBar;
    RelativeLayout loading;
    TextView left_admit_probility;
    TextView left_admit_probility_tip;
    TextView left_expect_line_score;
    TextView left_expect_line_tip;
    TextView left_history_line_tip;
    TextView major_line1_tip;
    TextView major_line2_tip;
    TextView major_line3_tip;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_college_adimit,container,false);
        initView(view);
        HttpServer.getCollegeAdmit(new CallbBackString(this));
        return view;
    }
    public void initView(View view) {
        expect_score_stripview= (ScoreStripView) view.findViewById(R.id.expect_score_stripview);
        my_score_stripview= (ScoreStripView) view.findViewById(R.id.my_score_stripview);
        circleProgressBar= (CircleProgressBar) view.findViewById(R.id.circleProgressBar);
        loading= (RelativeLayout) view.findViewById(R.id.loading);
        left_admit_probility=(TextView) view.findViewById(R.id.left_admit_probility);
        left_admit_probility_tip=(TextView) view.findViewById(R.id.left_admit_probility_tip);
        left_expect_line_score=(TextView) view.findViewById(R.id.left_expect_line_score);
        left_expect_line_tip=(TextView) view.findViewById(R.id.left_expect_line_tip);
        left_history_line_tip=(TextView) view.findViewById(R.id.left_history_line_tip);
        major_line1_tip=(TextView) view.findViewById(R.id.major_line1_tip);
        major_line2_tip=(TextView) view.findViewById(R.id.major_line2_tip);
        major_line3_tip=(TextView) view.findViewById(R.id.major_line3_tip);
        listView = (MajorListView) view.findViewById(R.id.major_list_view);
        mLineChartView = (PanelLnChart) view.findViewById(R.id.college_chart);
    }
    public void initData() {
        /*===================录取概率圆圈、分数线以及标注横线=========================*/
        left_expect_line_score.setText(String.valueOf(collegeAdimitData.getExpect_line()));
        circleProgressBar.setProgress(collegeAdimitData.getYiben_possibility());
        if(collegeAdimitData.getYiben_possibility()>0){
            if(collegeAdimitData.getYiben_possibility()<10) left_admit_probility.setText("<10");
            else left_admit_probility.setText(String.valueOf(collegeAdimitData.getYiben_possibility()));
        }else{
            if(collegeAdimitData.getErben_possibility()<10) left_admit_probility.setText("<10");
            else left_admit_probility.setText(String.valueOf(collegeAdimitData.getErben_possibility()));
        }
        if(collegeAdimitData.getPici().equals("一本预测线")){
            left_expect_line_tip.setText(R.string.yiben_expect_line_tip);
            left_history_line_tip.setText(R.string.yiben_history_line_tip);
            left_admit_probility_tip.setText(R.string.yiben_expect_probility_tip);
        }
        else {
            left_expect_line_tip.setText(R.string.erben_expect_line_tip);
            left_history_line_tip.setText(R.string.erben_history_line_tip);
            left_admit_probility_tip.setText(R.string.erben_expect_probility_tip);
        }
        /*===================学校各专业分数线排行列表=========================*/
        data = new ArrayList<>();
        for(int i=0;i<collegeAdimitData.getMajor_lines().size();i++){
            String line1="-";
            String line2="-";
            String line3="-";
            if(collegeAdimitData.getMajor_lines().get(i).getLines().get(2).getLine()>0) line1= String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(2).getLine());
            if(collegeAdimitData.getMajor_lines().get(i).getLines().get(1).getLine()>0) line2= String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(1).getLine());
            if(collegeAdimitData.getMajor_lines().get(i).getLines().get(0).getLine()>0) line3= String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(0).getLine());
            data.add(new MajorLines(collegeAdimitData.getMajor_lines().get(i).getMajor_name(),line1,line2,line3));
            if(i<1){
                major_line1_tip.setText(String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(2).getYear()));
                major_line2_tip.setText(String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(1).getYear()));
                major_line3_tip.setText(String.valueOf(collegeAdimitData.getMajor_lines().get(i).getLines().get(0).getYear()));
            }
        }
        mybaseadapter = new MyBaseAdapter(getActivity(), data);
        listView.setAdapter(mybaseadapter);
        /*===================历年分数线走势 折线图=========================*/
        lineValue=new ArrayList<>();
       // List<Integer> Lines= new ArrayList<>();
        for(int i=collegeAdimitData.getLines().size()-1;i>=0;i--){
           // Lines.add(collegeAdimitData.getLines().get(i).getLine());
            lineValue.add(new LineValue(collegeAdimitData.getLines().get(i).getYear(),collegeAdimitData.getLines().get(i).getLine()));
        }
       // List<Integer> Years= new ArrayList<>();
       //// for(int i=collegeAdimitData.getLines().size()-1;i>=0;i--){
       ///     Years.add(collegeAdimitData.getLines().get(i).getYear());
        //}
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy");
        int  date = Integer.parseInt(sDateFormat.format(new java.util.Date()));
        lineValue.add(new LineValue(date,collegeAdimitData.getExpect_line()));
        mLineChartView.setLineData(lineValue,Integer.parseInt(UserMsg.score));
       // setPointsValues(Lines);          //设置每条线的节点值
        /*===================预测线与我的分数线柱状图=========================*/
        expect_score_stripview.setColor(this.getResources().getColor(R.color.yiben_color));
        if(collegeAdimitData.getYiben_expect_line()>0)expect_score_stripview.setSize((float)collegeAdimitData.getYiben_expect_line());
        else expect_score_stripview.setSize((float)collegeAdimitData.getErben_expect_line());
        expect_score_stripview.invalidate();
        my_score_stripview.setColor(this.getResources().getColor(R.color.myben_color));
        my_score_stripview.setSize(Float.parseFloat(UserMsg.score));
        my_score_stripview.invalidate();
    }
    public void initListener() {

    }

    //TODO 数据处理
    @Override
    public void getNetData(String rdata) {
        collegeAdimitData = new Gson().fromJson(rdata, CollegeAdimitData.class);
        initData();
        loading.setVisibility(View.GONE);
    }



    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<MajorLines> data;

        public MyBaseAdapter(Context context, List<MajorLines> data) {
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_major_lines_list, null);
            TextView major_name = (TextView) view.findViewById(R.id.major_name);
            TextView major_line1 = (TextView) view.findViewById(R.id.major_line1);
            TextView major_line2 = (TextView) view.findViewById(R.id.major_line2);
            TextView major_line3 = (TextView) view.findViewById(R.id.major_line3);
       //     Toast.makeText(getActivity(),(data.get(position)).getMajor_line1()+" ",Toast.LENGTH_LONG).show();
            major_name.setText((data.get(position)).getMajor_name());
            major_line1.setText(String.valueOf(data.get(position).getMajor_line1()));
            major_line2.setText(String.valueOf(data.get(position).getMajor_line2()));
            major_line3.setText(String.valueOf(data.get(position).getMajor_line3()));
            return view;
        }
    }


}
