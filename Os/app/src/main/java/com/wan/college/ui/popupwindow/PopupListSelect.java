package com.wan.college.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wan.college.R;
import com.wan.college.activity.discovery.PiCiActivity;
import com.wan.college.model.Pici;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/5.
 */

public class PopupListSelect extends PopupWindow {
    private Activity context;
    private PopOnClickListener popOnClickListener;
    public PopupListSelect(Activity context, String[] data, PopOnClickListener popOnClickListener,final PopOnDismissListener popOnDismissListener) {
        super(context);
        this.context=context;
        this.popOnClickListener=popOnClickListener;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_list_select, null);
        ListView listView= (ListView) view.findViewById(R.id.pop_list);
        MyBaseAdapter mybaseadapter = new MyBaseAdapter(context, data);
        listView.setAdapter(mybaseadapter);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.popwindow_style);
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setOutsideTouchable(false);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1f);//popupwindow消失的时候恢复成原来的透明度
                popOnDismissListener.onDismiss();
            }
        });
    }
   private class MyBaseAdapter extends BaseAdapter {
        Context context;
        String[] data;

        MyBaseAdapter(Context context, String[] data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.length;
        }
        @Override
        public Object getItem(int position) {
            return data[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_pop_list, parent,false);
            TextView item_pop = (TextView) view.findViewById(R.id.item_pop);
            item_pop.setText(data[position]);
            item_pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popOnClickListener.getPosition(position);
                    dismiss();
                }
            });
            return view;
        }
    }
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        bgAlpha(0.5f);
    }
    //实现回调功能
    public interface PopOnClickListener {
        public void getPosition(int position);
    }
    public interface PopOnDismissListener {
        void onDismiss();
    }
    /**
     * 改变窗体透明度方法
     *
     * @param alpha
     */
    private void bgAlpha(float alpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = alpha;
        context.getWindow().setAttributes(lp);
    }
}
