package com.my.tfz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.bean.AppItemInfo
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var appInfoList = MutableLiveData<AppItemInfo>()

    init {
        viewModelScope.launch {

        }
    }

}