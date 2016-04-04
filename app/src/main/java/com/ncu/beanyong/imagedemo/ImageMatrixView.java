package com.ncu.beanyong.imagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BeanYong on 2015/8/16.
 */
public class ImageMatrixView extends View {
    private Bitmap mBitmap;
    private float[] mMatrix;

    public ImageMatrixView(Context context) {
        this(context, null);
    }

    public ImageMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setMatrix(null);
    }

    public void setMatrix(float[] matrix) {
        this.mMatrix = matrix;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        if (mMatrix != null) {
            matrix.setValues(mMatrix);
        }
        canvas.drawBitmap(mBitmap, 0, 0, null);//对比图
        canvas.drawBitmap(mBitmap, matrix, null);
    }
}
