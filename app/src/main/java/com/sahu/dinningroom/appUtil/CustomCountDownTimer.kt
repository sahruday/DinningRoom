package com.sahu.dinningroom.appUtil

import android.os.CountDownTimer

abstract class CustomCountDownTimer(
    val expireTimeInMills: Long,
    val alertTimeInMills: Int,
    millisInFuture: Long,
    countDownInterval: Long
) : CountDownTimer(millisInFuture, countDownInterval)