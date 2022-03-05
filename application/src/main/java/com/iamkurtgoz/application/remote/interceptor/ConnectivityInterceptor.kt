package com.iamkurtgoz.application.remote.interceptor

import android.app.Application
import com.iamkurtgoz.domain.remote.exception.NoConnectivityException
import com.iamkurtgoz.domain.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityInterceptor @Inject constructor(val context: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!NetworkUtil.isConnected(context)) {
            chain.call().cancel()
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }
}