package com.wan.college.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wan.college.R;

import java.text.DecimalFormat;

import static android.R.attr.radius;
/**
 * Created by 万文杰 on 2017/2/17.
 */

/**
 * 带动画的自定义饼形图
 * 作者：cg
 * 时间：2016/9/6 0006 下午 2:48
 */
public class AnimationCirPre extends View {

    private float iBNum = 12;                                     //流入大单数
    private int iBColor = getResources().getColor(R.color.risk_low_bg);             //流入大单颜色
    private float iMNum = 13;                                     //流入中单数
    private int iMColor = getResources().getColor(R.color.risk_high_bg);             //流入中单颜色
    private float iSNum = 12;                                     //流入小单数
    private int iSColor =  getResources().getColor(R.color.risk_mid_bg);             //流入小单颜色
    private float oBNum = 0;                                     //流出大单数
    private int oBColor = Color.parseColor("#25D98E");             //流出大单颜色
    private float oMNum = 0;                                     //流出中单数
    private int oMColor = Color.parseColor("#2EBA80");             //流出中单颜色
    private float oSNum = 0;                                     //流出小单数
    private int oSColor = Color.parseColor("#1D8057");             //流出小单颜色

    private int StrokeWidth =  140;                                //弧的宽度
    private int TextSize = 50;                                     //文字大小

    private boolean isAnimation = true;                           //是否使用动画效果
    private int AnimationSpeed = 6;                                //动画的速度
    private int circleCenterColor = Color.parseColor("#ffffff");   //圆心颜色，默认是白色



    private Paint mPaint;                                          //画笔
    private RectF mRectf;                                          //外圈圆的方形
    private Rect mBound;                                           //文字外框

    private String txtNumPre;                                      //显示所占百分比
    float[] point;                                                 //记录弧度最外边中心点的坐标

    private int adjustDist = 20;                                   //在弧度上面的线，为了调整其最好的显示位置设置一个调整的距离数

    private float txtBeginX = 0;                                   //线上文字开始的位置的X坐标值
    private float txtBeginY = 0;                                   //线上文字开始的位置的y坐标值
    private float txtEndX =0;                                      //线上文字结束的位置的x坐标值


    //计算总数
    private float sum;

    //计算六个值的百分比
    private float oBpre;
    private float oMpre;
    private float oSpre;
    private float iBpre;
    private float iMpre;
    private float iSpre;

    //计算六个值所占圆的弧度数
    private float oBArc;
    private float oMArc;
    private float oSArc;
    private float iBArc;
    private float iMArc;
    private float iSArc;

    //六个值弧度的初始值
    private float mOBArc = 1;
    private float mOMArc = 1;
    private float mOSArc = 1;
    private float mISArc = 1;
    private float mIMArc = 1;
    private float mIBArc = 1;


    public AnimationCirPre(Context context) {
        this(context,null);
    }

    public AnimationCirPre(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimationCirPre(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MyPre, defStyleAttr, 0);
        int n = array.getIndexCount();
        for(int i=0;i<n;i++)
        {
            int arr = array.getIndex(i);
            switch (arr)
            {
                case R.styleable.MyPre_iBNum:
                    iBNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_iBColor:
                    iBColor = array.getColor(arr,Color.parseColor("#EE755C"));
                    break;
                case R.styleable.MyPre_iMNum:
                    iMNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_iMColor:
                    iMColor = array.getColor(arr,Color.parseColor("#D7583E"));
                    break;
                case R.styleable.MyPre_iSNum:
                    iSNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_iSColor:
                    iSColor = array.getColor(arr,Color.parseColor("#C73F23"));
                    break;
                case R.styleable.MyPre_oBNum:
                    oBNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_oBColor:
                    oBColor = array.getColor(arr,Color.parseColor("#25D98E"));
                    break;
                case R.styleable.MyPre_oMNum:
                    oMNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_oMColor:
                    oMColor = array.getColor(arr,Color.parseColor("#2EBA80"));
                    break;
                case R.styleable.MyPre_oSNum:
                    oSNum = array.getFloat(arr,0);
                    break;
                case R.styleable.MyPre_oSColor:
                    oSColor = array.getColor(arr,Color.parseColor("#1D8057"));
                    break;
                case R.styleable.MyPre_StrokeWidth:
                    StrokeWidth = array.getInt(arr, 200);
                    break;
                case R.styleable.MyPre_textSizes:
                    TextSize = array.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyPre_isAnimation:
                    isAnimation = array.getBoolean(arr,false);
                    break;
                case R.styleable.MyPre_AnimationSpeed:
                    AnimationSpeed = array.getInt(arr,2);
                    break;
                case R.styleable.MyPre_circleCenterColor:
                    circleCenterColor = array.getColor(arr,Color.parseColor("#ffffff"));
                    break;
            }
        }

        array.recycle();

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);


