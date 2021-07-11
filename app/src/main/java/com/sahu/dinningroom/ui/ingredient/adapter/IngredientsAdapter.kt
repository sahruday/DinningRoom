package com.sahu.dinningroom.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.dinningroom.R
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.databinding.IngredientItemBinding

class IngredientsAdapter :
    ListAdapter<MenuItem, IngredientsAdapter.ItemVH>(DiffCallback()) {

    inner class ItemVH(val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: MenuItem) {
            binding.ingredient = menuItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val itemLayoutBinding = DataBindingUtil.inflate<IngredientItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.ingredient_item, parent, false
        )
        return ItemVH(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) =
        holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean =
            oldItem == newItem
    }
}