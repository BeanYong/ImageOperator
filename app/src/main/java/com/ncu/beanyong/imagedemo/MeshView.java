package com.ncu.beanyong.imagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * 通过网格像素块的方法处理图片，实现旗帜飘扬的效果
 * Created by BeanYong on 2015/8/19.
 */
public class MeshView extends View {

    private Bitmap mBitmap;
    private int WIDTH = 200;//将图片水平方向分为的像素块数
    private int HEIGHT = 200;//将图片竖直方向分为的像素块数
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);//总的像素点数目，包括每一个网格的顶点
    private float[] verts = new float[COUNT * 2];//变化后的所有像素点的坐标数组（偶数存储横坐标，奇数纵坐标）
    private float[] originals = new float[COUNT * 2];//原始的所有像素点的坐标数组（偶数存储横坐标，奇数纵坐标）
    private float K = 0f;//周期系数

    public MeshView(Context context) {
        this(context, null);
    }

    public MeshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);
        int bitmapHeight = mBitmap.getHeight();
        float fx = 0f;//在mBitmap上对应比例的像素点的横坐标
        float fy = 0f;//在mBitmap上对应比例的像素点的纵坐标
        int index = 0;//数组下标
        for (int i = 0; i <= WIDTH; i++) {
            fy = bitmapHeight * i / HEIGHT;
            for (int j = 0; j <= HEIGHT; j++) {
                fx = bitmapHeight * j / HEIGHT;
                index = (i * (WIDTH+1) + j) * 2;
                originals[index] = verts[index] = fx;
                originals[index + 1] = verts[index + 1] = fy + 100;//向下平移100个像素
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float offsetX = 0f;//网格像素点水平方向的偏移量
        float offsetY = 0f;//网格像素点竖直方向的偏移量
        int index = 0;//数组下标
        for (int i = 0; i <= WIDTH; i++) {
            for (int j = 0; j <= HEIGHT; j++) {
                offsetY = (float) Math.sin(2 * Math.PI * (float) j / WIDTH + 2 * Math.PI * K);
                index = 2 * (i * (WIDTH + 1) + j);
//                verts[index] = originals[index] + offsetX * 50;//50是振幅，此效果不需要x轴变化
                verts[index + 1] = originals[index + 1] + offsetY * 50;
            }
        }

        K += 0.2f;
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        invalidate();
    }
}
