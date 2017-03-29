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
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 万文杰 on 2017/1/31.
 */

public class ScoreStripView3 extends View {
    private static final int a = 500;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private final RectF j;
    private Paint k;
    private int l;
    private int m;

    public ScoreStripView3(Context arg3) {
        super(arg3);
        this.f = 20;
        this.g = 10;
        this.h = 0;
        this.i = false;
        this.j = new RectF();
        this.l = 0;
        this.m = 750;
        this.a(arg3, null);
    }

    public ScoreStripView3(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        this.f = 20;
        this.g = 10;
        this.h = 0;
        this.i = false;
        this.j = new RectF();
        this.l = 0;
        this.m = 750;
        this.a(arg3, arg4);
    }

    public ScoreStripView3(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        this.f = 20;
        this.g = 10;
        this.h = 0;
        this.i = false;
        this.j = new RectF();
        this.l = 0;
        this.m = 750;
        this.a(arg3, arg4);
    }

    private void a(Context arg6, AttributeSet arg7) {
        int v4 = 20;
        if(arg7 != null) {
            TypedArray v0 = arg6.obtainStyledAttributes(arg7, new int[]{2130772091, 2130772092, 2130772093, 2130772094, 2130772095, 2130772096});
            if(v0 != null) {
                this.d = Color.parseColor("#000000");//v0.getColor(3, 0);
                this.c = Color.parseColor("#000000");// v0.getColor(2, 0);
             //   this.b = v0.getDimensionPixelOffset(1, v4);
            //    this.e = v0.getDimensionPixelOffset(0, 50);
             //   this.f = v0.getDimensionPixelOffset(4, v4);
              //  this.g = v0.getDimensionPixelOffset(5, 10);
                v0.recycle();
            }
        }

        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setColor(this.c);
        this.k.setStyle(Paint.Style.FILL);
        this.k.setTextSize(((float)this.b));
    }

    static RectF a(ScoreStripView3 arg1) {
        return arg1.j;
    }

    static boolean a(ScoreStripView3 arg0, boolean arg1) {
        arg0.i = arg1;
        return arg1;
    }

    public void a() {
        if(!this.i && this.m > 0 && this.l >= 0 && this.l <= this.m) {
            ValueAnimator v0 = new ValueAnimator();
            v0.setDuration(((long)(this.l * 500 / this.m)));
            v0.setInterpolator(new LinearInterpolator());
            v0.setObjectValues(new Object[]{Integer.valueOf(this.getHeight() - this.g), Integer.valueOf(
                    this.getHeight() - (this.h - this.g) * this.l / this.m - this.g)});
            v0.addUpdateListener(new cg(this));
            v0.addListener(new ch(this));
            v0.start();
        }
    }

    public void a(int arg1, int arg2) {
        this.l = arg1;
        this.m = arg2;
        this.postInvalidate();
        this.a();
    }

    protected void onDraw(Canvas arg6) {
        super.onDraw(arg6);
        this.k.setColor(this.c);
        if(!this.i) {
            this.j.top = ((float)(this.getHeight() - this.l * (this.h - this.g) / this.m - this.g));
        }

        arg6.drawRect(this.j, this.k);
        String v0 = String.valueOf(this.l);
        float v1 = this.k.measureText(v0);
        this.k.setColor(this.d);
        arg6.drawText(v0, ((((float)this.getWidth())) - v1) / 2f, this.j.top - (((float)this.f)), this
                .k);
    }

    protected void onSizeChanged(int arg3, int arg4, int arg5, int arg6) {
        super.onSizeChanged(arg3, arg4, arg5, arg6);
        this.j.left = ((float)((arg3 - this.e) / 2));
        this.j.right = ((float)((this.e + arg3) / 2));
        this.j.top = ((float)(arg4 - this.g));
        this.j.bottom = ((float)arg4);
        this.h = arg4 - this.b - this.f;
    }

    public void setBarColor(int arg1) {
        this.c = arg1;
        this.postInvalidate();
    }

    public void setBarTextColor(int arg1) {
        this.d = arg1;
        this.postInvalidate();
    }
    class cg implements ValueAnimator.AnimatorUpdateListener {
        ScoreStripView3 a;
        cg(ScoreStripView3 arg1) {
            super();
            this.a = arg1;
        }

        public void onAnimationUpdate(ValueAnimator arg3) {
            int v0 = (int)arg3.getAnimatedValue();
            if(ScoreStripView3.a(this.a) != null) {
                ScoreStripView3.a(this.a).top = ((float)v0);
                this.a.postInvalidate();
            }
        }
    }
    class ch implements Animator.AnimatorListener {
        ScoreStripView3 a;
        ch(ScoreStripView3 arg1) {
            super();
            this.a = arg1;
        }

        public void onAnimationCancel(Animator arg3) {
            ScoreStripView3.a(this.a, false);
        }

        public void onAnimationEnd(Animator arg3) {
            ScoreStripView3.a(this.a, false);
        }

        public void onAnimationRepeat(Animator arg1) {
        }

        public void onAnimationStart(Animator arg3) {
            ScoreStripView3.a(this.a, true);
        }
    }
}

