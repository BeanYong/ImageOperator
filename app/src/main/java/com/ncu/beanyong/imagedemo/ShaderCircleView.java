package com.ncu.beanyong.imagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用Shader实现将矩形图片处理为圆形,可缩放
 * Created by BeanYong on 2015/8/17.
 */
public class ShaderCircleView extends View {
    private Bitmap mBitmap;
    private int mBitmapWidth, mBitmapHeight, mRadius;
    private Paint mPaint;

    public ShaderCircleView(Context context) {
        this(context, null);
    }

    public ShaderCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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
        float scaleX = (float) mRadius * 2 / mBitmapWidth;//水平方向的缩放比例
        float scaleY = (float) mRadius * 2 / mBitmapHeight;//竖直方向的缩放比例
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        Bitmap scaleBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmapWidth, mBitmapHeight, matrix, true);
        //绘制圆形
        Shader shader = new BitmapShader(scaleBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }
}
