package com.iamkurtgoz.application.useCase

import com.iamkurtgoz.domain.remote.resource.FlowResource
import com.iamkurtgoz.domain.useCase.IUseCase
import com.iamkurtgoz.application.repository.ECommerceRepository
import com.iamkurtgoz.domain.useCase.IUseCaseWithoutParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCategoriesUseCase @Inject constructor(
    private val repository: ECommerceRepository
): IUseCaseWithoutParam<Flow<FlowResource<List<String>>>> {

    override fun invoke(): Flow<FlowResource<List<String>>> = repository.productCategories()

}