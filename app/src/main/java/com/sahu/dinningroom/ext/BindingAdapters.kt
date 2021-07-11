package com.sahu.dinningroom.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sahu.dinningroom.R

@BindingAdapter("time")
fun atTime(view: TextView, time: String){
    view.text = view.resources.getString(R.string.atTime, time.getDisplayableDate())
}

@BindingAdapter("items")
fun numberOfItems(view: TextView, count: Int){
    view.text = view.resources.getString(R.string.itemCount, count.toString())
}


@BindingAdapter("color")
fun colorAndTextOfItemIngredient(view:TextView, count: Int) {
    view.text = count.toString()
    view.setTextColor(view.resources.getColor(if(count<=5) R.color.red else R.color.green))
}

@BindingAdapter("quanity")
fun quantityDisplay(view: TextView, count: Int) {
    view.text = view.resources.getText(R.string.itemCount, count.toString())
}
