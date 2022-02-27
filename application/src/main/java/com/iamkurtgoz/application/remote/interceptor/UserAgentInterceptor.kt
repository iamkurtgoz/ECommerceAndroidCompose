package com.iamkurtgoz.application.remote.interceptor

import android.app.Application
import com.iamkurtgoz.domain.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAgentInterceptor @Inject constructor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("user-agent", userAgent)
            .header("os", "android")
            .header("Accept", "application/json")
            .header("Accept-Language", language())
            .header("app", "api")
            .header("timezone", TimeZone.getDefault().id)
            .build()
        return chain.proceed(request)
    }

    private fun language() = Locale.getDefault().language
    private val userAgent: String
        get() = NetworkUtil.getUserAgent(application)
}
