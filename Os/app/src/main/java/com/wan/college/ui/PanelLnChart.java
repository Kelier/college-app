package com.wan.college.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;

import com.wan.college.R;
import com.wan.college.model.LineValue;

import java.util.List;

/**
 * Created by 万文杰 on 2017/2/21.
 */

public class PanelLnChart extends View {

    private int myScore=390;
    private int ViewHeight;
    private int ViewWidth;
    private Rect mBound;  //文字外框
    private float lineHeightPercent;
    private Paint PaintGrayLine = null;
    private Paint PaintText = null;
    private Paint PaintYline = null;
    private Paint PaintLineChart = null;
    private Paint PaintPieChart = null;
    final int[] colors = new int[]{
            R.color.red,
            R.color.white,
            R.color.green,
            R.color.black,
            R.color.blue,
    };

    //饼图演示用的比例,实际使用中，即为外部传入的比例参数
    final float arrPer[] = new float[]{20f,30f,10f,40f};

    //图演示用的比例,实际使用中，即为外部传入的比例参数
  //  private final int[] arrNum = {2,4,3,1}
    private List<LineValue>  lineValue ;//{467,488,474,450};     {2,4,3,1};
    private float avg_score;
    private float sum_score;
    private final int[] arrXtext = {2014,2015,2016,2017};

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
        //获取View的宽高
        ViewHeight=height-100;
        ViewWidth=width;
        Log.i("onMeasure","width:  "+ViewWidth+"   height:  "+ViewHeight);
        setMeasuredDimension(width, height);
    }

    public PanelLnChart(Context context){
        super(context);

    }

    public PanelLnChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        //解决4.1版本 以下canvas.drawTextOnPath()不显示问题
        this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        mBound = new Rect();

        //设置边缘特殊效果
        BlurMaskFilter PaintBGBlur = new BlurMaskFilter(
                1, BlurMaskFilter.Blur.INNER);

        PaintGrayLine= new Paint();
        PaintGrayLine.setColor(getResources().getColor(R.color.half_alpha_gray));
        PaintGrayLine.setAntiAlias(true);

        PaintText = new Paint();
        PaintText.setColor(getResources().getColor(R.color.gray_text_color));
        PaintText.setAntiAlias(true);
        PaintText.setTextSize(30);

        PaintYline = new Paint();
        PaintYline.setColor(getResources().getColor(R.color.half_orange_color));
        PaintYline.setStyle(Paint.Style.STROKE);
        PaintYline.setAntiAlias(true);
        PaintYline.setStrokeWidth(2);

        PaintLineChart= new Paint();
        PaintLineChart.setColor(getResources().getColor(R.color.orange_color));
        PaintLineChart.setStyle(Paint.Style.STROKE);
        PaintLineChart.setAntiAlias(true);
        PaintLineChart.setStrokeWidth(3);
        PaintLineChart.setMaskFilter(PaintBGBlur);

        PaintPieChart= new Paint();
        PaintPieChart.setColor(getResources().getColor(R.color.orange_color));
        PaintPieChart.setAntiAlias(true);
        PaintPieChart.setStrokeWidth(3);
        PaintPieChart.setStyle(Paint.Style.FILL);
        PaintPieChart.setMaskFilter(PaintBGBlur);
        PaintPieChart.setTextSize(28);
        PaintPieChart.setDither(true);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        PaintPieChart.setTypeface( font );
    }

    public void onDraw(Canvas canvas) {
        if (lineValue != null) {
            //画布背景
            canvas.drawColor(Color.WHITE);
            int i = 0;
            //计算中线的平均分//
            sum_score = 0;
            for (i = 0; i < lineValue.size(); i++) {
                sum_score += lineValue.get(i).getScore();
            }
            avg_score = sum_score / lineValue.size();
            lineHeightPercent = ViewHeight / (2 * avg_score);// 单位：高/分

            int startx = 100;
            int endx = startx + 20;
            int starty = ViewHeight;
            int endy = ViewHeight;
            int initX = startx;
            int initY = starty;

            int lnWidth = 10; //标识线宽度
            int lnSpace = 80; //标识间距
            ViewWidth = ViewWidth - 2 * startx;
            int lnSpaceX = (ViewWidth - 100) / lineValue.size();//多留出右侧一段距离
            int rectHeight = 10; //柱形框高度

            /////////////////////////
            //折线图
            ///////////////////////////

            // Y 轴  标识线和值
            int yCount = 5;
            for (i = 0; i < yCount; i++) {
                starty = initY - (i + 1) * lnSpace;
                endy = starty;
                if (i == 0) continue;
                //画横线
                canvas.drawLine(initX, starty + lnSpace, startx + ViewWidth, starty + lnSpace, PaintGrayLine);
                //Y轴文字
                //   canvas.drawText(Integer.toString(yCount[i]), initX - 30,endy + lnSpace, PaintText);
            }

            // Y 轴
            canvas.drawLine(startx, starty, initX, initY, PaintGrayLine);
            //画X轴线
            canvas.drawLine(initX, initY, startx + ViewWidth, initY, PaintGrayLine);

            //用于画折线
            Path p = new Path();
            p.moveTo(initX, initY - lineValue.get(0).getScore() * lineHeightPercent);
            //X 轴
            for (i = 0; i < lineValue.size(); i++) {
                startx = initX + (i + 1) * lnSpaceX;
                endx = startx;
                //画竖线
                canvas.drawLine(startx, initY, startx, initY - lineValue.get(i).getScore() * lineHeightPercent, PaintYline);
                //标识文字
                String text = String.valueOf(lineValue.get(i).getYear());
                PaintText.getTextBounds(text, 0, text.length(), mBound);
                canvas.drawText(text, startx - mBound.width() / 2, initY + mBound.height() + 15, PaintText);
                //画折线
                p.lineTo(startx, initY - lineValue.get(i).getScore() * lineHeightPercent);
            }
            //画折线
            canvas.drawPath(p, PaintLineChart);

            for (i = 0; i < lineValue.size(); i++) {   //计算X坐标
                startx = initX + (i + 1) * lnSpaceX;
                starty = (int) (initY - lineValue.get(i).getScore() * lineHeightPercent);
                //=========================设置文字宽高===========================//
                String text = String.valueOf(lineValue.get(i).getScore());
                PaintPieChart.getTextBounds(text, 0, text.length(), mBound);
                //====================画圆外圈与气泡圆角方框========================//
                PaintPieChart.setColor(getResources().getColor(R.color.orange_color));
                canvas.drawCircle(startx, starty, 15, PaintPieChart);
                if (i != lineValue.size() - 1) {
                    canvas.drawRoundRect(new RectF(startx - mBound.width() / 2 - 10, starty - 67, startx + mBound.width() / 2 + 10, starty - 30), 3, 3, PaintPieChart);
                    //=========================绘制气泡的三角==========================//
                    Path path = new Path();
                    path.moveTo(startx - 6, starty - 31);
                    path.lineTo(startx, starty - 23);
                    path.lineTo(startx + 6, starty - 31);
                    path.close(); // 使这些点构成封闭的多边形
                    canvas.drawPath(path, PaintPieChart);
                }
                //====================画圆内圈与气泡文字========================//
                PaintPieChart.setColor(Color.WHITE);
                canvas.drawCircle(startx, starty, 12.5f, PaintPieChart);
                canvas.drawText(text, startx - mBound.width() / 2, starty - 38, PaintPieChart);
            }

            PaintText.setStyle(Paint.Style.STROKE);
            starty = (int) (initY - myScore * lineHeightPercent);
            canvas.drawCircle(startx, starty, 15, PaintText);
            PaintText.setStyle(Paint.Style.FILL);
            canvas.drawCircle(startx, starty, 8, PaintText);
            String text = "你在这里";
            PaintText.getTextBounds(text, 0, text.length(), mBound);
            canvas.drawRoundRect(new RectF(startx - mBound.width() / 2 - 10, starty - mBound.height() - 40, startx + mBound.width() / 2 + 10, starty - mBound.height()), 3, 3, PaintText);
            //=========================绘制气泡的三角==========================//
            Path path = new Path();
            path.moveTo(startx - 6, starty - 31);
            path.lineTo(startx, starty - 23);
            path.lineTo(startx + 6, starty - 31);
            path.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(path, PaintText);
            PaintText.setColor(Color.WHITE);

            canvas.drawText(text, startx - mBound.width() / 2, starty - mBound.height() - 10, PaintText);
            //////////////////////////////////////////////////////////////////
            /////////////////////////
            //折线图与横向柱形图的混合
            ///////////////////////////
        /*
        startx = 120;// ScrWidth / 2 - 50;
        endx = startx + 20;

        starty = ScrHeight - ScrHeight / 3 ;
        endy = ScrHeight - ScrHeight / 3 ;

        initX = startx;
        initY = starty;

        // Y 轴  标识线和值
        for(i=0; i<5; i++)
        {
            starty =  initY - (i+1) * lnSpace;
            endy = starty;
            if(i == 0) continue;
            canvas.drawLine( startx - lnWidth  ,starty + lnSpace ,initX,endy + lnSpace, PaintText);
            canvas.drawText(Integer.toString(i), initX - 30,endy + lnSpace, PaintText);
        }

        // Y 轴
        canvas.drawLine( startx ,starty ,initX ,initY, PaintText);

        //用于画折线
        Path p2 = new Path();
        p2.moveTo(initX,initY);

        //X 轴
        for(i=0; i< arrNum.length; i++)
        {
            startx=  initX + (i+1) * lnSpace;
            endx = startx;
            //柱形
            canvas.drawRect(startx - rectHeight  ,initY,
                    startx + rectHeight  ,initY  -  arrNum[i] * lnSpace, arrPaintArc[3]);

            //标识文字
            canvas.drawText(Integer.toString(arrNum[i]), startx ,initY + lnSpace, arrPaintArc[3]);
            //画折线
            p2.lineTo(startx  ,initY  -  arrNum[i] * lnSpace);

            //  p2.lineTo(startx + rectHeight  + lnWidth ,initY  -  arrNum[i] * lnSpace);
        }
        canvas.drawLine( initX ,initY  ,ScrWidth - 10  ,initY, PaintText);
        //画折线
        canvas.drawPath(p2,arrPaintArc[0]);
        ///////////////////////////////
*/

        }
    }
    public void setLineData(List<LineValue> lineValue, int myScore){
        this.lineValue=lineValue;
        this.myScore=myScore;
        postInvalidate();
    }
}
