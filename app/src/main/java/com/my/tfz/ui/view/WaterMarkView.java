package com.my.tfz.ui.view;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.my.tfz.util.ConvertUtils;

public class WaterMarkView {
    /**
     * 水印文本
     */
    private String mText;
    /**
     * 字体颜色，十六进制形式，例如：0xAEAEAEAE
     */
    private int mTextColor;
    /**
     * 字体大小，单位为sp
     */
    private float mTextSize;
    /**
     * 旋转角度
     */
    private float mRotation;
    private static WaterMarkView sInstance;

    private WaterMarkView() {
        mText = "";
        mTextColor = 0xa5F2F2F2;
        mTextSize = 18;
        mRotation = -20;
    }

    public static WaterMarkView getInstance() {
        if (sInstance == null) {
            synchronized (WaterMarkView.class) {
                sInstance = new WaterMarkView();
            }
        }
        return sInstance;
    }

    /**
     * 设置水印文本
     *
     * @param text 文本
     * @return Watermark实例
     */
    public WaterMarkView setText(String text) {
        mText = text;
        return sInstance;
    }

    /**
     * 设置字体颜色
     *
     * @param color 颜色，十六进制形式，例如：0xAEAEAEAE
     * @return Watermark实例
     */
    public WaterMarkView setTextColor(int color) {
        mTextColor = color;
        return sInstance;
    }

    /**
     * 设置字体大小
     *
     * @param size 大小，单位为sp
     * @return Watermark实例
     */
    public WaterMarkView setTextSize(float size) {
        mTextSize = size;
        return sInstance;
    }

    /**
     * 设置旋转角度
     *
     * @param degrees 度数
     * @return Watermark实例
     */
    public WaterMarkView setRotation(float degrees) {
        mRotation = degrees;
        return sInstance;
    }

    /**
     * 显示水印，铺满整个页面
     *
     * @param activity 活动
     */
    public void show(Activity activity) {
        show(activity, mText);
    }

    /**
     * 显示水印，铺满整个页面
     *
     * @param activity 活动
     * @param text     水印
     */
    public void show(Activity activity, String text) {
        WatermarkDrawable drawable = new WatermarkDrawable();
        drawable.mText = text;
        drawable.mTextColor = mTextColor;
        drawable.mTextSize = mTextSize;
        drawable.mRotation = mRotation;

        ViewGroup rootView = activity.findViewById(android.R.id.content);
        FrameLayout layout = new FrameLayout(activity);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setBackground(drawable);
        rootView.addView(layout);
    }

    private class WatermarkDrawable extends Drawable {
        private Paint mPaint;
        /**
         * 水印文本
         */
        private String mText;
        /**
         * 字体颜色，十六进制形式，例如：0xAEAEAEAE
         */
        private int mTextColor;
        /**
         * 字体大小，单位为sp
         */
        private float mTextSize;
        /**
         * 旋转角度
         */
        private float mRotation;

        private WatermarkDrawable() {
            mPaint = new Paint();
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            int width = getBounds().right;
            int height = getBounds().bottom;
//            int diagonal = (int) Math.sqrt(width * width + height * height); // 对角线的长度
            int diagonal = height; // 对角线的长度

            mPaint.setColor(mTextColor);
            mPaint.setTextSize(ConvertUtils.sp2px(mTextSize)); // ConvertUtils.spToPx()这个方法是将sp转换成px，ConvertUtils这个工具类在我提供的demo里面有
            mPaint.setAntiAlias(true);
            float textWidth = mPaint.measureText(mText);

            canvas.drawColor(0x00000000);
            canvas.rotate(mRotation);

            int index = 0;

            float positionX = (width / 2 - textWidth) / 2;
            float positionY = 200;

            float positionX1 = width / 2 + (width / 2 - textWidth) / 2-100;
            float positionY1 = 360;


            for (int i = 0; i < 20; i++) {

                if (index % 2 == 0) {
                    positionX += -80 * (index == 0 ? 0 : 1);
                    positionY += 200 * (index == 0 ? 0 : 1);
                    canvas.drawText(mText, positionX, positionY, mPaint);
                } else {
                    positionX1 += -80 * (index == 1 ? 0 : 1);
                    positionY1 += 200 * (index == 1 ? 0 : 1);
                    canvas.drawText(mText, positionX1, positionY1, mPaint);
                }

                index++;
            }

            canvas.save();
            canvas.restore();
        }

        @Override
        public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

    }
}
