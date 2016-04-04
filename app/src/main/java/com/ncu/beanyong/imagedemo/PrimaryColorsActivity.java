package com.ncu.beanyong.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by BeanYong on 2015/8/15.
 */
public class PrimaryColorsActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private static final int MAX_VALUE = 255;
    private static final int MID_VALUE = 127;

    private ImageView mImg;
    private Bitmap mBitmap;
    private SeekBar mHue, mSaturation, mLum;
    private float mHueVal, mSaturationVal, mLumVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_colors);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);

        mImg = (ImageView) findViewById(R.id.id_iv_img);
        mHue = (SeekBar) findViewById(R.id.id_sb_hue);
        mSaturation = (SeekBar) findViewById(R.id.id_sb_saturation);
        mLum = (SeekBar) findViewById(R.id.id_sb_lum);

        mHue.setOnSeekBarChangeListener(this);
        mSaturation.setOnSeekBarChangeListener(this);
        mLum.setOnSeekBarChangeListener(this);

        mHue.setMax(MAX_VALUE);
        mSaturation.setMax(MAX_VALUE);
        mLum.setMax(MAX_VALUE);

        mHue.setProgress(MID_VALUE);
        mSaturation.setProgress(MID_VALUE);
        mLum.setProgress(MID_VALUE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.id_sb_hue:
                mHueVal = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.id_sb_saturation:
                mSaturationVal = progress * 1.0F / MID_VALUE;
                break;
            case R.id.id_sb_lum:
                mLumVal = progress * 1.0F / MID_VALUE;
                break;
        }
        Bitmap bp = ImageHelper.operatePrimaryColors(mBitmap, mHueVal, mSaturationVal, mLumVal);
        mImg.setImageBitmap(bp);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
