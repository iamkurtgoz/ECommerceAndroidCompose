package com.iamkurtgoz.application.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products/categories")
    suspend fun productCategories(): Response<List<String>>

}
