package com.my.tfz.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.my.tfz.R;
import com.my.tfz.util.Utils;

public class LetterView extends View {

    // 字母表中的字符
    private String letters[] = {"@", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    // 字母的颜色
    private int defaultColor = 0xff7D8A7B;
    private int selectColor = 0xffffffff;

    // 被选中的字符
    private int selectedIndex = 0;

    private int W = 0;
    private int H = 0;
    private int letterH;
    private int letterW;
    private int letterAreaH;

    // 画笔--用于绘制右侧字母
    Paint paint = new Paint();

    // 选中的字母被改变监听器
    private OnTouchLetterChangedListener changedListener;
    // 选中的字母被释放监听器
    private OnTouchLetterReleasedListener releasedListener;

    public LetterView(Context context) {
        super(context);
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (W == 0) {
            W = getWidth();
            H = getHeight();
            letterAreaH = H / 28;
            letterH = new Utils().dipToPixels(9f);
        }


        // 获取当前View的宽度和高度
//        int width = getWidth();
        // 计算单个字符所占高度
//        int singleLetter = getHeight() / (alphabet.length);

        // 自上而下逐一绘制字母表中的每个字符
        for (int i = 0; i < letters.length; i++) {

            float[] widths = new float[1];
            paint.getTextWidths(letters[i], widths);
            letterW = (int) widths[0];
            int x = (W - letterW) / 2;
            int y = letterAreaH * i + (letterAreaH - letterH) / 2 + letterH;

            // 若没有没选中时显示默认颜色，若被选中显示指定的高亮色
            if (i != selectedIndex) {
                paint.setColor(defaultColor);
                paint.setAntiAlias(true);
                paint.setTextSize(25);
            } else {
                paint.setTextSize(25);
                paint.setColor(selectColor);
            }

            if (i == selectedIndex) {
                Paint p = new Paint();
                p.setAntiAlias(true);
                p.setColor(0xff5E9F69);
                canvas.drawCircle(x+10, y-10, 20, p);
            }

            // 在指定位置绘制指定字符
            canvas.drawText(letters[i], 0, 1, x, y, paint);
            // 重置画笔的属性
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // 被触摸的是字母表中的第几个字符
        int index = (int) (event.getY() / letterAreaH);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (null != changedListener) {
                    selectedIndex = index;
                    if (selectedIndex >= 0 && selectedIndex <= letters.length - 1) {
                        changedListener
                                .onTouchLetterChangedListener(letters[selectedIndex]);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (null != changedListener) {
                    if (selectedIndex != index) {
                        selectedIndex = index;
                        if (selectedIndex >= 0 && selectedIndex <= letters.length - 1) {
                            changedListener
                                    .onTouchLetterChangedListener(letters[selectedIndex]);
                            invalidate();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 监听到弹起事件后，调用相应的监听事件并将字母表的背景设置为没有背景
                releasedListener.onTouchLetterReleasedListener();
                invalidate();
                break;
            default:
                break;
        }

        return true;
    }


    // 设置字母默认显示的颜色
    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    // 设置字母被选中时显示的颜色
    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    // 设置选中字母已改变监听事件
    public void setOnTouchLetterChangedListener(
            OnTouchLetterChangedListener changedListener) {
        this.changedListener = changedListener;
    }

    // 设置已释放字母选中监听事件
    public void setOnTouchLetterReleasedListener(
            OnTouchLetterReleasedListener releasedListener) {
        this.releasedListener = releasedListener;
    }

    public interface OnTouchLetterChangedListener {

        void onTouchLetterChangedListener(String s);
    }

    public interface OnTouchLetterReleasedListener {

        void onTouchLetterReleasedListener();
    }

}
