package com.wan.college.ui;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.TypeEvaluator;
import com.romainpiel.shimmer.ShimmerTextView;
import com.romainpiel.shimmer.ShimmerViewBase;
import com.romainpiel.shimmer.ShimmerViewHelper;
/**
 * Created by 万文杰 on 2017/2/21.
 */

public class MyScoreAdjustView extends RelativeLayout {
    public MyScoreAdjustView(Context context) {
        super(context);
    }

    public MyScoreAdjustView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScoreAdjustView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    private static final int a = 1500;
    private int b;
    private int c;
    private int d;
    private View e;
    private MyScoreMaskView f;
    private TextView g;
    private ShimmerTextView h;
    private ShimmerViewHelper i;
    private int j;
    private bz k;

    public MyScoreAdjustView(Context arg2) {
        super(arg2);
        this.d = 4;
        this.j = 92;
        this.a(arg2, null);
    }

    public MyScoreAdjustView(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.d = 4;
        this.j = 92;
        this.a(arg2, arg3);
    }

    public MyScoreAdjustView(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.d = 4;
        this.j = 92;
        this.a(arg2, arg3);
    }

    private void a(Context arg4, AttributeSet arg5) {
        this.b = 750;
        View v1 = LayoutInflater.from(arg4).inflate(2130903231, ((ViewGroup)this), true);
        this.e = v1.findViewById(2131362767);
        this.f = v1.findViewById(2131362768);
        v1.findViewById(2131362769).setOnClickListener(new by(this));
        this.g = v1.findViewById(2131362770);
        this.e.setRotation(90f);
        this.h = v1.findViewById(2131362771);
    }

    static View a(MyScoreAdjustView arg1) {
        return arg1.e;
    }

    private void a() {
        this.b();
    }

    private void b() {
        if(this.i != null && (this.i.g())) {
            this.i.f();
            this.i = null;
        }
    }

    static int b(MyScoreAdjustView arg1) {
        return arg1.d;
    }

    static MyScoreMaskView c(MyScoreAdjustView arg1) {
        return arg1.f;
    }

    static bz d(MyScoreAdjustView arg1) {
        return arg1.k;
    }

    public int getCurrentValue() {
        return this.c;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a();
    }

    protected void onDetachedFromWindow() {
        this.b();
        super.onDetachedFromWindow();
    }

    public void setCurrentValue(int arg7) {
        while(arg7 > this.b) {
            arg7 %= this.b;
        }

        int v0 = this.j;
        int v1 = (360 - this.d) * arg7 / this.b + 90 + this.d / 2;
        this.c = arg7;
        this.g.setText(String.valueOf(this.c));
        if(v0 != v1) {
            ValueAnimator v2 = new ValueAnimator();
            v2.setDuration(((long)(Math.abs(v1 - v0) * 1500 / 360)));
            v2.setObjectValues(new Object[]{Integer.valueOf(v0)});
            v2.setInterpolator(new LinearInterpolator());
            v2.setEvaluator(new bw(this, v0, v1));
            v2.addUpdateListener(new bx(this));
            v2.start();
        }

        this.j = v1;
    }

    public void setMaxValue(int arg2) {
        this.b = arg2;
        this.setCurrentValue(this.c);
    }

    public void setOnClickScoreViewListener(bz arg1) {
        this.k = arg1;
    }
    public interface bz {
        void a();
    }
    class by implements View.OnClickListener {
        MyScoreAdjustView a;
        by(MyScoreAdjustView arg1) {
            super();
            this.a = arg1;
        }

        public void onClick(View arg2) {
            if(MyScoreAdjustView.d(this.a) != null) {
                MyScoreAdjustView.d(this.a).a();
            }
        }
    }
    class bw implements TypeEvaluator {
        MyScoreAdjustView c;
        int a;
        int b;
        bw(MyScoreAdjustView arg1, int arg2, int arg3) {
            super();
            this.c = arg1;
            this.a = arg2;
            this.b = arg3;
        }

        public Integer a(float arg4, Integer arg5, Integer arg6) {
            return Integer.valueOf(((int)((((float)this.a)) + (((float)(this.b - this.a))) * arg4)));
        }

        public Object evaluate(float arg2, Object arg3, Object arg4) {
            return this.a(arg2, ((Integer)arg3), ((Integer)arg4));
        }
    }
    class bx implements ValueAnimator.AnimatorUpdateListener {
        MyScoreAdjustView a;
        bx(MyScoreAdjustView arg1) {
            super();
            this.a = arg1;
        }

        public void onAnimationUpdate(ValueAnimator arg4) {
            int v0 = (int)arg4.getAnimatedValue();
            MyScoreAdjustView.a(this.a).setRotation(((float)v0));
            MyScoreAdjustView.c(this.a).a(v0, 450 - MyScoreAdjustView.b(this.a) / 2);
        }
    }*/
}
