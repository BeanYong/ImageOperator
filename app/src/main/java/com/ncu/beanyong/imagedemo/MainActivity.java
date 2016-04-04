package com.ncu.beanyong.imagedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mPrimaryColors, mColorMatrix, mPixels,
            mLocMatrix, mCircleImage, mShader, mMirrorEffect, mMesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrimaryColors = (Button) findViewById(R.id.id_btn_primaryColor);
        mPrimaryColors.setOnClickListener(this);
        mColorMatrix = (Button) findViewById(R.id.id_btn_colorMatrix);
        mColorMatrix.setOnClickListener(this);
        mPixels = (Button) findViewById(R.id.id_btn_pixels);
        mPixels.setOnClickListener(this);
        mLocMatrix = (Button) findViewById(R.id.id_btn_locMatrix);
        mLocMatrix.setOnClickListener(this);
        mCircleImage = (Button) findViewById(R.id.id_btn_circleImage);
        mCircleImage.setOnClickListener(this);
        mShader = (Button) findViewById(R.id.id_btn_shader);
        mShader.setOnClickListener(this);
        mMirrorEffect = (Button) findViewById(R.id.id_btn_mirrorEffect);
        mMirrorEffect.setOnClickListener(this);
        mMesh = (Button) findViewById(R.id.id_btn_mesh);
        mMesh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_primaryColor:
                startActivity(new Intent(this, PrimaryColorsActivity.class));
                break;
            case R.id.id_btn_colorMatrix:
                startActivity(new Intent(this, ColorMatrixActivity.class));
                break;
            case R.id.id_btn_pixels:
                startActivity(new Intent(this, PixelsActivity.class));
                break;
            case R.id.id_btn_locMatrix:
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.id_btn_circleImage:
                startActivity(new Intent(this, CircleImageActivity.class));
                break;
            case R.id.id_btn_shader:
                startActivity(new Intent(this, ShaderCircleActivity.class));
                break;
            case R.id.id_btn_mirrorEffect:
                startActivity(new Intent(this, MirrorEffectActivity.class));
                break;
            case R.id.id_btn_mesh:
                startActivity(new Intent(this, MeshActivity.class));
                break;
        }
    }
}
