package com.my.tfz.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.tfz.bean.AppItemInfo
import com.my.tfz.databinding.ItemAppLayoutBinding

class HomeAppAdapter :
    ListAdapter<AppItemInfo, HomeAppAdapter.ViewHolder>(GardenPlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(binding: ItemAppLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }

            fun bind(info: AppItemInfo) {
                with(binding) {
                    appInfo = info
                    executePendingBindings()
                }
            }
        }
    }

}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<AppItemInfo>() {

    override fun areItemsTheSame(
        oldItem: AppItemInfo,
        newItem: AppItemInfo
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AppItemInfo,
        newItem: AppItemInfo
    ): Boolean {
        return oldItem.name == newItem.name
    }
}
