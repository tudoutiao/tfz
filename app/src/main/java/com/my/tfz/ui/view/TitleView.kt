package com.my.tfz.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
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

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_title_layout, this)
        var typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.title_view_style)
        content = typedArray.getString(R.styleable.title_view_style_content)!!
        tv_title.text = content
        typedArray.recycle()
    }

}