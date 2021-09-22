package com.my.tfz.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.tfz.R
import com.my.tfz.bean.MessageBean
import com.my.tfz.databinding.ItemMessageLayoutBinding

class MessageAppAdapter :
    ListAdapter<MessageBean, MessageAppAdapter.ViewHolder>(
        GardenPlantDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemMessageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(info: MessageBean) {
            with(binding) {
                msgBean = info

                executePendingBindings()
            }
        }
    }

    class GardenPlantDiffCallback : DiffUtil.ItemCallback<MessageBean>() {

        override fun areItemsTheSame(
            oldItem: MessageBean,
            newItem: MessageBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MessageBean,
            newItem: MessageBean
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

}

