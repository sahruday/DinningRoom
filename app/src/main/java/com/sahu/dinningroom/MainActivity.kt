package com.sahu.dinningroom

import android.os.Bundle
import com.sahu.dinningroom.appUtil.ui.BaseActivity
import com.sahu.dinningroom.databinding.MainActivityBinding
import com.sahu.dinningroom.ext.replaceFragment
import com.sahu.dinningroom.ui.orders.OrdersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity
    : BaseActivity<MainActivityBinding>(R.layout.main_activity) {

    override fun init(savedInstanceState: Bundle?, binding: MainActivityBinding) {
        replaceFragment(
            R.id.container,
            OrdersFragment.newInstance(),
            "MainFragment"
        )
    }
}