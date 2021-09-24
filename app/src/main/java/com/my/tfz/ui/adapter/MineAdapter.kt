package com.my.tfz.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.tfz.R
import com.my.tfz.bean.SimpleBean
import com.my.tfz.databinding.ItemMineLayoutBinding

class MineAdapter : ListAdapter<SimpleBean, MineAdapter.ViewHolder>(GardenPlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_mine_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding: ItemMineLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(bean: SimpleBean) {
            with(binding) {
                binding.tvName.text = bean.name
                bean.value.let {
                    binding.tvValue.text = it
                }
                binding.icon.setImageResource(bean.icon ?: 0)
            }
        }
    }

    class GardenPlantDiffCallback : DiffUtil.ItemCallback<SimpleBean>() {

        override fun areItemsTheSame(
            oldItem: SimpleBean,
            newItem: SimpleBean
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SimpleBean,
            newItem: SimpleBean
        ): Boolean {
            return oldItem.value == newItem.value
        }
    }

}