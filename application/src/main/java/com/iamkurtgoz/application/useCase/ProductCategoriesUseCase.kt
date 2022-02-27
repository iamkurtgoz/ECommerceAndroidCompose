package com.iamkurtgoz.application.useCase

import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.domain.useCase.IUseCase
import com.iamkurtgoz.application.repository.ECommerceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoriesUseCase @Inject constructor(
    private val repository: ECommerceRepository
): IUseCase<Any, Flow<FlowResource<List<String>>>> {

    override fun invoke(params: Any?): Flow<FlowResource<List<String>>> = repository.productCategories()

}