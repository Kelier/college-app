package com.wan.college.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.activity.discovery.PiCiActivity;
import com.wan.college.network.CallbBackString;
import com.wan.college.network.HttpServer;
import com.wan.college.network.UserMsg;

import java.util.List;

/**
 * Created by asus on 2017/3/3.
 */

public class ProvinceAdapter extends BaseAdapter {
    Context context;
    List<String> data;
    int color_position=-1;

    public ProvinceAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        for (int i=0;i<data.size();i++){
         //   Log.i("province",data.get(i)+"  "+i+"  "+UserMsg.province);
            if(data.get(i).equals(UserMsg.province)){
                setColorPosition(i);
                break;
            }
        }
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
        final View view = LayoutInflater.from(context).inflate(R.layout.item_major_list, parent, false);
        final TextView item_major = (TextView) view.findViewById(R.id.item_major);
        if (data.get(position).length() > 0) {
            item_major.setText(data.get(position));
            if (color_position>=0&&position==color_position){
              //  item_major.setTextColor(R.color.color_theme);
                item_major.setTextColor(Color.BLUE);
            }
        }

        return view;
    }

    public void setColorPosition(int color_position){
            this.color_position=color_position;
    }
    public int getColorPosition(){
        return this.color_position;
    }
}