package com.ncu.beanyong.imagedemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by BeanYong on 2015/8/15.
 */
public class ImageHelper {
/**********************************************************************************************
 *图片颜色处理
 **********************************************************************************************/
    /**
     * 变化oldBitmap的hue（色调）、saturation（饱和度），lum(亮度)
     *
     * @param oldBitmap
     * @param hue
     * @param saturation
     * @param lum
     * @return
     */
    public static Bitmap operatePrimaryColors(Bitmap oldBitmap, float hue, float saturation, float lum) {
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap.getWidth(), oldBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

        canvas.drawBitmap(oldBitmap, 0, 0, paint);

        return newBitmap;
    }

    /**
     * 通过矩阵变换处理图片
     *
     * @param oldBitmap
     * @param colorMatrix
     * @return
     */
    public static Bitmap operateColorMatrix(Bitmap oldBitmap, float[] colorMatrix) {
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap.getWidth(), oldBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix matrix = new ColorMatrix(colorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(oldBitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 通过像素点处理方式得到图片的底片效果
     *
     * @param oldBitmap
     * @return
     */
    public static Bitmap operateNegative(Bitmap oldBitmap) {
        int width = oldBitmap.getWidth();//图片的宽度（水平方向像素点个数）
        int height = oldBitmap.getHeight();//图片的高度（竖直方向像素点个数）
        int pixelsCount = width * height;//总的像素点个数
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPixels = new int[pixelsCount];
        int[] newPixels = new int[pixelsCount];

        oldBitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < pixelsCount; i++) {
            int oldColor = oldPixels[i];
            int r = Color.red(oldColor);
            int g = Color.green(oldColor);
            int b = Color.blue(oldColor);
            int a = Color.alpha(oldColor);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPixels[i] = Color.argb(a, r, g, b);
        }

        newBitmap.setPixels(newPixels, 0, width, 0, 0, width, height);

        return newBitmap;
    }

    /**
     * 通过像素点处理方式得到图片的老照片效果
     *
     * @param oldBitmap
     * @return
     */
    public static Bitmap operateOld(Bitmap oldBitmap) {
        int width = oldBitmap.getWidth();
        int height = oldBitmap.getHeight();
        int pixelsCount = width * height;
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPixels = new int[pixelsCount];
        int[] newPixels = new int[pixelsCount];

        oldBitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < pixelsCount; i++) {
            int oldColor = oldPixels[i];
            int r = Color.red(oldColor);
            int g = Color.green(oldColor);
            int b = Color.blue(oldColor);
            int a = Color.alpha(oldColor);

            r = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPixels[i] = Color.argb(a, r, g, b);
        }

        newBitmap.setPixels(newPixels, 0, width, 0, 0, width, height);

        return newBitmap;
    }

    /**
     * 通过像素点处理方式得到图片的浮雕效果
     *
     * @param oldBitmap
     * @return
     */
    public static Bitmap operateCameo(Bitmap oldBitmap) {
        int width = oldBitmap.getWidth();
        int height = oldBitmap.getHeight();
        int pixelsCount = width * height;
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPixels = new int[pixelsCount];
        int[] newPixels = new int[pixelsCount];

        oldBitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);

        newPixels[0] = oldPixels[0];

        for (int i = 1; i < pixelsCount; i++) {
            int oldColor = oldPixels[i];
            int r = Color.red(oldColor);
            int g = Color.green(oldColor);
            int b = Color.blue(oldColor);
            int a = Color.alpha(oldColor);

            int beforeColor = oldPixels[i - 1];
            int r1 = Color.red(beforeColor);
            int g1 = Color.green(beforeColor);
            int b1 = Color.blue(beforeColor);

            r = r1 - r + 127;
            g = g1 - g + 127;
            b = b1 - b + 127;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }

            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            newPixels[i] = Color.argb(a, r, g, b);
        }

        newBitmap.setPixels(newPixels, 0, width, 0, 0, width, height);

        return newBitmap;
    }
}
