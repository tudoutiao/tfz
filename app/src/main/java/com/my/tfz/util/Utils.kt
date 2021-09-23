package com.my.tfz.util

import com.my.tfz.MainApplication

open class Utils {
    fun dipToPixels(dip: Float): Int {
        var result = MainApplication.context.getResources().getDisplayMetrics().density * dip
        return result.toInt()
    }

}