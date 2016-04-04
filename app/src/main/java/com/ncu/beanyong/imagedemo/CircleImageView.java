package com.ncu.beanyong.imagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用xFermode实现将矩形图片处理为圆形图片，可缩放
 * Created by BeanYong on 2015/8/17.
 */
public class CircleImageView extends View {
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mRadius;
    private int mBitmapWidth;
    private int mBitmapHeight;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);//禁用硬件加速（由于Xfermode中很多操作不支持硬件）
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRadius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;
        //缩放图片
        float scaleX = (float) getMeasuredWidth() / mBitmapWidth;
        float scaleY = (float) getMeasuredHeight() / mBitmapHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        Bitmap scaleBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmapWidth, mBitmapHeight, matrix, true);
        //Dst
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //Src
        canvas.drawBitmap(scaleBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
    }
}
