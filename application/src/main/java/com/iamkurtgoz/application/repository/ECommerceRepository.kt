package com.iamkurtgoz.application.repository

import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.entities.ProductResponse
import com.iamkurtgoz.domain.remote.resource.FlowResource
import kotlinx.coroutines.flow.Flow

interface ECommerceRepository {

    fun productCategories(): Flow<FlowResource<List<String>>>

    fun products(category: String): Flow<FlowResource<List<ProductModel>>>

    fun getFavorites(): Flow<FlowResource<List<ProductModel>>>

    fun productFavorite(id: Int): Flow<FlowResource<ProductModel>>
}