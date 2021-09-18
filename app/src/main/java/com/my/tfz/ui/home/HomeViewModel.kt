package com.my.tfz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.bean.AppItemInfo
import com.my.tfz.util.DataUtil
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var appInfoList = MutableLiveData<List<AppItemInfo>>()

    init {
        viewModelScope.launch {
            appInfoList.value = DataUtil.getInfoList()
        }
    }

}