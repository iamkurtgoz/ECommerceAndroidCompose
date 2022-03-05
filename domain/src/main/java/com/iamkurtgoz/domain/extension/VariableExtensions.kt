package com.iamkurtgoz.domain.extension

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Float?.orZero(): Float = this ?: 0.0F
fun String?.orEmpty(): String = this ?: ""
fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true
fun Boolean?.isTrue(): Boolean = this == true
fun Any?.isNull(): Boolean = this == null
fun Any?.isNotNull(): Boolean = this != null