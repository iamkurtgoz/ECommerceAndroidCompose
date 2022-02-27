package com.iamkurtgoz.application.remote.injection

import com.iamkurtgoz.domain.BuildConfig
import com.iamkurtgoz.application.remote.injection.BaseNetworkModule.Companion.nullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import com.iamkurtgoz.application.remote.api.ApiService
import com.iamkurtgoz.application.remote.interceptor.ConnectivityInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiNetworkModule {

    @Provides
    @Singleton
    fun provideApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
        converterFactory: Converter.Factory,
        connectivityInterceptor: ConnectivityInterceptor
    ): ApiService {
        okHttpClientBuilder.addInterceptor(connectivityInterceptor)

        val client = okHttpClientBuilder.build()
        return builder.baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(converterFactory)
            .build()
            .create(ApiService::class.java)
    }
}
