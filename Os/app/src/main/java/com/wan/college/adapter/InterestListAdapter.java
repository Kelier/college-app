package com.wan.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.model.CollegeList;
import com.wan.college.network.UserMsg;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class InterestListAdapter extends RecyclerView.Adapter{
    private MyItemClickListener mItemClickListener;
    private Context context;
    private List<CollegeList> data;

    public InterestListAdapter(Context context, List<CollegeList> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_college_list_layout, parent, false);
        //将全局的监听传递给holder
        MyHolder holder = new MyHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给空间内的元素控件赋值
        if (holder instanceof MyHolder) {
            x.image().bind(((MyHolder) holder).college_logo, data.get(position).getCollege_icon());
            ((MyHolder) holder).college_name.setText(data.get(position).getCollege_name());
            ((MyHolder) holder).admission_score.setText(data.get(position).getExpect_line() + "");
            ((MyHolder) holder).admisson_prob.setText(data.get(position).getPossibility() + "%");
            for (int i=0;i<((MyHolder) holder).college_tag.size();i++) {
                ((MyHolder) holder).college_tag.get(i).setVisibility(View.GONE);
            }
            if(data.get(position).getTags()!=null){
                for (int i=0;i<data.get(position).getTags().size();i++) {
                    ((MyHolder) holder).college_tag.get(i).setText(data.get(position).getTags().get(i));
                    ((MyHolder) holder).college_tag.get(i).setVisibility(View.VISIBLE);
                }
            }
                ((MyHolder) holder).interest_view.setBackground(context.getResources().getDrawable(R.drawable.follow_btn_pressed));
                ((MyHolder) holder).interest_text.setTextColor(context.getResources().getColor(R.color.orange_btn_normal_background_color));
                ((MyHolder) holder).interest_text.setText("取消");
        }
    }


    private class MyHolder extends RecyclerView.ViewHolder {
        private RelativeLayout college_list_item;
        private ImageView college_logo;
        private TextView college_name;
        private TextView admission_score;
        private TextView admisson_prob;
        private ArrayList<TextView> college_tag;
        private LinearLayout interest_add;
        private ImageView interest_view;
        private TextView interest_text;


        MyHolder(View itemView, MyItemClickListener itemClickListener) {
            super(itemView);
            college_list_item = (RelativeLayout) itemView.findViewById(R.id.college_list_item);
            college_logo = (ImageView) itemView.findViewById(R.id.college_logo);
            college_name = (TextView) itemView.findViewById(R.id.college_name);
            admission_score = (TextView) itemView.findViewById(R.id.admission_score);
            admisson_prob = (TextView) itemView.findViewById(R.id.admisson_prob);
            interest_add= (LinearLayout) itemView.findViewById(R.id.interest_add);
            interest_view= (ImageView) itemView.findViewById(R.id.interest_view);
            interest_text= (TextView) itemView.findViewById(R.id.interest_text);
            college_tag=new ArrayList<>();
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag1));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag2));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag3));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag4));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag5));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag6));
            college_tag.add((TextView) itemView.findViewById(R.id.college_tag7));
            //将全局的监听赋值给接口
            mItemClickListener = itemClickListener;
            college_list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Log.i("大学列表位置：",getLayoutPosition()+"");
                    mItemClickListener.onItemClick(v, getLayoutPosition());
                }
            });
            interest_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserMsg.cid=String.valueOf(data.get(getLayoutPosition()).getCollege_id());
                    mItemClickListener.onRemoveItemClick(v,getLayoutPosition());
                }
            });
        }

        /**
         * 实现OnClickListener接口重写的方法
         * @param v
         */
        /*
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }*/
    }

    /**
     * 创建一个回调接口,监听点击事件接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
        void onRemoveItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     * 设置item点击事件
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

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
}
