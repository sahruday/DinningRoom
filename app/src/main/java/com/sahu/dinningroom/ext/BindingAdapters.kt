package com.sahu.dinningroom.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sahu.dinningroom.R

@BindingAdapter("time")
fun atTime(view: TextView, time: String){
    view.text = view.resources.getString(R.string.atTime, time.getDisplayableDate())
}