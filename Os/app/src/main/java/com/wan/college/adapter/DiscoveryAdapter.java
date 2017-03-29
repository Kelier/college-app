package com.wan.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wan.college.R;
import com.wan.college.model.FindBean;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryAdapter extends RecyclerView.Adapter {
    private MyItemClickListener mItemClickListener;
    private Context context;
    private List<FindBean> data;
    public DiscoveryAdapter(Context context, ArrayList<FindBean> data){
        this.data=data;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_item_discovery,parent,false);
        //将全局的监听传递给holder
        MyHolder holder = new MyHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给空间内的元素控件赋值
        if(holder instanceof MyHolder) {
            ((MyHolder)holder).textTitle.setText(data.get(position).getText_title());
            ((MyHolder)holder).textContent.setText(data.get(position).getText_content());
            ((MyHolder)holder).imageView.setImageResource(data.get(position).getLogo());
        }
    }
    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textTitle;
        private  TextView textContent;
        private MyItemClickListener mListener;
        MyHolder(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.dis_image);
            textTitle= (TextView) itemView.findViewById(R.id.dis_title_text);
            textContent= (TextView) itemView.findViewById(R.id.dis_title_text_content);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
        }

        /**
         * 实现OnClickListener接口重写的方法
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }
    }
    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }



}
