package com.wan.college.activity.discovery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.wan.college.R;
import com.wan.college.adapter.ExpandableListView.ParentAdapter;
import com.wan.college.entity.ChildEntity;
import com.wan.college.entity.ParentEntity;
import com.wan.college.entity.TabEntity;
import com.wan.college.model.MajorListData;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.INetResult;
import com.wan.college.network.UserMsg;

import java.util.ArrayList;

public class MajorSelectActivity extends AppCompatActivity implements ExpandableListView.OnGroupExpandListener,
        ParentAdapter.OnChildTreeViewClickListener ,INetResult<String>{

    private Context mContext;
    private LinearLayout btn_back;
    private static int HTTP_COLLEGE=0;
    private static int HTTP_BACHELOR=1;
    private static int HTTP_TYPE=0;
    private int current_parentPosition=0;
    private int current_groupPosition=0;
    private int current_childPosition=0;
    private ExpandableListView[] major_expandable_list =new ExpandableListView[2];
    private ExpandableListView current_expandable_list;
    private ArrayList<ParentEntity>[] parents =new ArrayList[2];
    private ParentEntity parent;
    private ChildEntity child;
    private ArrayList<CustomTabEntity> mTabEntities;
    private ParentAdapter[] major_adapter =new ParentAdapter[2];;
    int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    CommonTabLayout major_tab_indicator;
    RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_major_select);
        initView();
        initListener();
        HttpServer.getMajorList(new CallbBackString(this),String.valueOf(HTTP_TYPE+1));
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

    /**
     *         初始化菜单数据源
     * */
    private void initData() {
        //------------- 设置 ------------//
        major_expandable_list[HTTP_TYPE].setOnGroupExpandListener(this);
        major_adapter[HTTP_TYPE] = new ParentAdapter(mContext, parents[HTTP_TYPE]);
        major_expandable_list[HTTP_TYPE].setAdapter(major_adapter[HTTP_TYPE]);
        major_adapter[HTTP_TYPE].setOnChildTreeViewClickListener(this);
    }

    /**
     *         初始化ExpandableListView
     * */
    private void initView() {
        btn_back= (LinearLayout) findViewById(R.id.back_btn);
        loading=(RelativeLayout) findViewById(R.id.loading);
        major_tab_indicator=(CommonTabLayout) findViewById(R.id.major_tab_indicator);
        String[] mTitles = new String[] {"本科专业", "专科专业"};
        mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    //    tabdata = Arrays.asList(mTitles);
        major_tab_indicator.setTabData(mTabEntities);
        major_tab_indicator.setCurrentTab(0);
        major_tab_indicator.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                HTTP_TYPE = position;
                Log.i("major","选项卡的postion:"+position);
                if(parents[HTTP_TYPE]==null){
                    loading.setVisibility(View.VISIBLE);
                    HttpServer.getMajorList(new CallbBackString(MajorSelectActivity.this),String.valueOf(HTTP_TYPE+1));
                }
                if(HTTP_TYPE==HTTP_COLLEGE){
                    major_expandable_list[HTTP_BACHELOR].setVisibility(View.GONE);
                    major_expandable_list[HTTP_COLLEGE].setVisibility(View.VISIBLE);
                }
                if(HTTP_TYPE==HTTP_BACHELOR){
                    major_expandable_list[HTTP_COLLEGE].setVisibility(View.GONE);
                    major_expandable_list[HTTP_BACHELOR].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //    eList.setGroupIndicator(this.getResources().getDrawable(R.drawable.major_expandlistview_selector));
        //-------------本科专业 -------------//
        major_expandable_list[0]=(ExpandableListView) findViewById(R.id.major_college_list);
        //-------------专科专业 ------------//
        major_expandable_list[1] = (ExpandableListView) findViewById(R.id.major_bachelor_list);
    }

    /**
     * @author 万文杰
     *                            第三级点击事件，跳转专业具体详情页面
     *         点击子ExpandableListView的子项时，回调本方法，根据下标获取值来做相应的操作
     * */
    @Override
    public void onClickPosition(int parentPosition, int groupPosition,
                                int childPosition) {
/*
        String childName = parents[HTTP_TYPE].get(parentPosition).getChilds()
                .get(groupPosition).getChildNames().get(childPosition);
        String childId = parents[HTTP_TYPE].get(parentPosition).getChilds()
                .get(groupPosition).getChildIds().get(childPosition);

        Toast.makeText(mContext,
                "点击的下标为： parentPosition=" + parentPosition
                        + "   groupPosition=" + groupPosition
                        + "   childPosition=" + childPosition + "\n点击的是："
                        + childName+ "\n点击的id："
                        + childId, Toast.LENGTH_SHORT).show();*/
        UserMsg.mid = parents[HTTP_TYPE].get(parentPosition).getChilds().get(groupPosition).getChildIds().get(childPosition);
        startActivity(new Intent().setClass(MajorSelectActivity.this, MajorDetailActivity.class));
    }
    /**
     * @author 万文杰
     *                        第二级点击事件：发送获取第三级数据请求
     * */
    @Override
    public void onClickGroupPosition(int parentPosition, int groupPosition,ExpandableListView expandableListView) {
      //  Toast.makeText(mContext, "点击的下标为： groupPosition=" + groupPosition, Toast.LENGTH_SHORT).show();
        current_groupPosition=groupPosition;
        current_expandable_list=expandableListView;
        if (parents[HTTP_TYPE].get(current_parentPosition).getChilds().get(groupPosition).getChildIds()==null){
            loading.setVisibility(View.VISIBLE);
            current_expandable_list.collapseGroup(0);
            String third_level = parents[HTTP_TYPE].get(current_parentPosition).getChilds().get(groupPosition).getGroupName();
            String second_level = parents[HTTP_TYPE].get(current_parentPosition).getGroupName();
            HttpServer.getMajorList(new CallbBackString(this),String.valueOf(HTTP_TYPE+1),second_level,third_level);
        }
    }

    /**
     * @author 万文杰
     *                  第一级点击事件：发送获取第二级数据请求
     *               展开一项，关闭其他项，保证每次只能展开一项
     * */
    @Override
    public void onGroupExpand(int groupPosition) {
        current_parentPosition=groupPosition;
        if (parents[HTTP_TYPE].get(current_parentPosition).getChilds()==null){
            loading.setVisibility(View.VISIBLE);
            major_expandable_list[HTTP_TYPE].collapseGroup(current_parentPosition);
            HttpServer.getMajorList(new CallbBackString(this),String.valueOf(HTTP_TYPE+1),parents[HTTP_TYPE].get(current_parentPosition).getGroupName());
        }
        else {
            for (int i = 0; i < parents[HTTP_TYPE].size(); i++) {
                if (i != groupPosition) {
                    major_expandable_list[HTTP_TYPE].collapseGroup(i);
                }
            }
        }
    }

    @Override
    public void getNetData(String data) {
        MajorListData majorListData =new Gson().fromJson(data,MajorListData.class);
        loading.setVisibility(View.GONE);
        if(parents[HTTP_TYPE]==null){//判断parent
            parents[HTTP_TYPE]= new ArrayList<>();//创建对应类型的数据源，只创建一次
            for(int i =0;i<majorListData.getMajors().size();i++){
                parent = new ParentEntity();
                parent.setGroupName(majorListData.getMajors().get(i).getMajor_name());
                parent.setGroupColor(getResources().getColor(
                        R.color.item_first_level_text_color));
                parents[HTTP_TYPE].add(parent); //第一层数据集合
            }
            initData();
            Log.i("major","第一层数据请求");
        }else if (parents[HTTP_TYPE].get(current_parentPosition).getChilds()==null){
            ArrayList<ChildEntity> childs = new ArrayList<>();
            for(int i =0;i<majorListData.getMajors().size();i++){
                child = new ChildEntity();
                child.setGroupName(majorListData.getMajors().get(i).getMajor_name());
                child.setGroupColor(getResources().getColor(
                        R.color.item_second_level_text_color));
                childs.add(child);//第二层数据集合
            }
            parents[HTTP_TYPE].get(current_parentPosition).setChilds(childs);//第一层里插入对应的第二层
            Log.i("major","第二层数据请求");
            major_adapter[HTTP_TYPE] = new ParentAdapter(mContext, parents[HTTP_TYPE]);
            major_adapter[HTTP_TYPE].notifyDataSetChanged();
            major_expandable_list[HTTP_TYPE].expandGroup(current_parentPosition);
        }else {
            ArrayList<ChildEntity> childs = parents[HTTP_TYPE].get(current_parentPosition).getChilds();
            ChildEntity child = childs.get(current_groupPosition);
            ArrayList<String> childNames = new ArrayList<>();
            ArrayList<String> childIds = new ArrayList<>();
            for(int i =0;i<majorListData.getMajors().size();i++){
                childNames.add(majorListData.getMajors().get(i).getMajor_name());
                childIds.add(String.valueOf(majorListData.getMajors().get(i).getMajor_id()));
            }
            child.setChildNames(childNames);
            child.setChildIds(childIds);
            childs.set(current_groupPosition,child);
            parents[HTTP_TYPE].get(current_parentPosition).setChilds(childs);//第一层里更新含有第三层的的第二层
            major_adapter[HTTP_TYPE] = new ParentAdapter(mContext, parents[HTTP_TYPE]);
            major_adapter[HTTP_TYPE].notifyDataSetChanged();
            current_expandable_list.expandGroup(0);
        }

    }
}
