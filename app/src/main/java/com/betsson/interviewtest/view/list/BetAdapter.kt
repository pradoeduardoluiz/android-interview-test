package com.betsson.interviewtest.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.betsson.interviewtest.commom.presentation.BetState
import com.betsson.interviewtest.databinding.ListItemBinding

class BetAdapter : ListAdapter<BetState.BetViewData, BetAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    abstract class ViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: BetState.BetViewData)
    }

    class ItemViewHolder(
        private val binding: ListItemBinding
    ) : ViewHolder(binding) {

        override fun bind(item: BetState.BetViewData) {
            binding.apply {
                type.text = item.type
                sellIn.text = item.sellIn.toString()
                odds.text = item.odds.toString()
                image.load(item.image)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BetState.BetViewData>() {
            override fun areItemsTheSame(
                oldItem: BetState.BetViewData, newItem: BetState.BetViewData
            ): Boolean = oldItem.type == newItem.type

            override fun areContentsTheSame(
                oldItem: BetState.BetViewData, newItem: BetState.BetViewData
            ): Boolean = oldItem == newItem
        }
    }
}
