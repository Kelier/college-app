package com.wan.college.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

/**
 * Created by 万文杰 on 2017/1/10.
 */

public class FragmentIntro extends Fragment{
    MajorListView special_major_list_view;
    MajorListView important_major_list_view;
    List<String> data;
    CollegeDetailData collegeDetailData;
    TextView master_num;
    TextView doctor_num;
    TextView important_major_num;
    TextView sex_rate;
    TextView belong_to;
    TextView college_type;
    TextView college_addr;
    TextView special_major_title;
    TextView important_major_title;
    TextView college_tel;
    ExpandableTextView expandable_text;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_college_intro,container,false);
        this.view=view;
        initView(view);
        initData();
        return view;
    }
    private void initView(View view){
        master_num= (TextView) view.findViewById(R.id.master_num);
        doctor_num= (TextView) view.findViewById(R.id.doctor_num);
        important_major_num= (TextView) view.findViewById(R.id.important_major_num);
        sex_rate= (TextView) view.findViewById(R.id.sex_rate);
        belong_to= (TextView) view.findViewById(R.id.belong_to);
        college_type= (TextView) view.findViewById(R.id.college_type);
        college_addr= (TextView) view.findViewById(R.id.college_addr);
        college_tel= (TextView) view.findViewById(R.id.college_tel);
        expandable_text= (ExpandableTextView) view.findViewById(R.id.expandable_text);
        special_major_list_view = (MajorListView) view.findViewById(R.id.special_major_list_view);
        important_major_list_view = (MajorListView) view.findViewById(R.id.important_major_list_view);
        special_major_title= (TextView) view.findViewById(R.id.special_major_title);
        important_major_title= (TextView) view.findViewById(R.id.important_major_title);
        special_major_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(special_major_list_view.getVisibility()==View.GONE){
                    special_major_list_view.setVisibility(View.VISIBLE);
                    Drawable drawable = getResources().getDrawable(R.drawable.xiala_icon_turn180);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    special_major_title.setCompoundDrawables(null, null, drawable, null);//画在右边
                }else{
                    special_major_list_view.setVisibility(View.GONE);
                    Drawable drawable = getResources().getDrawable(R.drawable.xiala_icon_normal);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    special_major_title.setCompoundDrawables(null, null, drawable, null);//画在右边
                }
            }
        });
        important_major_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(important_major_list_view.getVisibility()==View.GONE){
                    important_major_list_view.setVisibility(View.VISIBLE);
                    Drawable drawable = getResources().getDrawable(R.drawable.xiala_icon_turn180);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    important_major_title.setCompoundDrawables(null, null, drawable, null);//画在右边
                }else{
                    important_major_list_view.setVisibility(View.GONE);
                    Drawable drawable = getResources().getDrawable(R.drawable.xiala_icon_normal);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    important_major_title.setCompoundDrawables(null, null, drawable, null);//画在右边
                }
            }
        });
    }

    private void initData(){
        if(collegeDetailData!=null){
            master_num.setText(String.valueOf(collegeDetailData.getMaster_num()));
            doctor_num.setText(String.valueOf(collegeDetailData.getDoctor_num()));
            important_major_num.setText(String.valueOf(collegeDetailData.getImportant_major_num()));
            sex_rate.setText(new BigDecimal(collegeDetailData.getSex_rate().get(1)).setScale(0, BigDecimal.ROUND_HALF_UP).toString()+":"+new BigDecimal(collegeDetailData.getSex_rate().get(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
            belong_to.setText(collegeDetailData.getBelong_to());
            college_type.setText(collegeDetailData.getCollege_type());
            college_addr.setText(collegeDetailData.getAddress());
            college_tel.setText(collegeDetailData.getTelnum());
            expandable_text.setText(collegeDetailData.getCollege_description());

            //////////////////// 特色专业与重点专业列表////////////////////////
            LinearLayout special_major= (LinearLayout) view.findViewById(R.id.special_major);
            special_major.setVisibility(View.GONE);
            LinearLayout important_major= (LinearLayout) view.findViewById(R.id.important_major);
            important_major.setVisibility(View.GONE);
            MyBaseAdapter mybaseadapter;
            if(collegeDetailData.getSpecial_majors()!=null){
                data = new ArrayList<>();
                for(int i=0;i<collegeDetailData.getSpecial_majors().size();i++){
                    data.add(collegeDetailData.getSpecial_majors().get(i));
                }
                mybaseadapter = new MyBaseAdapter(getActivity(), data);
                special_major_list_view.setAdapter(mybaseadapter);
                special_major.setVisibility(View.VISIBLE);
            }
            if(collegeDetailData.getImportant_majors()!=null){
                data = new ArrayList<>();
                for(int i=0;i<collegeDetailData.getImportant_majors().size();i++){
                    data.add(collegeDetailData.getImportant_majors().get(i).getMajor_name());
                }
                mybaseadapter = new MyBaseAdapter(getActivity(), data);
                important_major_list_view.setAdapter(mybaseadapter);
                important_major.setVisibility(View.VISIBLE);
            }
        }
    }

    class MyBaseAdapter extends BaseAdapter {
        Context context;
        List<String> data;

        public MyBaseAdapter(Context context, List<String> data) {
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
            TextView tv_name = (TextView) view.findViewById(R.id.item_major);
            tv_name.setText(( data.get(position)).toString());


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
