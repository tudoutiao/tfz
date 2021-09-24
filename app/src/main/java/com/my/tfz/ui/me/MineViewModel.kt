package com.my.tfz.ui.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.tfz.R
import com.my.tfz.bean.SimpleBean
import com.my.tfz.util.DataUtil

class MineViewModel : ViewModel() {
    var dataList = MutableLiveData<List<SimpleBean>>()

    init {
        var result = DataUtil().getMineList()
        result!!.forEach {
            if (it.name.equals("帮助中心")) {
                it.icon = R.drawable.ic_more
            } else if (it.name.equals("我的二维码")) {
                it.icon = R.drawable.ic_sqr
            }
        }
        dataList.postValue(result!!)

    }
}