        mRectf = new RectF();
        mBound = new Rect();


        //计算总数
        sum = oBNum + oMNum + oSNum + iBNum + iMNum + iSNum;

        //计算六个值的百分比
        oBpre = oBNum / sum;
        oMpre = oMNum / sum;
        oSpre = oSNum / sum;
        iBpre = iBNum / sum;
        iMpre = iMNum / sum;
        iSpre = iSNum / sum;

        //计算六个值所占圆的弧度数
        oBArc = oBpre * 360;
        oMArc = oMpre * 360;
        oSArc = oSpre * 360;
        iBArc = iBpre * 360;
        iMArc = iMpre * 360;
        iSArc = iSpre * 360;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width ;
        int height ;

        if(widthMode==MeasureSpec.EXACTLY)
        {
            width = widthSize;
        }else
        {
            width = getPaddingLeft() + getWidth() + getPaddingRight();
        }

        if(heightMode==MeasureSpec.EXACTLY)
        {
            height = heightSize;
        }else
        {
            height = getPaddingTop() + getHeight() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);


        float radius;        //定义半径
        float left;
        float top;
        float right;
        float bottom;

        //圆环的半径取控件的高度一半，减去60,是为了给上下各留出30显示文字的空间
        radius = getHeight()/2 - 60;

        left = getWidth()/2 - radius  ;
        top = getHeight()/2 -radius  ;
        right = getWidth()/2 + radius;
        bottom = getHeight()/2 +  radius;

        mRectf.set(left, top, right, bottom);



        if(isAnimation) {
            /**
             * 画出六个扇面
             */
            mPaint.setColor(oBColor);


            //第一个
            canvas.drawArc(mRectf, 269, mOBArc, true, mPaint);
            mOBArc = mOBArc + AnimationSpeed;
            if (mOBArc <= oBArc) {
                postInvalidate();
            }

            //第二个
            if (mOBArc >= oBArc) {
                DrawOB(canvas, radius, oBArc, oBpre);
                mPaint.setColor(oMColor);
                canvas.drawArc(mRectf, 270 + oBArc, mOMArc, true, mPaint);
                mOMArc = mOMArc + AnimationSpeed;

                if (mOMArc <= oMArc) {
                    postInvalidate();
                }
            }

            //第三个
            if (mOMArc >= oMArc) {
                DrawOM(canvas, radius, oBArc, oMArc, oMpre);
                mPaint.setColor(oSColor);
                canvas.drawArc(mRectf, 270 + oBArc + oMArc, mOSArc, true, mPaint);
                mOSArc = mOSArc + AnimationSpeed;

                if (mOSArc <= oSArc) {
                    postInvalidate();
                }
            }

            //第四个
            if (mOSArc >= oSArc) {
                DrawOS(canvas, radius, oBArc + oMArc, oSArc, oSpre);
                mPaint.setColor(iSColor);
                canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc, mISArc, true, mPaint);
                mISArc = mISArc + AnimationSpeed;

                if (mISArc <= iSArc) {
                    postInvalidate();
                }
            }

            //第五个
            if (mISArc >= iSArc) {
                DrawIS(canvas, radius, oBArc + oMArc + oSArc, iSArc, iSpre);
                mPaint.setColor(iMColor);
                canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc + iSArc, mIMArc, true, mPaint);
                mIMArc = mIMArc + AnimationSpeed;

