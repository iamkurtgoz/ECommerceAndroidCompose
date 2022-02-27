package com.iamkurtgoz.application.remote.injection

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.GsonBuilder
import com.iamkurtgoz.domain.BuildConfig
import com.iamkurtgoz.domain.static.StaticStatePack.TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import com.iamkurtgoz.application.remote.interceptor.ConnectivityInterceptor
import com.iamkurtgoz.application.remote.interceptor.UserAgentInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class BaseNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideChuckerInterceptor(application: Application): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = application,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_WEEK
        )

        return ChuckerInterceptor.Builder(application)
            .collector(chuckerCollector)
            .maxContentLength(950_000L)
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(
        application: Application,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        userAgentInterceptor: UserAgentInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient.Builder {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(application.cacheDir, cacheSize.toLong())

        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
           // .cache(cache)

        getUnsafeOkHttpClient(okHttpClientBuilder)

        okHttpClientBuilder.addNetworkInterceptor(userAgentInterceptor)
        okHttpClientBuilder.addNetworkInterceptor(connectivityInterceptor)
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
            okHttpClientBuilder.addInterceptor(chuckerInterceptor)
        }

        return okHttpClientBuilder
    }

    companion object {
        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ) =
                object : Converter<ResponseBody, Any?> {
                    val nextResponseBodyConverter =
                        retrofit.nextResponseBodyConverter<Any?>(
                            converterFactory(),
                            type,
                            annotations
                        )

                    override fun convert(value: ResponseBody) =
                        if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
                }
        }

        fun getUnsafeOkHttpClient(okHttpClient: OkHttpClient.Builder): OkHttpClient.Builder {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<X509TrustManager>(object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory


                okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts[0])
                okHttpClient.hostnameVerifier(HostnameVerifier { _, _ -> true })

                return okHttpClient
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
