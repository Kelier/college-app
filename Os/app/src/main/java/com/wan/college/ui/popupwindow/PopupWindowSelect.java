package com.wan.college.ui.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wan.college.R;

/**
 * Created by 万文杰 on 2017/2/4.
 */

public class PopupWindowSelect extends PopupWindow{
    private Activity context;
    public PopupWindowSelect(final Activity context, final int layout_id, int radio_id, final PopOnClickListener popOnClickListener,final PopOnDismissListener popOnDismissListener) {
        super(context);
        /*
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout_id, null);
        */
        this.context=context;
        View view = LayoutInflater.from(context).inflate(layout_id, null);
        RadioGroup radiogroup= (RadioGroup) view.findViewById(radio_id);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                popOnClickListener.getId(i);
                dismiss();
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.popwindow_style);
//        设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //实例化一个ColorDrawable颜色为全透明！！！为了和window底色融合，设置#00ffffff
//         ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.transparent));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置SelectPicPopupWindow如果点击选择框外面则销毁弹出框
        this.setOutsideTouchable(false);
        //设置销毁监听
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1f);//popupwindow消失的时候恢复成原来的透明度
                popOnDismissListener.onDismiss();
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
         void getId(int id);
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
