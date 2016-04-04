package com.ncu.beanyong.imagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BeanYong on 2015/8/18.
 */
public class MirrorEffectView extends View {

    private Bitmap mBitmap;
    private int mBitmapWidth, mBitmapHeight;

    public MirrorEffectView(Context context) {
        this(context, null);
    }

    public MirrorEffectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MirrorEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //获取镜像图片
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1);//图片反转（轴对称）
        Bitmap mirrorBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmapWidth, mBitmapHeight, matrix, true);
        //设置渲染梯度
        LinearGradient gradient = new LinearGradient(0f, mBitmapHeight, 0, 1.4f * mBitmapHeight,
                0xdd000000, 0x44000000, Shader.TileMode.CLAMP);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(gradient);
        canvas.drawBitmap(mirrorBitmap, null, new RectF(0, mBitmapHeight, mBitmapWidth, mBitmapHeight * 2), null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, mBitmapHeight, mBitmapWidth, 2 * mBitmapHeight, paint);
        paint.setXfermode(null);
    }
}
