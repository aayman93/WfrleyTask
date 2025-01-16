package com.github.aayman93.wfrleytask.utils

import com.github.aayman93.wfrleytask.utils.Constants.DATE_TIME_FORMAT
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDate(
    toPattern: String = DATE_TIME_FORMAT,
    locale: String = "ar"
): String {

    return try {
        val date = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        val formatter = DateTimeFormatter.ofPattern(toPattern, Locale(locale))

        date.format(formatter)
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}

fun Double.formatPrice(): String {
    return if (this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        String.format(Locale.ENGLISH,"%.2f", this)
    }
}