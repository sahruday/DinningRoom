package com.sahu.dinningroom.ui.orders

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.ui.BaseFragment
import com.sahu.dinningroom.databinding.OrdersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment
    : BaseFragment<OrdersFragmentBinding>(R.layout.orders_fragment) {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private val viewModel: OrdersViewModel by activityViewModels()
    private lateinit var binding: OrdersFragmentBinding

    override fun init(savedInstanceState: Bundle?, binding: OrdersFragmentBinding) {
        this.binding = binding
    }

}