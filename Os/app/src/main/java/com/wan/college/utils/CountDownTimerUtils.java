package com.wan.college.utils;

import android.graphics.Color;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by Leyiteyanzhi on 2017/1/4.
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    public CountDownTimerUtils(TextView textView,long millisInFuTure,long countDownInterval){
        super(millisInFuTure,countDownInterval);
        this.mTextView = textView;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        mTextView.setText(millisUntilFinished / 1000 + "秒后可重新获取");
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span,0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);

    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);


    }
}
