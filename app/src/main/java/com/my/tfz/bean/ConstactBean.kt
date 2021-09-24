package com.my.tfz.bean

import com.my.tfz.util.PinYinUtils
import java.io.Serializable

class ConstactBean(var id: Int?, var name: String?, var isTop: Boolean?, var imgId: Int?) :
    Serializable,
    Comparable<ConstactBean> {
    //    var id: Int? = null
//    var name: String? = null
    var position: String? = null//职位
    var project: String? = null//组织架构
    var department: String? = null//部门
    var platform: String? = null//平台
    var sex: String? = null//性别
    var pinyin: String? = null
    var icon: String? = null


    fun getPinYin(): String {
        if (pinyin == null) {
            pinyin = PinYinUtils().getPinyin(name!!)
        }
        return pinyin!!
    }

    override fun compareTo(other: ConstactBean): Int {
        return this.pinyin!!.compareTo(other.pinyin!!)
    }


}