package com.my.tfz.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.tfz.R
import com.my.tfz.bean.AppItemInfo
import com.my.tfz.databinding.ItemAppLayoutBinding

class HomeAppAdapter :
    ListAdapter<AppItemInfo, HomeAppAdapter.ViewHolder>(
        GardenPlantDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_app_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemAppLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }
        }

        fun bind(info: AppItemInfo) {
            with(binding) {
                appInfo = info
                image.setImageResource(info.icon!!)
                executePendingBindings()
            }
        }
    }

}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<AppItemInfo>() {

    override fun areItemsTheSame(
        oldItem: AppItemInfo,
        newItem: AppItemInfo
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AppItemInfo,
        newItem: AppItemInfo
    ): Boolean {
        return oldItem.name == newItem.name
    }
}
