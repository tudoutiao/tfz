package com.my.tfz.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.bean.MessageBean
import com.my.tfz.util.DataUtil
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    var messageList = MutableLiveData<List<MessageBean>>()

    init {
        viewModelScope.launch {
            var dataList = DataUtil().getMessageList()
            messageList.postValue(dataList!!)
        }
    }
}