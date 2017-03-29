package com.wan.college.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.college.R;

/**
 * Created by 万文杰 on 2017/2/15.
 */

public class CustomToast {
    private static TextView mTextView;
    private static ImageView mImageView;

    public static void showDone(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.view_shared_toast_with_image_tipview, null);
        //初始化布局控件
        mTextView = (TextView) toastRoot.findViewById(R.id.toast_tip_text);
        mImageView = (ImageView) toastRoot.findViewById(R.id.toast_tip_image);
        //为控件设置属性
        mTextView.setText(message);
        mImageView.setImageResource(R.drawable.toast_done);
        initToast(context,toastRoot);
    }
    public static void showErrorToast(Context context, String message) {
        //加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.view_shared_toast_with_image_tipview, null);
        //初始化布局控件
        mTextView = (TextView) toastRoot.findViewById(R.id.toast_tip_text);
        mImageView = (ImageView) toastRoot.findViewById(R.id.toast_tip_image);
        //为控件设置属性
        mTextView.setText(message);
        mImageView.setImageResource(R.drawable.toast_error);
        initToast(context,toastRoot);
    }
    private static void initToast(Context context,View toastRoot){
        //Toast的初始化
        Toast toastStart = new Toast(context);
        //获取屏幕高度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的中间，不会出现不适配的问题
        toastStart.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toastStart.setDuration(Toast.LENGTH_SHORT);
        toastStart.setView(toastRoot);
        toastStart.show();
    }
}
