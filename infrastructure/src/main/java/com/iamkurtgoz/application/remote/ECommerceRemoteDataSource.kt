package com.iamkurtgoz.application.remote

import com.iamkurtgoz.application.remote.api.ApiService
import javax.inject.Inject

class ECommerceRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun productCategories() = apiService.productCategories()

}