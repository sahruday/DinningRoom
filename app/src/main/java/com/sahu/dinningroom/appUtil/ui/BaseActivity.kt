package com.sahu.dinningroom.appUtil.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    abstract fun init(savedInstanceState: Bundle?, binding: VDB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .setContentView<VDB>(this, layoutResId)
            .apply {
                this.lifecycleOwner = this@BaseActivity
            }.also {
                init(savedInstanceState, it)
            }
    }
}