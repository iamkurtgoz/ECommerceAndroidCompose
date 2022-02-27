package com.iamkurtgoz.application.repository

import com.iamkurtgoz.domain.remote.resource.FlowResource
import kotlinx.coroutines.flow.Flow

interface ECommerceRepository {

    fun productCategories(): Flow<FlowResource<List<String>>>
}