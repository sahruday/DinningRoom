package com.sahu.dinningroom.appUtil

import android.media.AudioManager
import android.media.ToneGenerator
import android.media.ToneGenerator.MAX_VOLUME
import android.media.ToneGenerator.TONE_CDMA_PIP

object GroupToneGenerator {
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, MAX_VOLUME)

    private val alertList = hashSetOf<Int>()

    fun addToList(orderId: Int) {
        alertList.add(orderId)
        toneGenerator.startTone(TONE_CDMA_PIP)
    }

    fun removeFromList(orderId: Int) {
        alertList.remove(orderId)
        if (alertList.isEmpty())
            toneGenerator.stopTone()
    }

    fun clear() {
        alertList.clear()
        toneGenerator.stopTone()
    }
}