package com.wan.college.ui;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.wan.college.R;

/**
 * Created by 万文杰 on 2017/1/31.
 */

public class ScoreStripView extends View {
    Context context;
    float left=0;
    float right=100;
    float top=45;
    float bottom=300;
    int score=100;
    Paint p= new Paint();
    Canvas canvasLine;
    public ScoreStripView(Context context) {
        super(context);
        this.context=context;

    }

    public ScoreStripView(Context context, AttributeSet arg4) {
        super(context, arg4);

    }

    public ScoreStripView(Context context, AttributeSet arg4, int arg5) {
        super(context, arg4, arg5);

    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasLine=canvas;
        draw();
    }

    public void setSize(float score) {
        this.left=0;
        this.top=this.getLayoutParams().height-(score/750f)*this.getLayoutParams().height+45;
        this.right=this.getLayoutParams().width;
        this.bottom=this.getLayoutParams().height;
        this.score=(int)(score);

    }
    public void setColor(int color) {
        p.setColor(color);// 设置颜色
    }

    public void draw(){
        p.setStyle(Paint.Style.FILL);//设置填满
        p.setTextSize(60.0f);
        p.setDither(true);
        p.setAntiAlias(true);
        canvasLine.drawText(String.valueOf(score), 8, top, p);
        canvasLine.drawRect(left, top+5, right, bottom, p);// 长方形
        this.invalidate();
    }


}

