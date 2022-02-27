package com.iamkurtgoz.domain.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.simpleDateConvert(newFormat: String): String? {
    if (this.isNullOrEmpty()) return ""
    val dateFormat = SimpleDateFormat("yyyy-dd-MM", Locale.ROOT)

    val myDate: Date?
    try {
        myDate = dateFormat.parse(this)
    } catch (e: ParseException) {
        return ""
    }

    val timeFormat = SimpleDateFormat(newFormat, Locale.ROOT)
    return timeFormat.format(myDate ?: Date())
}

fun String?.toStringFormat(inputFormat: String, newFormat: String): String? {
    if (this.isNullOrEmpty()) return ""
    val dateFormat = SimpleDateFormat(inputFormat, Locale.ROOT)

    val myDate: Date?
    try {
        myDate = dateFormat.parse(this)
    } catch (e: ParseException) {
        return ""
    }

    val timeFormat = SimpleDateFormat(newFormat, Locale.ROOT)
    return timeFormat.format(myDate ?: Date())
}