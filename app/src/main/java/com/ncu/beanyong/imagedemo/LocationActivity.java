package com.ncu.beanyong.imagedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

/**
 * 通过改变位置矩阵，控制Bitmap绘制图片的缩放，平移，错切,旋转
 * <p>
 * Created by BeanYong on 2015/8/16.
 */
public class LocationActivity extends Activity implements View.OnClickListener {
    private ImageMatrixView mImg;
    private GridLayout mGroup;
    private Button mChange, mReset;
    private EditText[] mEditTexts = new EditText[9];
    private float[] mMatrix = new float[9];
    private int mEditTextWidth, mEditTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        mChange = (Button) findViewById(R.id.id_btn_changeLoc);
        mReset = (Button) findViewById(R.id.id_btn_resetLoc);
        mGroup = (GridLayout) findViewById(R.id.id_gl_matrixLoc);
        mImg = (ImageMatrixView) findViewById(R.id.id_imv_img);

        mChange.setOnClickListener(this);
        mReset.setOnClickListener(this);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEditTextWidth = mGroup.getWidth() / 3;
                mEditTextHeight = mGroup.getHeight() / 3;
                getMatrix();
            }
        });
    }

    private void getMatrix() {
        resetMatrix();
        for (int i = 0; i < 9; i++) {
            EditText et = new EditText(this);
            et.setText(mMatrix[i] + "");
            et.setGravity(Gravity.CENTER);
            mEditTexts[i] = et;
            mGroup.addView(et, mEditTextWidth, mEditTextHeight);
        }
    }

    private void resetMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mMatrix[i] = 1;
            } else {
                mMatrix[i] = 0;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_changeLoc:
                for (int i = 0; i < 9; i++) {
                    mMatrix[i] = Float.parseFloat(mEditTexts[i].getText().toString());
                }
                mImg.setMatrix(mMatrix);
                mImg.invalidate();
                break;
            case R.id.id_btn_resetLoc:
                resetMatrix();
                for (int i = 0; i < 9; i++) {
                    mEditTexts[i].setText(mMatrix[i] + "");
                }
                mImg.setMatrix(mMatrix);
                mImg.invalidate();
                break;
        }
    }
}
