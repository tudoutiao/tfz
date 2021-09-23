package com.my.tfz.ui.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.tfz.R;

import java.util.HashMap;

public class AlphabetListView extends RelativeLayout {
    // 字母表中的字母与ListView中各元素位置间的映射关系
    private HashMap<String, Integer> alphaIndexMap;

    private ListView listView;
    private TextView tvCenterLetter;
    private AlphabetView alphabetView;

    private WindowManager windowManager;

    public AlphabetListView(Context context) {
        super(context);

        initView(context);
    }

    public AlphabetListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public AlphabetListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(context);
    }

    private void initView(Context context) {
        initCenterLetterHit(context);
        LayoutInflater.from(context).inflate(R.layout.alphabet_listview, this,
                true);
        listView = (ListView) findViewById(R.id.customListView);
        alphabetView = (AlphabetView) findViewById(R.id.alphabetView);

        alphabetView
                .setOnTouchLetterChangedListener(s -> {
                    tvCenterLetter.setText(s);
                    tvCenterLetter.setVisibility(View.VISIBLE);

                    if (null != alphaIndexMap
                            && alphaIndexMap.containsKey(s)) {
                        Integer alphaIndex = alphaIndexMap.get(s);
                        listView.setSelection(alphaIndex);
                    }
                });

        alphabetView
                .setOnTouchLetterReleasedListener(() -> tvCenterLetter.setVisibility(View.GONE));
    }

    // 初始化位于字母中央的字母提示
    private void initCenterLetterHit(Context context) {
        View centerLetterView = LayoutInflater.from(context).inflate(
                R.layout.view_center_letter, null);
        tvCenterLetter = (TextView) centerLetterView
                .findViewById(R.id.tvCenterLetter);
        tvCenterLetter.setVisibility(View.INVISIBLE);

        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(centerLetterView, layoutParams);
    }

    public void setAlphaIndexMap(HashMap<String, Integer> alphaIndexMap) {
        this.alphaIndexMap = alphaIndexMap;
    }

    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    public void setOnItemClickListener(
            final AdapterView.OnItemClickListener onItemClickListener) {
        if (null != onItemClickListener) {
            listView.setOnItemClickListener((parent, convertView, position, id) -> onItemClickListener.onItemClick(parent, convertView,
                    position, id));
        }
    }

    public void setOnItemLongClickListenter(
            final OnItemLongClickListener onItemLongClickListener) {
        if (null != onItemLongClickListener) {
            listView.setOnItemLongClickListener((parent, convertView, position, id) -> {
                onItemLongClickListener.onItemLongClick(parent,
                        convertView, position, id);

                return false;
            });
        }
    }

    public void setBackgroundDrawable(Drawable background) {
        listView.setBackgroundDrawable(background);
    }

    public void setBackResource(int resourceId) {
        listView.setBackgroundResource(resourceId);
    }

    public void setAlphabetDefaultColor(int defaultColor) {
        alphabetView.setDefaultColor(defaultColor);
    }

    public void setAlphabetSelectedColor(int selectColor) {
        alphabetView.setSelectColor(selectColor);
    }

    public void setDivider(int height) {
        listView.setDividerHeight(height);
    }

    public void setCenterLetterColor(int color) {
        tvCenterLetter.setTextColor(color);
    }

    public void setCenterLetterBackgroundResource(int resourceId) {
        tvCenterLetter.setBackgroundResource(resourceId);
    }

    public void setCenterLetterTextSize(float size) {
        tvCenterLetter.setTextSize(size);
    }

    public interface OnItemClickListenter {

        public void onItemClick(AdapterView<?> parent, View convertView,
                                int position, long id);
    }

    public interface OnItemLongClickListener {

        public boolean onItemLongClick(AdapterView<?> parent, View convertView,
                                       int position, long id);
    }

}
