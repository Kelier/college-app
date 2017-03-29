package com.wan.college.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wan.college.R;

import java.util.List;

/**
 * Created by 万文杰 on 2017/1/8.
 */

public class CommonRecyclerViewAdapter extends RecyclerView.Adapter{

    private Context context ;
    private List<String> data ;
    private OnItemClickListener mItemClickListener ;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 一定要注意 此处inflate方法需要使用3个参数的，否则item横向不平铺
        View view = LayoutInflater.from(context).inflate(R.layout.content_footer_fragment_discovery , parent , false) ;
        RecyclerView.ViewHolder holder = new MyViewHolder(view , mItemClickListener) ;
        return holder ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
            ((MyViewHolder)holder).tv.setText(data.get(position));
        }
    }

    private  class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv ;
        private MyViewHolder(View itemView , final OnItemClickListener listener) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycler_view1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v , getLayoutPosition());
                }
            });
        }
    }

    /***
     * 监听点击事件接口
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }

    /***
     * 设置item点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
