package com.my.tfz

import androidx.appcompat.app.AppCompatActivity
import com.my.tfz.ui.view.WaterMarkView

open class BaseActivity : AppCompatActivity() {
    open fun initView() {
        WaterMarkView.getInstance().show(this, "赵薇：92194")
    }
}