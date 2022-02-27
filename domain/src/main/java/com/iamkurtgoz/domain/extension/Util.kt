package com.iamkurtgoz.domain.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.ArrayList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun Html.text(html: String): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(html)
    }

fun <T : Any, R : Any> Collection<T?>.whenAllNotNull(block: (List<T>) -> R) {
    if (this.all { it != null }) {
        block(this.filterNotNull()) // or do unsafe cast to non null collectino
    }
}

fun <T : Any, R : Any> Collection<T?>.whenAnyNotNull(block: (List<T>) -> R) {
    if (this.any { it != null }) {
        block(this.filterNotNull())
    }
}

inline fun <T, R> Iterable<T>.mapToArrayList(transform: (T) -> R): ArrayList<R> {
    return mapTo(ArrayList<R>(), transform)
}

fun <T> Iterable<T>?.toStringWithComma(): String {
    var res = ""
    this?.forEachIndexed { index, t ->
        res += t
        if (count() != index + 1)
            res += ","
    }
    return res
}

fun <T> List<T>?.toArrayList(): ArrayList<T> {
    return ArrayList<T>(this)
}

fun String?.toListIntType(): List<Int> =
    this?.split(",")?.map { (it.toIntOrNull() ?: 0) } ?: listOf()

fun String?.toListStringType(): List<String> = this?.split(",") ?: listOf()

fun String?.isNotNullOrEmpty(): Boolean {
    return !TextUtils.isEmpty(this)
}

fun String?.isNotTextNullOrEmpty(): Boolean {
    return !TextUtils.isEmpty(this) && "null" != this
}

fun Boolean.isFalse() = !this

fun Boolean.isTrue() = this

fun Activity?.openDialler(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
    this?.startActivity(intent)
}

fun Any?.tryCatch(blockTry: () -> Unit, blockCatch: ((Exception) -> Unit)? = null) {
    try {
        blockTry.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
        blockCatch?.invoke(e)
    }
}