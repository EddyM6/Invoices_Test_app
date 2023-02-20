package com.xmm_homework.domain.util

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {
    private const val DATE_TIME_FORMAT = "d MMM, HH:mm a"

    @SuppressLint("NewApi")
    fun dateFormat(date: String): String {
        return try {
            if (date.isNotBlank()) {
                val parseDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
                parseDate.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.US))
            } else date
        } catch (e: Exception) {
            date
        }
    }
}