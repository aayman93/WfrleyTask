package com.github.aayman93.wfrleytask.utils

import android.content.Context
import android.widget.Toast
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDateTime(
    dateTime: String,
    toPattern: String,
    locale: String = "ar"
): String {
    return try {
        val localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        val sourceTimeZone = ZoneId.of("UTC")
        val zonedDateTime = localDateTime.atZone(sourceTimeZone)

        val deviceTimeZone = ZoneId.systemDefault()
        val deviceDateTime = zonedDateTime.withZoneSameInstant(deviceTimeZone)

        val formatter = DateTimeFormatter.ofPattern(toPattern, Locale(locale))
        deviceDateTime.format(formatter)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun getTodayDateFormatted(
    dateFormat: String = Constants.TODAY_DATE_FORMAT,
    locale: String = "ar"
): String {
    val todayDate = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale(locale))
    return todayDate.format(formatter)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}