                if (mIMArc <= iMArc) {
                    postInvalidate();
                }
            }

            //第六个
            if (mIMArc >= iMArc ) {
                DrawIM(canvas, radius, oBArc + oMArc + oSArc + iSArc, iMArc, iMpre);
                mPaint.setColor(iBColor);
                canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc + iSArc + iMArc, mIBArc, true, mPaint);
                mIBArc = mIBArc + AnimationSpeed;

                if (mIBArc <= iBArc + 1) {
                    postInvalidate();
                }
            }

            if (mIBArc >= iBArc) {
                DrawIB(canvas, radius, oBArc + oMArc + oSArc + iSArc + iMArc, iBArc, iBpre);
            }


        }else {

            /**
             * 画出六个扇面
             */
            mPaint.setColor(oBColor);
            canvas.drawArc(mRectf, 270, oBArc, true, mPaint);

            mPaint.setColor(oMColor);
            canvas.drawArc(mRectf, 270 + oBArc, oMArc, true, mPaint);

            mPaint.setColor(oSColor);
            canvas.drawArc(mRectf, 270 + oBArc + oMArc, oSArc, true, mPaint);

            mPaint.setColor(iSColor);
            canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc, iSArc, true, mPaint);

            mPaint.setColor(iMColor);
            canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc + iSArc, iMArc, true, mPaint);

            mPaint.setColor(iBColor);
            canvas.drawArc(mRectf, 270 + oBArc + oMArc + oSArc + iSArc + iMArc, iBArc, true, mPaint);


            DrawOB(canvas, radius, oBArc, oBpre);

            DrawOM(canvas, radius, oBArc, oMArc, oMpre);

            DrawOS(canvas, radius, oBArc + oMArc, oSArc, oSpre);

            DrawIS(canvas, radius, oBArc + oMArc + oSArc, iSArc, iSpre);

            DrawIM(canvas, radius, oBArc + oMArc + oSArc + iSArc, iMArc, iMpre);

            DrawIB(canvas, radius, oBArc + oMArc + oSArc + iSArc + iMArc, iBArc, iBpre);
        }


        /**
         * 画出中间的空白区域
         */
        mPaint.setColor(circleCenterColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius - StrokeWidth, mPaint);
    }


    /**
     * 流出大单
     * @param canvas　　　　　画板
     * @param radius　　　　　中心半径
     * @param oBArc　　　　　 流出大单弧度
     * @param oBpre　　　　　 流出大单百分比
     */
    public void DrawOB(Canvas canvas,float radius,float oBArc,float oBpre)
    {

        /**
         * 流出大单
         */
        if(oBNum>0)
        {
            //设置显示的大单数量，在横线上显示的文字
            mPaint.setColor(oBColor);
            mPaint.setTextSize(TextSize);
            String txt = "大单  " + oBNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            //计算弧度的中心点
            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - oBArc / 2)) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - oBArc / 2)) / 180));


            /**
             * 右侧第一个弧度，无论为何值，它都不会在左半球，出现折线，所以它有四种情况
             * 1,弧度小于160时，它的中心线在90度以上，只所以没有取180，是因为中间要留出20度，给直线，所以它有一个向上的折线。
             * 2,弧度小于等于200时，因为是一个else语句，所以它的中心点范围是80--100,是一条直线。
             * 3,弧度小于340时，因为是一个else语句，所以它的中心点范围是 100-170,是一条向下的折线。
             * 4,弧度小于360时，因为是一个else语句，所以它的中心点范围是 170-180,它是一条向下垂直的线
             */
            //画弧上的线
            drawLine(canvas, oBArc / 2, radius, 1);

            canvas.drawText(txt, txtEndX - mBound.width() - adjustDist / 2, txtBeginY - adjustDist / 2, mPaint);

            //画线后的椭圆
            txtNumPre = getProValText(oBpre);
            mPaint.getTextBounds(txtNumPre, 0, txtNumPre.length(), mBound);
            mPaint.setStyle(Paint.Style.FILL);
            /*=====  此写法兼容api 19 =====*/
            canvas.drawRoundRect(new RectF(txtEndX + 10, txtBeginY - mBound.height() / 2 - adjustDist/2, txtEndX + adjustDist/2 + mBound.width() + 30,
                    txtBeginY - mBound.height() / 2 + mBound.height() + adjustDist/2), 15, 15, mPaint);
            /* 这个必须API 21以上才能执行
            canvas.drawRoundRect(txtEndX + 10,
                    txtBeginY - mBound.height() / 2 - adjustDist/2,
                    txtEndX + adjustDist/2 + mBound.width() + 30,
                    txtBeginY - mBound.height() / 2 + mBound.height() + adjustDist/2,
                    15, 15, mPaint);
            */

            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawText(txtNumPre, txtEndX + adjustDist / 2 + adjustDist / 2, txtBeginY + mBound.height() / 2, mPaint);
        }
    }

    /**
     * 流出中单
     * @param canvas　　　　　画板
     * @param radius　　　　　中心半径
     * @param oBArc　　　　　 流出大单弧度
     * @param oMArc         流出中单弧度
     * @param oMpre　　　　　 流出中单百分比
     */
    public void DrawOM(Canvas canvas,float radius,float oBArc,float oMArc,float oMpre)
    {
        if(oMNum>0)
        {
            mPaint.setColor(oMColor);
            mPaint.setTextSize(TextSize);
            String txt = "中单  " + oMNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - (oMArc / 2 + oBArc))) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - (oMArc/2 + oBArc)))/180));


            /**
             * 右侧第二个弧度，所以它有七种情况，它的情况与第一个弧度有关，所以它的弧度值要与第一个弧度进行相加来处理
             * 　　第一个弧度加上本身弧度的一半，可以得到中心点的位置，根据中心的位置进行判断其位置
             * 1,弧度中心点小于80时，它的中心线在90度以上，只所以没有取180，是因为中间要留出20度，给直线，所以它有一个向上的折线。
             * 2,弧度中心点小于等于100时，因为是一个else语句，所以它的中心点范围是80--100,是一条直线。
             * 3,弧度中心点小于170时，因为是一个else语句，所以它的中心点范围是 100-170,是一条向下的折线。
             * 4,弧度中心点小于180时，因为是一个else语句，所以它的中心点范围是 170-180,它是一条向下垂直的线
             * 5,弧度中心点小于260时，因为是一个else语句，所以它会在左边生成一个向下拆的线
             * 6,弧度中心点小于280时，因为是一个else语句，它会在左边中间的位置生下一条直线
             * 7,弧度中心点于小360时，因为是一个else语句，它会在左边上部，生成一个向上有折线的线
             */

            float oMCenterArc = oBArc + oMArc/2;


            //画弧上的线
            drawLine(canvas, oMCenterArc, radius, 1);

            //画出线上数据与后面椭圆型的百分比
            drawRect(canvas, txt, oMpre, oMCenterArc,1);
        }
    }

    /**
     * 流出小单
     * @param canvas　　　　　画板
     * @param radius　　　　　中心半径
     * @param oBMArc　　　　　流出大单和中单弧度和
     * @param oSArc　　　　　 流出小单弧度
     * @param oSpre         流出小单百分比
     */
    public void DrawOS(Canvas canvas,float radius,float oBMArc,float oSArc,float oSpre)
    {
        if(oSNum>0)
        {
            mPaint.setColor(oSColor);
            mPaint.setTextSize(TextSize);
            String txt = "小单  " + oSNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - (oSArc / 2 + oBMArc))) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - (oSArc/2 + oBMArc)))/180));


            float oMCenterArc = oBMArc + oSArc/2;

            //画弧上的线
            drawLine(canvas,oMCenterArc,radius,1);

            drawRect(canvas,txt,oSpre,oMCenterArc,1);
        }
    }

    /**
     * 流入小单
     * @param canvas　　　　　画板
     * @param radius　　　　　中心半径
     * @param oBMSArc       流出大单,中单，小单弧度和
     * @param iSArc         流入小单弧度
     * @param iSpre         流入小单百分比
     */
    public void DrawIS(Canvas canvas,float radius,float oBMSArc,float iSArc,float iSpre)
    {
        if(iSNum>0)
        {
            mPaint.setColor(iSColor);
            mPaint.setTextSize(TextSize);
            String txt = "小单  " + iSNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - (iSArc / 2 + oBMSArc))) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - (iSArc / 2 + oBMSArc))) / 180));


            float oMCenterArc = oBMSArc + iSArc/2;

            //画弧上的线
            drawLine(canvas,oMCenterArc,radius,2);

            //画出线上数据与后面椭圆型的百分比
            drawRect(canvas,txt,iSpre,oMCenterArc,2);
        }
    }

    /**
     * 流入中单
     * @param canvas　　　　　画板
     * @param radius　　　　　中心半径　　
     * @param oBMSiMArc     流出大单，中单，小单，流入小单弧度和
     * @param iMArc         流入中单弧度
     * @param iMpre         流入中单百分比
     */
    public void DrawIM(Canvas canvas,float radius,float oBMSiMArc,float iMArc,float iMpre)
    {
        if(iMNum>0)
        {
            mPaint.setColor(iSColor);
            mPaint.setTextSize(TextSize);
            String txt = "中单  " + iMNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - (iMArc / 2 + oBMSiMArc))) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - (iMArc/2 + oBMSiMArc)))/180));


            float oMCenterArc = oBMSiMArc + iMArc/2;

            //画弧上的线
            drawLine(canvas, oMCenterArc, radius,2);

            //画出线上数据与后面椭圆型的百分比
            drawRect(canvas,txt,iMpre,oMCenterArc,2);
        }
    }

    /**
     * 流入大单
     * @param canvas　　　　　画板
     * @param radius        中心半径
     * @param oBMSiSMArc    流出大单，中单，小单，流入小单，中单弧度和
     * @param iBArc         流入大单弧度
     * @param iBpre         流入大单百分比
     */
    public void DrawIB(Canvas canvas,float radius,float oBMSiSMArc,float iBArc,float iBpre)
    {
        if(iMNum>0)
        {
            mPaint.setColor(iSColor);
            mPaint.setTextSize(TextSize);
            String txt = "大单  " + iBNum;
            mPaint.getTextBounds(txt, 0, txt.length(), mBound);


            point = new float[2];
            point[0] = (float)(radius * Math.cos(Math.PI * (Math.abs(90 - (iBArc / 2 + oBMSiSMArc))) / 180));
            point[1] = (float)(radius * Math.sin(Math.PI * (Math.abs(90 - (iBArc/2 + oBMSiSMArc)))/180));

            float oMCenterArc = oBMSiSMArc + iBArc/2;

            //画弧上的线
            drawLine(canvas,oMCenterArc,radius,2);

            //画出线上数据与后面椭圆型的百分比
            drawRect(canvas,txt,iBpre,oMCenterArc,2);

        }
    }

    /**
     * 根据弧度值，来画出弧度中心点伸出的线
     * @param canvas    画板
     * @param sumArc    弧度之和
     * @param radius    中心半径
     * @param flag      标识位，1:左侧流出单　2:右侧注入单
     */
    public void drawLine(Canvas canvas,float sumArc,float radius,int flag)
    {
        if(sumArc <= 80)
        {
            draw80(canvas);

        }else if(sumArc <=100) {
            draw100(canvas,sumArc);
        }else if(sumArc <=170){

            draw170(canvas);

        }else if(sumArc <=190)
        {
            draw180(canvas,radius,flag);

        }else if(sumArc <=260) {
            draw260(canvas);
        }
        else if(sumArc <=290) {
            draw280(canvas);
        }else
        {
            draw360(canvas);
        }
    }



    /**
     * 画出弧度中心点小于80弧度的上面的线与值
     * 这里的弧度中心点小于80,是它前面的所有弧度加上它本身的总度数的一半，如果是第一个弧，则为其本身的一半，此处画出的是左侧80度以下，带有向上折线的两根线
     * @param canvas   画板
     */
    public void draw80(Canvas canvas)
    {
        txtBeginX = getWidth()/2 + point[0] + 40;
        txtBeginY = getHeight()/2 - point[1] - 20;

        canvas.drawLine(getWidth()/2 + point[0],
                getHeight()/2 - point[1],
                txtBeginX,
                txtBeginY,mPaint);

        txtEndX = txtBeginX + mBound.width() + 20;

        canvas.drawLine(txtBeginX,txtBeginY,
                txtEndX,txtBeginY,mPaint);
    }

    /**
     * 画出弧度中心点大于等于80小于等于100弧度上面的线与值
     * 这里的弧度中心点大于等于８0小于等于100,是它前面的所有弧度加上它本身的总度数的一半，如果是第一个弧，则为其本身的一半,此处画出的是左侧80-100度之间，一根直线
     * @param canvas    画板
     * @param sumArc    当前总弧度，当弧度大于90度时，其Y轴起始点应该向下移
     */
    public void draw100(Canvas canvas,float sumArc)
    {
        txtBeginX = getWidth() / 2 + point[0];
        if(sumArc>90)
        {
            txtBeginY = getHeight() / 2 + point[1];
        }else {
            txtBeginY = getHeight() / 2 - point[1];
        }

        txtEndX = txtBeginX + mBound.width() + adjustDist;

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }

    /**
     * 画出弧度中心点大于100小于170弧度上面的线与值
     * 这里的弧度中心点大于100小于170,是它前面的所有弧度加上它本身的总度数的一半，如果是第一个弧，则为其本身的一半,此处画出的是左侧100-170度之间，带有向下折线的两根线
     * @param canvas　　　画板
     */
    public void draw170(Canvas canvas)
    {
        txtBeginX = getWidth()/2 + point[0] + adjustDist*2;
        txtBeginY = getHeight()/2 + point[1] + adjustDist;

        canvas.drawLine(getWidth()/2 + point[0],
                getHeight()/2 + point[1],
                txtBeginX,
                txtBeginY, mPaint);

        txtEndX = txtBeginX + mBound.width() + adjustDist;

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }


    /**
     * 画出弧度中心点大于170小于190弧度上面的线与值
     * 这里的弧度中心点大于170小于190,是它前面的所有弧度加上它本身的总度数的一半，如果是第一个弧，则为其本身的一半,此处画出的是左侧170-190度之间，带有垂直向下折线的两根线
     * @param canvas　　　画板
     * @param radius　　　半径
     * @param flag       标识位 1:右侧流出量   2:左侧流入量
     */
    public void draw180(Canvas canvas,float radius,int flag)
    {
        txtBeginX = getWidth() / 2 + point[0];
        txtBeginY = getHeight() / 2 + point[1] + adjustDist;

        canvas.drawLine(getWidth() / 2 + point[0],
                getHeight() / 2 + point[1],
                txtBeginX,
                txtBeginY, mPaint);

        //此处加一个判断，主要是当在最下面画线的时候，流出向右画线，流入向左画线
        if(flag==1) {
            txtEndX = txtBeginX + radius / 2 + mBound.width() + adjustDist;
        }else{
            txtEndX = txtBeginX - radius / 2 - mBound.width() - adjustDist;
        }

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }

    /**
     * 画出弧度中心点大于190,小于260弧度上面的线与值
     * 这里的弧度中心点大于190小于260,是它前面的所有弧度加上它本身的总度数的一半，此处画出的是左侧190-260度之间，右边带有垂直向下折线的两根线
     * @param canvas　　　画板
     */
    public void draw260(Canvas canvas)
    {
        txtBeginX = getWidth() / 2 + point[0] - 40;
        txtBeginY = getHeight() / 2 + point[1] + 20;

        canvas.drawLine(getWidth() / 2 + point[0],
                getHeight() / 2 + point[1],
                txtBeginX,
                txtBeginY, mPaint);

        txtEndX = txtBeginX - mBound.width() - 20;

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }

    /**
     * 画出弧度中心点大于260,小于280弧度上面的线与值
     * 这里的弧度中心点大于260小于280,是它前面的所有弧度加上它本身的总度数的一半，此处画出的是左侧260-280度之间，右边的一根直线
     * @param canvas　　　画板
     */
    public void draw280(Canvas canvas)
    {
        txtBeginX = getWidth() / 2 + point[0];
        txtBeginY = getHeight() / 2 + point[1];

        txtEndX = txtBeginX - mBound.width() - 20;

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }

    /**
     * 画出弧度中心点大于280,小于360弧度上面的线与值
     * 这里的弧度中心点大于280小于360,是它前面的所有弧度加上它本身的总度数的一半，此处画出的是左侧280-360度之间，右边向上折的两根直线
     * @param canvas     画板
     */
    public void draw360(Canvas canvas)
    {
        txtBeginX = getWidth()/2 + point[0] - 40;
        txtBeginY = getHeight()/2 + point[1] - 20;

        canvas.drawLine(getWidth() / 2 + point[0],
                getHeight() / 2 + point[1],
                txtBeginX,
                txtBeginY, mPaint);

        txtEndX = txtBeginX - mBound.width() - 20;

        canvas.drawLine(txtBeginX, txtBeginY,
                txtEndX, txtBeginY, mPaint);
    }


    /**
     * 画线上的文字与后面椭圆型的百分比
     * @param canvas         画板
     * @param txt            线上文字
     * @param iBpre          百分比
     * @param oMCenterArc    弧度数
     * @param flag           标识位 1: 右边流出量　 2: 左边流入量
     */
    public void drawRect(Canvas canvas,String txt,float iBpre,float oMCenterArc,int flag)
    {
        if(oMCenterArc >=80 && oMCenterArc <=100)
        {

            draw100Rect(canvas,txt,iBpre);
        }
        else if(oMCenterArc<=190) {

            draw190Rect(canvas,txt,iBpre,flag,oMCenterArc);
        }else
        {

            drawOtherRect(canvas,txt,iBpre);
        }
    }


    /**
     * 处了右侧第一个弧度外。当前弧度的一半加上之前弧度的和，在80-100之间，画出线上文字与之后的百分比
     * @param canvas    画板
     * @param txt　　　  线上文字
     * @param oMpre     百分比
     */
    public void draw100Rect(Canvas canvas,String txt,float oMpre)
    {
        canvas.drawText(txt, txtBeginX + 10, txtBeginY - 10, mPaint);

        //画线后的椭圆
        txtNumPre = getProValText(oMpre);
        mPaint.getTextBounds(txtNumPre, 0, txtNumPre.length(), mBound);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(txtEndX + 10,
                txtBeginY - mBound.height() / 2 - 10,
                txtEndX + 10 + mBound.width() + 30,
                txtBeginY - mBound.height() / 2 + mBound.height() + 10), 15, 15, mPaint);
        /*
        canvas.drawRoundRect(txtEndX + 10,
                txtBeginY - mBound.height() / 2 - 10,
                txtEndX + 10 + mBound.width() + 30,
                txtBeginY - mBound.height() / 2 + mBound.height() + 10,
                15, 15, mPaint);*/
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawText(txtNumPre, txtEndX + 10 + 10, txtBeginY + mBound.height() / 2, mPaint);
    }

    /**
     * 处了右侧第一个弧度外。当前弧度的一半加上之前弧度的和，在小于等于190，并且不包括80-100的数据，画出线上文字与之后的百分比
     * @param canvas　　 画板
     * @param txt       线上文字
     * @param oMpre     百分比
     * @param flag      标识位  1:右侧流出  2:左侧流入
     * @param oMArc　　　弧度之和
     */
    public void draw190Rect(Canvas canvas,String txt,float oMpre,int flag,float oMArc)
    {
        //左侧的流入三个弧度，如果在正下方，则特殊处理
        if(flag==2 && (oMArc>=170 && oMArc <=190))
        {
            canvas.drawText(txt, txtEndX + 20, txtBeginY - 10, mPaint);
            //画线后的椭圆
            txtNumPre = getProValText(oMpre);
            mPaint.getTextBounds(txtNumPre, 0, txtNumPre.length(), mBound);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(new RectF(txtEndX - 10 - 10 - mBound.width() - 10 ,
                    txtBeginY - mBound.height() / 2 - 10,
                    txtEndX - 10,
                    txtBeginY - mBound.height() / 2 + mBound.height() + 10), 15, 15, mPaint);
            /*
            canvas.drawRoundRect(txtEndX - 10 - 10 - mBound.width() - 10 ,
                    txtBeginY - mBound.height() / 2 - 10,
                    txtEndX - 10,
                    txtBeginY - mBound.height() / 2 + mBound.height() + 10,
                    15, 15, mPaint);*/
            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawText(txtNumPre, txtEndX - 10 - 10 - mBound.width(), txtBeginY + mBound.height() / 2, mPaint);
        }else {
            canvas.drawText(txt, txtEndX - mBound.width() - 20, txtBeginY - 10, mPaint);
            //画线后的椭圆
            txtNumPre = getProValText(oMpre);
            mPaint.getTextBounds(txtNumPre, 0, txtNumPre.length(), mBound);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(new RectF(txtEndX + 10,
                    txtBeginY - mBound.height() / 2 - 10,
                    txtEndX + 20 + mBound.width() + 20,
                    txtBeginY - mBound.height() / 2 + mBound.height() + 10), 15, 15, mPaint);
            /*
            canvas.drawRoundRect(txtEndX + 10,
                    txtBeginY - mBound.height() / 2 - 10,
                    txtEndX + 20 + mBound.width() + 20,
                    txtBeginY - mBound.height() / 2 + mBound.height() + 10,
                    15, 15, mPaint);*/
            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawText(txtNumPre, txtEndX + 20, txtBeginY + mBound.height() / 2, mPaint);
        }
    }

    /**
     * 处了右侧第一个弧度外。当前弧度的一半加上之前弧度的和，在大于190，画出线上文字与之后的百分比
     * @param canvas    画板
     * @param txt       线上文字
     * @param oMpre     百分比
     */
    public void drawOtherRect(Canvas canvas,String txt,float oMpre)
    {
        canvas.drawText(txt, txtEndX + 10, txtBeginY - 10, mPaint);
        //画线后的椭圆
        txtNumPre = getProValText(oMpre);
        mPaint.getTextBounds(txtNumPre, 0, txtNumPre.length(), mBound);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(txtEndX - 10 - mBound.width() - 30, txtBeginY - mBound.height() / 2 - 10, txtEndX - 10,
                txtBeginY - mBound.height() / 2 + mBound.height() + 10), 15, 15, mPaint);
        /*
        canvas.drawRoundRect(txtEndX - 10 - mBound.width() - 30,
                txtBeginY - mBound.height() / 2 - 10,
                txtEndX - 10,
                txtBeginY - mBound.height() / 2 + mBound.height() + 10,
                15, 15, mPaint);*/
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawText(txtNumPre, txtEndX - 10 - mBound.width() - 30 + 10, txtBeginY + mBound.height() / 2, mPaint);
    }




    /**
     * 格式化显示的百分比
     * @param proValue   传入的数值一般是0.1234这种格式的
     * @return           返回一个小数点后一位的百分比字符串
     */
    private String getProValText(float proValue)
    {
        DecimalFormat format = new DecimalFormat("#0.0");
        return format.format(proValue * 100) + "%";
    }


    public void setNum(float oBNum,float oMNum, float oSNum,float iBNum,float iMNum,float iSNum)
    {
        this.oBNum = oBNum;
        this.oMNum = oMNum;
        this.oSNum = oSNum;
        this.iBNum = iBNum;
        this.iMNum = iMNum;
        this.iSNum = iSNum;

        //计算总数
        sum = oBNum + oMNum + oSNum + iBNum + iMNum + iSNum;

        //计算六个值的百分比
        oBpre = oBNum / sum;
        oMpre = oMNum / sum;
        oSpre = oSNum / sum;
        iBpre = iBNum / sum;
        iMpre = iMNum / sum;
        iSpre = iSNum / sum;

        //计算六个值所占圆的弧度数
        oBArc = oBpre * 360;
        oMArc = oMpre * 360;
        oSArc = oSpre * 360;
        iBArc = iBpre * 360;
        iMArc = iMpre * 360;
        iSArc = iSpre * 360;

        mOBArc = 1;
        mOMArc = 1;
        mOSArc = 1;
        mISArc = 1;
        mIMArc = 1;
        mIBArc = 1;

        postInvalidate();
    }
}