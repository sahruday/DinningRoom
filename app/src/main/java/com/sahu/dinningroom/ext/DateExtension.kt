package com.sahu.dinningroom.ext

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private const val SERVER_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val DISPLAYABLE_DATE_PATTERN = "HH:mmaaa"

fun String.getDisplayableDate() =
    transformDateFormat(this, SERVER_DATE_PATTERN, DISPLAYABLE_DATE_PATTERN) ?: "Error while parsing"

fun transformDateFormat(date: String, fromPattern: String, toPattern: String): String? {
    return try {
        SimpleDateFormat(fromPattern, Locale.US)
            .parse(date)
            ?.let {
                SimpleDateFormat(toPattern, Locale.US).format(it)
            }
    } catch (ex: Exception) {
        null
    }
}

fun String.toTimeInMills(): Long = SimpleDateFormat(SERVER_DATE_PATTERN, Locale.US).parse(this).time

@SuppressLint("SimpleDateFormat")
fun Calendar.getTimeZoneDate() : String{
    val formatter = SimpleDateFormat(SERVER_DATE_PATTERN)
    formatter.timeZone = TimeZone.getDefault()
    return formatter.format(Date(this.timeInMillis))
}