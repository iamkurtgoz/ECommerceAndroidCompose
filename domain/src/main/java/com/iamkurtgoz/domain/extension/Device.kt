package com.iamkurtgoz.domain.extension

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import java.util.*

fun Context.getDeviceUUID(): String {
    var serial: String? = null
    val shortID = ("35" +
            Build.BOARD.length % 10 + Build.BRAND.length % 10 +
            Build.DEVICE.length % 10 + Build.DISPLAY.length % 10 +
            Build.HOST.length % 10 + Build.ID.length % 10 +
            Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 +
            Build.PRODUCT.length % 10 + Build.TAGS.length % 10 +
            Build.TYPE.length % 10 + Build.USER.length % 10)

    try {
        serial += getDeviceIMEI(this) ?: ""
        serial += getSimSerialNumber(this) ?: ""
        serial += getSecureAndroidId(this) ?: ""
    } catch (e: Exception){
        serial += ""
    }
    return UUID(shortID.hashCode().toLong(), serial?.hashCode()?.toLong() ?: 0L).toString()
}

fun getDeviceIMEI(context: Context): String? {
    val telephonyManager = getTelephonyManager(context)
    return if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        if (Build.VERSION.SDK_INT >= 26) telephonyManager.imei
        else telephonyManager.deviceId
    else
        null
}

fun getSimSerialNumber(context: Context): String? {
    val telephonyManager = getTelephonyManager(context)
    return if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        telephonyManager.simSerialNumber
    else
        null
}

fun getSecureAndroidId(context: Context): String? {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: null
}

fun getTelephonyManager(context: Context): TelephonyManager {
    return context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
}