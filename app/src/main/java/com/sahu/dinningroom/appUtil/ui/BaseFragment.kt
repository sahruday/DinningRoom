package com.sahu.dinningroom.appUtil.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    val baseActivity by lazy { activity as? BaseActivity<*> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil
            .inflate<VDB>(inflater, layoutResId, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
            }
            .also {
                init(savedInstanceState, it)
            }
            .root
    }

    abstract fun init(savedInstanceState: Bundle?, binding: VDB)
}