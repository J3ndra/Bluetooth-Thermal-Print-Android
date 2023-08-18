package com.junianto.edcsekolah.util

import android.os.Build
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateTime(): String {
    val currentTime: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        System.currentTimeMillis()
    } else {
        System.currentTimeMillis()
    }

    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(currentTime))
}

fun getCurrentDate(): String {
    val currentTime: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        System.currentTimeMillis()
    } else {
        System.currentTimeMillis()
    }

    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(currentTime))
}

fun getCurrentTime(): String {
    val currentTime: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        System.currentTimeMillis()
    } else {
        System.currentTimeMillis()
    }

    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(currentTime))
}