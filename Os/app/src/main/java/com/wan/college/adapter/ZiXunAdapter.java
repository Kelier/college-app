package com.wan.college.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.model.News;
import com.wan.college.model.Rank;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ZiXunAdapter extends RecyclerView.Adapter {
    private MyItemClickListener mItemClickListener;
    private Context context;
    private List<News> data;
    public ZiXunAdapter(Context context, ArrayList<News> data){
        this.data=data;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_item_zixun,parent,false);
        //将全局的监听传递给holder
        MyHolder holder = new MyHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给空间内的元素控件赋值
        if(holder instanceof MyHolder) {
          ((MyHolder)holder).zixun_title.setText(data.get(position).getNew_title());
            ((MyHolder)holder).zixun_subtitle.setText(data.get(position).getNew_subtitle());
            ((MyHolder)holder).zixun_thirdtitle.setText(data.get(position).getNew_thirdtitle());
            ((MyHolder)holder).zixun_read_num.setText(data.get(position).getNews_read_num());
           x.image().bind(((MyHolder)holder).zixun_bg,data.get(position).getNews_bg());

        }
    }
    private class MyHolder extends RecyclerView.ViewHolder {
        CardView zixun_card;
        private ImageView zixun_bg;
        private TextView zixun_title;
        private TextView zixun_subtitle;
        private TextView zixun_thirdtitle;
        private TextView zixun_read_num;
        private MyItemClickListener mListener;
        MyHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            zixun_bg= (ImageView) itemView.findViewById(R.id.zixun_bg);
            zixun_title= (TextView) itemView.findViewById(R.id.zixun_title);
            zixun_subtitle= (TextView) itemView.findViewById(R.id.zixun_subtitle);
            zixun_thirdtitle= (TextView) itemView.findViewById(R.id.zixun_thirdtitle);
            zixun_read_num= (TextView) itemView.findViewById(R.id.zixun_read_num);
            zixun_card= (CardView) itemView.findViewById(R.id.zixun_card);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            Log.i("adapter" , "" + (null==this.mListener));

            zixun_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v , getLayoutPosition());
                }
            });

        }

    }
    /**
     * 创建一个回调接口,监听点击事件接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *设置item点击事件
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
