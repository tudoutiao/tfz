package com.my.tfz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.tfz.R
import com.my.tfz.bean.ConstactBean
import com.my.tfz.databinding.ItemConstactLayoutBinding
import com.my.tfz.databinding.ItemValueLayoutBinding
import com.my.tfz.util.ImageUtil

class ConstactAdapter :
    ListAdapter<ConstactBean, RecyclerView.ViewHolder>(GardenPlantDiffCallback()) {
    //如果拼音第一个字符一样就隐藏显示拼音的TextView
    var preConstact: ConstactBean? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return TopViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_value_layout,
                    parent,
                    false
                )
            )
        } else {
            return MyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_constact_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        var bean = getItem(position)
        if (null != bean.isTop && bean.isTop!!) {
            return 0
        } else {
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)==0){
            (holder as TopViewHolder).bind(getItem(position))
        }else{
            if (position > 0) {
                preConstact = getItem(position - 1)
            }
            (holder as MyViewHolder).bind(getItem(position), preConstact)
        }

    }

    class MyViewHolder(val binding: ItemConstactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: ConstactBean, pre: ConstactBean?) {
            with(binding) {
                constact = info
                ImageUtil().setCircleHead(info.icon, image)
                if (info.sex.equals("X"))
                    sex.setImageResource(R.drawable.ic_girl)
                else
                    sex.setImageResource(R.drawable.ic_boy)
                if (info.department.isNullOrEmpty()) {
                    organizational.text = info.project + "|" + info.platform
                } else {
                    organizational.text =
                        info.project + "|" + info.platform + "|" + info.department!!
                }
                py.text = info.getPinYin()[0].toString()

                if (null != pre && info.getPinYin()!![0].equals(pre.getPinYin()!![0])) {
                    py.visibility = View.GONE
                } else {
                    py.visibility = View.VISIBLE
                }

                executePendingBindings()
            }
        }
    }

    class TopViewHolder(val binding: ItemValueLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(info: ConstactBean) {
            with(binding) {
                constact = info
                binding.avatar.setImageResource(info.imgId!!)
                executePendingBindings()
            }
        }
    }

    class GardenPlantDiffCallback : DiffUtil.ItemCallback<ConstactBean>() {

        override fun areItemsTheSame(
            oldItem: ConstactBean,
            newItem: ConstactBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ConstactBean,
            newItem: ConstactBean
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }


}