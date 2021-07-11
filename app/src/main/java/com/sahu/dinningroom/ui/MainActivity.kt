package com.sahu.dinningroom.ui

import android.os.Bundle
import android.widget.Toast
import com.sahu.dinningroom.R
import com.sahu.dinningroom.appUtil.ui.BaseActivity
import com.sahu.dinningroom.databinding.MainActivityBinding
import com.sahu.dinningroom.ext.replaceFragment
import com.sahu.dinningroom.ui.orders.OrdersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity
    : BaseActivity<MainActivityBinding>(R.layout.main_activity) {

    private var pressedTime = 0L

    override fun init(savedInstanceState: Bundle?, binding: MainActivityBinding) {
        if (savedInstanceState == null)
            replaceFragment(
                R.id.container,
                OrdersFragment.newInstance(),
                "MainFragment"
            )
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else {
            if (pressedTime + 3000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finish();
            } else {
                Toast.makeText(baseContext, resources.getText(R.string.exit_toast_meg), Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis();
        }
    }
}