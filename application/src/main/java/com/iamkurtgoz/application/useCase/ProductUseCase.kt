package com.iamkurtgoz.application.useCase

import com.iamkurtgoz.application.model.sub.ProductModel
import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.domain.useCase.IUseCase
import com.iamkurtgoz.application.repository.ECommerceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductUseCase @Inject constructor(
    private val repository: ECommerceRepository
): IUseCase<String, Flow<FlowResource<List<ProductModel>>>> {

    override fun invoke(params: String): Flow<FlowResource<List<ProductModel>>> = repository.products(params)

}