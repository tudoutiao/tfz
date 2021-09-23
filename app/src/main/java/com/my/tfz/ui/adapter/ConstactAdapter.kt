package com.my.tfz.ui.adapter

import android.opengl.Visibility
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
import com.my.tfz.util.ImageUtil

class ConstactAdapter :
    ListAdapter<ConstactBean, ConstactAdapter.ViewHolder>(GardenPlantDiffCallback()) {
    //如果拼音第一个字符一样就隐藏显示拼音的TextView
    var preConstact: ConstactBean? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_constact_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > 0) {
            preConstact = getItem(position - 1)
        }
        holder.bind(getItem(position), preConstact)
    }

    class ViewHolder(val binding: ItemConstactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }

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

                if (null != pre && info.pinyin!![0].equals(pre.pinyin!![0])) {
                    py.visibility = View.GONE
                } else {
                    py.visibility = View.VISIBLE
                }

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