package com.ncu.beanyong.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by BeanYong on 2015/8/16.
 */
public class ColorMatrixActivity extends Activity implements View.OnClickListener {
    private Button mChange, mReset;
    private ImageView mImg;
    private GridLayout mGroup;
    private EditText[] mEditTexts = new EditText[20];
    private float[] mMatrix = new float[20];
    private Bitmap mBitmap;
    private int mEditTextWidth, mEditTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);
        initViews();
    }

    private void initViews() {
        mImg = (ImageView) findViewById(R.id.id_iv_colorMatrixImg);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mGroup = (GridLayout) findViewById(R.id.id_gl_matrix);
        mChange = (Button) findViewById(R.id.id_btn_change);
        mReset = (Button) findViewById(R.id.id_btn_reset);

        mImg.setImageBitmap(mBitmap);

        mChange.setOnClickListener(this);
        mReset.setOnClickListener(this);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEditTextWidth = mGroup.getWidth() / 5;
                mEditTextHeight = mGroup.getHeight() / 4;

                getEditText();
            }
        });
    }

    /**
     * 向GridLayout中填充20个EditText
     */
    private void getEditText() {
        resetMatrix();
        for (int i = 0; i < 20; i++) {
            EditText et = new EditText(this);
            et.setText(String.valueOf(mMatrix[i]));
            et.setWidth(mEditTextWidth);
            et.setHeight(mEditTextHeight);
            et.setGravity(Gravity.CENTER);
            mEditTexts[i] = et;
            mGroup.addView(et);
        }
    }

    /**
     * 重置颜色矩阵
     */
    private void resetMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mMatrix[i] = 1;
            } else {
                mMatrix[i] = 0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_change:
                changeImgFromMatrix();
                Bitmap bp = ImageHelper.operateColorMatrix(mBitmap, mMatrix);
                mImg.setImageBitmap(bp);
                break;
            case R.id.id_btn_reset:
                resetMatrix();
                for (int i = 0; i < 20; i++) {
                    mEditTexts[i].setText(mMatrix[i] + "");
                }
                mImg.setImageBitmap(mBitmap);
                break;
        }
    }

    private void changeImgFromMatrix() {
        for (int i = 0; i < 20; i++) {
            mMatrix[i] = Float.parseFloat(mEditTexts[i].getText().toString());
        }
    }
}
