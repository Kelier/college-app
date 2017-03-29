package com.wan.college.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wan.college.R;

/**
 * Created by 万文杰 on 2017/2/17.
 */

public class NumPicTextView extends LinearLayout {
    public static int[] a;

    static {
        a = new int[]{R.drawable.number_0, R.drawable.number_1, R.drawable.number_2, R.drawable.number_3, R.drawable.number_4, R.drawable.number_5,
                R.drawable.number_6, R.drawable.number_7, R.drawable.number_8, R.drawable.number_9};
    }

    public NumPicTextView(Context arg1) {
        super(arg1);
    }

    public NumPicTextView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public NumPicTextView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    private ImageView a(int arg5) {
        ImageView v0;
        int v2 = -2;
        if(arg5 < 0 || arg5 > 9) {
            v0 = null;
        }
        else {
            LayoutParams v1 = new LayoutParams(v2, v2);
            v1.leftMargin = 0;
            v1.rightMargin = 0;
            v0 = new ImageView(this.getContext());
            v0.setImageResource(NumPicTextView.a[arg5]);
            v0.setLayoutParams(((ViewGroup.LayoutParams)v1));
            v0.setPadding(0, 0, 0, 0);
        }

        return v0;
    }

    public void setNumber(int arg3) {
        this.removeAllViews();
        do {
            int v0 = arg3 % 10;
            arg3 /= 10;
            this.addView(this.a(v0), 0);
        }
        while(arg3 != 0);
    }
}