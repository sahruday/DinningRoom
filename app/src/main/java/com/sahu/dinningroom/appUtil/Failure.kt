package com.sahu.dinningroom.appUtil

data class Failure(
    val code: Int?,
    val message: String,
    val exception: Exception? = null
)