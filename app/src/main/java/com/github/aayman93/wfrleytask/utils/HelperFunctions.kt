package com.github.aayman93.wfrleytask.utils

import android.content.Context
import android.widget.Toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getTodayDateFormatted(
    dateFormat: String = Constants.DATE_FORMAT,
    locale: String = "ar"
): String {
    val todayDate = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale(locale))
    return todayDate.format(formatter)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}