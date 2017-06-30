package com.siziksu.kow.common.utils

import java.util.*

class DateUtils {

    companion object {
        private const val TAG = "SystemUtils"
    }

    val currentTime: String
        get() {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            val seconds = calendar.get(Calendar.SECOND)
            if (hour < 10) {
                return "0$hour:" + (if (minutes < 10) "0$minutes:" else "$minutes:") + if (seconds < 10) "0$seconds" else seconds
            } else {
                return "$hour:" + (if (minutes < 10) "0$minutes:" else "$minutes:") + if (seconds < 10) "0$seconds" else seconds
            }
        }
}
