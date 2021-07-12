package com.sahu.dinningroom.ui.orders

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.GroupToneGenerator
import com.sahu.dinningroom.appUtil.ui.BaseFragment
import com.sahu.dinningroom.databinding.OrdersFragmentBinding
import com.sahu.dinningroom.ext.addFragment
import com.sahu.dinningroom.ui.AppViewModel
import com.sahu.dinningroom.ui.ingredient.IngredientsFragment
import com.sahu.dinningroom.ui.orders.adapter.OrderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class OrdersFragment
    : BaseFragment<OrdersFragmentBinding>(R.layout.orders_fragment) {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: OrdersFragmentBinding

    private val ordersAdapter = OrderAdapter { order, updateStatus ->
        viewModel.updateOrderState(order.id, updateStatus)
    }

    override fun init(savedInstanceState: Bundle?, binding: OrdersFragmentBinding) {
        this.binding = binding
        setHasOptionsMenu(true)
        binding.recycle.adapter = ordersAdapter
        binding.recycle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(binding.recycle)

        lifecycleScope.launchWhenResumed {
            viewModel.getOrders().collect {
                binding.message.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
                GroupToneGenerator.clear()
                ordersAdapter.submitList(it.sortedByDescending { order -> order.id })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.orders_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.showIngredients -> showIngredientsFragment()
            R.id.placeOrder -> viewModel.placeAnOrder()
            R.id.clearOrders -> viewModel.clearOrders()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showIngredientsFragment() {
        addFragment(
            R.id.container,
            IngredientsFragment.newInstance(),
            "IngredientFragment",
        )
    }

}