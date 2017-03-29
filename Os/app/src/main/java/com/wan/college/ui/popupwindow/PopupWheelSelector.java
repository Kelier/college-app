package com.wan.college.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.wan.college.R;
import com.wx.wheelview.adapter.BaseWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/5.
 */

public class PopupWheelSelector extends PopupWindow {
    private TextView pop_cancel;
    private TextView pop_confirm;
    private WheelView wheelview;
    private Activity context;
    private PopOnClickListener popOnClickListener;
    private List<String> data;
    private String province;
    public PopupWheelSelector(Activity context, List<String> data,String province,PopOnClickListener popOnClickListener)  {
        super(context);
        this.context=context;
        this.data=data;
        this.province=province;
        this.popOnClickListener=popOnClickListener;
        initView();
        initListener();
    }
    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_wheel, null);
        pop_cancel= (TextView) view.findViewById(R.id.pop_cancel);
        pop_confirm= (TextView) view.findViewById(R.id.pop_confirm);
        wheelview= (WheelView) view.findViewById(R.id.wheelview);
        wheelview.setWheelAdapter(new WheelAdapter(context));
        wheelview.setWheelSize(3);
        wheelview.setSkin(WheelView.Skin.Holo);
        wheelview.setWheelData(data);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.holoBorderColor= context.getResources().getColor(R.color.header_white_line_color);
        style.holoBorderWidth=1;
        style.textColor = context.getResources().getColor(R.color.gray_background);
        style.selectedTextColor = context.getResources().getColor(R.color.color_theme);
        wheelview.setStyle(style);
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
                bgAlpha(1f);
            }
        });
    }
    private void initListener(){
    pop_cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    });
        pop_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popOnClickListener.getPosition(wheelview.getCurrentPosition(),wheelview.getSelectionItem().toString());
                dismiss();
            }
        });
}
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        bgAlpha(0.5f);
    }
    //实现回调功能
    public interface PopOnClickListener {
        public void getPosition(int position, String province);
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
    private class WheelAdapter extends BaseWheelAdapter<String> {

        private Context mContext;

         WheelAdapter(Context context) {
            mContext = context;
        }

        @Override
        protected View bindView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_wheel_list, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.item_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(mList.get(position));
            if(mList.get(position).equals(province)) wheelview.setSelection(position);
            return convertView;
        }

         class ViewHolder {
            TextView textView;
        }

    }
}
