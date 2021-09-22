package com.my.tfz.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.tfz.R
import com.my.tfz.bean.AppItemInfo
import com.my.tfz.util.DataUtil
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var appInfoList = MutableLiveData<List<AppItemInfo>>()
    val imageArr = intArrayOf(
        R.drawable.ic_sale, R.drawable.ic_order, R.drawable.ic_personnel,
        R.drawable.ic_knowledge, R.drawable.ic_approve, R.drawable.ic_feedback,
        R.drawable.ic_notice, R.drawable.ic_meeting, R.drawable.ic_after_sale
    )
    var titleArr = arrayOf("销售", "工单", "人事", "知识", "审批", "反馈", "公告", "会议", "售后")

    init {
        viewModelScope.launch {

            var dataList = DataUtil().getInfoList()
            var index = 0
            dataList?.map {
                it.icon = imageArr[index]
                index++
            }
            appInfoList.postValue(dataList!!)
        }
    }


}