package com.sahu.dinningroom.ui.orders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.dinningroom.R
import com.sahu.dinningroom.dataHolders.Items
import com.sahu.dinningroom.databinding.OrderedItemsItemBinding

class OrderItemsAdapter
    : ListAdapter<Items, OrderItemsAdapter.ItemVH>(DiffCallback()) {

    inner class ItemVH(val binding: OrderedItemsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.item = item
            val addOnTxt = StringBuilder()
            for (addOn in item.addOns) {
                addOnTxt.append("x${addOn.quantity}\t")
                addOnTxt.append(addOn.title)
                addOnTxt.append("\n")
            }
            binding.addOnTxt.text = addOnTxt.toString()
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val itemLayoutBinding = DataBindingUtil.inflate<OrderedItemsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.ordered_items_item, parent, false
        )
        return ItemVH(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(getItem(position))
    }
}