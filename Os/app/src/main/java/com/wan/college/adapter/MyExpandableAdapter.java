package com.wan.college.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wan.college.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Yan on 1/12/2017.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> groupArray;
    private List<String> childArray;
    private Context mcontext;
    View groupView;
    List<ImageView> icon=new ArrayList<>();

    public MyExpandableAdapter(List<String> groupArray, List<String> childArray, Context context) {
        this.groupArray = groupArray;
        this.childArray = childArray;
        this.mcontext = context;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (mcontext == null)
            Log.e("tip", "null");
        View view = convertView;
        GroupHolder holder = null;
        if (view == null) {
            holder = new GroupHolder();
            view = LayoutInflater.from(mcontext).inflate(R.layout.expand_list_group, null);
            groupView=view;
            holder.groupName = (TextView) view.findViewById(R.id.tv_group_name);
            holder.set_checked = (ImageView) view.findViewById(R.id.set_checked);
            holder.arrow = (ImageView) view.findViewById(R.id.iv_arrow);
            icon.add(holder.set_checked);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }

        //判断是否已经打开列表
        if (isExpanded) {
            holder.arrow.setBackgroundResource(R.drawable.nav_screening_up_normal);
        } else {
            holder.arrow.setBackgroundResource(R.drawable.nav_screening_down_normal);
        }
        holder.groupName.setText(groupArray.get(groupPosition));
        return view;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,View convertView,ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        RelativeLayout child;
        if (view == null) {
            holder = new ChildHolder();
            view = LayoutInflater.from(mcontext).inflate(R.layout.expand_list_item, null);
            holder.childName = (TextView) view.findViewById(R.id.tv_child_name);
            //   holder.child= (RelativeLayout)view.findViewById(R.id.iv_divider);
            view.setTag(holder);

        } else {
            holder = (ChildHolder) view.getTag();
        }

        Log.e("position", String.valueOf(childPosition));
        holder.childName.setText(childArray.get(childPosition));

        return view;
    }
public List<ImageView> getIcon(){
    return icon;
}

    class GroupHolder {
        TextView groupName;
        ImageView arrow;
        ImageView set_checked;
    }

    class ChildHolder {
        TextView childName;
    }


    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
