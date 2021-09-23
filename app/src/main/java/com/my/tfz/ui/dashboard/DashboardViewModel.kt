package com.my.tfz.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.bean.ConstactBean
import com.my.tfz.util.DataUtil
import kotlinx.coroutines.launch
import java.util.*

class DashboardViewModel : ViewModel() {

    var constactList = MutableLiveData<List<ConstactBean>>()

    init {
        viewModelScope.launch {
            var dataList = DataUtil().getConstactList()
            dataList!!.map { it.getPinYin() }
            Collections.sort(dataList)
            constactList.postValue(dataList!!)
        }
    }
}