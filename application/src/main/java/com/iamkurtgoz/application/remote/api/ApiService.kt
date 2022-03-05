package com.iamkurtgoz.application.remote.api

import com.iamkurtgoz.domain.entities.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products/categories")
    suspend fun productCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun products(@Path("category") category: String): Response<List<ProductResponse>>
}
