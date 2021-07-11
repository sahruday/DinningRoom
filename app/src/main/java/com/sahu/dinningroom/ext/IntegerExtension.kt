package com.sahu.dinningroom.ext

import android.content.res.Resources
import com.sahu.dinningroom.R

fun Int.formattedTime(resource: Resources) : String{
    return when {
        this > 60 -> resource.getString(R.string.minuteHolder, ((this/60)+1).toString())
        this > 0 -> resource.getString(R.string.secondHolder, (this % 60).toString())
        else -> ""
    }
}