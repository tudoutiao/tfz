package com.my.tfz.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.my.tfz.R
import kotlinx.android.synthetic.main.view_title_layout.view.*

class TitleView : LinearLayout {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    var content: String = ""
    var leftImage: Int? = null
    var rightImage: Int? = null
    private fun init(context: Context, attrs: AttributeSet?) {
        var typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.title_view_style)
        content = typedArray.getString(R.styleable.title_view_style_content)!!
        leftImage = typedArray.getResourceId(R.styleable.title_view_style_leftImg, -1)
        rightImage = typedArray.getResourceId(R.styleable.title_view_style_rightImg, -1)

        LayoutInflater.from(context).inflate(R.layout.view_title_layout, this)
        tv_title.text = content
        if (leftImage != -1)
            img_right.setImageResource(rightImage!!)
        if (rightImage != -1)
            img_right.setImageResource(rightImage!!)
        typedArray.recycle()
    }

}