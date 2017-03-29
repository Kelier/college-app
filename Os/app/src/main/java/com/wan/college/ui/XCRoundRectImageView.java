package com.wan.college.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 万文杰 on 2017/1/7.
 */

public class XCRoundRectImageView extends ImageView{

    private Paint paint;
    boolean touchEffect = true;
    public final float[] BG_PRESSED = new float[] { 1, 0, 0, 0, -50, 0, 1,
            0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };
    public final float[] BG_NOT_PRESSED = new float[] { 1, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

    public XCRoundRectImageView(Context context) {
        this(context,null);
    }

    public XCRoundRectImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XCRoundRectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint  = new Paint();
    }

    @Override
    public void setPressed(boolean pressed) {
        updateView(pressed);
        super.setPressed(pressed);
    }
    /**
     * 根据是否按下去来刷新bg和src
     * created by minghao.zl at 2014-09-18
     * @param pressed
     */
    private void updateView(boolean pressed){
        //如果没有点击效果
        if( !touchEffect ){
            return;
        }//end if
        if( pressed ){//点击
            /**
             * 通过设置滤镜来改变图片亮度@minghao
             */
            this.setDrawingCacheEnabled(true);
            this.setColorFilter( new ColorMatrixColorFilter(BG_PRESSED) ) ;
            this.getBackground().setColorFilter( new ColorMatrixColorFilter(BG_PRESSED) );
        }else{//未点击
            this.setColorFilter( new ColorMatrixColorFilter(BG_NOT_PRESSED) ) ;
            this.getBackground().setColorFilter(
                    new ColorMatrixColorFilter(BG_NOT_PRESSED));
        }
    }


    /**
     * 绘制圆角矩形图片
     * @author caizhiming
     */
    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getRoundBitmap(bitmap, 100);
            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            paint.reset();
            canvas.drawBitmap(b, rectSrc, rectDest, paint);

        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆角矩形图片方法
     * @param bitmap
     * @param roundPx,一般设置成14
     * @return Bitmap
     * @author caizhiming
     */
    private Bitmap getRoundBitmap(Bitmap bitmap, int roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int x = bitmap.getWidth();

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;


    }
}
