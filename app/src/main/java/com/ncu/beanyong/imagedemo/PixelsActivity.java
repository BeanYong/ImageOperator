package com.ncu.beanyong.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by BeanYong on 2015/8/16.
 */
public class PixelsActivity extends Activity {
    private ImageView mOriginal, mNegative, mOld, mCameo;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixels);
        mOriginal = (ImageView) findViewById(R.id.id_iv_original);
        mNegative = (ImageView) findViewById(R.id.id_iv_negative);
        mOld = (ImageView) findViewById(R.id.id_iv_old);
        mCameo = (ImageView) findViewById(R.id.id_iv_cameo);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
        mOriginal.setImageBitmap(mBitmap);
        mNegative.setImageBitmap(ImageHelper.operateNegative(mBitmap));
        mOld.setImageBitmap(ImageHelper.operateOld(mBitmap));
        mCameo.setImageBitmap(ImageHelper.operateCameo(mBitmap));
    }
}
