package com.wan.college.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.activity.discovery.CollegeDetailActivity;
import com.wan.college.model.CollegeDetailData;
import com.wan.college.ui.MajorListView;
import com.wan.progressbar.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/1/10.
 */

public class FragmentJiuye extends Fragment{
    MajorListView listView;
    List<Major> data;
    CollegeDetailData collegeDetailData;
    TextView avarage_salary;
    /*==============进度条==============*/
    CircleProgressBar circleProgressBar;
    TextView employ_rate_view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_college_employment,container,false);
        initView(view);
        initData();
        return view;
    }
    private void initView(View view){
        avarage_salary= (TextView) view.findViewById(R.id.avarage_salary);
        listView = (MajorListView) view.findViewById(R.id.employ_rate_major_top_list_view);
        circleProgressBar= (CircleProgressBar) view.findViewById(R.id.circleProgressBar);
        employ_rate_view=(TextView) view.findViewById(R.id.employ_rate_view);
    }
    private void initData(){
        circleProgressBar.setProgress(collegeDetailData.getEmployment().getHalf_year_employment_ratio());
        if(collegeDetailData.getEmployment().getHalf_year_employment_ratio()>0)
        employ_rate_view.setText(String.valueOf(collegeDetailData.getEmployment().getHalf_year_employment_ratio()));
        if(collegeDetailData.getEmployment().getHalf_year_salary()>0)
        avarage_salary.setText(String.valueOf(collegeDetailData.getEmployment().getHalf_year_salary()));
        if(collegeDetailData.getEmployment().getSalary_rank()!=null){
            data = new ArrayList<>();
            for(int i=0;i<collegeDetailData.getEmployment().getSalary_rank().size();i++)
                data.add(new Major(collegeDetailData.getEmployment().getSalary_rank().get(i).getMajor_name(),collegeDetailData.getEmployment().getSalary_rank().get(i).getSalary()));
            MyBaseAdapter mybaseadapter = new MyBaseAdapter(getActivity(), data);
            listView.setAdapter(mybaseadapter);
        }
    }
    class Major{
        String name;
        String salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
        public Major() {
        }
        public Major(String name, String salary) {
            this.name = name;
            this.salary = salary;
        }
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
            major_name.setText((data.get(position).getName()));
            TextView major_salary = (TextView) view.findViewById(R.id.item_salary);
            major_salary.setText((data.get(position).getSalary()+"￥"));
            return view;
        }
    }
    /**
     * 获取全局数据源
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        collegeDetailData=((CollegeDetailActivity)context).getDetailData();
    }
}
