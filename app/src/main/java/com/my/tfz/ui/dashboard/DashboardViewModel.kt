package com.my.tfz.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.MainApplication
import com.my.tfz.R
import com.my.tfz.bean.ConstactBean
import com.my.tfz.room.AppDatabase
import com.my.tfz.room.ContactsDao
import com.my.tfz.util.DataUtil
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class DashboardViewModel : ViewModel() {

    var constactList = MutableLiveData<List<ConstactBean>>()

    init {
        viewModelScope.launch {
            var dataList: ArrayList<ConstactBean>? =
                DataUtil().getConstactList() as ArrayList<ConstactBean>?
            dataList!!.map { it.getPinYin() }
            Collections.sort(dataList)
            dataList.add(0, ConstactBean(1, "哗啦啦小蜜", true, R.drawable.avatar_secretary))
            dataList.add(1, ConstactBean(2, "我的群组", true, R.drawable.ic_group))
            constactList.postValue(dataList!!)
        }
    }
}