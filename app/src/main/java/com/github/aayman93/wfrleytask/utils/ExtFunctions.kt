package com.github.aayman93.wfrleytask.utils

import com.github.aayman93.wfrleytask.utils.Constants.DATE_FORMAT
import com.github.aayman93.wfrleytask.utils.Constants.DATE_TIME_FORMAT
import com.github.aayman93.wfrleytask.utils.Constants.TIME_FORMAT
import java.util.Locale

fun String.toDateTime(
    toPattern: String = DATE_TIME_FORMAT,
    locale: String = "ar"
): String {
    return formatDateTime(dateTime = this, toPattern = toPattern, locale = locale)
}

fun String.toDate(
    toPattern: String = DATE_FORMAT,
    locale: String = "ar"
): String {
    return formatDateTime(dateTime = this, toPattern = toPattern, locale = locale)
}

fun String.toTime(
    toPattern: String = TIME_FORMAT,
    locale: String = "en"
): String {
    return formatDateTime(dateTime = this, toPattern = toPattern, locale = locale)
}

fun Double.formatDouble(
    alwaysHasDecimal: Boolean = false
): String {
    return if (!alwaysHasDecimal && this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        String.format(Locale.ENGLISH,"%.2f", this)
    }
}