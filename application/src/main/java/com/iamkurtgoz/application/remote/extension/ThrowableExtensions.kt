package com.iamkurtgoz.application.remote.extension

import android.content.Context
import com.iamkurtgoz.application.R
import com.iamkurtgoz.domain.data.remote.network.exception.NoConnectivityException
import com.iamkurtgoz.domain.data.remote.network.exception.NullableException
import com.iamkurtgoz.application.model.BaseErrorResponse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.getErrorMessage(context: Context): String {
    return when (this) {
        is NoConnectivityException -> context.getString(R.string.error_connection_not_found)
        is HttpException -> context.getString(R.string.error_unknown)
        is NullableException -> context.getString(R.string.error_nullable)
        is SocketTimeoutException -> context.getString(R.string.error_connection_timeout)
        is IOException -> context.getString(R.string.error_io_exception)
        is BaseErrorResponse -> generateServiceException(context)
        else -> context.getString(R.string.error_unknown)
    }
}

fun Throwable.getErrorType(): String? {
    return when (this) {
        is BaseErrorResponse -> type
        else -> null
    }
}

fun Throwable.isNetworkConnectionException() = this is NoConnectivityException
fun Throwable.isHttpException() = this is HttpException
fun Throwable.isNullableException() = this is NullableException
fun Throwable.isSocketTimeoutException() = this is SocketTimeoutException
fun Throwable.isIOException() = this is IOException
fun Throwable.isServiceException() = this is BaseErrorResponse

private fun BaseErrorResponse.generateServiceException(context: Context): String {
    return message
}
